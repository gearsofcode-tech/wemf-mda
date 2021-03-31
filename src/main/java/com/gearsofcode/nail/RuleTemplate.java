package com.gearsofcode.nail;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.thymeleaf.context.Context;

import com.gearsofcode.emft.EMFTemplate;
import com.gearsofcode.nail.metamodel.Rule;
import com.gearsofcode.wemf.EMFModelGenerationException;

/**
 * Represents a ".rulet" file. This class helps to generate code in a "per rule" way. 
 * */
public class RuleTemplate extends EMFTemplate{

	public RuleTemplate(String module, File file) {
		super(module, file);
	}

	private Set<Rule> processed = new HashSet<Rule>();
	
	@Override
	public File getGeneratedFile(File root, Context ctx) throws EMFModelGenerationException {
		Rule rule = (Rule)ctx.getVariable("rule");
		EClassifier eClass = (EClassifier) ctx.getVariable("clazz");
		String fileNamePattern = getFileNamePattern();
		String subpackage = getSubpackage();
		subpackage = String.format(subpackage, rule.getName());
		String genfolder = getGenfolder();
		
		String fullPackage = eClass.getEPackage().getName();
		if (!subpackage.isEmpty()) fullPackage += "." + subpackage;
		
		String fileName = String.format(fileNamePattern, rule.getName());
		String generatedFile = subpackage + "/" + fileName;
		
		if ("java".equals(genfolder) || "test".equals(genfolder)) {
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
	
	
	@Override
	public File getGeneratedFile(File root, EPackage epkg) throws EMFModelGenerationException {
		throw new EMFModelGenerationException("Rule templates do not support global templates.");
	}

	
	
	@Override
	public boolean willProcess(Context ctx) throws EMFModelGenerationException {
		Rule rule = (Rule)ctx.getVariable("rule");
		if (rule == null) return false;
		if (processed.contains(rule)) return false;
		processed.add(rule);
		return true;
	}


	@Override
	public boolean isRuleTemplate() {
		return true;
	}

}
