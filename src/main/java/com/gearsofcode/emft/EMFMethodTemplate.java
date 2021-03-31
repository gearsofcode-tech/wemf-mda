package com.gearsofcode.emft;

import java.io.File;
import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.thymeleaf.context.Context;
import org.thymeleaf.util.StringUtils;

import com.gearsofcode.wemf.EMFModelGenerationException;

/**
 * Represents a ".emfmt" file.
 * This class is used to allow code generation "per method".
 * */
public class EMFMethodTemplate extends EMFTemplate{

	
	private Iterator<EOperation> it;
	
	public EMFMethodTemplate(String module, File file) {
		super(module, file);
	}

	

	/**
	 * Determines the file where the result of processing this template against a given method of the given class will be saved.
	 * 
	 * It takes into account the following variables from the template:
	 *  -> fileNamePattern: a string in the "String format" format, accepting only one "%s". "%sRepository", for example, would generate "ClassNameRepository". 
	 *  -> subpackage: a string to be concatenated to the base model package in case of "java" genfolder, or simply a subdirectory. "model", for example, would generate "mypackage.model".
	 *  -> genfolder: a string determing the "sub root" folder. Within the maven directory structure, the value genfolder = "java" will mean ("src/main/java"), other values will be generated in the correspondent folder. For example, "webapp" would be "root/webapp".  
	 *  
	 *  When genfolder = "java", it translates the package name into directory structure.
	 *  For example, if fileNamePattern="%s.java", package = "mypackage", subpackage="model", genfolder = "java", root = "/myproject", class name="Entity", then the generated file is:
	 *  "/myproject/src/main/java/mypackage/model/Entity.java"
	 *  
	 *  Another example, if fileNamePattern="%sService.js", package = "mypackage", subpackage="js/services", genfolder = "webapp", root = "/myproject", class name="Entity", then the generated file is:
	 *  "/myproject/webapp/js/services/EntityService.js"
	 *  
	 *  In this last example, because genfolder != "java", subpackage is just a subdirectory name.
	 *  
	 * @param root base directory where files will be saved. 
	 * @param ctx current processing context. It informs current class and current operation for code generation.
	 * @return file where the contents will be written.
	 * @throws EMFModelGenerationException if it is not possible to determine where the file will be saved.
	 */
	@Override
	public File getGeneratedFile(File root, Context ctx) throws EMFModelGenerationException {
		EClassifier eClass = (EClassifier) ctx.getVariable("clazz");
		EOperation eOperation = (EOperation) ctx.getVariable("operation");
		String fileNamePattern = getFileNamePattern();
		String subpackage = getSubpackage();
		subpackage = String.format(subpackage, eClass.getName());
		String genfolder = getGenfolder();
		
		String fullPackage = eClass.getEPackage().getName();
		if (!subpackage.isEmpty()) fullPackage += "." + subpackage;
		
		
		String fileName = String.format(fileNamePattern, StringUtils.capitalize(eOperation.getName()));
		String generatedFile = subpackage + "/" + fileName;
		
		if ("java".equals(genfolder) || "test".contentEquals(genfolder)) {
			String dirName = fullPackage.replaceAll("\\.", "/");
			generatedFile = dirName + "/" + fileName;
		}

		File genRoot = root;
		if (genfolder!=null && !genfolder.isEmpty()) {
			genRoot = new File(root, genfolder);
		}
		if ("test".equals(genfolder)) {
			genRoot = new File(root.getParentFile(), "test/java");
		}
		return new File(genRoot, generatedFile);
	}
	
	
	
	/**
	 * Determines the generated file for global templates. That is, templates marked with the "global=true" property.
	 * In this case, the fileNamePattern receives the system name as parameter.
	 * 
	 * */
	@Override
	public File getGeneratedFile(File root, EPackage epkg) throws EMFModelGenerationException {
		throw new UnsupportedOperationException();
	}
	


	/**
	 * First implementation expects only "EClass" on "clazz" context variable.
	 * This method returns true while "clazz" has remaining methods.
	 * */
	@Override
	public boolean willProcess(Context ctx) throws EMFModelGenerationException {
		EClassifier c = (EClassifier) ctx.getVariable("clazz");
		if (c instanceof EEnum) return false;
		EClass e = (EClass) c;
		boolean willProcess = true;
		
		if (it == null){
			it = e.getEAllOperations().iterator();
		}
		
		willProcess = it.hasNext();
		if (willProcess){
			ctx.setVariable("operation", it.next());
		}
		return willProcess;
	}
}
