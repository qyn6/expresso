package expression;

import static org.junit.Assert.*;

import org.junit.Test;

import expresso.Add;
import expresso.Expression;
import expresso.Multiply;

//Tests for the Add.java class and its methods
public class AddTest {
    
    //Partitions for the constructor method:
    //e1 and e2 can be a Constant, Variable, Add term, or Mul term
    //The Constants can be integers or doubles
    @Test
    public void testAddConstants(){
        Expression e1 = Expression.parse("3");
        Expression e2 = Expression.parse("5");
        Expression add = new Add(e1, e2);
        assertEquals("5+3", add.toString());

        Expression e3 = Expression.parse("3.3");
        Expression e4 = Expression.parse("55.5");
        Expression add1 = new Add(e3, e4);
        assertEquals("55.5+3.3", add1.toString());
        
        Expression add2 = new Add(e1, e3);
        assertEquals("3.3+3", add2.toString());
    }
    
    //The variables are sequences of letters:
    //can be 1 letter, >1 letter
    @Test
    public void testAddVariables(){
        Expression e1 = Expression.parse("x");
        Expression e2 = Expression.parse("y");
        Expression add = new Add(e1, e2);
        assertEquals("y+x", add.toString());

        Expression e3 = Expression.parse("variable");
        Expression e4 = Expression.parse("foo");
        Expression add1 = new Add(e3, e4);
        assertEquals("foo+variable", add1.toString());
    }
    
    //Add 'add terms', which should also be type Add
    @Test
    public void testAddAddTerms(){
        Expression e1 = Expression.parse("x");
        Expression e2 = Expression.parse("y");
        Expression add = new Add(e1, e2);
        Expression e3 = Expression.parse("3.3");
        Expression e4 = Expression.parse("55.5");
        Expression add1 = new Add(e3, e4);
        
        Expression addTotal = new Add(add, add1);
        assertEquals("55.5+3.3+y+x", addTotal.toString());
        
        Expression e5 = Expression.parse("3");
        Expression e6 = Expression.parse("var");
        Expression add2 = new Add(e5, e6);
        
        Expression addTotal2 = new Add(addTotal, add2);
        assertEquals("var+3+55.5+3.3+y+x", addTotal2.toString());
    }
    
    //
    @Test
    public void testAddMulTerms(){
        Expression e1 = Expression.parse("x");
        Expression e2 = Expression.parse("y");
        Expression mul = new Multiply(e1, e2);
        Expression e3 = Expression.parse("3.3");
        Expression e4 = Expression.parse("55.5");
        Expression mul1 = new Multiply(e3, e4);
        
        Expression addTotal = new Add(mul, mul1);
        assertEquals("(55.5)*(3.3)+(y)*(x)", addTotal.toString());
        
        Expression e5 = Expression.parse("3");
        Expression e6 = Expression.parse("var");
        Expression mul2 = new Multiply(e5, e6);
        
        Expression addTotal2 = new Add(addTotal, mul2);
        assertEquals("(var)*(3)+(55.5)*(3.3)+(y)*(x)", addTotal2.toString());
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
