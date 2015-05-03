package expresso;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import expresso.parser.ExpressionLexer;
import expresso.parser.ExpressionParser;

public class ParenthesisExpression {
    
    public static void main(String[] args) {
       
    CharStream stream = new ANTLRInputStream("(3 + x)*x*y*(3 + x + 3)");
    ExpressionLexer lexer = new ExpressionLexer(stream);
    TokenStream tokens = new CommonTokenStream(lexer);
    
    ExpressionParser parser = new ExpressionParser(tokens);
    
    ParseTree tree = parser.line();
    System.err.println(tree.toStringTree(parser));
    ((RuleContext)tree).inspect(parser);
    
    }
    
}
