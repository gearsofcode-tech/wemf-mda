package com.gearsofcode.emft;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import com.gearsofcode.emft.thymeleaf.EMFTDialect;
import com.gearsofcode.wemf.EMFModelGenerationException;
import com.gearsofcode.wemf.EMFModelGenerator;
import com.gearsofcode.wemf.parser.EMFModelGeneratorListener;

/**
 * @author Carlos Padoa
 *
 * Uses Thymeleaf to process templates against models.
 */
public class EMFTemplateProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(EMFTemplateProcessor.class);

	
	/**
	 * Thymeleaf template engine. 
	 */
	private TemplateEngine templateEngine;
	
	
	
	/**
	 * Configures thymeleaf template engine with the EMFTDialect. 
	 */
	public EMFTemplateProcessor(){
		templateEngine = new TemplateEngine();
		
		FileTemplateResolver defaultResolver = new FileTemplateResolver();
		defaultResolver.setTemplateMode("TEXT");
		templateEngine.addDialect(new EMFTDialect());
		templateEngine.addTemplateResolver(defaultResolver);
		
	}
	
	
	/**
	 * Convenient method to process the specified .wemf file against the given template and print the result on the console. 
	 * @param inputFile wemf file in the "resources" folder.
	 * @param template emft file in "resources" folder.
	 * @throws EMFModelGenerationException if code generation is not possible.
	 */
	public void process(String inputFile, String template) throws EMFModelGenerationException{
		EMFModelGenerator emfGen = new EMFModelGenerator();
		EPackage model = emfGen.generateModel(inputFile);
		Context ctx = new Context();
		ctx.setVariable("model", model);
		ctx.setVariable("system", model.getEAnnotation(EMFModelGeneratorListener.SYSTEM_NAME_ANNOTATION).getDetails().get("name"));
		PrintWriter pw = new PrintWriter(System.out);
		for (EClassifier e: model.getEClassifiers()) {
			ctx.setVariable("clazz", e);
			templateEngine.process(template, ctx, pw);
			pw.write("\n");
		}
	}
	
	
	/**
	 * Process the given file against the specified template writing the result in the given target.
	 * @param inputFile ".wemf" file anywhere in the filesystem.
	 * @param template the template to be processed.
	 * @param root the base directory where files are to be written. 
	 * @param classifiers 
	 * @throws EMFModelGenerationException is code generation is not possible.
	 */
	public void process(EPackage model, EMFTemplate template, File root, List<String> classifiers) throws EMFModelGenerationException{
		Context ctx = new Context();
		ctx.setVariable("model", model);
		ctx.setVariable("system", model.getEAnnotation(EMFModelGeneratorListener.SYSTEM_NAME_ANNOTATION).getDetails().get("name"));
		if (template.isGlobal()) {
			try {
				File file = template.getGeneratedFile(root, model);
				if (!file.getParentFile().exists()){
					if (!file.getParentFile().mkdirs()) {
						String msg = "Could not create directories for file generation:" + file.getAbsolutePath();
						logger.error(msg);
						throw new EMFModelGenerationException();
					}
				}
				FileWriter fw = new FileWriter(file);
				templateEngine.process(template.getFilename(), ctx, fw);
				fw.close();
			}
			catch (IOException e1) {
				String msg = "IO error generating code.";
				logger.error(msg, e1);
				throw new EMFModelGenerationException(msg, e1);
			}
		}
		else {
			try {
				File file;
				for (EClassifier e: model.getEClassifiers()) {
					if (!classifiers.isEmpty() && !classifiers.contains(e.getName())) continue;
					file = template.getGeneratedFile(root, e);
					if (!file.getParentFile().exists()){
						if (!file.getParentFile().mkdirs()) {
							String msg = "Could not create directories for file generation:" + file.getAbsolutePath();
							logger.error(msg);
							throw new EMFModelGenerationException();
						}
					}
					FileWriter fw = new FileWriter(file);
					logger.info(String.format("Generating file '%s'.", file.getCanonicalPath()));
					ctx.setVariable("clazz", e);
					templateEngine.process(template.getFilename(), ctx, fw);
					fw.close();
				}
			}
			catch (IOException e1) {
				String msg = "IO error generating code.";
				logger.error(msg, e1);
				throw new EMFModelGenerationException(msg, e1);
			}
		}
	}
	
	
	public static void main(String args[]){
		EMFTemplateProcessor p = new EMFTemplateProcessor();
		try {
			p.process("Teste001.wemf", "JSAngularVisualizaController");
		}
		catch (EMFModelGenerationException e) {
			e.printStackTrace();
		}
	}
}
