package com.gearsofcode.nail;

import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

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
public class NAILTemplateProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(NAILTemplateProcessor.class);

	
	/**
	 * Thymeleaf template engine. 
	 */
	private TemplateEngine templateEngine;
	
	
	
	/**
	 * Configures thymeleaf template engine with the EMFTDialect. 
	 */
	public NAILTemplateProcessor(){
		templateEngine = new TemplateEngine();
		
		FileTemplateResolver defaultResolver = new FileTemplateResolver();
		defaultResolver.setTemplateMode("TEXT");
		templateEngine.addDialect(new EMFTDialect());
		templateEngine.addDialect(new NAILDialect());
		templateEngine.addTemplateResolver(defaultResolver);
		
	}
	
	
	/**
	 * Convenient method to process the specified .nail file against the given template and print the result on the console. 
	 * @param inputFile nail file in the "resources" folder.
	 * @param template emft file in "resources" folder.
	 * @throws EMFModelGenerationException if code generation is not possible.
	 */
	public void process(String inputFile, String template) throws EMFModelGenerationException{
		NAILModelGenerator nailGen = new NAILModelGenerator();
		EMFModelGenerator emfGen = new EMFModelGenerator();
		NAILModel nailModel = nailGen.generateModel(inputFile);
		String wemfFile = nailModel.getWemfFile();
		String entityName = nailModel.getEntity();
		EPackage model = emfGen.generateModel(wemfFile);
		Context ctx = new Context();
		ctx.setVariable("model", model);
		ctx.setVariable("nail", nailModel);
		ctx.setVariable("system", model.getEAnnotation(EMFModelGeneratorListener.SYSTEM_NAME_ANNOTATION).getDetails().get("name"));
		PrintWriter pw = new PrintWriter(System.out);
		Optional<EClassifier> optEClassifier = model.getEClassifiers().stream().filter(clazz -> clazz.getName().equals(entityName)).findFirst();
		File currentDir = new File(ClassLoader.getSystemResource(".").getFile());
		File templateFile = new File(currentDir, template);
		if (optEClassifier.isPresent()){
			EClass clazz = (EClass) optEClassifier.get();
			ctx.setVariable("clazz", clazz);
			if (template.endsWith(".rulet")) {
				for (final Rule rule : nailModel.getRules()) {
					ctx.setVariable("rule", rule);
					List<EAttribute> lstAttributes = new LinkedList<EAttribute>();
					for (String field: rule.getFields()) {
						Optional<EAttribute> attr = clazz.getEAttributes().stream().filter(attribute -> attribute.getName().equals(field)).findFirst();
						if (!attr.isPresent()) throw new NAILException(String.format("Class '%s' does not have attribute '%s'.", clazz.getName(), field));
						lstAttributes.add(attr.get());
					}
					ctx.setVariable("attributes", lstAttributes);
					templateEngine.process(templateFile.getAbsolutePath(), ctx, pw);
				}
			}
			else {
				templateEngine.process(templateFile.getAbsolutePath(), ctx, pw);
			}
			pw.write("\n");
		}
	}
	
	
	
	
	public static void main(String args[]){
		NAILTemplateProcessor p = new NAILTemplateProcessor();
		try {
			p.process("Teste001Validation.nail", "emft/entidade-descritiva/java/Regra.rulet");
		}
		catch (EMFModelGenerationException e) {
			e.printStackTrace();
		}
	}
}
