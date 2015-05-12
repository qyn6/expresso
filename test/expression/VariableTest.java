package expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import expresso.Variable;


/**
 * Tests for the variable class
 * Partitions:
 * variable of length 1, >1
 * tests equality for variables, equal if strings are equal
 *
 */
public class VariableTest {
    

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void oneVar(){
        Variable var = new Variable("x");
        assertEquals("x", var.toString());
    }
    
    @Test 
    public void multipleLengthVars(){
        Variable var = new Variable("xyz");
        assertEquals("xyz", var.toString());
        
    }
    @Test
    public void testEquals(){
        Variable var1 = new Variable("abc");
        Variable var2 = new Variable("abc");
        assertTrue(var1.equals(var2));
    }
    @Test
    public void notEquals(){
        Variable var1 = new Variable("abc");
        Variable var2 = new Variable("cba");
        assertFalse(var1.equals(var2));
    }

}
