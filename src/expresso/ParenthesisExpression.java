package expresso;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import expresso.parser.WarmupLexer;
import expresso.parser.WarmupParser;

public class ParenthesisExpression {
    
    public static void main(String[] args) {
       
    CharStream stream = new ANTLRInputStream("()()((()()))()()");
    WarmupLexer lexer = new WarmupLexer(stream);
    TokenStream tokens = new CommonTokenStream(lexer);
    
    WarmupParser parser = new WarmupParser(tokens);
    
    ParseTree tree = parser.line();
    System.err.println(tree.toStringTree(parser));
    ((RuleContext)tree).inspect(parser);
    
    }
    
}