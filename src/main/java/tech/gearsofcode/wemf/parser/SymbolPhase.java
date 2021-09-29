package tech.gearsofcode.wemf.parser;

import java.util.ArrayList;
import java.util.List;

import tech.gearsofcode.wemf.parser.WEMFParser.ConcreteClassContext;

/**
 * This class is used to traverse the tree and identify all classes defined by the user.
 * 
 * @author SamuraiCharlie
 * */
public class SymbolPhase extends WEMFBaseListener{

	private List<String> userDefinedEClassifiers = new ArrayList<String>();
	

	@Override
	public void enterConcreteClass(ConcreteClassContext ctx) {
		String name = ctx.getChild(1).getText();
		userDefinedEClassifiers.add(name);
	}

	
	public List<String> getUserDefinedEClassifiers(){
		return userDefinedEClassifiers;
	}

	
}
