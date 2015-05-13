package expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import expresso.Constant;

public class ConstantTest {
    //Test constructor method
    @Test
    public void oneConstant(){
        Constant c = new Constant(100.01);
        assertEquals("100.01", c.toString());
    }
    
    @Test
    public void testEquals(){
        Constant c = new Constant(100.01);
        Constant d = new Constant(100.01);
        assertTrue(c.equals(d));
    }
    @Test
    public void notEquals(){
        Constant c = new Constant(100.01);
        Constant d = new Constant(30.1);
        assertFalse(c.equals(d));
    }
}
