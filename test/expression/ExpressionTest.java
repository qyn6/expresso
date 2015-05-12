package expression;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import expresso.Add;
import expresso.Constant;
import expresso.Expression;
import expresso.ExpressionsTreeListener;
import expresso.Multiply;
import expresso.Term;
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
    
    /**
     * expression contains only constant
     */
    @Test
    public void testDifferentiateOnlyConstant() {
        Expression e = Expression.parse("2");
        Expression diffe = e.differentiate("x");
        Expression expected = new Constant(0);
        
        assertEquals(expected, diffe);
    }
    
    /**
     * expression in one variable
     * differentiated w.r.t. same variable
     */
    @Test
    public void testDifferentiateOneVariableExpression() {
        Expression e = Expression.parse("x");
        Expression diffe = e.differentiate("x");
        Expression expected = new Constant(1);
        
        assertEquals(expected, diffe);
    }
    
    /**
     * expression in multiple variables
     * one term differentiated w.r.t. different variable
     * add rule
     */
    @Test
    public void testDifferentiateMultiVariableExpression() {
        Expression e = Expression.parse("x+y");
        Expression diffe = e.differentiate("x");
        Expression expected = new Add(new Constant(0), new Constant(1));
        
        assertEquals(expected, diffe);
    }
    
    /**
     * chain rule
     */
    @Test
    public void testDifferentiateChainRule() {
        Expression e = Expression.parse("x*u");
        Expression diffe = e.differentiate("x");
        Expression expected = new Add(new Multiply(new Constant(0), new Variable("x")), new Multiply(new Variable("u"), new Constant(1)));
        
        assertEquals(expected, diffe);
    }
    
    /**
     * no operations
     */
    @Test
    public void testSimplifyNoOperations() {
        Expression e = Expression.parse("x");
        List<Term> simplifiede = e.simplify();
        
        List<Term> expected = new ArrayList<>();
        expected.add(new Term(1.0,new ArrayList<>(Arrays.asList("x"))));
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * only constants in expression
     * equal terms placed next to each other
     */
    @Test
    public void testSimplifyAdditionOfConstants() {
        Expression e = Expression.parse("1+2+3+4+5+6+7+8+9+10");
        List<Term> simplifiede = e.simplify();
        
        List<Term> expected = new ArrayList<>();
        expected.add(new Term(55.0,new ArrayList<>(Arrays.asList(""))));
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * constants and same variable in expression
     * equal terms placed away to each other
     */
    @Test
    public void testSimplifyAdditionSameVariable() {
        Expression e = Expression.parse("x+1+2+x+x");
        List<Term> simplifiede = e.simplify();
        
        List<Term> expected = new ArrayList<>();
        expected.add(new Term(3.0,new ArrayList<>(Arrays.asList("x"))));
        expected.add(new Term(3.0,new ArrayList<>(Arrays.asList(""))));
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * only multiplication
     */
    @Test
    public void testSimplifyOnlyMultiplication() {
        Expression e = Expression.parse("3*3*x*4*x*3");
        List<Term> simplifiede = e.simplify();
        
        List<Term> expected = new ArrayList<>();
        expected.add(new Term(108.0,new ArrayList<>(Arrays.asList("x","x"))));
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * multiplications within addition
     */
    @Test
    public void testSimplifyMultiplicationWithinAddition() {
        Expression e = Expression.parse("x*x*3+x*x+x+5*x*x");
        List<Term> simplifiede = e.simplify();
        
        List<Term> expected = new ArrayList<>();
        expected.add(new Term(9.0,new ArrayList<>(Arrays.asList("x","x"))));
        expected.add(new Term(1.0,new ArrayList<>(Arrays.asList("x"))));
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * multiplications within addition
     */
    @Test
    public void testSimplifyAdditionWithinMultiplication() {
        Expression e = Expression.parse("(x+2)*(x+3)");
        List<Term> simplifiede = e.simplify();
        
        List<Term> expected = new ArrayList<>();
        expected.add(new Term(5.0,new ArrayList<>(Arrays.asList("x"))));
        expected.add(new Term(6.0,new ArrayList<>(Arrays.asList(""))));
        expected.add(new Term(1.0,new ArrayList<>(Arrays.asList("x","x"))));
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * equal variables in multiplication far apart from each other
     */
    @Test
    public void testSimplifyMultiplicationEqualVariablesApart() {
        Expression e = Expression.parse("6+u*x*x*y+x*y*u*x+2");
        List<Term> simplifiede = e.simplify();
        
        List<Term> expected = new ArrayList<>();
        expected.add(new Term(8.0,new ArrayList<>(Arrays.asList(""))));
        expected.add(new Term(2.0,new ArrayList<>(Arrays.asList("u","x","x","y"))));
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * expression containing multiple adds and multiplies
     */
    @Test
    public void testSimplifyComplexExpression() {
        Expression e = Expression.parse("((x+2)*(x+3)+2+x+x+2+x*y)*z+y+4");
        List<Term> simplifiede = e.simplify();
        
        List<Term> expected = new ArrayList<>();
        expected.add(new Term(4.0,new ArrayList<>(Arrays.asList(""))));
        expected.add(new Term(1.0,new ArrayList<>(Arrays.asList("y"))));
        expected.add(new Term(10.0,new ArrayList<>(Arrays.asList("z"))));
        expected.add(new Term(7.0,new ArrayList<>(Arrays.asList("x","z"))));
        expected.add(new Term(1.0,new ArrayList<>(Arrays.asList("x","y","z"))));
        expected.add(new Term(1.0,new ArrayList<>(Arrays.asList("x","x","z"))));
        
        assertEquals(expected, simplifiede);
    }
    

    /**
     * differentiate w.r.t. different variable and simplify
     */
    @Test
    public void testDifferentiateConstantAndSimplify() {
        Expression e = Expression.parse("4+x");
        Expression diffe = e.differentiate("y");
        List<Term> simplifiede = diffe.simplify();
        
        List<Term> expected = new ArrayList<>();
        expected.add(new Term(0.0,new ArrayList<>(Arrays.asList(""))));
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * differentiate complex expression and simplify
     */
    @Test
    public void testDifferentiateComplexExpressionAndSimplify() {
        Expression e = Expression.parse("(x+2*x)*(y+2)+x+(x+1)*(x+2)");
        Expression diffe = e.differentiate("y");
        List<Term> simplifiede = diffe.simplify();
        
        assertTrue(simplifiede.get(0).getConstant() == 0.0);
        assertTrue(simplifiede.get(1).getConstant() == 3.0);
        assertTrue(simplifiede.get(1).getVariables().size() == 1);
        assertEquals("x",simplifiede.get(1).getVariables().get(0));
        assertTrue(simplifiede.get(2).getConstant() == 0.0);
        assertTrue(simplifiede.get(3).getConstant() == 0.0);
    }
}
