package expression;

import static org.junit.Assert.*;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import expresso.Add;
import expresso.Constant;
import expresso.Expression;
import expresso.ExpressionsTreeListener;
import expresso.Multiply;
import expresso.Variable;
import expresso.parser.ExpressionLexer;
import expresso.parser.ExpressionParser;

/**
 * Tests for the expression parser
 * partitions:
 * single constant int, decimal, single variable,
 * add instance, multiply instance, 
 * add within multiply, multiply within add
 * nested parenthesis
 * invalid inputs
 */
public class ExpressionTest {

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testParseSingleVar(){
        CharStream stream = new ANTLRInputStream("x");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        
        ParseTree tree = parser.line();
        System.err.println(tree.toStringTree(parser)); //throw error somehow
    
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionsTreeListener treeWalker = new ExpressionsTreeListener();
        walker.walk(treeWalker, tree);
        
        Expression expected = new Variable("x");
        
        assertEquals(expected, treeWalker.exp);

    }
    @Test
    public void testParseSingleConstantInt(){
        CharStream stream = new ANTLRInputStream("3");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        
        ParseTree tree = parser.line();
        System.err.println(tree.toStringTree(parser)); //throw error somehow
    
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionsTreeListener treeWalker = new ExpressionsTreeListener();
        walker.walk(treeWalker, tree);
        
        Expression expected = new Constant(3);
        
        assertEquals(expected, treeWalker.exp);

    }
    
    @Test
    public void testParseSingleConstantDecimal(){
        CharStream stream = new ANTLRInputStream(".3");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        
        ParseTree tree = parser.line();
        System.err.println(tree.toStringTree(parser)); //throw error somehow
    
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionsTreeListener treeWalker = new ExpressionsTreeListener();
        walker.walk(treeWalker, tree);
        
        Expression expected = new Constant(.3);
        
        assertEquals(expected, treeWalker.exp);

    }
    
    @Test
    public void testParseAdd(){
        CharStream stream = new ANTLRInputStream("x+3");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        
        ParseTree tree = parser.line();
        System.err.println(tree.toStringTree(parser)); //throw error somehow
    
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionsTreeListener treeWalker = new ExpressionsTreeListener();
        walker.walk(treeWalker, tree);
        
        Expression expected = new Add(new Constant(3), new Variable("x"));
        
        assertEquals(expected, treeWalker.exp);

    }
    
    @Test
    public void testParseMultiply(){
        CharStream stream = new ANTLRInputStream("x*2");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        
        ParseTree tree = parser.line();
        System.err.println(tree.toStringTree(parser)); //throw error somehow
    
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionsTreeListener treeWalker = new ExpressionsTreeListener();
        walker.walk(treeWalker, tree);
        
        Expression expected = new Multiply(new Constant(2), new Variable("x"));
        
        assertEquals(expected, treeWalker.exp);

    }
    
    @Test
    public void testParseMultiplyinAdd(){
        CharStream stream = new ANTLRInputStream("x+3*y");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        
        ParseTree tree = parser.line();
        System.err.println(tree.toStringTree(parser)); //throw error somehow
    
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionsTreeListener treeWalker = new ExpressionsTreeListener();
        walker.walk(treeWalker, tree);
        
        Expression expected = new Add(new Multiply(new Variable("y"),new Constant(3)),new Variable("x"));
        
        assertEquals(expected, treeWalker.exp);

    }
    @Test
    public void testParseAddinMultiply(){
        CharStream stream = new ANTLRInputStream("(x+3)*y");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        
        ParseTree tree = parser.line();
        System.err.println(tree.toStringTree(parser)); //throw error somehow
    
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionsTreeListener treeWalker = new ExpressionsTreeListener();
        walker.walk(treeWalker, tree);
        
        Expression expected = new Multiply( new Variable("y"), new Add(new Constant(3), new Variable("x")));
        
        assertEquals(expected, treeWalker.exp);

    }
    
    @Test
    public void testParseNestedParenthesis(){
        CharStream stream = new ANTLRInputStream("(x+3)*((y+2)*z)");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        
        ParseTree tree = parser.line();
        System.err.println(tree.toStringTree(parser)); //throw error somehow
    
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionsTreeListener treeWalker = new ExpressionsTreeListener();
        walker.walk(treeWalker, tree);
        
        Expression expected = new Multiply(new Multiply(new Variable("z"), new Add(new Constant(2), new Variable("y"))), new Add(new Constant(3),
                new Variable("x")));
        
        assertEquals(expected, treeWalker.exp);

    }
}
