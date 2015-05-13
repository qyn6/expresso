package expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import expresso.Term;

/**
 * Partitions:
 * term with only constant, only variable, constant and variable
 * variables length 0, 1, >1
 * constant 0,1, >1, decimal, int
 * equals: test variables in order, not in order
 *
 */
public class TermTest {
    private static double c1;
    private static double c2;
    private static double c3;

    private static List<String>  v1;
    private static List<String>  v2;
    private static List<String>  v3;

    
    @BeforeClass
    public static void setUpBeforeClass() {
        c1 = 1;
        c2 = 3.4;
        c3 = 5;
        v1 = new ArrayList<String>(Arrays.asList(""));
        v2 = new ArrayList<String>(Arrays.asList("x"));
        v3 = new ArrayList<String>(Arrays.asList("x", "y"));
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    /**
     * test variables length 0
     */
    @Test
    public void testConstant(){
        Term t = new Term(0.0, v1);
        assertEquals("0", t.toString());
    }
    /**
     * tests variable length 1
     */
    @Test
    public void testVariable(){
        Term t = new Term(5.0, v2);
        assertEquals("5*x", t.toString());
    }
    /**
     * tests variable length >1
     */
    @Test
    public void testConstantAndVariables(){
        Term t = new Term(3.4,v3);
        assertEquals("3.4*x*y", t.toString());
    }
    @Test
    public void testEquals(){
        Term t = new Term(5.0,v3);
        Term t2 = new Term(5.0, v3);
        assertEquals(t, t2);
    }
    @Test
    public void testNotEquals(){
        Term t = new Term(3.2,v2);
        Term t2 = new Term(3.2, v3);
        assertFalse(t.equals(t2));
    }
}
