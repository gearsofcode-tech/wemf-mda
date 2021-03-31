// Generated from NAIL.g4 by ANTLR 4.5.1

package com.gearsofcode.nail.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link NAILParser}.
 */
public interface NAILListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link NAILParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(NAILParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(NAILParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#import_declaration}.
	 * @param ctx the parse tree
	 */
	void enterImport_declaration(NAILParser.Import_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#import_declaration}.
	 * @param ctx the parse tree
	 */
	void exitImport_declaration(NAILParser.Import_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#entity_declaration}.
	 * @param ctx the parse tree
	 */
	void enterEntity_declaration(NAILParser.Entity_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#entity_declaration}.
	 * @param ctx the parse tree
	 */
	void exitEntity_declaration(NAILParser.Entity_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#rules_declaration}.
	 * @param ctx the parse tree
	 */
	void enterRules_declaration(NAILParser.Rules_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#rules_declaration}.
	 * @param ctx the parse tree
	 */
	void exitRules_declaration(NAILParser.Rules_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#rule_declaration}.
	 * @param ctx the parse tree
	 */
	void enterRule_declaration(NAILParser.Rule_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#rule_declaration}.
	 * @param ctx the parse tree
	 */
	void exitRule_declaration(NAILParser.Rule_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#defined}.
	 * @param ctx the parse tree
	 */
	void enterDefined(NAILParser.DefinedContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#defined}.
	 * @param ctx the parse tree
	 */
	void exitDefined(NAILParser.DefinedContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#constraints}.
	 * @param ctx the parse tree
	 */
	void enterConstraints(NAILParser.ConstraintsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#constraints}.
	 * @param ctx the parse tree
	 */
	void exitConstraints(NAILParser.ConstraintsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterConstraint(NAILParser.ConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitConstraint(NAILParser.ConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#assert_true}.
	 * @param ctx the parse tree
	 */
	void enterAssert_true(NAILParser.Assert_trueContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#assert_true}.
	 * @param ctx the parse tree
	 */
	void exitAssert_true(NAILParser.Assert_trueContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#assert_false}.
	 * @param ctx the parse tree
	 */
	void enterAssert_false(NAILParser.Assert_falseContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#assert_false}.
	 * @param ctx the parse tree
	 */
	void exitAssert_false(NAILParser.Assert_falseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RepositoryFunction}
	 * labeled alternative in {@link NAILParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void enterRepositoryFunction(NAILParser.RepositoryFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RepositoryFunction}
	 * labeled alternative in {@link NAILParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void exitRepositoryFunction(NAILParser.RepositoryFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionExpression}
	 * labeled alternative in {@link NAILParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionExpression(NAILParser.FunctionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionExpression}
	 * labeled alternative in {@link NAILParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionExpression(NAILParser.FunctionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link NAILParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void enterNegation(NAILParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link NAILParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void exitNegation(NAILParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinaryExpression}
	 * labeled alternative in {@link NAILParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpression(NAILParser.BinaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinaryExpression}
	 * labeled alternative in {@link NAILParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpression(NAILParser.BinaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValueExpression}
	 * labeled alternative in {@link NAILParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void enterValueExpression(NAILParser.ValueExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValueExpression}
	 * labeled alternative in {@link NAILParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void exitValueExpression(NAILParser.ValueExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GroupExpression}
	 * labeled alternative in {@link NAILParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void enterGroupExpression(NAILParser.GroupExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GroupExpression}
	 * labeled alternative in {@link NAILParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void exitGroupExpression(NAILParser.GroupExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(NAILParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(NAILParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#unique}.
	 * @param ctx the parse tree
	 */
	void enterUnique(NAILParser.UniqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#unique}.
	 * @param ctx the parse tree
	 */
	void exitUnique(NAILParser.UniqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#matches}.
	 * @param ctx the parse tree
	 */
	void enterMatches(NAILParser.MatchesContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#matches}.
	 * @param ctx the parse tree
	 */
	void exitMatches(NAILParser.MatchesContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#required}.
	 * @param ctx the parse tree
	 */
	void enterRequired(NAILParser.RequiredContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#required}.
	 * @param ctx the parse tree
	 */
	void exitRequired(NAILParser.RequiredContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#max_value}.
	 * @param ctx the parse tree
	 */
	void enterMax_value(NAILParser.Max_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#max_value}.
	 * @param ctx the parse tree
	 */
	void exitMax_value(NAILParser.Max_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#max_length}.
	 * @param ctx the parse tree
	 */
	void enterMax_length(NAILParser.Max_lengthContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#max_length}.
	 * @param ctx the parse tree
	 */
	void exitMax_length(NAILParser.Max_lengthContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#integer_digits}.
	 * @param ctx the parse tree
	 */
	void enterInteger_digits(NAILParser.Integer_digitsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#integer_digits}.
	 * @param ctx the parse tree
	 */
	void exitInteger_digits(NAILParser.Integer_digitsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#decimal_digits}.
	 * @param ctx the parse tree
	 */
	void enterDecimal_digits(NAILParser.Decimal_digitsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#decimal_digits}.
	 * @param ctx the parse tree
	 */
	void exitDecimal_digits(NAILParser.Decimal_digitsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#validation_declaration}.
	 * @param ctx the parse tree
	 */
	void enterValidation_declaration(NAILParser.Validation_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#validation_declaration}.
	 * @param ctx the parse tree
	 */
	void exitValidation_declaration(NAILParser.Validation_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#validate_on_insert}.
	 * @param ctx the parse tree
	 */
	void enterValidate_on_insert(NAILParser.Validate_on_insertContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#validate_on_insert}.
	 * @param ctx the parse tree
	 */
	void exitValidate_on_insert(NAILParser.Validate_on_insertContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#validate_on_update}.
	 * @param ctx the parse tree
	 */
	void enterValidate_on_update(NAILParser.Validate_on_updateContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#validate_on_update}.
	 * @param ctx the parse tree
	 */
	void exitValidate_on_update(NAILParser.Validate_on_updateContext ctx);
	/**
	 * Enter a parse tree produced by {@link NAILParser#validate_on_delete}.
	 * @param ctx the parse tree
	 */
	void enterValidate_on_delete(NAILParser.Validate_on_deleteContext ctx);
	/**
	 * Exit a parse tree produced by {@link NAILParser#validate_on_delete}.
	 * @param ctx the parse tree
	 */
	void exitValidate_on_delete(NAILParser.Validate_on_deleteContext ctx);
}