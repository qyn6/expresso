package expression;

import static org.junit.Assert.*;

import org.junit.Test;

import expresso.Add;
import expresso.Expression;

public class AddTest {
    
    @Test
    public void testAddConstants(){
        Expression e1 = Expression.parse("3");
        Expression e2 = Expression.parse("5");
        Expression add = new Add(e1, e2);
        
        assertEquals("5+3", add.toString());
    }
    
    @Test
    public void testAddDoubles(){
        Expression e1 = Expression.parse("3.3");
        Expression e2 = Expression.parse("55.5");
        Expression add = new Add(e1, e2);
        
        assertEquals("55.5+3.3", add.toString());
    }
    
    //If the order is different, the add terms are not the same
    @Test
    public void testAddEquals(){
        Expression e1 = Expression.parse("3.3");
        Expression e2 = Expression.parse("55.5");
        Expression e3 = Expression.parse("70");
        Expression add = new Add(e1, e2);
        Expression add1 = new Add(e1, e2);
        Expression add2 = new Add(e2, e1);
        Expression add3 = new Add(e3, e1);
        
        assertEquals(add, add1);
        assertFalse(add.equals(add2));
        assertFalse(add3.equals(add));
        
    }
    
    @Test
    public void testAddHashMethod(){
        Expression e1 = Expression.parse("3.3");
        Expression e2 = Expression.parse("55.5");
        Expression e3 = Expression.parse("70");
        Expression add = new Add(e1, e2);
        Expression add1 = new Add(e1, e2);
        Expression add2 = new Add(e3, e1);
        Expression add3 = new Add(e3, e1);
        
        assertEquals(add.hashCode(), add1.hashCode());
        assertEquals(add2.hashCode(), add3.hashCode());
        
    }
}
