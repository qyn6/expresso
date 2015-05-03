// Generated from Expression.g4 by ANTLR 4.5

package expresso.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CHAR=1, DIGIT=2, OPERATION=3, LEFT_PAREN=4, RIGHT_PAREN=5, WS=6;
	public static final int
		RULE_line = 0, RULE_legal_expr = 1, RULE_legal_paren = 2;
	public static final String[] ruleNames = {
		"line", "legal_expr", "legal_paren"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "CHAR", "DIGIT", "OPERATION", "LEFT_PAREN", "RIGHT_PAREN", "WS"
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
	public String getGrammarFileName() { return "Expression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    // This method makes the lexer or parser stop running if it encounters
	    // invalid input and throw a RuntimeException.
	    public void reportErrorsAsExceptions() {
	        removeErrorListeners();
	        addErrorListener(new ExceptionThrowingErrorListener());
	    }
	    
	    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
	        @Override
	        public void syntaxError(Recognizer<?, ?> recognizer,
	                Object offendingSymbol, int line, int charPositionInLine,
	                String msg, RecognitionException e) {
	            throw new RuntimeException(msg);
	        }
	    }

	public ExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class LineContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ExpressionParser.EOF, 0); }
		public List<Legal_exprContext> legal_expr() {
			return getRuleContexts(Legal_exprContext.class);
		}
		public Legal_exprContext legal_expr(int i) {
			return getRuleContext(Legal_exprContext.class,i);
		}
		public List<Legal_parenContext> legal_paren() {
			return getRuleContexts(Legal_parenContext.class);
		}
		public Legal_parenContext legal_paren(int i) {
			return getRuleContext(Legal_parenContext.class,i);
		}
		public List<TerminalNode> OPERATION() { return getTokens(ExpressionParser.OPERATION); }
		public TerminalNode OPERATION(int i) {
			return getToken(ExpressionParser.OPERATION, i);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitLine(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_line);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			switch (_input.LA(1)) {
			case CHAR:
			case DIGIT:
				{
				setState(6);
				legal_expr();
				}
				break;
			case LEFT_PAREN:
				{
				setState(7);
				legal_paren();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(17);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPERATION) {
				{
				{
				setState(10);
				match(OPERATION);
				setState(13);
				switch (_input.LA(1)) {
				case CHAR:
				case DIGIT:
					{
					setState(11);
					legal_expr();
					}
					break;
				case LEFT_PAREN:
					{
					setState(12);
					legal_paren();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(19);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(20);
			match(EOF);
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

	public static class Legal_exprContext extends ParserRuleContext {
		public TerminalNode CHAR() { return getToken(ExpressionParser.CHAR, 0); }
		public TerminalNode DIGIT() { return getToken(ExpressionParser.DIGIT, 0); }
		public List<TerminalNode> OPERATION() { return getTokens(ExpressionParser.OPERATION); }
		public TerminalNode OPERATION(int i) {
			return getToken(ExpressionParser.OPERATION, i);
		}
		public List<Legal_exprContext> legal_expr() {
			return getRuleContexts(Legal_exprContext.class);
		}
		public Legal_exprContext legal_expr(int i) {
			return getRuleContext(Legal_exprContext.class,i);
		}
		public List<Legal_parenContext> legal_paren() {
			return getRuleContexts(Legal_parenContext.class);
		}
		public Legal_parenContext legal_paren(int i) {
			return getRuleContext(Legal_parenContext.class,i);
		}
		public Legal_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_legal_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterLegal_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitLegal_expr(this);
		}
	}

	public final Legal_exprContext legal_expr() throws RecognitionException {
		Legal_exprContext _localctx = new Legal_exprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_legal_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			_la = _input.LA(1);
			if ( !(_la==CHAR || _la==DIGIT) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(30);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(23);
					match(OPERATION);
					setState(26);
					switch (_input.LA(1)) {
					case CHAR:
					case DIGIT:
						{
						setState(24);
						legal_expr();
						}
						break;
					case LEFT_PAREN:
						{
						setState(25);
						legal_paren();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					} 
				}
				setState(32);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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

	public static class Legal_parenContext extends ParserRuleContext {
		public TerminalNode LEFT_PAREN() { return getToken(ExpressionParser.LEFT_PAREN, 0); }
		public TerminalNode RIGHT_PAREN() { return getToken(ExpressionParser.RIGHT_PAREN, 0); }
		public Legal_exprContext legal_expr() {
			return getRuleContext(Legal_exprContext.class,0);
		}
		public Legal_parenContext legal_paren() {
			return getRuleContext(Legal_parenContext.class,0);
		}
		public Legal_parenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_legal_paren; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterLegal_paren(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitLegal_paren(this);
		}
	}

	public final Legal_parenContext legal_paren() throws RecognitionException {
		Legal_parenContext _localctx = new Legal_parenContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_legal_paren);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(LEFT_PAREN);
			setState(36);
			switch (_input.LA(1)) {
			case CHAR:
			case DIGIT:
				{
				setState(34);
				legal_expr();
				}
				break;
			case LEFT_PAREN:
				{
				setState(35);
				legal_paren();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(38);
			match(RIGHT_PAREN);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\b+\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\3\2\5\2\13\n\2\3\2\3\2\3\2\5\2\20\n\2\7\2\22\n\2\f\2\16"+
		"\2\25\13\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3\35\n\3\7\3\37\n\3\f\3\16\3\"\13"+
		"\3\3\4\3\4\3\4\5\4\'\n\4\3\4\3\4\3\4\2\2\5\2\4\6\2\3\3\2\3\4-\2\n\3\2"+
		"\2\2\4\30\3\2\2\2\6#\3\2\2\2\b\13\5\4\3\2\t\13\5\6\4\2\n\b\3\2\2\2\n\t"+
		"\3\2\2\2\13\23\3\2\2\2\f\17\7\5\2\2\r\20\5\4\3\2\16\20\5\6\4\2\17\r\3"+
		"\2\2\2\17\16\3\2\2\2\20\22\3\2\2\2\21\f\3\2\2\2\22\25\3\2\2\2\23\21\3"+
		"\2\2\2\23\24\3\2\2\2\24\26\3\2\2\2\25\23\3\2\2\2\26\27\7\2\2\3\27\3\3"+
		"\2\2\2\30 \t\2\2\2\31\34\7\5\2\2\32\35\5\4\3\2\33\35\5\6\4\2\34\32\3\2"+
		"\2\2\34\33\3\2\2\2\35\37\3\2\2\2\36\31\3\2\2\2\37\"\3\2\2\2 \36\3\2\2"+
		"\2 !\3\2\2\2!\5\3\2\2\2\" \3\2\2\2#&\7\6\2\2$\'\5\4\3\2%\'\5\6\4\2&$\3"+
		"\2\2\2&%\3\2\2\2\'(\3\2\2\2()\7\7\2\2)\7\3\2\2\2\b\n\17\23\34 &";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}