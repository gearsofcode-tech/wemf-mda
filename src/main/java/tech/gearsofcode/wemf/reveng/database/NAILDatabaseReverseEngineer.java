package tech.gearsofcode.wemf.reveng.database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EcorePackage;

import tech.gearsofcode.wemf.reveng.database.model.Column;
import tech.gearsofcode.wemf.reveng.database.model.DataModel;
import tech.gearsofcode.wemf.reveng.database.model.DatabaseObjectsFactory;
import tech.gearsofcode.wemf.reveng.database.model.Table;
import tech.gearsofcode.wemf.reveng.database.naming.DatabaseNamingRules;

/**
 * This class generates ".nail" files from an existing database.
 * 
 * @author SamuraiCharlie
 *
 */
public class NAILDatabaseReverseEngineer { 

	private PrintWriter pw;
	private DatabaseNamingRules namingRules = DatabaseObjectsFactory.getDatabaseNamingRules();
	private List<String> validateOnInsert = new LinkedList<>();
	private List<String> validateOnUpdate = new LinkedList<>();
	private List<String> validateOnDelete = new LinkedList<>();
	
	/**
	 * Sets the output to the console.
	 */
	public NAILDatabaseReverseEngineer() {
		this(new PrintWriter(System.out));
	}



	/**
	 * Sets the output to the given printwriter.
	 * @param pw where generated content will be printed.
	 */
	public NAILDatabaseReverseEngineer(PrintWriter pw) {
		this.pw = pw;
	}

	

	/**
	 * Generates NAIL code for the given table of the given data model.
	 */
	public void reverseEngineer(DataModel dataModel, String tableName) {
		//for (Table table: dataModel.getTables()) {
			Table table = dataModel.getTables().stream().filter(t -> t.getName().equals(tableName)).findFirst().get();
			//if (table.getName().endsWith("_AUD")) continue;
			beginImport(dataModel.getName());
			beginEntity(table);
			beginRulesSection();
			for (Column field : table.getColumns()) {
				declareRule(field);
			}
			endRulesSection();
			validate(validateOnInsert,"INSERT");
			validate(validateOnUpdate,"UPDATE");
			validate(validateOnDelete,"DELETE");
		//}
	}

	
	private void validate(List<String> rules, String type) {
		pw.write("\n\nVALIDATE-ON-");
		pw.write(type);
		pw.write("\n");
		for (String rule : rules) {
			pw.write("\t");
			pw.write(rule);
			pw.write("\n");
		}
		pw.write("END-VALIDATE-ON-");
		pw.write(type);
		pw.write("\n");
	}
	
	
	private void beginImport(String name) {
		pw.write("IMPORT ");
		pw.write(name);
		pw.write(".wemf\n");
	}



	private void declareRule(Column column) {
		String fieldName = namingRules.getJavaMemberName(column);
		String baseRuleName = fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
		if (column.isPrimaryKey()) {
			String ruleName = baseRuleName + "Obrigatorio";
			validateOnInsert.add(ruleName);
			validateOnUpdate.add(ruleName);
			validateOnDelete.add(ruleName);
			pw.printf("\n\n\tRULE %s\n", ruleName);
			pw.printf("\t\tON FIELD %s\n", fieldName);
			pw.printf("\t\tON ERROR '%s é obrigatório'\n", getLabel(fieldName));
			pw.write("\t\tCONSTRAINTS\n\t\t\tREQUIRED\n\t\tEND-CONSTRAINTS\n");
		}
		else if (!column.isNullAlowed()) {
			String ruleName = baseRuleName + "Obrigatorio";
			validateOnInsert.add(ruleName);
			validateOnUpdate.add(ruleName);
			pw.printf("\n\tRULE %s\n", ruleName);
			pw.printf("\t\tON FIELD %s\n", fieldName);
			pw.printf("\t\tON ERROR '%s é obrigatório'\n", getLabel(fieldName));
			pw.write("\t\tCONSTRAINTS\n\t\t\tREQUIRED\n\t\tEND-CONSTRAINTS\n");
		}
		if (column.getSize()!=null) {
			if (column.getType().getEMFType().equals(EcorePackage.eINSTANCE.getEBigDecimal())) {
				String ruleName = baseRuleName + "Decimal";
				validateOnInsert.add(ruleName);
				validateOnUpdate.add(ruleName);
				pw.printf("\n\tRULE %s\n", ruleName);
				pw.printf("\t\tON FIELD %s\n", fieldName);
				pw.printf("\t\tON ERROR '%s deve ter no máximo %s casas inteiras e %s casas decimais'\n", getLabel(fieldName), column.getSize().getIntegerDigits(), column.getSize().getDecimalDigits());
				pw.printf("\t\tCONSTRAINTS\n\t\t\tINTEGER-DIGITS %s\n\t\t\tDECIMAL-DIGITS %s\n\t\tEND-CONSTRAINTS\n", column.getSize().getIntegerDigits(), column.getSize().getDecimalDigits());
			}
			else if (column.getType().getEMFType().equals(EcorePackage.eINSTANCE.getEString())) {
				String ruleName = baseRuleName + "MaxLength";
				validateOnInsert.add(ruleName);
				validateOnUpdate.add(ruleName);
				pw.printf("\n\tRULE %s\n", ruleName);
				pw.printf("\t\tON FIELD %s\n", fieldName);
				pw.printf("\t\tON ERROR '%s não pode ter mais que %s caracteres'\n", getLabel(fieldName), column.getSize());
				pw.printf("\t\tCONSTRAINTS\n\t\t\tMAX-LENGTH %s\n\t\tEND-CONSTRAINTS\n", column.getSize());
			}
			else if (column.getType().getEMFType().equals(EcorePackage.eINSTANCE.getELongObject())) {
				String ruleName = baseRuleName + "MaxValue";
				validateOnInsert.add(ruleName);
				validateOnUpdate.add(ruleName);
				StringBuilder strb = new StringBuilder();
				for (int i=1;i<=column.getSize().getColumnSize();i++) strb.append("9");
				pw.printf("\n\tRULE %s\n", ruleName);
				pw.printf("\t\tON FIELD %s\n", fieldName);
				pw.printf("\t\tON ERROR '%s dever ser inferior a %s'\n", getLabel(fieldName), strb.toString());
				pw.printf("\t\tCONSTRAINTS\n\t\t\tMAX-VALUE %s\n\t\tEND-CONSTRAINTS\n", strb.toString());
			}
		}
	}



	private void beginEntity(Table table) {
		pw.write("ENTITY ");
		pw.write(namingRules.getClassName(table));
		pw.write("\n");
	}


	private void beginRulesSection() {
		pw.write("RULES-SECTION\n");
	}

	private void endRulesSection() {
		pw.write("END-RULES-SECTION\n");
	}



	public static void main(String args[]) {
		PrintWriter pw = new PrintWriter(System.out);
		String jdbcUrl = "jdbc:mysql://localhost:3306/schema";
		String user = "user";
		String passwd = "password";
		try (Connection conn = DriverManager.getConnection(jdbcUrl, user, passwd)){
			DataModel dataModel = DataModel.reverseEngineer(conn.getMetaData(), "schema");
			NAILDatabaseReverseEngineer reveng = new NAILDatabaseReverseEngineer(pw);
			reveng.reverseEngineer(dataModel, "table");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.flush();
	}
	
	
	
	private String getLabel(String typeName) {
		StringBuilder strb = new StringBuilder();
		for(int i=0; i<typeName.length();i++) {
			if(i==0) {
				strb.append(typeName.substring(0, 1).toUpperCase());
			}
			else {
				if(typeName.substring(i,i+1).matches("[A-Z]") && typeName.substring(i-1,i).matches("[a-z]")){
					strb.append(" ");
				} 
				strb.append(typeName.substring(i, i+1).toLowerCase());
			}
		}
		return strb.toString();
	}
}