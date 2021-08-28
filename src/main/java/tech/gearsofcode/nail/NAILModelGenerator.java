package tech.gearsofcode.nail;

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

import tech.gearsofcode.nail.metamodel.NAILModel;
import tech.gearsofcode.nail.parser.NAILLexer;
import tech.gearsofcode.nail.parser.NAILModelGeneratorListener;
import tech.gearsofcode.nail.parser.NAILParser;
import tech.gearsofcode.wemf.ANTLRExceptionListener;
import tech.gearsofcode.wemf.EMFModelGenerationException;

/**
 * Façade to generate model from ".nail" file.
 *
 * @author Carlos Padoa
 *
 */
public class NAILModelGenerator {

	
	/**
	 * Generates model for the specified file.
	 * @param fileName name of a ".wemf" file in the "resources" directory.
	 * @return generated EMF model.
	 * @throws EMFModelGenerationException if it is not possible to generate the model.
	 */
	public NAILModel generateModel(String fileName) throws NAILException{
		URL resource = getClass().getClassLoader().getResource(fileName);
		if (resource == null) {
			throw new NAILException(String.format("Could not read file '%s' for model generation.", fileName));
		}
		Path filePath = null;
		try {
			filePath = Paths.get(resource.toURI());
		}
		catch (URISyntaxException e) {
			throw new NAILException("Could not find file for model generation.", e);
		}
		
		return generateModel(filePath.toFile());
	}
	
	
	
	/**
	 * Generates model for the specified file.
	 * @param file any ".nail" file in the file system.
	 * @return generated NAIL model.
	 * @throws NAILException
	 */
	public NAILModel generateModel(File file) throws NAILException{
		NAILLexer lexer = new NAILLexer(null);
		StringBuilder strb = new StringBuilder();
		List<String> linhas;
		try {
			linhas = Files.readAllLines(file.toPath());
		}
		catch (IOException e) {
			throw new NAILException("Could not read file for model generation.", e);
		}
		
		linhas.forEach(linha -> strb.append(linha).append("\n"));
		String query = strb.toString();
		lexer.setInputStream(new ANTLRInputStream(query));
		lexer.addErrorListener(new ANTLRExceptionListener());

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		NAILParser parser = new NAILParser(tokens);
		parser.addErrorListener(new ANTLRExceptionListener());
		ParseTreeWalker walker = new ParseTreeWalker();
		ParseTree tree = parser.prog();		
		NAILModelGeneratorListener mg = new NAILModelGeneratorListener();
		walker.walk(mg, tree);
			
		return mg.getModel();
	}
}
