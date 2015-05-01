package expresso.parser;

import java.util.concurrent.Future;

import javax.swing.JDialog;

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

public class ExpressionTest {
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testExpression() {
        CharStream stream = new ANTLRInputStream("((())()(())())()()()()");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        
        ExpressionParser parser = new ExpressionParser(tokens);
        
        ParseTree tree = parser.line();
        System.err.println(tree.toStringTree(parser));
        ((RuleContext)tree).inspect(parser);
        
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionListener listener = new ExpressionListenerPrintEverything();
        walker.walk(listener, tree);
        
    }
    
    private static class ExpressionListenerPrintEverything extends ExpressionBaseListener {
        @Override public void enterLine(ExpressionParser.LineContext ctx) { 
            System.out.println("enter line");
        }
        
        @Override public void exitLine(ExpressionParser.LineContext ctx) { 
            System.out.println("exit line");
        }
        
        @Override public void enterLegal_expr(ExpressionParser.Legal_exprContext ctx) { 
            System.out.println("enter legal expr");
        }
        
        @Override public void exitLegal_expr(ExpressionParser.Legal_exprContext ctx) { 
            System.out.println("exit legal expr");
        }

        @Override public void enterEveryRule(ParserRuleContext ctx) { }
        
        @Override public void exitEveryRule(ParserRuleContext ctx) { }
        
        @Override public void visitTerminal(TerminalNode node) { }
        
        @Override public void visitErrorNode(ErrorNode node) { }
    }
}
