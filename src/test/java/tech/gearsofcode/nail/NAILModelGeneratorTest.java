package tech.gearsofcode.nail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import tech.gearsofcode.nail.metamodel.NAILModel;
import tech.gearsofcode.nail.metamodel.Rule;
import tech.gearsofcode.nail.parser.NAILLexer;
import tech.gearsofcode.nail.parser.NAILModelGeneratorListener;
import tech.gearsofcode.nail.parser.NAILParser;
import tech.gearsofcode.wemf.ANTLRExceptionListener;

public class NAILModelGeneratorTest {

	@Test
	public void testParser() throws IOException, URISyntaxException{
		NAILLexer lexer = new NAILLexer(null);
		StringBuilder strb = new StringBuilder();
		Path filePath = Paths.get(getClass().getClassLoader().getResource("Teste001Validation.nail").toURI());
		List<String> linhas = Files.readAllLines(filePath);
		linhas.forEach(linha -> strb.append(linha).append("\n"));
		String query = strb.toString();
		lexer.setInputStream(new ANTLRInputStream(query));
		lexer.addErrorListener(new ANTLRExceptionListener());

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		NAILParser parser = new NAILParser(tokens);
		parser.addErrorListener(new ANTLRExceptionListener());
		ParseTreeWalker walker = new ParseTreeWalker();
		
		ParseTree tree = parser.prog();
		NAILModelGeneratorListener listener = new NAILModelGeneratorListener();
		walker.walk(listener, tree);
		
		NAILModel model = listener.getModel();
		List<Rule> rules = model.getRules();
		assertNotNull(rules);
		assertFalse(rules.isEmpty());
		Rule rule1 = rules.get(0);
		assertEquals("Teste001.wemf", model.getWemfFile());
		assertEquals("NomeObrigatorio", rule1.getName());
		assertEquals("nome", rule1.getFields().get(0));
		assertEquals("Nome é obrigatório", rule1.getMessage());
		assertEquals("Todo aluno deve possuir um nome.", rule1.getDocumentation());
		assertEquals("Aluno", model.getEntity());
		assertTrue(rule1.isRequired());
		
		List<Rule> validateOnInsert = model.getValidateOnInsert();
		assertFalse(validateOnInsert.isEmpty());
		
		List<Rule> validateOnUpdate = model.getValidateOnUpdate();
		assertFalse(validateOnUpdate.isEmpty());
		
		//RULE2
		assertTrue(rules.size()==4);
		Rule rule2 = rules.get(1);
		assertEquals("CpfDataNascimentoObrigatorio", rule2.getName());
		assertNotNull(rule2.getAssertTrue());
		assertEquals("(cpf!=null && !cpf.isEmpty()) && dataNascimento!=null", rule2.getAssertTrue().toString());
	
		
		//RULE3
		Rule rule3 = rules.get(2);
		assertEquals("CpfJaExiste", rule3.getName());
		assertNotNull(rule3.getAssertFalse());
		assertEquals("alunoRepository.existeCpf(cpf)", rule3.getAssertFalse().toString());
		assertTrue(rule3.isModifyOnly());
		
		//RULE4
		Rule rule4 = rules.get(3);
		assertEquals("AlunoInscrito", rule4.getName());
		assertNotNull(rule4.getAssertFalse());
		assertEquals("anoInscricao.equals(2020)", rule4.getAssertFalse().toString());
		assertTrue(rule4.isDeleteOnly());
	}
}
