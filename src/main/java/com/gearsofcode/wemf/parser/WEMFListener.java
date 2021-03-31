// Generated from WEMF.g4 by ANTLR 4.5.1

package com.gearsofcode.wemf.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link WEMFParser}.
 */
public interface WEMFListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link WEMFParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(WEMFParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link WEMFParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(WEMFParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link WEMFParser#system}.
	 * @param ctx the parse tree
	 */
	void enterSystem(WEMFParser.SystemContext ctx);
	/**
	 * Exit a parse tree produced by {@link WEMFParser#system}.
	 * @param ctx the parse tree
	 */
	void exitSystem(WEMFParser.SystemContext ctx);
	/**
	 * Enter a parse tree produced by {@link WEMFParser#epackage}.
	 * @param ctx the parse tree
	 */
	void enterEpackage(WEMFParser.EpackageContext ctx);
	/**
	 * Exit a parse tree produced by {@link WEMFParser#epackage}.
	 * @param ctx the parse tree
	 */
	void exitEpackage(WEMFParser.EpackageContext ctx);
	/**
	 * Enter a parse tree produced by {@link WEMFParser#packageName}.
	 * @param ctx the parse tree
	 */
	void enterPackageName(WEMFParser.PackageNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link WEMFParser#packageName}.
	 * @param ctx the parse tree
	 */
	void exitPackageName(WEMFParser.PackageNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConcreteClass}
	 * labeled alternative in {@link WEMFParser#eclass}.
	 * @param ctx the parse tree
	 */
	void enterConcreteClass(WEMFParser.ConcreteClassContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConcreteClass}
	 * labeled alternative in {@link WEMFParser#eclass}.
	 * @param ctx the parse tree
	 */
	void exitConcreteClass(WEMFParser.ConcreteClassContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AbstractClass}
	 * labeled alternative in {@link WEMFParser#eclass}.
	 * @param ctx the parse tree
	 */
	void enterAbstractClass(WEMFParser.AbstractClassContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AbstractClass}
	 * labeled alternative in {@link WEMFParser#eclass}.
	 * @param ctx the parse tree
	 */
	void exitAbstractClass(WEMFParser.AbstractClassContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Enumeration}
	 * labeled alternative in {@link WEMFParser#eclass}.
	 * @param ctx the parse tree
	 */
	void enterEnumeration(WEMFParser.EnumerationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Enumeration}
	 * labeled alternative in {@link WEMFParser#eclass}.
	 * @param ctx the parse tree
	 */
	void exitEnumeration(WEMFParser.EnumerationContext ctx);
	/**
	 * Enter a parse tree produced by {@link WEMFParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(WEMFParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link WEMFParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(WEMFParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link WEMFParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(WEMFParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link WEMFParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(WEMFParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link WEMFParser#reference}.
	 * @param ctx the parse tree
	 */
	void enterReference(WEMFParser.ReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link WEMFParser#reference}.
	 * @param ctx the parse tree
	 */
	void exitReference(WEMFParser.ReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link WEMFParser#method}.
	 * @param ctx the parse tree
	 */
	void enterMethod(WEMFParser.MethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link WEMFParser#method}.
	 * @param ctx the parse tree
	 */
	void exitMethod(WEMFParser.MethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link WEMFParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(WEMFParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link WEMFParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(WEMFParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link WEMFParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(WEMFParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link WEMFParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(WEMFParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link WEMFParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void enterCardinality(WEMFParser.CardinalityContext ctx);
	/**
	 * Exit a parse tree produced by {@link WEMFParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void exitCardinality(WEMFParser.CardinalityContext ctx);
	/**
	 * Enter a parse tree produced by {@link WEMFParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(WEMFParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link WEMFParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(WEMFParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link WEMFParser#enumValues}.
	 * @param ctx the parse tree
	 */
	void enterEnumValues(WEMFParser.EnumValuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link WEMFParser#enumValues}.
	 * @param ctx the parse tree
	 */
	void exitEnumValues(WEMFParser.EnumValuesContext ctx);
	/**
	 * Enter a parse tree produced by {@link WEMFParser#enumValue}.
	 * @param ctx the parse tree
	 */
	void enterEnumValue(WEMFParser.EnumValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link WEMFParser#enumValue}.
	 * @param ctx the parse tree
	 */
	void exitEnumValue(WEMFParser.EnumValueContext ctx);
}