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
		T__17=18, PRIMITIVE=19, WRAPPER=20, EXTERNAL=21, NEWLINE=22, INT=23, SHORT=24, 
		BYTE=25, LONG=26, FLOAT=27, DOUBLE=28, BOOLEAN=29, STRINGLITERAL=30, ID=31, 
		WS=32;
	public static final int
		RULE_prog = 0, RULE_system = 1, RULE_epackage = 2, RULE_packageName = 3, 
		RULE_eclass = 4, RULE_attribute = 5, RULE_type = 6, RULE_reference = 7, 
		RULE_method = 8, RULE_parameters = 9, RULE_parameter = 10, RULE_cardinality = 11, 
		RULE_annotation = 12, RULE_annotParameters = 13, RULE_annotParam = 14;
	public static final String[] ruleNames = {
		"prog", "system", "epackage", "packageName", "eclass", "attribute", "type", 
		"reference", "method", "parameters", "parameter", "cardinality", "annotation", 
		"annotParameters", "annotParam"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'system'", "';'", "'package'", "'{'", "'}'", "'.'", "'class'", 
		"'abstract class'", "':'", "'('", "')'", "','", "'['", "'..'", "'*'", 
		"']'", "'@'", "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "PRIMITIVE", "WRAPPER", "EXTERNAL", 
		"NEWLINE", "INT", "SHORT", "BYTE", "LONG", "FLOAT", "DOUBLE", "BOOLEAN", 
		"STRINGLITERAL", "ID", "WS"
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
			setState(30);
			system();
			setState(31);
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
			setState(33);
			match(T__0);
			setState(34);
			match(ID);
			setState(35);
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
		public PackageNameContext packageName() {
			return getRuleContext(PackageNameContext.class,0);
		}
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
			setState(37);
			match(T__2);
			setState(38);
			packageName();
			setState(39);
			match(T__3);
			setState(47); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(40);
					annotation();
					}
					}
					setState(45);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(46);
				eclass();
				}
				}
				setState(49); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__16))) != 0) );
			setState(51);
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

	public static class PackageNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(WEMFParser.ID, 0); }
		public PackageNameContext packageName() {
			return getRuleContext(PackageNameContext.class,0);
		}
		public PackageNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).enterPackageName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WEMFListener ) ((WEMFListener)listener).exitPackageName(this);
		}
	}

	public final PackageNameContext packageName() throws RecognitionException {
		PackageNameContext _localctx = new PackageNameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_packageName);
		try {
			setState(57);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				match(ID);
				setState(55);
				match(T__5);
				setState(56);
				packageName();
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
		enterRule(_localctx, 8, RULE_eclass);
		int _la;
		try {
			setState(93);
			switch (_input.LA(1)) {
			case T__6:
				_localctx = new ConcreteClassContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				match(T__6);
				setState(60);
				match(ID);
				setState(61);
				match(T__3);
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16 || _la==ID) {
					{
					setState(76);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						setState(65);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__16) {
							{
							{
							setState(62);
							annotation();
							}
							}
							setState(67);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(68);
						attribute();
						}
						break;
					case 2:
						{
						setState(72);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__16) {
							{
							{
							setState(69);
							annotation();
							}
							}
							setState(74);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(75);
						method();
						}
						break;
					}
					}
					setState(80);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(81);
				match(T__4);
				}
				break;
			case T__7:
				_localctx = new AbstractClassContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
				match(T__7);
				setState(83);
				match(ID);
				setState(84);
				match(T__3);
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ID) {
					{
					setState(87);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						setState(85);
						attribute();
						}
						break;
					case 2:
						{
						setState(86);
						method();
						}
						break;
					}
					}
					setState(91);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(92);
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
		enterRule(_localctx, 10, RULE_attribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(ID);
			setState(96);
			match(T__8);
			setState(97);
			type();
			setState(99);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(98);
				cardinality();
				}
			}

			setState(101);
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
		enterRule(_localctx, 12, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			switch (_input.LA(1)) {
			case PRIMITIVE:
				{
				setState(103);
				match(PRIMITIVE);
				}
				break;
			case WRAPPER:
				{
				setState(104);
				match(WRAPPER);
				}
				break;
			case EXTERNAL:
				{
				setState(105);
				match(EXTERNAL);
				}
				break;
			case ID:
				{
				setState(106);
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
		enterRule(_localctx, 14, RULE_reference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
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
		enterRule(_localctx, 16, RULE_method);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(ID);
			setState(112);
			match(T__9);
			setState(114);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(113);
				parameters();
				}
			}

			setState(116);
			match(T__10);
			setState(123);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(117);
				match(T__8);
				setState(118);
				type();
				}
				break;
			case 2:
				{
				setState(119);
				match(T__8);
				setState(120);
				type();
				setState(121);
				cardinality();
				}
				break;
			}
			setState(125);
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
		enterRule(_localctx, 18, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			parameter();
			setState(130);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(128);
				match(T__11);
				setState(129);
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
		enterRule(_localctx, 20, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(ID);
			setState(133);
			match(T__8);
			setState(134);
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
		enterRule(_localctx, 22, RULE_cardinality);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(T__12);
			setState(137);
			match(INT);
			setState(138);
			match(T__13);
			setState(139);
			_la = _input.LA(1);
			if ( !(_la==T__14 || _la==INT) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(140);
			match(T__15);
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
		enterRule(_localctx, 24, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(T__16);
			setState(143);
			match(ID);
			setState(148);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(144);
				match(T__9);
				setState(145);
				annotParameters();
				setState(146);
				match(T__10);
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
		enterRule(_localctx, 26, RULE_annotParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			annotParam();
			setState(153);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(151);
				match(T__11);
				setState(152);
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
		enterRule(_localctx, 28, RULE_annotParam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(ID);
			setState(156);
			match(T__17);
			setState(157);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\"\u00a2\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\7\4,\n\4\f\4\16\4/\13\4\3\4\6\4\62\n\4\r\4\16"+
		"\4\63\3\4\3\4\3\5\3\5\3\5\3\5\5\5<\n\5\3\6\3\6\3\6\3\6\7\6B\n\6\f\6\16"+
		"\6E\13\6\3\6\3\6\7\6I\n\6\f\6\16\6L\13\6\3\6\7\6O\n\6\f\6\16\6R\13\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\7\6Z\n\6\f\6\16\6]\13\6\3\6\5\6`\n\6\3\7\3\7\3"+
		"\7\3\7\5\7f\n\7\3\7\3\7\3\b\3\b\3\b\3\b\5\bn\n\b\3\t\3\t\3\n\3\n\3\n\5"+
		"\nu\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n~\n\n\3\n\3\n\3\13\3\13\3\13\5"+
		"\13\u0085\n\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\5\16\u0097\n\16\3\17\3\17\3\17\5\17\u009c\n\17\3\20\3"+
		"\20\3\20\3\20\3\20\2\2\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\3\4"+
		"\2\21\21\31\31\u00a6\2 \3\2\2\2\4#\3\2\2\2\6\'\3\2\2\2\b;\3\2\2\2\n_\3"+
		"\2\2\2\fa\3\2\2\2\16m\3\2\2\2\20o\3\2\2\2\22q\3\2\2\2\24\u0081\3\2\2\2"+
		"\26\u0086\3\2\2\2\30\u008a\3\2\2\2\32\u0090\3\2\2\2\34\u0098\3\2\2\2\36"+
		"\u009d\3\2\2\2 !\5\4\3\2!\"\5\6\4\2\"\3\3\2\2\2#$\7\3\2\2$%\7!\2\2%&\7"+
		"\4\2\2&\5\3\2\2\2\'(\7\5\2\2()\5\b\5\2)\61\7\6\2\2*,\5\32\16\2+*\3\2\2"+
		"\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\60\3\2\2\2/-\3\2\2\2\60\62\5\n\6\2\61"+
		"-\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\65\3\2\2\2\65\66"+
		"\7\7\2\2\66\7\3\2\2\2\67<\7!\2\289\7!\2\29:\7\b\2\2:<\5\b\5\2;\67\3\2"+
		"\2\2;8\3\2\2\2<\t\3\2\2\2=>\7\t\2\2>?\7!\2\2?P\7\6\2\2@B\5\32\16\2A@\3"+
		"\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2DF\3\2\2\2EC\3\2\2\2FO\5\f\7\2GI\5"+
		"\32\16\2HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3\2\2\2LJ\3\2\2\2M"+
		"O\5\22\n\2NC\3\2\2\2NJ\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QS\3\2\2\2"+
		"RP\3\2\2\2S`\7\7\2\2TU\7\n\2\2UV\7!\2\2V[\7\6\2\2WZ\5\f\7\2XZ\5\22\n\2"+
		"YW\3\2\2\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\^\3\2\2\2][\3\2\2"+
		"\2^`\7\7\2\2_=\3\2\2\2_T\3\2\2\2`\13\3\2\2\2ab\7!\2\2bc\7\13\2\2ce\5\16"+
		"\b\2df\5\30\r\2ed\3\2\2\2ef\3\2\2\2fg\3\2\2\2gh\7\4\2\2h\r\3\2\2\2in\7"+
		"\25\2\2jn\7\26\2\2kn\7\27\2\2ln\5\20\t\2mi\3\2\2\2mj\3\2\2\2mk\3\2\2\2"+
		"ml\3\2\2\2n\17\3\2\2\2op\7!\2\2p\21\3\2\2\2qr\7!\2\2rt\7\f\2\2su\5\24"+
		"\13\2ts\3\2\2\2tu\3\2\2\2uv\3\2\2\2v}\7\r\2\2wx\7\13\2\2x~\5\16\b\2yz"+
		"\7\13\2\2z{\5\16\b\2{|\5\30\r\2|~\3\2\2\2}w\3\2\2\2}y\3\2\2\2}~\3\2\2"+
		"\2~\177\3\2\2\2\177\u0080\7\4\2\2\u0080\23\3\2\2\2\u0081\u0084\5\26\f"+
		"\2\u0082\u0083\7\16\2\2\u0083\u0085\5\24\13\2\u0084\u0082\3\2\2\2\u0084"+
		"\u0085\3\2\2\2\u0085\25\3\2\2\2\u0086\u0087\7!\2\2\u0087\u0088\7\13\2"+
		"\2\u0088\u0089\5\16\b\2\u0089\27\3\2\2\2\u008a\u008b\7\17\2\2\u008b\u008c"+
		"\7\31\2\2\u008c\u008d\7\20\2\2\u008d\u008e\t\2\2\2\u008e\u008f\7\22\2"+
		"\2\u008f\31\3\2\2\2\u0090\u0091\7\23\2\2\u0091\u0096\7!\2\2\u0092\u0093"+
		"\7\f\2\2\u0093\u0094\5\34\17\2\u0094\u0095\7\r\2\2\u0095\u0097\3\2\2\2"+
		"\u0096\u0092\3\2\2\2\u0096\u0097\3\2\2\2\u0097\33\3\2\2\2\u0098\u009b"+
		"\5\36\20\2\u0099\u009a\7\16\2\2\u009a\u009c\5\34\17\2\u009b\u0099\3\2"+
		"\2\2\u009b\u009c\3\2\2\2\u009c\35\3\2\2\2\u009d\u009e\7!\2\2\u009e\u009f"+
		"\7\24\2\2\u009f\u00a0\7 \2\2\u00a0\37\3\2\2\2\23-\63;CJNPY[_emt}\u0084"+
		"\u0096\u009b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}