package tech.gearsofcode.wemf.reveng.database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcorePackage;

import tech.gearsofcode.wemf.reveng.database.model.Column;
import tech.gearsofcode.wemf.reveng.database.model.DataModel;
import tech.gearsofcode.wemf.reveng.database.model.DatabaseObjectsFactory;
import tech.gearsofcode.wemf.reveng.database.model.Table;
import tech.gearsofcode.wemf.reveng.database.naming.DatabaseNamingRules;

/**
 * This class generates ".wemf" files from an existing database.
 * 
 * @author Carlos Padoa
 *
 */
public class WEMFDatabaseReverseEngineer {

	private boolean javaxValidation = false;
	private PrintWriter pw;
	private String packageName;
	private DatabaseNamingRules namingRules = DatabaseObjectsFactory.getDatabaseNamingRules();
	private DataModel dm;
	
	
	/**
	 * Sets the output to the console.
	 */
	public WEMFDatabaseReverseEngineer() {
		this(new PrintWriter(System.out));
	}



	/**
	 * Sets the output to the given printwriter.
	 * @param pw where generated content will be printed.
	 */
	public WEMFDatabaseReverseEngineer(PrintWriter pw) {
		this.pw = pw;
	}

	
	/**
	 * Generates WEMF code for the given data model. 
	 */
	public void reverseEngineer(DataModel dataModel) {
		this.dm = dataModel;
		String packageName = getPackageName();
		beginSystem(dataModel.getName());
		beginPackage(packageName);
		for (Table table: dataModel.getTables()) {
			if (table.getName().endsWith("_AUD")) continue;
			beginClass(table);
			for (Column field : table.getColumns()) {
				declareField(field);
			}
			endClass();
		}
		endPackage();
	}
	
	
	/**
	 * Generates partial WEMF code for the given table of the given data model. 
	 */
	public void reverseEngineer(DataModel dataModel, final String tableName) {
		Table table = dataModel.getTables().stream().filter(t -> t.getName().equals(tableName)).findFirst().get();		
		beginClass(table);
		for (Column field : table.getColumns()) {
			declareField(field);
		}
		endClass();
	}

	
	
	private void beginSystem(String name) {
		pw.write("system ");
		pw.write(name);
		pw.write(";\n");
	}



	private void declareField(Column column) {
		pw.write("\n\n\t\t");
		if (column.isPrimaryKey()) {
			pw.write("@Id\n\t\t");
		}
		if (javaxValidation && !column.isNullAlowed()) {
			pw.write("@NotNull\n\t\t");
		}
		
		if (javaxValidation && column.getSize()!=null) {
			if (column.getType().getEMFType().equals(EcorePackage.eINSTANCE.getEBigDecimal())) {
				pw.write("@Digits(integer="+column.getSize().getColumnSize()+",fraction="+column.getSize().getDecimalDigits()+")\n\t\t");
			}
			else if (column.getType().getEMFType().equals(EcorePackage.eINSTANCE.getEString())) {
				pw.write("@Size(max="+column.getSize()+")\n\t\t");
			}
			else if (column.getType().getEMFType().equals(EcorePackage.eINSTANCE.getELongObject())) {
				StringBuilder strb = new StringBuilder();
				for (int i=1;i<=column.getSize().getColumnSize();i++) strb.append("9");
				pw.write("@Max("+strb.toString()+")\n\t\t");
			}
		}
		
		if (column.getReferencedColumn()==null) {
			pw.write("@Column('name=\"");
			pw.write(column.getName());
			pw.write("\"')\n\t\t");
			pw.write(namingRules.getJavaMemberName(column));
			pw.write(" : ");
			pw.write(column.getType().getEMFType().getName());
			pw.write(";");
		}
		else {
			pw.write("@OneToMany\n");
			pw.write("\t\t@JoinColumn('name=\"");
			pw.write(column.getName());
			pw.write("\"')\n\t\t");
			pw.write(namingRules.getJavaMemberName(column));
			pw.write(" : ");
			Table table = dm.getTables().stream().filter(x -> x.getName().equals(column.getReferencedColumn().getTable())).findFirst().get();
			pw.write(namingRules.getClassName(table));
			pw.write(";");
		}
	}



	private void beginClass(Table table) {
		pw.write("\n\n\n\n\t@Entity");
		pw.write("\n\t@Table('name=\"");
		pw.write(table.getName());
		pw.write("\"')");
		pw.write("\n\tclass ");
		pw.write(namingRules.getClassName(table));
		pw.write(" {");
	}



	private void endClass() {
		pw.write("\n\t}");
	}



	private void beginPackage(String packageName) {
		pw.write("package ");
		pw.write(packageName);
		pw.write("{\n");
	}



	private void endPackage() {
		pw.write("\n}");
		pw.flush();
	}



	public static void main(String args[]) {
		PrintWriter pw = new PrintWriter(System.out);
		String jdbcUrl = "jdbc:mysql://localhost:3306/schema";
		String user = "user";
		String passwd = "password";
		try (Connection conn = DriverManager.getConnection(jdbcUrl, user, passwd)){
			DataModel dataModel = DataModel.reverseEngineer(conn.getMetaData(), "schema");
			WEMFDatabaseReverseEngineer reveng = new WEMFDatabaseReverseEngineer(pw);
			reveng.setPackageName("tech.gearsofcode.project");
			reveng.reverseEngineer(dataModel);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.flush();
	}


	class TestPojo {

		private String nome;
		private Long id;



		public String getNome() {
			return nome;
		}



		public void setNome(String nome) {
			this.nome = nome;
		}



		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
		}



		public void doSomething() {
			// Does nothing
		}
	}


	
	public String getPackageName() {
		return packageName;
	}



	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

}
