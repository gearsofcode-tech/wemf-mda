package com.gearsofcode.wemf;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.eclipse.emf.ecore.EPackage;

import com.gearsofcode.wemf.parser.EMFModelGeneratorListener;
import com.gearsofcode.wemf.parser.SymbolPhase;
import com.gearsofcode.wemf.parser.WEMFLexer;
import com.gearsofcode.wemf.parser.WEMFParser;

/**
 * Façade to generate model from ".wemf" file.
 *
 * @author Carlos Padoa
 *
 */
public class EMFModelGenerator {

	
	/**
	 * Generates model for the specified file.
	 * @param fileName name of a ".wemf" file in the "resources" directory.
	 * @return generated EMF model.
	 * @throws EMFModelGenerationException if it is not possible to generate the model.
	 */
	public EPackage generateModel(String fileName) throws EMFModelGenerationException{
		URL resource = getClass().getClassLoader().getResource(fileName);
		if (resource == null) {
			throw new EMFModelGenerationException("Could not read file for model generation.");
		}
		Path filePath = null;
		try {
			filePath = Paths.get(resource.toURI());
		}
		catch (URISyntaxException e) {
			throw new EMFModelGenerationException("Could not find file for model generation.", e);
		}
		
		return generateModel(filePath.toFile());
	}
	
	
	
	/**
	 * Generates model for the specified file.
	 * @param file any ".wemf" file in the file system.
	 * @return generated EMF model.
	 * @throws EMFModelGenerationException
	 */
	public EPackage generateModel(File file) throws EMFModelGenerationException{
		WEMFLexer lexer = new WEMFLexer(null);
		StringBuilder strb = new StringBuilder();
		List<String> linhas;
		try {
			linhas = Files.readAllLines(file.toPath());
		}
		catch (IOException e) {
			throw new EMFModelGenerationException("Could not read file for model generation.", e);
		}
		
		linhas.forEach(linha -> strb.append(linha));
		String query = strb.toString();
		lexer.setInputStream(new ANTLRInputStream(query));
		lexer.addErrorListener(new ANTLRExceptionListener());

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		WEMFParser parser = new WEMFParser(tokens);
		parser.addErrorListener(new ANTLRExceptionListener());
		ParseTreeWalker walker = new ParseTreeWalker();
		
		SymbolPhase sp = new SymbolPhase();
		ParseTree tree = parser.prog();
		walker.walk(sp, tree);
		
		EMFModelGeneratorListener mg = new EMFModelGeneratorListener(sp.getUserDefinedEClassifiers());
		walker.walk(mg, tree);
		
		mg.setSecondPass();
		walker.walk(mg, tree);
		
		return mg.getResult();
	}
}
