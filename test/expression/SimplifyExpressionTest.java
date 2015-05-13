package expression;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import expresso.Add;
import expresso.Constant;
import expresso.Expression;
import expresso.Multiply;
import expresso.SimplifyExpression;
import expresso.Term;
import expresso.Variable;

/**
 * Partitions:
 * simplifyExpression:
 * terms has length 1, >1
 * term has all different powers, two terms have same highest power
 * term has different variables with different powers, ex x*x*x + y*y + x
 * constant zero, not zero
 * add()
 * terms being added have same variables, don't have same variables
 * consecutive terms have same variables, non consecutive terms have same variables
 * terms only have constants
 * 
 * multiply()
 * both terms have length 1, >1; one term has length 1, one >1
 * terms only have constants
 * one list being multiplied has only constants, other list has variables
 */
public class SimplifyExpressionTest {
    
    private static List<String>  v1;
    private static List<String>  x;
    private static List<String>  xy;
    private static List<String>  y;
    private static List<String>  xxy;
    private static List<String>  xx;
    private static List<String>  xxx;
    private static List<String>  yyx;

    
    @BeforeClass
    public static void setUpBeforeClass() {

        v1 = new ArrayList<String>(Arrays.asList(""));
        x = new ArrayList<String>(Arrays.asList("x"));
        xxy = new ArrayList<String>(Arrays.asList("x","x","y"));
        xxx = new ArrayList<String>(Arrays.asList("x","x","x"));
        xx = new ArrayList<String>(Arrays.asList("x","x"));
        yyx = new ArrayList<String>(Arrays.asList("y", "y", "x"));
        xy = new ArrayList<String>(Arrays.asList("x", "y"));
        y = new ArrayList<String>(Arrays.asList("y"));

    }

    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void simplifyOneTerm(){
        List<Term> terms = new ArrayList<>();
        
        terms.add(new Term(2.0, x));
        SimplifyExpression simpexp = new SimplifyExpression(terms);
        
        Expression simplified = simpexp.simplifyExpression();
        Expression exp = new Multiply( new Variable("x"), new Constant(2.0));
        assertEquals(exp, simplified);
        assertEquals("(2)*(x)", simplified.toString());

    }
    
    @Test
    public void simplifyMultipleTerms(){
        List<Term> terms = new ArrayList<>();
        
        terms.add(new Term(2.0, x));
        terms.add(new Term(4.0, y));
        SimplifyExpression simpexp = new SimplifyExpression(terms);
        
        Expression simplified = simpexp.simplifyExpression();
        Expression exp = new Add(new Multiply( new Variable("x"), new Constant(2.0)),new Multiply(new Variable("y"), new Constant(4.0)));
        assertEquals(exp, simplified);
        assertEquals("(4)*(y)+(2)*(x)", simplified.toString());

    }
    
    @Test
    public void simplifySamePowers(){
     List<Term> terms = new ArrayList<>();
        
        terms.add(new Term(2.0, xxy));
        terms.add(new Term(4.0, yyx));
        SimplifyExpression simpexp = new SimplifyExpression(terms);
        
        Expression simplified = simpexp.simplifyExpression();

        assertEquals("(4)*((x)*((y)*(y)))+(2)*((x)*((x)*(y)))", simplified.toString());

    }
    
    @Test
    public void simplifyDifferentPowers(){
        List<Term> terms = new ArrayList<>();
        terms.add(new Term(1.0, x));
        terms.add(new Term(4.0, xx));
        terms.add(new Term(3.0, xxx));

        SimplifyExpression simpexp = new SimplifyExpression(terms);
        
        Expression simplified = simpexp.simplifyExpression();

        assertEquals("(3)*((x)*((x)*(x)))+(4)*((x)*(x))+x", simplified.toString());

    }
    
    @Test
    public void simplifyMoreThanOneVariable(){
        List<Term> terms = new ArrayList<>();
        terms.add(new Term(1.0, yyx));
        terms.add(new Term(4.0, x));
        terms.add(new Term(3.5, xxx));

        SimplifyExpression simpexp = new SimplifyExpression(terms);
        
        Expression simplified = simpexp.simplifyExpression();

        assertEquals("3.5*x*x*x+x*y*y+4*x", simplified.toString().replaceAll("\\(|\\)", ""));

    }
    
    @Test
    public void simplifyZero(){
        List<Term> terms = new ArrayList<>();
        List<Term> terms2 = new ArrayList<>();
        
        terms.add(new Term(0.0, yyx));
        terms2.add(new Term(0.0, x));
        terms2.add(new Term(0.0, xxx));

        SimplifyExpression simpexp = new SimplifyExpression(terms);
        
        Expression simplified = simpexp.simplifyExpression();

        assertEquals("0", simplified.toString());
    }
    @Test
    public void addOnlyConstants(){
        List<Term> terms = new ArrayList<>();
        List<Term> terms2 = new ArrayList<>();
        
        terms.add(new Term(2.0, v1));
        terms2.add(new Term(4.0, v1));
        SimplifyExpression simpexp = new SimplifyExpression(terms);
        SimplifyExpression simpexp2 = new SimplifyExpression(terms2);
        
        List<Term> addTerms = new ArrayList<>();
        addTerms = simpexp.add(simpexp2);
        
        List<Term> answer = new ArrayList<>(Arrays.asList(new Term(6.0, v1)));
        assertEquals(answer, addTerms);
        
    }
    
    @Test
    public void addSameVars(){
        List<Term> terms = new ArrayList<>();
        List<Term> terms2 = new ArrayList<>();
        
        terms.add(new Term(2.0, x));
        terms2.add(new Term(4.0, x));
        SimplifyExpression simpexp = new SimplifyExpression(terms);
        SimplifyExpression simpexp2 = new SimplifyExpression(terms2);
        
        List<Term> addTerms = new ArrayList<>();
        addTerms = simpexp.add(simpexp2);
        
        List<Term> answer = new ArrayList<>(Arrays.asList(new Term(6.0, x)));
        assertEquals(answer, addTerms);
        
    }
    
    @Test
    public void addDifferentVars(){
        List<Term> terms = new ArrayList<>();
        List<Term> terms2 = new ArrayList<>();
        
        terms.add(new Term(2.0, x));
        terms2.add(new Term(4.0, y));
        SimplifyExpression simpexp = new SimplifyExpression(terms);
        SimplifyExpression simpexp2 = new SimplifyExpression(terms2);
        
        List<Term> addTerms = new ArrayList<>();
        addTerms = simpexp.add(simpexp2);
        
        List<Term> answer = new ArrayList<>(Arrays.asList(new Term(2.0, x), new Term(4.0, y)));
        assertEquals(answer, addTerms);
    }
    
    @Test
    public void addConsecutiveTerms(){
        List<Term> terms = new ArrayList<>();
        List<Term> terms2 = new ArrayList<>();
        
        terms.add(new Term(2.0, x));
        terms.add(new Term(3.0, x));
        terms2.add(new Term(4.0, y));
        SimplifyExpression simpexp = new SimplifyExpression(terms);
        SimplifyExpression simpexp2 = new SimplifyExpression(terms2);
        
        List<Term> addTerms = new ArrayList<>();
        addTerms = simpexp.add(simpexp2);
        
        List<Term> answer = new ArrayList<>(Arrays.asList(new Term(5.0, x), new Term(4.0, y)));
        assertEquals(answer, addTerms);
    }
    
    @Test
    public void addNonConsecutiveTerms(){
        List<Term> terms = new ArrayList<>();
        List<Term> terms2 = new ArrayList<>();
        
        terms.add(new Term(2.0, x));
        terms.add(new Term(4.0, y));
        terms2.add(new Term(3.0, x));
        terms2.add(new Term(5.4, y));
        SimplifyExpression simpexp = new SimplifyExpression(terms);
        SimplifyExpression simpexp2 = new SimplifyExpression(terms2);
        
        List<Term> addTerms = new ArrayList<>();
        addTerms = simpexp.add(simpexp2);
        
        List<Term> answer = new ArrayList<>(Arrays.asList(new Term(5.0, x), new Term(9.4, y)));
        assertEquals(answer, addTerms);
    }
    
    
    @Test
    public void multiplyOnlyConstants(){
        List<Term> terms = new ArrayList<>();
        terms.add(new Term(2.0, v1));
        List<Term> terms2 = new ArrayList<>();
        terms2.add(new Term(5.0, v1));

        SimplifyExpression simpexp = new SimplifyExpression(terms);
        SimplifyExpression simpexp2 = new SimplifyExpression(terms2);

        List<Term> addTerms = new ArrayList<>();
        addTerms = simpexp.multiply(simpexp2);
        
        List<Term> answer = new ArrayList<>(Arrays.asList(new Term(10.0,v1)));
        assertEquals(answer, addTerms);
    }
    
    @Test
    public void multiplyConstantandSameVars(){
        List<Term> terms = new ArrayList<>();
        terms.add(new Term(2.0, x));
        List<Term> terms2 = new ArrayList<>();
        terms2.add(new Term(5.0, x));

        SimplifyExpression simpexp = new SimplifyExpression(terms);
        SimplifyExpression simpexp2 = new SimplifyExpression(terms2);

        List<Term> addTerms = new ArrayList<>();
        addTerms = simpexp.multiply(simpexp2);
        
        List<Term> answer = new ArrayList<>(Arrays.asList(new Term(10.0,xx)));
        assertEquals(answer, addTerms);
    }
    
    @Test
    public void multiplyConstantandDifferentVars(){
        List<Term> terms = new ArrayList<>();
        terms.add(new Term(2.0, x));
        List<Term> terms2 = new ArrayList<>();
        terms2.add(new Term(5.0, y));

        SimplifyExpression simpexp = new SimplifyExpression(terms);
        SimplifyExpression simpexp2 = new SimplifyExpression(terms2);

        List<Term> addTerms = new ArrayList<>();
        addTerms = simpexp.multiply(simpexp2);
        
        List<Term> answer = new ArrayList<>(Arrays.asList(new Term(10.0,xy)));
        assertEquals(answer, addTerms);
    }
    
    @Test
    public void multiplyOneConstantOneVar(){
        List<Term> terms = new ArrayList<>();
        terms.add(new Term(2.0, v1));
        List<Term> terms2 = new ArrayList<>();
        terms2.add(new Term(1.0, y));

        SimplifyExpression simpexp = new SimplifyExpression(terms);
        SimplifyExpression simpexp2 = new SimplifyExpression(terms2);

        List<Term> addTerms = new ArrayList<>();
        addTerms = simpexp.multiply(simpexp2);
        
        List<Term> answer = new ArrayList<>(Arrays.asList(new Term(2.0,y)));
        assertEquals(answer, addTerms);
    }
    
    @Test
    public void multiplyoneTermsLengthGreaterOne(){
        List<Term> terms = new ArrayList<>();
        terms.add(new Term(2.0, x));
        List<Term> terms2 = new ArrayList<>();
        terms2.add(new Term(5.0, y));
        terms2.add(new Term(3.0, x));

        SimplifyExpression simpexp = new SimplifyExpression(terms);
        SimplifyExpression simpexp2 = new SimplifyExpression(terms2);

        List<Term> addTerms = new ArrayList<>();
        addTerms = simpexp.multiply(simpexp2);
        
        List<Term> answer = new ArrayList<>(Arrays.asList(new Term(10.0,xy), new Term(6.0, xx)));
        assertEquals(answer, addTerms);
    }
    
    @Test
    public void multiplyoneTermsLengthGreaterOneReverse(){
        List<Term> terms = new ArrayList<>();
        List<Term> terms2 = new ArrayList<>();
        terms2.add(new Term(2.0, x));

        terms.add(new Term(5.0, y));
        terms.add(new Term(3.0, x));

        SimplifyExpression simpexp = new SimplifyExpression(terms);
        SimplifyExpression simpexp2 = new SimplifyExpression(terms2);

        List<Term> addTerms = new ArrayList<>();
        addTerms = simpexp.multiply(simpexp2);
        
        List<Term> answer = new ArrayList<>(Arrays.asList(new Term(10.0,xy), new Term(6.0, xx)));
        assertEquals(answer, addTerms);
    }
    
    /**
     * tests (x+2)*(x+3)
     */
    @Test
    public void mulitplyFoilOneVar(){
        List<Term> terms = new ArrayList<>();
        List<Term> terms2 = new ArrayList<>();
        terms2.add(new Term(1.0, x));

        terms2.add(new Term(3.0, v1));
        terms.add(new Term(1.0, x));

        terms.add(new Term(2.0, v1));
        SimplifyExpression simpexp = new SimplifyExpression(terms);
        SimplifyExpression simpexp2 = new SimplifyExpression(terms2);

        List<Term> addTerms = new ArrayList<>();
        addTerms = simpexp.multiply(simpexp2);
        
        List<Term> answer = new ArrayList<>(Arrays.asList(new Term(5.0,x), new Term(1.0, xx), new Term(6.0, v1)));
        assertEquals(answer, addTerms);
    }
    
    /**
     * tests (x+2)*(y+3)
     */
    @Test
    public void mulitplyFoilTwoVar(){
        List<Term> terms = new ArrayList<>();
        List<Term> terms2 = new ArrayList<>();
        terms2.add(new Term(1.0, x));

        terms2.add(new Term(3.0, v1));
        terms.add(new Term(1.0, y));

        terms.add(new Term(2.0, v1));
        SimplifyExpression simpexp = new SimplifyExpression(terms);
        SimplifyExpression simpexp2 = new SimplifyExpression(terms2);

        List<Term> addTerms = new ArrayList<>();
        addTerms = simpexp.multiply(simpexp2);
        
        List<Term> answer = new ArrayList<>(Arrays.asList(new Term(1.0,xy), new Term(3.0, y), new Term(2.0, x), new Term(6.0, v1)));
        assertEquals(answer, addTerms);
    }
}
