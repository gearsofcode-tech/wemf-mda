package tech.gearsofcode.wemf.reveng;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import tech.gearsofcode.emft.metamodel.TypeMapper;

/**
 * This class generates ".wemf" files from existing Java code.
 * 
 * @author SamuraiCharlie
 *
 */
public class WEMFJavaReverseEngineer {

	private PrintWriter pw;


	
	/**
	 * Sets the output to the console.
	 */
	public WEMFJavaReverseEngineer() {
		this(new PrintWriter(System.out));
	}



	/**
	 * Sets the output to the given printwriter.
	 * @param pw where generated content will be printed.
	 */
	public WEMFJavaReverseEngineer(PrintWriter pw) {
		this.pw = pw;
	}



	/**
	 * Reverse engineers the given class and prints the output on the configured print writer.
	 * @param clazz to be reverse engineered.
	 */
	public void reverseEngineer(Class<?> clazz) {
		String packageName = clazz.getPackage().getName();
		beginPackage(packageName);
		beginClass(clazz);
		for (Field field : clazz.getDeclaredFields()) {
			declareField(field);
		}

		for (Method m : clazz.getDeclaredMethods()) {
			if (!m.getName().startsWith("get") && !m.getName().startsWith("set")) {
				declareMethod(m);
			}
		}
		endClass(clazz);
		endPackage(packageName);
	}



	private void declareMethod(Method m) {
		pw.write("\n\t\t");
		pw.write(m.getName());
		pw.write("(");

		for (int i = 0; i < m.getParameterCount(); i++) {
			if (i != 0) pw.write(", ");
			pw.write(m.getParameters()[i].getName());
			pw.write(" : ");
			pw.write(TypeMapper.getEMFType(m.getParameters()[i].getType()));
		}
		pw.write(")");
		Class<?> returnType = m.getReturnType();
		if (!returnType.equals(void.class)) {
			pw.write(" : ");
			pw.write(TypeMapper.getEMFType(returnType));
		}
		pw.write(";");
	}



	private void declareField(Field field) {
		pw.write("\n\t\t");
		pw.write(field.getName());
		pw.write(" : ");
		pw.write(TypeMapper.getEMFType(field.getType()));
		pw.write(";");
	}



	private void beginClass(Class<?> clazz) {
		pw.write("\tclass ");
		pw.write(clazz.getName());
		pw.write(" {\n");
	}



	private void endClass(Class<?> clazz) {
		pw.write("\n\t}");
	}



	private void beginPackage(String packageName) {
		pw.write("package ");
		pw.write(packageName);
		pw.write("{\n");
	}



	private void endPackage(String packageName) {
		pw.write("\n}");
		pw.flush();
	}



	public static void main(String args[]) {
		PrintWriter pw = new PrintWriter(System.out);
		WEMFJavaReverseEngineer reveng = new WEMFJavaReverseEngineer(pw);
		reveng.reverseEngineer(TestPojo.class);
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

}
