package expression;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import expresso.Add;
import expresso.Constant;
import expresso.Expression;
import expresso.Multiply;
import expresso.Variable;

//Tests for the Add.java class and its methods
public class AddTest {

    private static Constant c1;
    private static Constant c2;
    private static Constant c3;
    private static Constant c4;
    private static Variable v1;
    private static Variable v2;
    private static Add add1;
    private static Add add2;
    private static Multiply mul1;
    private static Multiply mul2;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        c1 = new Constant(3);
        c2 = new Constant(4);
        c3 = new Constant(3.3);
        c4 = new Constant(55.5);
        v1 = new Variable("x");
        v2 = new Variable("y");
        add1 = new Add(c1, v1); //(3 + x)
        add2 = new Add(c2, v2); //(4 + y)
        mul1 = new Multiply(c1, v1); //3*x
        mul2 = new Multiply(c2, v2); //4*y
    }
    
    //Partitions for the constructor method:
    //e1 and e2 can be a Constant, Variable, Add term, or Mul term
    //The Constants can be integers or doubles
    
    @Test
    public void testAddConstructorConstants(){
        Expression e1 = new Add(c1, c2);
        assertEquals("4+3", e1.toString());

        Expression add1 = new Add(c3, c4);
        assertEquals("55.5+3.3", add1.toString());
        
        Expression add2 = new Add(c3, c1);
        assertEquals("3+3.3", add2.toString());
    }
    
    //The variables are sequences of letters:
    //  can be 1 letter, >1 letter
    @Test
    public void testAddConstructorVariables(){
        Expression add = new Add(v1, v2);
        assertEquals("y+x", add.toString());

        Expression e3 = Expression.parse("variable");
        Expression e4 = Expression.parse("foo");
        Expression add1 = new Add(e3, e4);
        assertEquals("foo+variable", add1.toString());
    }
    
    //The Add and Mul terms can also be added
    @Test
    public void testAddAddAndMulTerms(){

        Expression addTotal = new Add(add1, add2);
        assertEquals("y+4+x+3", addTotal.toString());
        
        Expression addTotal2 = new Add(addTotal, add2);
        assertEquals("y+4+y+4+x+3", addTotal2.toString());
        
        Expression total = new Add(mul1, add2);
        assertEquals("y+4+(x)*(3)", total.toString());
        
        Expression total2 = new Add(add1, mul2);
        assertEquals("(y)*(4)+x+3", total2.toString());
        
        Expression mulTotal = new Add(mul1, mul2);
        assertEquals("(y)*(4)+(x)*(3)", mulTotal.toString());
        
        Expression mulTotal2 = new Add(mulTotal, mul2);
        assertEquals("(y)*(4)+(y)*(4)+(x)*(3)", mulTotal2.toString());
    }
    
    //Partitions for simplify:
    //Both left and right sides can be either a Constant, Variable, Add, or Mul term
    @Test
    public void testAddSimplify(){
        Expression sum = new Add(c1, c2);
        Expression sum1 = new Add(c1, v2);
        Expression sum2 = new Add(v1, add2);
        Expression sum3 = new Add(add1, add2);
        Expression sum4 = new Add(mul1, mul2);
        Expression mul3 = new Multiply(c2, v1); //4*y
        Expression sum5 = new Add(mul1, mul3);

        assertEquals(1, sum.simplify().size());
        assertTrue(sum.simplify().toString().contains("7"));
        assertEquals(2, sum1.simplify().size());
        assertTrue(sum1.simplify().toString().contains("3"));
        assertTrue(sum1.simplify().toString().contains("y"));
        assertEquals(3, sum2.simplify().size());
        assertTrue(sum2.simplify().toString().contains("x"));
        assertTrue(sum2.simplify().toString().contains("4"));
        assertTrue(sum2.simplify().toString().contains("y"));
        assertEquals(3, sum3.simplify().size());
        assertTrue(sum3.simplify().toString().contains("x"));
        assertTrue(sum3.simplify().toString().contains("7"));
        assertTrue(sum3.simplify().toString().contains("y"));
        assertEquals(2, sum4.simplify().size());
        assertTrue(sum4.simplify().toString().contains("3*x"));
        assertTrue(sum4.simplify().toString().contains("4*y"));
        assertEquals(1, sum5.simplify().size());
        assertTrue(sum5.simplify().toString().contains("7*x"));
    }
    
    //Partitions for differentiate:
    //Both left and right sides can be either a Constant, Variable, Add, or Mul term
    @Test
    public void testAddDifferentiate(){
        Expression sum = new Add(c1, c2);
        Expression sum1 = new Add(c1, v2);
        Expression sum2 = new Add(v1, add2);
        Expression sum3 = new Add(add1, add2);
        Expression sum4 = new Add(mul1, mul2);
        Expression mul3 = new Multiply(c2, v1); //4*y
        Expression sum5 = new Add(mul1, mul3);

        assertTrue(sum.differentiate("x").toString().contains("0+0"));
        assertTrue(sum1.differentiate("y").toString().contains("1+0"));
        assertTrue(sum2.differentiate("x").toString().contains("0+0+1"));
        assertTrue(sum2.differentiate("y").toString().contains("1+0+0"));
        assertTrue(sum3.differentiate("x").toString().contains("0+0+1+0"));
        assertTrue(sum3.differentiate("y").toString().contains("1+0+0+0"));
        assertTrue(sum4.differentiate("x").simplify().toString().contains("3"));
        assertTrue(sum4.differentiate("y").toString().contains("4"));
        assertTrue(sum5.differentiate("x").simplify().toString().contains("7"));
        assertTrue(sum5.differentiate("y").simplify().toString().contains("0"));
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
    
    //Two equal terms must have the same hash code
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
