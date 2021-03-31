package com.gearsofcode.emft;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import com.gearsofcode.emft.pretty.JavaPrettyPrinter;
import com.gearsofcode.emft.thymeleaf.EMFTDialect;
import com.gearsofcode.nail.metamodel.NAILModel;
import com.gearsofcode.nail.metamodel.Rule;
import com.gearsofcode.nail.thymeleaf.NAILDialect;
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
		templateEngine.addDialect(new NAILDialect());
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
	public void process(EPackage model, NAILModel nailModel, EMFTemplate template, File root, List<String> classifiers) throws EMFModelGenerationException{
		Context ctx = new Context();
		ctx.setVariable("model", model);
		ctx.setVariable("system", model.getEAnnotation(EMFModelGeneratorListener.SYSTEM_NAME_ANNOTATION).getDetails().get("name"));
		if (template.isRuleTemplate()) {
			try {
				File file;
				for (Rule r: nailModel.getRules()) {
					Optional<EClassifier> optEClassifier = model.getEClassifiers().stream().filter(e -> e.getName().equals(nailModel.getEntity())).findFirst();
					if (!optEClassifier.isPresent()) throw new EMFModelGenerationException(String.format("Did not find class '%s'.", nailModel.getEntity()));
					EClass clazz = (EClass) optEClassifier.get();
					List<ENamedElement> lstAttributes = new LinkedList<ENamedElement>();
					for (String field : r.getFields()) {
						String fieldName = field;
						if (field.endsWith("Anterior")) {
							fieldName = field.substring(0, field.length()-"Anterior".length());
						}
						if (field.contains(".")) {
							fieldName = field.substring(0, field.indexOf('.'));
						}
						final String searchName = fieldName;
						Optional<EAttribute> optAttr = clazz.getEAttributes().stream().filter(att -> att.getName().equals(searchName)).findFirst();
						Optional<EReference> optRef= clazz.getEReferences().stream().filter(ref -> ref.getName().equals(searchName)).findFirst();
						if (!optAttr.isPresent() && !optRef.isPresent()) throw new EMFModelGenerationException(String.format("Did not find attribute '%s' in class '%s'.", fieldName, nailModel.getEntity()));
						if (optAttr.isPresent())lstAttributes.add(optAttr.get());
						if (optRef.isPresent())lstAttributes.add(optRef.get());
					}
					ctx.setVariable("clazz", clazz);
					ctx.setVariable("rule", r);
					ctx.setVariable("attributes", lstAttributes);
					while (template.willProcess(ctx)) {
						file = template.getGeneratedFile(root, ctx);
						if (!file.getParentFile().exists()){
							if (!file.getParentFile().mkdirs()) {
								String msg = "Could not create directories for file generation:" + file.getAbsolutePath();
								logger.error(msg);
								throw new EMFModelGenerationException();
							}
						}
						FileWriter fw = new FileWriter(file);
						logger.info(String.format("Generating file '%s'.", file.getCanonicalPath()));
						templateEngine.process(template.getFilename(), ctx, fw);
						fw.close();
						prettyPrint(file);
					}
				}
			}
			catch (IOException e1) {
				String msg = "IO error generating code.";
				logger.error(msg, e1);
				throw new EMFModelGenerationException(msg, e1);
			}
		}
		else if (nailModel!=null) {
			try {
				File file;
				Optional<EClassifier> optEClassifier = model.getEClassifiers().stream().filter(e -> e.getName().equals(nailModel.getEntity())).findFirst();
				if (!optEClassifier.isPresent()) throw new EMFModelGenerationException(String.format("Did not find class '%s'.", nailModel.getEntity()));
				EClassifier clazz = optEClassifier.get();
				ctx.setVariable("clazz", clazz);
				ctx.setVariable("nail", nailModel);
				
				file = template.getGeneratedFile(root, ctx);
				if (!file.getParentFile().exists()){
					if (!file.getParentFile().mkdirs()) {
						String msg = "Could not create directories for file generation:" + file.getAbsolutePath();
						logger.error(msg);
						throw new EMFModelGenerationException();
					}
				}
				FileWriter fw = new FileWriter(file);
				logger.info(String.format("Generating file '%s'.", file.getCanonicalPath()));
				templateEngine.process(template.getFilename(), ctx, fw);
				fw.close();
				prettyPrint(file);
			}
			catch (IOException e1) {
				String msg = "IO error generating code.";
				logger.error(msg, e1);
				throw new EMFModelGenerationException(msg, e1);
			}
		}
		else if (template.isGlobal()) {
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
				prettyPrint(file);
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
					ctx.setVariable("clazz", e);
					while (template.willProcess(ctx)) {
						file = template.getGeneratedFile(root, ctx);
						if (!file.getParentFile().exists()){
							if (!file.getParentFile().mkdirs()) {
								String msg = "Could not create directories for file generation:" + file.getAbsolutePath();
								logger.error(msg);
								throw new EMFModelGenerationException();
							}
						}
						FileWriter fw = new FileWriter(file);
						logger.info(String.format("Generating file '%s'.", file.getCanonicalPath()));
						templateEngine.process(template.getFilename(), ctx, fw);
						fw.close();
						prettyPrint(file);
					}
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
	
	
	
	private void prettyPrint(File f) throws IOException {
		if (f.getName().endsWith("java")) {
			JavaPrettyPrinter.prettyPrint(f);
		}
	}
}
