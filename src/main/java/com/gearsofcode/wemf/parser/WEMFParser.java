// Generated from WEMF.g4 by ANTLR 4.5.1

package com.gearsofcode.wemf.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WEMFParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		PRIMITIVE=18, WRAPPER=19, EXTERNAL=20, NEWLINE=21, INT=22, SHORT=23, BYTE=24, 
		LONG=25, FLOAT=26, DOUBLE=27, BOOLEAN=28, STRINGLITERAL=29, ID=30, PACKAGE=31, 
		WS=32;
	public static final int
		RULE_prog = 0, RULE_system = 1, RULE_epackage = 2, RULE_eclass = 3, RULE_attribute = 4, 
		RULE_type = 5, RULE_reference = 6, RULE_method = 7, RULE_parameters = 8, 
		RULE_parameter = 9, RULE_cardinality = 10, RULE_annotation = 11, RULE_annotParameters = 12, 
		RULE_annotParam = 13;
	public static final String[] ruleNames = {
		"prog", "system", "epackage", "eclass", "attribute", "type", "reference", 
		"method", "parameters", "parameter", "cardinality", "annotation", "annotParameters", 
		"annotParam"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'system'", "';'", "'package'", "'{'", "'}'", "'class'", "'abstract class'", 
		"':'", "'('", "')'", "','", "'['", "'..'", "'*'", "']'", "'@'", "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, "PRIMITIVE", "WRAPPER", "EXTERNAL", 
		"NEWLINE", "INT", "SHORT", "BYTE", "LONG", "FLOAT", "DOUBLE", "BOOLEAN", 
		"STRINGLITERAL", "ID", "PACKAGE", "WS"
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
	public String getGrammarFileName() { return "WEMF.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public WEMFParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public SystemContext system() {
			return getRuleContext(SystemContext.class,0);
		}
		public EpackageContext epackage() {
			return getRuleContext(EpackageContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			system();
			setState(29);
			epackage();
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

	public static class SystemContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(WEMFParser.ID, 0); }
		public SystemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_system; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterSystem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitSystem(this);
		}
	}

	public final SystemContext system() throws RecognitionException {
		SystemContext _localctx = new SystemContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_system);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			match(T__0);
			setState(32);
			match(ID);
			setState(33);
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

	public static class EpackageContext extends ParserRuleContext {
		public TerminalNode PACKAGE() { return getToken(WEMFParser.PACKAGE, 0); }
		public List<EclassContext> eclass() {
			return getRuleContexts(EclassContext.class);
		}
		public EclassContext eclass(int i) {
			return getRuleContext(EclassContext.class,i);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public EpackageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_epackage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterEpackage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitEpackage(this);
		}
	}

	public final EpackageContext epackage() throws RecognitionException {
		EpackageContext _localctx = new EpackageContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_epackage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(T__2);
			setState(36);
			match(PACKAGE);
			setState(37);
			match(T__3);
			setState(45); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__15) {
					{
					{
					setState(38);
					annotation();
					}
					}
					setState(43);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(44);
				eclass();
				}
				}
				setState(47); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__15))) != 0) );
			setState(49);
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

	public static class EclassContext extends ParserRuleContext {
		public EclassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eclass; }
	 
		public EclassContext() { }
		public void copyFrom(EclassContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AbstractClassContext extends EclassContext {
		public TerminalNode ID() { return getToken(WEMFParser.ID, 0); }
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public List<MethodContext> method() {
			return getRuleContexts(MethodContext.class);
		}
		public MethodContext method(int i) {
			return getRuleContext(MethodContext.class,i);
		}
		public AbstractClassContext(EclassContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterAbstractClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitAbstractClass(this);
		}
	}
	public static class ConcreteClassContext extends EclassContext {
		public TerminalNode ID() { return getToken(WEMFParser.ID, 0); }
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public List<MethodContext> method() {
			return getRuleContexts(MethodContext.class);
		}
		public MethodContext method(int i) {
			return getRuleContext(MethodContext.class,i);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public ConcreteClassContext(EclassContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterConcreteClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitConcreteClass(this);
		}
	}

	public final EclassContext eclass() throws RecognitionException {
		EclassContext _localctx = new EclassContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_eclass);
		int _la;
		try {
			int _alt;
			setState(85);
			switch (_input.LA(1)) {
			case T__5:
				_localctx = new ConcreteClassContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				match(T__5);
				setState(52);
				match(ID);
				setState(53);
				match(T__3);
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__15 || _la==ID) {
					{
					setState(68);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						setState(57);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(54);
								annotation();
								}
								} 
							}
							setState(59);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
						}
						setState(60);
						attribute();
						}
						break;
					case 2:
						{
						setState(64);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__15) {
							{
							{
							setState(61);
							annotation();
							}
							}
							setState(66);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(67);
						method();
						}
						break;
					}
					}
					setState(72);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(73);
				match(T__4);
				}
				break;
			case T__6:
				_localctx = new AbstractClassContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				match(T__6);
				setState(75);
				match(ID);
				setState(76);
				match(T__3);
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__15 || _la==ID) {
					{
					setState(79);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						setState(77);
						attribute();
						}
						break;
					case 2:
						{
						setState(78);
						method();
						}
						break;
					}
					}
					setState(83);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(84);
				match(T__4);
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

	public static class AttributeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(WEMFParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public CardinalityContext cardinality() {
			return getRuleContext(CardinalityContext.class,0);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_attribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(87);
				annotation();
				}
			}

			setState(90);
			match(ID);
			setState(91);
			match(T__7);
			setState(92);
			type();
			setState(94);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(93);
				cardinality();
				}
			}

			setState(96);
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode PRIMITIVE() { return getToken(WEMFParser.PRIMITIVE, 0); }
		public TerminalNode WRAPPER() { return getToken(WEMFParser.WRAPPER, 0); }
		public TerminalNode EXTERNAL() { return getToken(WEMFParser.EXTERNAL, 0); }
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			switch (_input.LA(1)) {
			case PRIMITIVE:
				{
				setState(98);
				match(PRIMITIVE);
				}
				break;
			case WRAPPER:
				{
				setState(99);
				match(WRAPPER);
				}
				break;
			case EXTERNAL:
				{
				setState(100);
				match(EXTERNAL);
				}
				break;
			case ID:
				{
				setState(101);
				reference();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ReferenceContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(WEMFParser.ID, 0); }
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitReference(this);
		}
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_reference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
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

	public static class MethodContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(WEMFParser.ID, 0); }
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public CardinalityContext cardinality() {
			return getRuleContext(CardinalityContext.class,0);
		}
		public MethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitMethod(this);
		}
	}

	public final MethodContext method() throws RecognitionException {
		MethodContext _localctx = new MethodContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_method);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(ID);
			setState(107);
			match(T__8);
			setState(109);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(108);
				parameters();
				}
			}

			setState(111);
			match(T__9);
			setState(118);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(112);
				match(T__7);
				setState(113);
				type();
				}
				break;
			case 2:
				{
				setState(114);
				match(T__7);
				setState(115);
				type();
				setState(116);
				cardinality();
				}
				break;
			}
			setState(120);
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

	public static class ParametersContext extends ParserRuleContext {
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitParameters(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			parameter();
			setState(125);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(123);
				match(T__10);
				setState(124);
				parameters();
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

	public static class ParameterContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(WEMFParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitParameter(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(ID);
			setState(128);
			match(T__7);
			setState(129);
			type();
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

	public static class CardinalityContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(WEMFParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(WEMFParser.INT, i);
		}
		public CardinalityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cardinality; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterCardinality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitCardinality(this);
		}
	}

	public final CardinalityContext cardinality() throws RecognitionException {
		CardinalityContext _localctx = new CardinalityContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cardinality);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(T__11);
			setState(132);
			match(INT);
			setState(133);
			match(T__12);
			setState(134);
			_la = _input.LA(1);
			if ( !(_la==T__13 || _la==INT) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(135);
			match(T__14);
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

	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(WEMFParser.ID, 0); }
		public AnnotParametersContext annotParameters() {
			return getRuleContext(AnnotParametersContext.class,0);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitAnnotation(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(T__15);
			setState(138);
			match(ID);
			setState(143);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(139);
				match(T__8);
				setState(140);
				annotParameters();
				setState(141);
				match(T__9);
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

	public static class AnnotParametersContext extends ParserRuleContext {
		public AnnotParamContext annotParam() {
			return getRuleContext(AnnotParamContext.class,0);
		}
		public AnnotParametersContext annotParameters() {
			return getRuleContext(AnnotParametersContext.class,0);
		}
		public AnnotParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterAnnotParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitAnnotParameters(this);
		}
	}

	public final AnnotParametersContext annotParameters() throws RecognitionException {
		AnnotParametersContext _localctx = new AnnotParametersContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_annotParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			annotParam();
			setState(148);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(146);
				match(T__10);
				setState(147);
				annotParameters();
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

	public static class AnnotParamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(WEMFParser.ID, 0); }
		public TerminalNode STRINGLITERAL() { return getToken(WEMFParser.STRINGLITERAL, 0); }
		public AnnotParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterAnnotParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitAnnotParam(this);
		}
	}

	public final AnnotParamContext annotParam() throws RecognitionException {
		AnnotParamContext _localctx = new AnnotParamContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_annotParam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(ID);
			setState(151);
			match(T__16);
			setState(152);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\"\u009d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\7\4*\n\4\f\4\16\4-\13\4\3\4\6\4\60\n\4\r\4\16\4\61\3\4"+
		"\3\4\3\5\3\5\3\5\3\5\7\5:\n\5\f\5\16\5=\13\5\3\5\3\5\7\5A\n\5\f\5\16\5"+
		"D\13\5\3\5\7\5G\n\5\f\5\16\5J\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5R\n\5\f"+
		"\5\16\5U\13\5\3\5\5\5X\n\5\3\6\5\6[\n\6\3\6\3\6\3\6\3\6\5\6a\n\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\5\7i\n\7\3\b\3\b\3\t\3\t\3\t\5\tp\n\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\5\ty\n\t\3\t\3\t\3\n\3\n\3\n\5\n\u0080\n\n\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0092\n\r"+
		"\3\16\3\16\3\16\5\16\u0097\n\16\3\17\3\17\3\17\3\17\3\17\2\2\20\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\2\3\4\2\20\20\30\30\u00a2\2\36\3\2\2\2"+
		"\4!\3\2\2\2\6%\3\2\2\2\bW\3\2\2\2\nZ\3\2\2\2\fh\3\2\2\2\16j\3\2\2\2\20"+
		"l\3\2\2\2\22|\3\2\2\2\24\u0081\3\2\2\2\26\u0085\3\2\2\2\30\u008b\3\2\2"+
		"\2\32\u0093\3\2\2\2\34\u0098\3\2\2\2\36\37\5\4\3\2\37 \5\6\4\2 \3\3\2"+
		"\2\2!\"\7\3\2\2\"#\7 \2\2#$\7\4\2\2$\5\3\2\2\2%&\7\5\2\2&\'\7!\2\2\'/"+
		"\7\6\2\2(*\5\30\r\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,.\3\2\2\2"+
		"-+\3\2\2\2.\60\5\b\5\2/+\3\2\2\2\60\61\3\2\2\2\61/\3\2\2\2\61\62\3\2\2"+
		"\2\62\63\3\2\2\2\63\64\7\7\2\2\64\7\3\2\2\2\65\66\7\b\2\2\66\67\7 \2\2"+
		"\67H\7\6\2\28:\5\30\r\298\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<>\3\2"+
		"\2\2=;\3\2\2\2>G\5\n\6\2?A\5\30\r\2@?\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3"+
		"\2\2\2CE\3\2\2\2DB\3\2\2\2EG\5\20\t\2F;\3\2\2\2FB\3\2\2\2GJ\3\2\2\2HF"+
		"\3\2\2\2HI\3\2\2\2IK\3\2\2\2JH\3\2\2\2KX\7\7\2\2LM\7\t\2\2MN\7 \2\2NS"+
		"\7\6\2\2OR\5\n\6\2PR\5\20\t\2QO\3\2\2\2QP\3\2\2\2RU\3\2\2\2SQ\3\2\2\2"+
		"ST\3\2\2\2TV\3\2\2\2US\3\2\2\2VX\7\7\2\2W\65\3\2\2\2WL\3\2\2\2X\t\3\2"+
		"\2\2Y[\5\30\r\2ZY\3\2\2\2Z[\3\2\2\2[\\\3\2\2\2\\]\7 \2\2]^\7\n\2\2^`\5"+
		"\f\7\2_a\5\26\f\2`_\3\2\2\2`a\3\2\2\2ab\3\2\2\2bc\7\4\2\2c\13\3\2\2\2"+
		"di\7\24\2\2ei\7\25\2\2fi\7\26\2\2gi\5\16\b\2hd\3\2\2\2he\3\2\2\2hf\3\2"+
		"\2\2hg\3\2\2\2i\r\3\2\2\2jk\7 \2\2k\17\3\2\2\2lm\7 \2\2mo\7\13\2\2np\5"+
		"\22\n\2on\3\2\2\2op\3\2\2\2pq\3\2\2\2qx\7\f\2\2rs\7\n\2\2sy\5\f\7\2tu"+
		"\7\n\2\2uv\5\f\7\2vw\5\26\f\2wy\3\2\2\2xr\3\2\2\2xt\3\2\2\2xy\3\2\2\2"+
		"yz\3\2\2\2z{\7\4\2\2{\21\3\2\2\2|\177\5\24\13\2}~\7\r\2\2~\u0080\5\22"+
		"\n\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\23\3\2\2\2\u0081\u0082\7 \2"+
		"\2\u0082\u0083\7\n\2\2\u0083\u0084\5\f\7\2\u0084\25\3\2\2\2\u0085\u0086"+
		"\7\16\2\2\u0086\u0087\7\30\2\2\u0087\u0088\7\17\2\2\u0088\u0089\t\2\2"+
		"\2\u0089\u008a\7\21\2\2\u008a\27\3\2\2\2\u008b\u008c\7\22\2\2\u008c\u0091"+
		"\7 \2\2\u008d\u008e\7\13\2\2\u008e\u008f\5\32\16\2\u008f\u0090\7\f\2\2"+
		"\u0090\u0092\3\2\2\2\u0091\u008d\3\2\2\2\u0091\u0092\3\2\2\2\u0092\31"+
		"\3\2\2\2\u0093\u0096\5\34\17\2\u0094\u0095\7\r\2\2\u0095\u0097\5\32\16"+
		"\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\33\3\2\2\2\u0098\u0099"+
		"\7 \2\2\u0099\u009a\7\23\2\2\u009a\u009b\7\37\2\2\u009b\35\3\2\2\2\23"+
		"+\61;BFHQSWZ`hox\177\u0091\u0096";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}