package tech.gearsofcode.emft;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.thymeleaf.context.Context;

import tech.gearsofcode.wemf.EMFModelGenerationException;
import tech.gearsofcode.wemf.parser.EMFModelGeneratorListener;

/**
 * Represents a ".emft" file. This class helps to generate code in a "per classifier" way. 
 * */
public class EMFClassifierTemplate extends EMFTemplate{

	private Set<EClassifier> processed = new HashSet<EClassifier>();

	public EMFClassifierTemplate(String module, File file) {
		super(module, file);
	}

	

	/**
	 * Determines the file where the result of processing this template against the given class will be saved.
	 * 
	 * It takes into account the following variables from the template:
	 *  -> fileNamePattern: a string in the "String format" format, accepting only one "%s". "%sRepository", for example, would generate "ClassNameRepository". 
	 *  -> subpackage: a string to be concatenated to the base model package in case of "java" genfolder, or simply a subdirectory. "model", for example, would generate "mypackage.model".
	 *  -> genfolder: a string determing the "sub root" folder. Within the maven directory structure, the value genfolder = "java" will mean ("src/main/java"), other values will be generated in the correspondent folder. For example, "webapp" would be "root/webapp".  
	 *  
	 *  When genfolder = "java" or "test", it translates the package name into directory structure.
	 *  For example, if fileNamePattern="%s.java", package = "mypackage", subpackage="model", genfolder = "java", root = "/myproject", class name="Entity", then the generated file is:
	 *  "/myproject/src/main/java/mypackage/model/Entity.java"
	 *  
	 *  Another example, if fileNamePattern="%sService.js", package = "mypackage", subpackage="js/services", genfolder = "webapp", root = "/myproject", class name="Entity", then the generated file is:
	 *  "/myproject/webapp/js/services/EntityService.js"
	 *  
	 *  In this last example, because genfolder != "java", subpackage is just a subdirectory name.
	 *  
	 * @param root base directory where files will be saved. 
	 * @param ctx current processing context.
	 * @return file where the contents will be written.
	 * @throws EMFModelGenerationException if it is not possible to determine where the file will be saved.
	 */
	 @Override
	public File getGeneratedFile(File root, Context ctx) throws EMFModelGenerationException {
		EClassifier eClass = (EClassifier) ctx.getVariable("clazz");
		String fileNamePattern = getFileNamePattern();
		String subpackage = getSubpackage();
		subpackage = String.format(subpackage, eClass.getName());
		String genfolder = getGenfolder();
		
		String fullPackage = eClass.getEPackage().getName();
		if (!subpackage.isEmpty()) fullPackage += "." + subpackage;
		
		String fileName = String.format(fileNamePattern, eClass.getName());
		String generatedFile = subpackage + "/" + fileName;
		
		if ("java".equals(genfolder) || "test".equals(genfolder)) {
			String dirName = fullPackage.replaceAll("\\.", "/");
			generatedFile = dirName + "/" + fileName;
		}
		
		if ("test".equals(genfolder)) {
			root = new File(root.getParent(), "src/test/java");
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
	 @Override
	public File getGeneratedFile(File root, EPackage epkg) throws EMFModelGenerationException {
		String fileNamePattern = getFileNamePattern();
		String subpackage = getSubpackage();
		String genfolder = getGenfolder();
	
		String systemName = epkg.getEAnnotation(EMFModelGeneratorListener.SYSTEM_NAME_ANNOTATION).getDetails().get("name");
		String generatedFile = subpackage + "/" + String.format(fileNamePattern, systemName);
		
		if ("java".equals(genfolder) || "test".equals(genfolder)) {
			generatedFile = epkg.getName() + "/" + subpackage;
			generatedFile = generatedFile.replaceAll("\\.", "/");
			generatedFile += "/" + String.format(fileNamePattern, systemName);
		}
		
		if ("test".equals(genfolder)) {
			root = new File(root.getParent(), "src/test/java");
		}
		File genRoot = root;
		if (genfolder!=null && !genfolder.isEmpty()) {
			genRoot = new File(root, genfolder);
		}
		return new File(genRoot, generatedFile);
	}



	@Override
	public boolean willProcess(Context ctx) throws EMFModelGenerationException {
		EClassifier e = (EClassifier) ctx.getVariable("clazz");
		if (this.processed.contains(e)) return false;
		processed.add(e);
		boolean willProcess = true;
		String targetType = getProperty("targetType", "EClass");
		if ("EEnum".equals(targetType)){
			willProcess &= e instanceof EEnum;
		}
		else{
			willProcess &= e instanceof EClass;
		}
		
		String appliesTo = getProperty("appliesTo", null);
		if (appliesTo != null){
			willProcess &= e.getEAnnotations().stream().filter(annot -> annot.getSource().equals(appliesTo)).findFirst().isPresent();
		}
		
		
		String namePattern = getProperty("namePattern", null);
		if (namePattern != null){
			willProcess &= e.getName().matches(namePattern);
		}
		return willProcess;
	}
}
