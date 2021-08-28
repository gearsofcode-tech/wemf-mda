// Generated from NAIL.g4 by ANTLR 4.5.1

package tech.gearsofcode.nail.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NAILParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, FUNCTION=33, OPERATOR=34, ON_FIELD=35, STRINGLITERAL=36, ESC=37, 
		INTEGER=38, DIGIT=39, ID=40, REPOSITORY_FUNCTION=41, WS=42;
	public static final int
		RULE_prog = 0, RULE_import_declaration = 1, RULE_entity_declaration = 2, 
		RULE_rules_declaration = 3, RULE_rule_declaration = 4, RULE_defined = 5, 
		RULE_constraints = 6, RULE_constraint = 7, RULE_assert_true = 8, RULE_assert_false = 9, 
		RULE_boolean_expr = 10, RULE_variable = 11, RULE_unique = 12, RULE_matches = 13, 
		RULE_required = 14, RULE_max_value = 15, RULE_max_length = 16, RULE_integer_digits = 17, 
		RULE_decimal_digits = 18, RULE_validation_declaration = 19, RULE_validate_on_insert = 20, 
		RULE_validate_on_update = 21, RULE_validate_on_delete = 22;
	public static final String[] ruleNames = {
		"prog", "import_declaration", "entity_declaration", "rules_declaration", 
		"rule_declaration", "defined", "constraints", "constraint", "assert_true", 
		"assert_false", "boolean_expr", "variable", "unique", "matches", "required", 
		"max_value", "max_length", "integer_digits", "decimal_digits", "validation_declaration", 
		"validate_on_insert", "validate_on_update", "validate_on_delete"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'IMPORT'", "'.wemf'", "'ENTITY'", "'RULES-SECTION'", "'END-RULES-SECTION'", 
		"'RULE'", "'UPDATE-RULE'", "'DELETE-RULE'", "','", "'ON ERROR'", "'DEFINED AS'", 
		"'CONSTRAINTS'", "'END-CONSTRAINTS'", "'ASSERT-TRUE'", "'ASSERT-FALSE'", 
		"'NOT'", "'('", "')'", "'.'", "'UNIQUE'", "'MATCHES'", "'REQUIRED'", "'MAX-VALUE'", 
		"'MAX-LENGTH'", "'INTEGER-DIGITS'", "'DECIMAL-DIGITS'", "'VALIDATE-ON-INSERT'", 
		"'END-VALIDATE-ON-INSERT'", "'VALIDATE-ON-UPDATE'", "'END-VALIDATE-ON-UPDATE'", 
		"'VALIDATE-ON-DELETE'", "'END-VALIDATE-ON-DELETE'", null, null, null, 
		null, "'\\\\'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "FUNCTION", "OPERATOR", 
		"ON_FIELD", "STRINGLITERAL", "ESC", "INTEGER", "DIGIT", "ID", "REPOSITORY_FUNCTION", 
		"WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "NAIL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public NAILParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public Import_declarationContext import_declaration() {
			return getRuleContext(Import_declarationContext.class,0);
		}
		public Entity_declarationContext entity_declaration() {
			return getRuleContext(Entity_declarationContext.class,0);
		}
		public Rules_declarationContext rules_declaration() {
			return getRuleContext(Rules_declarationContext.class,0);
		}
		public Validation_declarationContext validation_declaration() {
			return getRuleContext(Validation_declarationContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			import_declaration();
			setState(47);
			entity_declaration();
			setState(48);
			rules_declaration();
			setState(49);
			validation_declaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Import_declarationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(NAILParser.ID, 0); }
		public Import_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterImport_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitImport_declaration(this);
		}
	}

	public final Import_declarationContext import_declaration() throws RecognitionException {
		Import_declarationContext _localctx = new Import_declarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_import_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(T__0);
			setState(52);
			match(ID);
			setState(53);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Entity_declarationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(NAILParser.ID, 0); }
		public Entity_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entity_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterEntity_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitEntity_declaration(this);
		}
	}

	public final Entity_declarationContext entity_declaration() throws RecognitionException {
		Entity_declarationContext _localctx = new Entity_declarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_entity_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(T__2);
			setState(56);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rules_declarationContext extends ParserRuleContext {
		public List<Rule_declarationContext> rule_declaration() {
			return getRuleContexts(Rule_declarationContext.class);
		}
		public Rule_declarationContext rule_declaration(int i) {
			return getRuleContext(Rule_declarationContext.class,i);
		}
		public Rules_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rules_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterRules_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitRules_declaration(this);
		}
	}

	public final Rules_declarationContext rules_declaration() throws RecognitionException {
		Rules_declarationContext _localctx = new Rules_declarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_rules_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(T__3);
			setState(60); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(59);
				rule_declaration();
				}
				}
				setState(62); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7))) != 0) );
			setState(64);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rule_declarationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(NAILParser.ID, 0); }
		public TerminalNode ON_FIELD() { return getToken(NAILParser.ON_FIELD, 0); }
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode STRINGLITERAL() { return getToken(NAILParser.STRINGLITERAL, 0); }
		public DefinedContext defined() {
			return getRuleContext(DefinedContext.class,0);
		}
		public ConstraintsContext constraints() {
			return getRuleContext(ConstraintsContext.class,0);
		}
		public Rule_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rule_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterRule_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitRule_declaration(this);
		}
	}

	public final Rule_declarationContext rule_declaration() throws RecognitionException {
		Rule_declarationContext _localctx = new Rule_declarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_rule_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(67);
			match(ID);
			setState(68);
			match(ON_FIELD);
			setState(69);
			variable();
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(70);
				match(T__8);
				setState(71);
				variable();
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
			match(T__9);
			setState(78);
			match(STRINGLITERAL);
			setState(80);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(79);
				defined();
				}
			}

			setState(83);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(82);
				constraints();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefinedContext extends ParserRuleContext {
		public TerminalNode STRINGLITERAL() { return getToken(NAILParser.STRINGLITERAL, 0); }
		public DefinedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defined; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterDefined(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitDefined(this);
		}
	}

	public final DefinedContext defined() throws RecognitionException {
		DefinedContext _localctx = new DefinedContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_defined);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(T__10);
			setState(86);
			match(STRINGLITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintsContext extends ParserRuleContext {
		public List<ConstraintContext> constraint() {
			return getRuleContexts(ConstraintContext.class);
		}
		public ConstraintContext constraint(int i) {
			return getRuleContext(ConstraintContext.class,i);
		}
		public ConstraintsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterConstraints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitConstraints(this);
		}
	}

	public final ConstraintsContext constraints() throws RecognitionException {
		ConstraintsContext _localctx = new ConstraintsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_constraints);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__11);
			setState(90); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(89);
				constraint();
				}
				}
				setState(92); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__14) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25))) != 0) );
			setState(94);
			match(T__12);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintContext extends ParserRuleContext {
		public RequiredContext required() {
			return getRuleContext(RequiredContext.class,0);
		}
		public UniqueContext unique() {
			return getRuleContext(UniqueContext.class,0);
		}
		public MatchesContext matches() {
			return getRuleContext(MatchesContext.class,0);
		}
		public Max_valueContext max_value() {
			return getRuleContext(Max_valueContext.class,0);
		}
		public Max_lengthContext max_length() {
			return getRuleContext(Max_lengthContext.class,0);
		}
		public Assert_trueContext assert_true() {
			return getRuleContext(Assert_trueContext.class,0);
		}
		public Assert_falseContext assert_false() {
			return getRuleContext(Assert_falseContext.class,0);
		}
		public Integer_digitsContext integer_digits() {
			return getRuleContext(Integer_digitsContext.class,0);
		}
		public Decimal_digitsContext decimal_digits() {
			return getRuleContext(Decimal_digitsContext.class,0);
		}
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitConstraint(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_constraint);
		try {
			setState(105);
			switch (_input.LA(1)) {
			case T__21:
				enterOuterAlt(_localctx, 1);
				{
				setState(96);
				required();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
				unique();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 3);
				{
				setState(98);
				matches();
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 4);
				{
				setState(99);
				max_value();
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 5);
				{
				setState(100);
				max_length();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 6);
				{
				setState(101);
				assert_true();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 7);
				{
				setState(102);
				assert_false();
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 8);
				{
				setState(103);
				integer_digits();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 9);
				{
				setState(104);
				decimal_digits();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assert_trueContext extends ParserRuleContext {
		public Boolean_exprContext boolean_expr() {
			return getRuleContext(Boolean_exprContext.class,0);
		}
		public Assert_trueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assert_true; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterAssert_true(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitAssert_true(this);
		}
	}

	public final Assert_trueContext assert_true() throws RecognitionException {
		Assert_trueContext _localctx = new Assert_trueContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_assert_true);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(T__13);
			setState(108);
			boolean_expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assert_falseContext extends ParserRuleContext {
		public Boolean_exprContext boolean_expr() {
			return getRuleContext(Boolean_exprContext.class,0);
		}
		public Assert_falseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assert_false; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterAssert_false(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitAssert_false(this);
		}
	}

	public final Assert_falseContext assert_false() throws RecognitionException {
		Assert_falseContext _localctx = new Assert_falseContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_assert_false);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(T__14);
			setState(111);
			boolean_expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Boolean_exprContext extends ParserRuleContext {
		public Boolean_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean_expr; }
	 
		public Boolean_exprContext() { }
		public void copyFrom(Boolean_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RepositoryFunctionContext extends Boolean_exprContext {
		public TerminalNode REPOSITORY_FUNCTION() { return getToken(NAILParser.REPOSITORY_FUNCTION, 0); }
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public RepositoryFunctionContext(Boolean_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterRepositoryFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitRepositoryFunction(this);
		}
	}
	public static class FunctionExpressionContext extends Boolean_exprContext {
		public TerminalNode FUNCTION() { return getToken(NAILParser.FUNCTION, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public FunctionExpressionContext(Boolean_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterFunctionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitFunctionExpression(this);
		}
	}
	public static class NegationContext extends Boolean_exprContext {
		public Boolean_exprContext boolean_expr() {
			return getRuleContext(Boolean_exprContext.class,0);
		}
		public NegationContext(Boolean_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitNegation(this);
		}
	}
	public static class BinaryExpressionContext extends Boolean_exprContext {
		public List<Boolean_exprContext> boolean_expr() {
			return getRuleContexts(Boolean_exprContext.class);
		}
		public Boolean_exprContext boolean_expr(int i) {
			return getRuleContext(Boolean_exprContext.class,i);
		}
		public TerminalNode OPERATOR() { return getToken(NAILParser.OPERATOR, 0); }
		public BinaryExpressionContext(Boolean_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterBinaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitBinaryExpression(this);
		}
	}
	public static class ValueExpressionContext extends Boolean_exprContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode STRINGLITERAL() { return getToken(NAILParser.STRINGLITERAL, 0); }
		public TerminalNode INTEGER() { return getToken(NAILParser.INTEGER, 0); }
		public ValueExpressionContext(Boolean_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterValueExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitValueExpression(this);
		}
	}
	public static class GroupExpressionContext extends Boolean_exprContext {
		public Boolean_exprContext boolean_expr() {
			return getRuleContext(Boolean_exprContext.class,0);
		}
		public GroupExpressionContext(Boolean_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterGroupExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitGroupExpression(this);
		}
	}

	public final Boolean_exprContext boolean_expr() throws RecognitionException {
		return boolean_expr(0);
	}

	private Boolean_exprContext boolean_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Boolean_exprContext _localctx = new Boolean_exprContext(_ctx, _parentState);
		Boolean_exprContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_boolean_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			switch (_input.LA(1)) {
			case T__15:
				{
				_localctx = new NegationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(114);
				match(T__15);
				setState(115);
				boolean_expr(6);
				}
				break;
			case STRINGLITERAL:
			case INTEGER:
			case ID:
				{
				_localctx = new ValueExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(119);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(116);
					variable();
					}
					break;
				case STRINGLITERAL:
					{
					setState(117);
					match(STRINGLITERAL);
					}
					break;
				case INTEGER:
					{
					setState(118);
					match(INTEGER);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case REPOSITORY_FUNCTION:
				{
				_localctx = new RepositoryFunctionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(121);
				match(REPOSITORY_FUNCTION);
				setState(122);
				match(T__16);
				setState(123);
				variable();
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(124);
					match(T__8);
					setState(125);
					variable();
					}
					}
					setState(130);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(131);
				match(T__17);
				}
				break;
			case FUNCTION:
				{
				_localctx = new FunctionExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(133);
				match(FUNCTION);
				setState(134);
				match(T__16);
				setState(135);
				variable();
				setState(136);
				match(T__17);
				}
				break;
			case T__16:
				{
				_localctx = new GroupExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(138);
				match(T__16);
				setState(139);
				boolean_expr(0);
				setState(140);
				match(T__17);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(149);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BinaryExpressionContext(new Boolean_exprContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_boolean_expr);
					setState(144);
					if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
					setState(145);
					match(OPERATOR);
					setState(146);
					boolean_expr(6);
					}
					} 
				}
				setState(151);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(NAILParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(NAILParser.ID, i);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_variable);
		try {
			int _alt;
			setState(160);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				match(ID);
				setState(156); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(154);
						match(T__18);
						setState(155);
						match(ID);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(158); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UniqueContext extends ParserRuleContext {
		public UniqueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unique; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterUnique(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitUnique(this);
		}
	}

	public final UniqueContext unique() throws RecognitionException {
		UniqueContext _localctx = new UniqueContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_unique);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MatchesContext extends ParserRuleContext {
		public TerminalNode STRINGLITERAL() { return getToken(NAILParser.STRINGLITERAL, 0); }
		public MatchesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matches; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterMatches(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitMatches(this);
		}
	}

	public final MatchesContext matches() throws RecognitionException {
		MatchesContext _localctx = new MatchesContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_matches);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(T__20);
			setState(165);
			match(STRINGLITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RequiredContext extends ParserRuleContext {
		public RequiredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_required; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterRequired(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitRequired(this);
		}
	}

	public final RequiredContext required() throws RecognitionException {
		RequiredContext _localctx = new RequiredContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_required);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(T__21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Max_valueContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(NAILParser.INTEGER, 0); }
		public Max_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_max_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterMax_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitMax_value(this);
		}
	}

	public final Max_valueContext max_value() throws RecognitionException {
		Max_valueContext _localctx = new Max_valueContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_max_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(T__22);
			setState(170);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Max_lengthContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(NAILParser.INTEGER, 0); }
		public Max_lengthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_max_length; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterMax_length(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitMax_length(this);
		}
	}

	public final Max_lengthContext max_length() throws RecognitionException {
		Max_lengthContext _localctx = new Max_lengthContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_max_length);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(T__23);
			setState(173);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Integer_digitsContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(NAILParser.INTEGER, 0); }
		public Integer_digitsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer_digits; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterInteger_digits(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitInteger_digits(this);
		}
	}

	public final Integer_digitsContext integer_digits() throws RecognitionException {
		Integer_digitsContext _localctx = new Integer_digitsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_integer_digits);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(T__24);
			setState(176);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Decimal_digitsContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(NAILParser.INTEGER, 0); }
		public Decimal_digitsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal_digits; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterDecimal_digits(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitDecimal_digits(this);
		}
	}

	public final Decimal_digitsContext decimal_digits() throws RecognitionException {
		Decimal_digitsContext _localctx = new Decimal_digitsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_decimal_digits);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(T__25);
			setState(179);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Validation_declarationContext extends ParserRuleContext {
		public Validate_on_insertContext validate_on_insert() {
			return getRuleContext(Validate_on_insertContext.class,0);
		}
		public Validate_on_updateContext validate_on_update() {
			return getRuleContext(Validate_on_updateContext.class,0);
		}
		public Validate_on_deleteContext validate_on_delete() {
			return getRuleContext(Validate_on_deleteContext.class,0);
		}
		public Validation_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_validation_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterValidation_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitValidation_declaration(this);
		}
	}

	public final Validation_declarationContext validation_declaration() throws RecognitionException {
		Validation_declarationContext _localctx = new Validation_declarationContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_validation_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			_la = _input.LA(1);
			if (_la==T__26) {
				{
				setState(181);
				validate_on_insert();
				}
			}

			setState(185);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(184);
				validate_on_update();
				}
			}

			setState(188);
			_la = _input.LA(1);
			if (_la==T__30) {
				{
				setState(187);
				validate_on_delete();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Validate_on_insertContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(NAILParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(NAILParser.ID, i);
		}
		public Validate_on_insertContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_validate_on_insert; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterValidate_on_insert(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitValidate_on_insert(this);
		}
	}

	public final Validate_on_insertContext validate_on_insert() throws RecognitionException {
		Validate_on_insertContext _localctx = new Validate_on_insertContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_validate_on_insert);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(T__26);
			setState(192); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(191);
				match(ID);
				}
				}
				setState(194); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(196);
			match(T__27);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Validate_on_updateContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(NAILParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(NAILParser.ID, i);
		}
		public Validate_on_updateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_validate_on_update; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterValidate_on_update(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitValidate_on_update(this);
		}
	}

	public final Validate_on_updateContext validate_on_update() throws RecognitionException {
		Validate_on_updateContext _localctx = new Validate_on_updateContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_validate_on_update);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(T__28);
			setState(200); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(199);
				match(ID);
				}
				}
				setState(202); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(204);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Validate_on_deleteContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(NAILParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(NAILParser.ID, i);
		}
		public Validate_on_deleteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_validate_on_delete; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).enterValidate_on_delete(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NAILListener ) ((NAILListener)listener).exitValidate_on_delete(this);
		}
	}

	public final Validate_on_deleteContext validate_on_delete() throws RecognitionException {
		Validate_on_deleteContext _localctx = new Validate_on_deleteContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_validate_on_delete);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(T__30);
			setState(208); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(207);
				match(ID);
				}
				}
				setState(210); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(212);
			match(T__31);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10:
			return boolean_expr_sempred((Boolean_exprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean boolean_expr_sempred(Boolean_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3,\u00d9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\6\5?\n\5\r\5\16\5@\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6K\n\6\f\6\16\6N\13\6\3\6\3\6\3\6\5\6S"+
		"\n\6\3\6\5\6V\n\6\3\7\3\7\3\7\3\b\3\b\6\b]\n\b\r\b\16\b^\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tl\n\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\5\fz\n\f\3\f\3\f\3\f\3\f\3\f\7\f\u0081\n\f\f\f\16"+
		"\f\u0084\13\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0091\n"+
		"\f\3\f\3\f\3\f\7\f\u0096\n\f\f\f\16\f\u0099\13\f\3\r\3\r\3\r\3\r\6\r\u009f"+
		"\n\r\r\r\16\r\u00a0\5\r\u00a3\n\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\5\25\u00b9"+
		"\n\25\3\25\5\25\u00bc\n\25\3\25\5\25\u00bf\n\25\3\26\3\26\6\26\u00c3\n"+
		"\26\r\26\16\26\u00c4\3\26\3\26\3\27\3\27\6\27\u00cb\n\27\r\27\16\27\u00cc"+
		"\3\27\3\27\3\30\3\30\6\30\u00d3\n\30\r\30\16\30\u00d4\3\30\3\30\3\30\2"+
		"\3\26\31\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\2\3\3\2\b\n\u00de"+
		"\2\60\3\2\2\2\4\65\3\2\2\2\69\3\2\2\2\b<\3\2\2\2\nD\3\2\2\2\fW\3\2\2\2"+
		"\16Z\3\2\2\2\20k\3\2\2\2\22m\3\2\2\2\24p\3\2\2\2\26\u0090\3\2\2\2\30\u00a2"+
		"\3\2\2\2\32\u00a4\3\2\2\2\34\u00a6\3\2\2\2\36\u00a9\3\2\2\2 \u00ab\3\2"+
		"\2\2\"\u00ae\3\2\2\2$\u00b1\3\2\2\2&\u00b4\3\2\2\2(\u00b8\3\2\2\2*\u00c0"+
		"\3\2\2\2,\u00c8\3\2\2\2.\u00d0\3\2\2\2\60\61\5\4\3\2\61\62\5\6\4\2\62"+
		"\63\5\b\5\2\63\64\5(\25\2\64\3\3\2\2\2\65\66\7\3\2\2\66\67\7*\2\2\678"+
		"\7\4\2\28\5\3\2\2\29:\7\5\2\2:;\7*\2\2;\7\3\2\2\2<>\7\6\2\2=?\5\n\6\2"+
		">=\3\2\2\2?@\3\2\2\2@>\3\2\2\2@A\3\2\2\2AB\3\2\2\2BC\7\7\2\2C\t\3\2\2"+
		"\2DE\t\2\2\2EF\7*\2\2FG\7%\2\2GL\5\30\r\2HI\7\13\2\2IK\5\30\r\2JH\3\2"+
		"\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2\2\2OP\7\f\2\2PR\7&"+
		"\2\2QS\5\f\7\2RQ\3\2\2\2RS\3\2\2\2SU\3\2\2\2TV\5\16\b\2UT\3\2\2\2UV\3"+
		"\2\2\2V\13\3\2\2\2WX\7\r\2\2XY\7&\2\2Y\r\3\2\2\2Z\\\7\16\2\2[]\5\20\t"+
		"\2\\[\3\2\2\2]^\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_`\3\2\2\2`a\7\17\2\2a\17"+
		"\3\2\2\2bl\5\36\20\2cl\5\32\16\2dl\5\34\17\2el\5 \21\2fl\5\"\22\2gl\5"+
		"\22\n\2hl\5\24\13\2il\5$\23\2jl\5&\24\2kb\3\2\2\2kc\3\2\2\2kd\3\2\2\2"+
		"ke\3\2\2\2kf\3\2\2\2kg\3\2\2\2kh\3\2\2\2ki\3\2\2\2kj\3\2\2\2l\21\3\2\2"+
		"\2mn\7\20\2\2no\5\26\f\2o\23\3\2\2\2pq\7\21\2\2qr\5\26\f\2r\25\3\2\2\2"+
		"st\b\f\1\2tu\7\22\2\2u\u0091\5\26\f\bvz\5\30\r\2wz\7&\2\2xz\7(\2\2yv\3"+
		"\2\2\2yw\3\2\2\2yx\3\2\2\2z\u0091\3\2\2\2{|\7+\2\2|}\7\23\2\2}\u0082\5"+
		"\30\r\2~\177\7\13\2\2\177\u0081\5\30\r\2\u0080~\3\2\2\2\u0081\u0084\3"+
		"\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0085\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0085\u0086\7\24\2\2\u0086\u0091\3\2\2\2\u0087\u0088\7"+
		"#\2\2\u0088\u0089\7\23\2\2\u0089\u008a\5\30\r\2\u008a\u008b\7\24\2\2\u008b"+
		"\u0091\3\2\2\2\u008c\u008d\7\23\2\2\u008d\u008e\5\26\f\2\u008e\u008f\7"+
		"\24\2\2\u008f\u0091\3\2\2\2\u0090s\3\2\2\2\u0090y\3\2\2\2\u0090{\3\2\2"+
		"\2\u0090\u0087\3\2\2\2\u0090\u008c\3\2\2\2\u0091\u0097\3\2\2\2\u0092\u0093"+
		"\f\7\2\2\u0093\u0094\7$\2\2\u0094\u0096\5\26\f\b\u0095\u0092\3\2\2\2\u0096"+
		"\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\27\3\2\2"+
		"\2\u0099\u0097\3\2\2\2\u009a\u00a3\7*\2\2\u009b\u009e\7*\2\2\u009c\u009d"+
		"\7\25\2\2\u009d\u009f\7*\2\2\u009e\u009c\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3\3\2\2\2\u00a2\u009a\3\2"+
		"\2\2\u00a2\u009b\3\2\2\2\u00a3\31\3\2\2\2\u00a4\u00a5\7\26\2\2\u00a5\33"+
		"\3\2\2\2\u00a6\u00a7\7\27\2\2\u00a7\u00a8\7&\2\2\u00a8\35\3\2\2\2\u00a9"+
		"\u00aa\7\30\2\2\u00aa\37\3\2\2\2\u00ab\u00ac\7\31\2\2\u00ac\u00ad\7(\2"+
		"\2\u00ad!\3\2\2\2\u00ae\u00af\7\32\2\2\u00af\u00b0\7(\2\2\u00b0#\3\2\2"+
		"\2\u00b1\u00b2\7\33\2\2\u00b2\u00b3\7(\2\2\u00b3%\3\2\2\2\u00b4\u00b5"+
		"\7\34\2\2\u00b5\u00b6\7(\2\2\u00b6\'\3\2\2\2\u00b7\u00b9\5*\26\2\u00b8"+
		"\u00b7\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba\u00bc\5,"+
		"\27\2\u00bb\u00ba\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00be\3\2\2\2\u00bd"+
		"\u00bf\5.\30\2\u00be\u00bd\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf)\3\2\2\2"+
		"\u00c0\u00c2\7\35\2\2\u00c1\u00c3\7*\2\2\u00c2\u00c1\3\2\2\2\u00c3\u00c4"+
		"\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00c7\7\36\2\2\u00c7+\3\2\2\2\u00c8\u00ca\7\37\2\2\u00c9\u00cb\7*\2\2"+
		"\u00ca\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd"+
		"\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\7 \2\2\u00cf-\3\2\2\2\u00d0\u00d2"+
		"\7!\2\2\u00d1\u00d3\7*\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4"+
		"\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\7\""+
		"\2\2\u00d7/\3\2\2\2\24@LRU^ky\u0082\u0090\u0097\u00a0\u00a2\u00b8\u00bb"+
		"\u00be\u00c4\u00cc\u00d4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}