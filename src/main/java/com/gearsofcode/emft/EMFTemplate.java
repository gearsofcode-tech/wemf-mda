package com.gearsofcode.emft;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gearsofcode.wemf.EMFModelGenerationException;
import com.gearsofcode.wemf.parser.EMFModelGeneratorListener;

/**
 * Represents a ".emft" file.
 * */
public class EMFTemplate {

	private static final Logger logger = LoggerFactory.getLogger(EMFTemplate.class);
	
	private String module;
	private File file;
	
	public EMFTemplate(String module, File file) {
		this.module = module;
		this.file = file;
	}

	

	/**
	 * Determines the file where the result of processing this template against the given class will be saved.
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
	 * @param eClass current processing class.
	 * @return file where the contents will be written.
	 * @throws EMFModelGenerationException if it is not possible to determine where the file will be saved.
	 */
	public File getGeneratedFile(File root, EClassifier eClass) throws EMFModelGenerationException {
		String fileNamePattern = getFileNamePattern();
		String subpackage = getSubpackage();
		subpackage = String.format(subpackage, eClass.getName());
		String genfolder = getGenfolder();
		
		String fullPackage = eClass.getEPackage().getName();
		if (!subpackage.isEmpty()) fullPackage += "." + subpackage;
		
		String fileName = String.format(fileNamePattern, eClass.getName());
		String generatedFile = subpackage + "/" + fileName;
		
		if ("java".equals(genfolder)) {
			String dirName = fullPackage.replaceAll("\\.", "/");
			generatedFile = dirName + "/" + fileName;
		}
		
		
		File genRoot = root;
		if (genfolder!=null && !genfolder.isEmpty()) {
			genRoot = new File(root, genfolder);
		}
		return new File(genRoot, generatedFile);
	}
	
	
	/**
	 * Determines the generated file for global templates. That is, templates marked with the "global=true" property.
	 * In this case, the fileNamePattern receives the system name as parameter.
	 * 
	 * */
	public File getGeneratedFile(File root, EPackage epkg) throws EMFModelGenerationException {
		String fileNamePattern = getFileNamePattern();
		String subpackage = getSubpackage();
		String genfolder = getGenfolder();
	
		String systemName = epkg.getEAnnotation(EMFModelGeneratorListener.SYSTEM_NAME_ANNOTATION).getDetails().get("name");
		String generatedFile = subpackage + "/" + String.format(fileNamePattern, systemName);
		
		if ("java".equals(genfolder)) {
			generatedFile = epkg.getName() + "/" + subpackage;
			generatedFile = generatedFile.replaceAll("\\.", "/");
			generatedFile += "/" + String.format(fileNamePattern, systemName);
		}
		File genRoot = root;
		if (genfolder!=null && !genfolder.isEmpty()) {
			genRoot = new File(root, genfolder);
		}
		return new File(genRoot, generatedFile);
	}

	
	/**
	 * Reads the fileNamePattern property from the file.
	 * @return the value of the fileNamePattern property.
	 * @throws EMFModelGenerationException if the property cannot be read.
	 */
	private String getFileNamePattern() throws EMFModelGenerationException {
		return getProperty("fileNamePattern", "%s");
	}
	
	/**
	 * Reads the subpackage property from the file.
	 * @return the value of the subpackage property.
	 * @throws EMFModelGenerationException if the property cannot be read.
	 */
	private String getSubpackage() throws EMFModelGenerationException {
		return getProperty("subpackage", "");
	}
	
	/**
	 * Reads the genfolder property from the file.
	 * @return the value of the genfolder property.
	 * @throws EMFModelGenerationException if the property cannot be read.
	 */
	private String getGenfolder() throws EMFModelGenerationException {
		return getProperty("genfolder", "");
	}
	
	
	/**
	 * Reads the specified property from the template file.
	 * @param propName the property name.
	 * @param defaultValue default value to be returned if property is not found.
	 * @return either the value of property, or the default value.
	 * @throws EMFModelGenerationException if property cannot be read.
	 */
	private String getProperty(String propName, String defaultValue) throws EMFModelGenerationException {
		try(Scanner scanner = new Scanner(file)){
			String linha;
			Pattern p = Pattern.compile(propName+"=(\\S+)");
			Matcher m;
			while(scanner.hasNextLine()) {
				linha = scanner.nextLine();
				m = p.matcher(linha);
				if (m.find()) {
					return m.group(1).trim();
				}
			}
			return defaultValue;
		}
		catch (FileNotFoundException e) {
			String msg = "IO error reading property";
			logger.error(msg, e);
			throw new EMFModelGenerationException(msg, e);
		}
	}

	
	/**
	 * @return the name of the file.
	 */
	public String getName() {
		return file.getAbsolutePath().substring(file.getAbsolutePath().indexOf(module));
	}

	/**
	 * @return absolute path of the template.
	 */
	public String getFilename() {
		return file.getAbsolutePath();
	}

	/**
	 * @return the value of the "global" property.
	 * @throws EMFModelGenerationException
	 */
	public boolean isGlobal() throws EMFModelGenerationException  {
		return "true".equals(getProperty("global", "false"));
	}
	
	@Override
	public String toString() {
		int index = getFilename().indexOf(module);
		return getFilename().substring(index);
	}
}
