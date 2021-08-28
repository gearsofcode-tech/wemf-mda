package tech.gearsofcode.nail.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;


import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import tech.gearsofcode.nail.NAILException;
import tech.gearsofcode.nail.metamodel.NAILModel;
import tech.gearsofcode.nail.metamodel.Rule;
import tech.gearsofcode.nail.metamodel.expression.BinaryExpression;
import tech.gearsofcode.nail.metamodel.expression.BinaryOperator;
import tech.gearsofcode.nail.metamodel.expression.BooleanExpression;
import tech.gearsofcode.nail.metamodel.expression.FunctionExpression;
import tech.gearsofcode.nail.metamodel.expression.GroupExpression;
import tech.gearsofcode.nail.metamodel.expression.NailFunction;
import tech.gearsofcode.nail.metamodel.expression.NegationExpression;
import tech.gearsofcode.nail.metamodel.expression.RepositoryFunctionExpression;
import tech.gearsofcode.nail.metamodel.expression.ValueExpression;
import tech.gearsofcode.nail.parser.NAILParser.Assert_falseContext;
import tech.gearsofcode.nail.parser.NAILParser.Assert_trueContext;
import tech.gearsofcode.nail.parser.NAILParser.BinaryExpressionContext;
import tech.gearsofcode.nail.parser.NAILParser.ConstraintContext;
import tech.gearsofcode.nail.parser.NAILParser.ConstraintsContext;
import tech.gearsofcode.nail.parser.NAILParser.DefinedContext;
import tech.gearsofcode.nail.parser.NAILParser.Entity_declarationContext;
import tech.gearsofcode.nail.parser.NAILParser.FunctionExpressionContext;
import tech.gearsofcode.nail.parser.NAILParser.GroupExpressionContext;
import tech.gearsofcode.nail.parser.NAILParser.Import_declarationContext;
import tech.gearsofcode.nail.parser.NAILParser.MatchesContext;
import tech.gearsofcode.nail.parser.NAILParser.Max_lengthContext;
import tech.gearsofcode.nail.parser.NAILParser.NegationContext;
import tech.gearsofcode.nail.parser.NAILParser.ProgContext;
import tech.gearsofcode.nail.parser.NAILParser.RepositoryFunctionContext;
import tech.gearsofcode.nail.parser.NAILParser.RequiredContext;
import tech.gearsofcode.nail.parser.NAILParser.Rule_declarationContext;
import tech.gearsofcode.nail.parser.NAILParser.Rules_declarationContext;
import tech.gearsofcode.nail.parser.NAILParser.UniqueContext;
import tech.gearsofcode.nail.parser.NAILParser.Validate_on_deleteContext;
import tech.gearsofcode.nail.parser.NAILParser.Validate_on_insertContext;
import tech.gearsofcode.nail.parser.NAILParser.Validate_on_updateContext;
import tech.gearsofcode.nail.parser.NAILParser.Validation_declarationContext;
import tech.gearsofcode.nail.parser.NAILParser.ValueExpressionContext;

public class NAILModelGeneratorListener extends NAILBaseListener{
	
	

	private String entity;
	private String wemfFile = null;
	private List<Rule> rules = new LinkedList<Rule>();
	private List<Rule> validateOnInsert = new LinkedList<Rule>();
	private List<Rule> validateOnDelete = new LinkedList<Rule>();
	private List<Rule> validateOnUpdate = new LinkedList<Rule>();
	private Stack<BooleanExpression> stack = new Stack<BooleanExpression>();

	@Override
	public void enterProg(ProgContext ctx) {
	}

	@Override
	public void exitProg(ProgContext ctx) {
	}


	@Override
	public void enterImport_declaration(Import_declarationContext ctx) {
		wemfFile = ctx.getChild(1).getText() + ".wemf";
	}

	@Override
	public void exitImport_declaration(Import_declarationContext ctx) {
	}

	@Override
	public void enterEntity_declaration(Entity_declarationContext ctx) {
		this.entity = ctx.getChild(1).getText();
	}

	@Override
	public void exitEntity_declaration(Entity_declarationContext ctx) {
	}

	@Override
	public void enterRules_declaration(Rules_declarationContext ctx) {
	}

	@Override
	public void exitRules_declaration(Rules_declarationContext ctx) {
	}

	@Override
	public void enterRule_declaration(Rule_declarationContext ctx) {
		Rule rule = new Rule();
		rule.setName(ctx.getChild(1).getText());
		rule.getFields().add(ctx.getChild(3).getText());
		if ("UPDATE-RULE".equals(ctx.getChild(0).getText())) rule.setModifyOnly(true);
		if ("DELETE-RULE".equals(ctx.getChild(0).getText())) rule.setDeleteOnly(true);
		int i = 4;
		while (",".equals(ctx.getChild(i).getText()) && i<ctx.getChildCount()) {
			rule.getFields().add(ctx.getChild(i+1).getText());
			i+=2;
		}
		i = 0;
		while (!ctx.getChild(i).getText().equals("ON ERROR")) i++;
		if (i<ctx.getChildCount()) {
			rule.setMessage(trim(ctx.getChild(i+1).getText()));
		}
		rules.add(rule);
	}

	@Override
	public void exitRule_declaration(Rule_declarationContext ctx) {
	}

	@Override
	public void enterDefined(DefinedContext ctx) {
		Rule rule = rules.get(rules.size()-1);
		rule.setDocumentation(trim(ctx.getChild(1).getText()));
	}
	
	
	private String trim(String str) {
		return str.substring(1, str.length()-1);
	}

	@Override
	public void exitDefined(DefinedContext ctx) {
	}

	@Override
	public void enterConstraints(ConstraintsContext ctx) {
	}

	@Override
	public void exitConstraints(ConstraintsContext ctx) {
	}

	@Override
	public void enterConstraint(ConstraintContext ctx) {
	}

	@Override
	public void exitConstraint(ConstraintContext ctx) {
	}

	@Override
	public void enterRequired(RequiredContext ctx) {
		Rule rule = rules.get(rules.size()-1);
		rule.setRequired(true);
	}

	@Override
	public void exitRequired(RequiredContext ctx) {
	}

	@Override
	public void enterValidation_declaration(Validation_declarationContext ctx) {
	}

	@Override
	public void exitValidation_declaration(Validation_declarationContext ctx) {
	}

	@Override
	public void enterValidate_on_insert(Validate_on_insertContext ctx) {
		for (int i=1; i<ctx.getChildCount()-1;i++) {
			final String ruleName = ctx.getChild(i).getText();
			Optional<Rule> optRule = rules.stream().filter(r -> r.getName().equals(ruleName)).findFirst();
			if (!optRule.isPresent()) {
				String msg = "Compilation problem: rule '" + ruleName + "' was not declared. Please review VALIDATE-ON-INSERT rules.";
				throw new NAILException(msg);
			}
			validateOnInsert.add(optRule.get());
		}
	}

	@Override
	public void exitValidate_on_insert(Validate_on_insertContext ctx) {
	}

	@Override
	public void enterValidate_on_update(Validate_on_updateContext ctx) {
		for (int i=1; i<ctx.getChildCount()-1;i++) {
			final String ruleName = ctx.getChild(i).getText();
			Optional<Rule> optRule = rules.stream().filter(r -> r.getName().equals(ruleName)).findFirst();
			if (!optRule.isPresent()) {
				String msg = "Compilation problem: rule '" + ruleName + "' was not declared. Please review VALIDATE-ON-UPDATE rules.";
				throw new NAILException(msg);
			}
			validateOnUpdate.add(optRule.get());
		}
	}

	@Override
	public void exitValidate_on_update(Validate_on_updateContext ctx) {
	}

	@Override
	public void enterValidate_on_delete(Validate_on_deleteContext ctx) {
		for (int i=1; i<ctx.getChildCount()-1;i++) {
			final String ruleName = ctx.getChild(i).getText();
			Optional<Rule> optRule = rules.stream().filter(r -> r.getName().equals(ruleName)).findFirst();
			if (!optRule.isPresent()) {
				String msg = "Compilation problem: rule '" + ruleName + "' was not declared. Please review VALIDATE-ON-DELETE rules.";
				throw new NAILException(msg);
			}
			validateOnDelete.add(optRule.get());
		}
	}

	@Override
	public void exitValidate_on_delete(Validate_on_deleteContext ctx) {
	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
	}

	@Override
	public void visitTerminal(TerminalNode node) {
	}

	@Override
	public void visitErrorNode(ErrorNode node) {
	}

	public NAILModel getModel() {
		NAILModel model = new NAILModel();
		model.setWemfFile(wemfFile);
		model.setEntity(entity);
		model.setRules(rules);
		model.setValidateOnDelete(validateOnDelete);
		model.setValidateOnInsert(validateOnInsert);
		model.setValidateOnUpdate(validateOnUpdate);
		return model;
	}

	@Override
	public void enterMax_length(Max_lengthContext ctx) {
		Rule rule = rules.get(rules.size()-1);
		rule.setMaxLength(Integer.parseInt(ctx.getChild(1).getText()));
	}
	
	
	@Override
	public void exitNegation(NegationContext ctx) {
		BooleanExpression negatedValue = stack.pop();
		NegationExpression n = new NegationExpression(negatedValue);
		stack.push(n);
	}

	@Override
	public void exitFunctionExpression(FunctionExpressionContext ctx) {
		FunctionExpression f = new FunctionExpression(NailFunction.fromString(ctx.getChild(0).getText()), ctx.getChild(2).getText());
		stack.push(f);
	}

	@Override
	public void exitBinaryExpression(BinaryExpressionContext ctx) {
		BinaryExpression b = new BinaryExpression(BinaryOperator.fromString(ctx.getChild(1).getText()));
		BooleanExpression opB = stack.pop();
		BooleanExpression opA = stack.pop();
		b.setOperandA(opA);
		b.setOperandB(opB);
		stack.push(b);
	}

	@Override
	public void exitValueExpression(ValueExpressionContext ctx) {
		ValueExpression v = new ValueExpression(ctx.getChild(0).getText());
		stack.push(v);
	}

	@Override
	public void exitGroupExpression(GroupExpressionContext ctx) {
		BooleanExpression b = stack.pop();
		GroupExpression g = new GroupExpression(b);
		stack.push(g);
	}
	
	
	@Override
	public void exitAssert_true(Assert_trueContext ctx) {
		Rule rule = rules.get(rules.size()-1);
		BooleanExpression b = stack.pop();
		rule.setAssertTrue(b);
	}

	@Override
	public void exitAssert_false(Assert_falseContext ctx) {
		Rule rule = rules.get(rules.size()-1);
		BooleanExpression b = stack.pop();
		rule.setAssertFalse(b);
	}

	@Override
	public void exitMatches(MatchesContext ctx) {
		Rule rule = rules.get(rules.size()-1);
		String pattern = ctx.getChild(1).getText();
		pattern = "\"" + trim(pattern) + "\"";
		rule.setPattern(pattern);
	}

	@Override
	public void enterUnique(UniqueContext ctx) {
		Rule rule = rules.get(rules.size()-1);
		rule.setUnique(true);
		rule.setRequiresRepository(true);
	}

	@Override
	public void exitRepositoryFunction(RepositoryFunctionContext ctx) {
		Rule rule = rules.get(rules.size()-1);
		rule.setRequiresRepository(true);
		String functionName = ctx.getChild(0).toString();
		List<String> arguments = new LinkedList<>();
		arguments.add(ctx.getChild(2).getText());
		int i = 3;
		while (",".equals(ctx.getChild(i).getText()) && i<ctx.getChildCount()) {
			arguments.add(ctx.getChild(i+1).getText());
			i+=2;
		}
		RepositoryFunctionExpression repositoryFunction = new RepositoryFunctionExpression(entity, functionName, arguments);
		stack.push(repositoryFunction);
	}

}