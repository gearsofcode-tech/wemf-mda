package com.gearsofcode.wemf.reveng.database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.gearsofcode.wemf.reveng.database.model.Column;
import com.gearsofcode.wemf.reveng.database.model.DataModel;
import com.gearsofcode.wemf.reveng.database.model.DatabaseObjectsFactory;
import com.gearsofcode.wemf.reveng.database.model.Table;
import com.gearsofcode.wemf.reveng.database.naming.DatabaseNamingRules;

/**
 * This class generates ".wemf" files from an existing database.
 * 
 * @author Carlos Padoa
 *
 */
public class WEMFDatabaseReverseEngineer {

	private PrintWriter pw;
	private String packageName;
	private DatabaseNamingRules namingRules = DatabaseObjectsFactory.getDatabaseNamingRules();
	
	
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
	 * Reverse engineers the given class and prints the output on the configured print writer.
	 * @param clazz to be reverse engineered.
	 */
	public void reverseEngineer(DataModel dataModel) {
		String packageName = getPackageName();
		beginPackage(packageName);
		for (Table table: dataModel.getTables()) {
			beginClass(table);
			for (Column field : table.getColumns()) {
				declareField(field);
			}
			endClass();
		}
		endPackage();
	}

	
	
	private void declareField(Column column) {
		pw.write("\n\n\t\t");
		if (column.isPrimaryKey()) {
			pw.write("@Id\n\t\t");
		}
		if (!column.isNullAlowed()) {
			pw.write("@NotNull\n\t\t");
		}
		pw.write("@Column(name='");
		pw.write(column.getName());
		pw.write("')\n\t\t");
		pw.write(namingRules.getJavaMemberName(column));
		pw.write(" : ");
		pw.write(column.getType().getEMFType().getName());
		pw.write(";");
	}



	private void beginClass(Table table) {
		pw.write("\n\n\t@Entity");
		pw.write("\n\t@Table(name='");
		pw.write(table.getName());
		pw.write("')");
		pw.write("\n\tclass ");
		pw.write(namingRules.getClassName(table));
		pw.write(" {\n");
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
		String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/XE";
		String user = "NUREMBERG_LOC";
		String passwd = "NUREMBERG_LOC";
		try (Connection conn = DriverManager.getConnection(jdbcUrl, user, passwd)){
			DataModel dataModel = DataModel.reverseEngineer(conn.getMetaData(), "NUREMBERG_LOC");
			WEMFDatabaseReverseEngineer reveng = new WEMFDatabaseReverseEngineer(pw);
			reveng.setPackageName("nuremberg");
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
