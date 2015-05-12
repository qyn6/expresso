package expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.BeforeClass;
import org.junit.Test;

import expresso.Add;
import expresso.Constant;
import expresso.Multiply;
import expresso.Variable;


/**
 * Tests for the multiply class
 * Partitions:
 * multiply of two constants, two variables, variable and constant
 * multiply of adds, multiply of multiplies, multiply of nested expression
 * Equality:
 * tests equal multiply with same components, tests componenets flipped should be not equal
 */
public class MultiplyTest {
    
    private static Constant c1;
    private static Constant c2;
    private static Variable v1;
    private static Variable v2;
    private static Add add1;
    private static Add add2;
    @BeforeClass
    public static void setUpBeforeClass() {
        c1 = new Constant(3);
        c2 = new Constant(4);
        v1 = new Variable("x");
        v2 = new Variable("y");
        add1 = new Add(c1, v1);
        add2 = new Add(c2, v2);
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void twoConstants(){
        Multiply m = new Multiply(c1, c2);
        assertEquals("(4)*(3)", m.toString());
    }
    
    @Test
    public void twoVariables(){
        Multiply m = new Multiply(v1, v2);
        assertEquals("(y)*(x)", m.toString());
    }
    
    @Test
    public void mulAdds(){
        Multiply m = new Multiply(add1, add2);
        assertEquals("(y+4)*(x+3)", m.toString());
    }
    
    @Test
    public void mulMuls(){
        Multiply m1 = new Multiply(c1, v1);
        Multiply m2 = new Multiply(c2, v2);
        Multiply mul = new Multiply(m1, m2);
        
        assertEquals("((y)*(4))*((x)*(3))", mul.toString());
    }
    
    @Test
    public void mulCombined(){
        Multiply m = new Multiply(add1, new Multiply(add2, v2));
        assertEquals("((y)*(y+4))*(x+3)", m.toString());
    }
    
    @Test
    public void testEquals(){
        Multiply m1 = new Multiply(add1, v1);
        Multiply m2 = new Multiply(add1, v1);
        assertEquals(m1, m2);
    }
    
    @Test
    public void testNotEquals(){
        Multiply m1 = new Multiply(add1, v1);
        Multiply m2 = new Multiply(v1, add1);
        assertFalse(m1.equals(m2));
    }
}
