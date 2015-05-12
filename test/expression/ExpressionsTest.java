package expression;

import static org.junit.Assert.*;

import org.junit.Test;

import expresso.Expressions;

/*
 * differentiate
 * Partitions:
 * differentiate w.r.t.: same variable, different variable
 * expression contains: constant, one variable, multiple variables
 * chain rule
 * add rule
 * chain rule + add rule
 * 
 * simplify
 * Partitions:
 * expression contains: no additions, no multiplications, addition, multiplication, multiplication of 2 additions
 * expression to differentiate and then simplify contains: constants, variables
 * equal terms: placed next to each other, away from each other
 * ordering of terms
 */
public class ExpressionsTest {
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    /**
     * expression contains only constant
     */
    @Test
    public void testDifferentiateOnlyConstant() {
        String e = "2";
        String diffe = Expressions.differentiate(e, "x");
        String expected = "0";
        
        assertEquals(expected, diffe);
    }
    
    /**
     * expression in one variable
     * differentiated w.r.t. same variable
     */
    @Test
    public void testDifferentiateOneVariableExpression() {
        String e = "x";
        String diffe = Expressions.differentiate(e, "x");
        String expected = "1";
        
        assertEquals(expected, diffe);
    }
    
    /**
     * expression in multiple variables
     * one term differentiated w.r.t. different variable
     * add rule
     */
    @Test
    public void testDifferentiateMultiVariableExpression() {
        String e = "x+y";
        String diffe = Expressions.differentiate(e, "x");
        String expected = "1+0";
        
        assertEquals(expected, diffe);
    }
    
    /**
     * chain rule
     */
    @Test
    public void testDifferentiateChainRule() {
        String e = "x*u";
        String diffe = Expressions.differentiate(e, "x");
        String expected = "(1)*(u)+(x)*(0)";
        
        assertEquals(expected, diffe);
    }
    
    /**
     * chain rule + add rule
     */
    @Test
    public void testDifferentiateChainRuleAndAddRule() {
        String e = "(x+2)*u+(y+2)*x";
        String diffe = Expressions.differentiate(e, "x");
        String expected = "(1+0)*(u)+(x+2)*(0)+(0+0)*(x)+(y+2)*(1)";
        
        assertEquals(expected, diffe);
    }
    
    /**
     * no operations
     */
    @Test
    public void testSimplifyNoOperations() {
        String e = "x";
        String simplifiede = Expressions.simplify(e);
        
        String expected = "x";
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * only constants in expression
     * equal terms placed next to each other
     */
    @Test
    public void testSimplifyAdditionOfConstants() {
        String e = "1+2+3+4+5+6+7+8+9+10";
        String simplifiede = Expressions.simplify(e);
        
        String expected = "55";
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * constants and same variable in expression
     * equal terms placed away to each other
     */
    @Test
    public void testSimplifyAdditionSameVariable() {
        String e = "x+1+2+x+x";
        String simplifiede = Expressions.simplify(e);
        
        String expected = "3*x+3";
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * only multiplication
     */
    @Test
    public void testSimplifyOnlyMultiplication() {
        String e = "3*3*x*4*x*3";
        String simplifiede = Expressions.simplify(e);
        
        String expected = "108*x*x";
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * multiplications within addition
     */
    @Test
    public void testSimplifyMultiplicationWithinAddition() {
        String e = "x*x*3+x*x+x+5*x*x";
        String simplifiede = Expressions.simplify(e);
        
        String expected = "9*x*x+x";
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * multiplications within addition
     */
    @Test
    public void testSimplifyAdditionWithinMultiplication() {
        String e = "(x+2)*(x+3)";
        String simplifiede = Expressions.simplify(e);
        
        String expected = "x*x+5*x+6";
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * equal variables in multiplication far apart from each other
     */
    @Test
    public void testSimplifyMultiplicationEqualVariablesApart() {
        String e = "6+u*x*x*x*x*y*x*x*x*x*x*x*x*x*x*x+x*x*x*x*y*x*x*x*x*x*x*u*x*x*x*x+2";
        String simplifiede = Expressions.simplify(e);
        
        String expected = "2*u*x*x*x*x*x*x*x*x*x*x*x*x*x*x*y+8";
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * expression containing multiple adds and multiplies
     */
    @Test
    public void testSimplifyComplexExpression() {
        String e = "((x+2)*(x+3)+2+x+x+2+x*y)*z+y+4";
        String simplifiede = Expressions.simplify(e);
        
        String expected = "x*x*z+x*y*z+7*x*z+10*z+y+4";
        
        assertEquals(expected, simplifiede);
    }
    

    /**
     * differentiate w.r.t. different variable and simplify
     */
    @Test
    public void testDifferentiateConstantAndSimplify() {
        String e = "4+x";
        String simplifiede = Expressions.simplify(Expressions.differentiate(e, "y"));
        
        String expected = "0";
        
        assertEquals(expected, simplifiede);
    }
    
    /**
     * differentiate complex expression and simplify
     */
    @Test
    public void testDifferentiateComlexExpressionAndSimplify() {
        String e = "(x+2*x*x)*(y+2)+x*z+4+z*(x+2)*(x+3)";
        String simplifiede = Expressions.simplify(Expressions.differentiate(e, "x"));
        
        String expected = "y+4*x*y+2*x*z+6*z+8*x+2";
        
        assertEquals(expected, simplifiede);
    }
}
