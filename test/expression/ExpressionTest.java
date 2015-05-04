package expression;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Test;



import expresso.parser.WarmupBaseListener;
import expresso.parser.WarmupLexer;
import expresso.parser.WarmupListener;
import expresso.parser.WarmupParser;

/**
 * tests printing expression tree
 * @category no_didit
 */
public class ExpressionTest {
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testExpression() {
        CharStream stream = new ANTLRInputStream("((())()(())())()()()()");
        WarmupLexer lexer = new WarmupLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        
        WarmupParser parser = new WarmupParser(tokens);
        
        ParseTree tree = parser.line();
        System.err.println(tree.toStringTree(parser));
        ((RuleContext)tree).inspect(parser);
        
        ParseTreeWalker walker = new ParseTreeWalker();
        WarmupListener listener = new WarmupListenerPrintEverything();
        walker.walk(listener, tree);
        
    }
    
    public static class WarmupListenerPrintEverything extends WarmupBaseListener {
        @Override public void enterLine(WarmupParser.LineContext ctx) { 
            System.out.println("enter line");
        }
        
        @Override public void exitLine(WarmupParser.LineContext ctx) { 
            System.out.println("exit line");
        }
        
        @Override public void enterLegal_expr(WarmupParser.Legal_exprContext ctx) { 
            System.out.println("enter legal expr");
        }
        
        @Override public void exitLegal_expr(WarmupParser.Legal_exprContext ctx) { 
            System.out.println("exit legal expr");
        }

        @Override public void enterEveryRule(ParserRuleContext ctx) { }
        
        @Override public void exitEveryRule(ParserRuleContext ctx) { }
        
        @Override public void visitTerminal(TerminalNode node) { }
        
        @Override public void visitErrorNode(ErrorNode node) { }
    }
}
