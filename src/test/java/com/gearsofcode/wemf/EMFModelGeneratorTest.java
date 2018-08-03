package com.gearsofcode.wemf;

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
import java.util.Optional;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Test;

import com.gearsofcode.wemf.ANTLRExceptionListener;
import com.gearsofcode.wemf.parser.EMFModelGeneratorListener;
import com.gearsofcode.wemf.parser.SymbolPhase;
import com.gearsofcode.wemf.parser.WEMFLexer;
import com.gearsofcode.wemf.parser.WEMFParser;


public class EMFModelGeneratorTest {

	@Test
	public void testParser() throws IOException, URISyntaxException{
		WEMFLexer lexer = new WEMFLexer(null);
		StringBuilder strb = new StringBuilder();
		Path filePath = Paths.get(getClass().getClassLoader().getResource("Teste001.WEMF").toURI());
		List<String> linhas = Files.readAllLines(filePath);
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
		
		
		EPackage epkg = mg.getResult();
		assertNotNull(epkg);
		
		//Test package
		assertEquals("teste001", epkg.getName());
		
		//Test class existence
		EList<EClassifier> lst = epkg.getEClassifiers();
		assertFalse(lst.isEmpty());
		
		EClass eClass = (EClass)lst.get(0);
		assertEquals("Aluno", eClass.getName());
		
		//Test class structural feature
		assertFalse(eClass.getEAttributes().isEmpty());
		EAttribute eAttrNome = eClass.getEAttributes().get(0);
		assertEquals("nome", eAttrNome.getName());
		
		assertEquals(EcorePackage.eINSTANCE.getEString(), eAttrNome.getEType());
		
		
		//Test class behavioal feature
		assertFalse(eClass.getEOperations().isEmpty());
		EOperation eOp = eClass.getEOperations().get(0);
		assertEquals("inscrever", eOp.getName());
		
		assertFalse(eOp.getEParameters().isEmpty());
		
		EParameter eParam = eOp.getEParameters().get(0);
		assertEquals("a", eParam.getName());
		assertNotNull("Parameter type not specified", eParam.getEType());

		assertEquals("Aula", eParam.getEType().getName());

		//Teste "future reference" 
		EClass classAula = (EClass) epkg.getEClassifier("Aula");
		assertNotNull(classAula);
		
		//Test class associations
		assertFalse(classAula.getEReferences().isEmpty());
		
		EReference eRef = classAula.getEReferences().get(0);
		assertEquals(eRef.getName(), "professor");
		
		assertNotNull(eRef.getEType());
		assertEquals("Professor", eRef.getEType().getName());
		
		EReference eRefAlunos = classAula.getEReferences().get(1);
		assertEquals(eRefAlunos.getName(), "alunos");
		
		assertEquals(10, eRefAlunos.getLowerBound());
		assertEquals(60, eRefAlunos.getUpperBound());
		//Test return type with multiplicity
		Optional<EOperation> optOp = eClass.getEOperations().stream().filter(op -> op.getName().equals("obterInscricoes")).findFirst();
		assertTrue(optOp.isPresent());
		EOperation opObter = optOp.get();
		assertNotNull("Return type not defined", opObter.getEType());
		EClass returnType = (EClass)opObter.getEType();
		assertEquals("Aula", returnType.getName());
		assertEquals(ETypedElement.UNBOUNDED_MULTIPLICITY, opObter.getUpperBound());
	}
}
