package expresso;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a multiplication of two expressions.
 * Two multiply expressions are equal if both of their components are equal, so 2*x is equal to 2*x,
 * but 2*x is not equal to x*2
 *
 */
public class Multiply implements Expression {
    // Abstraction Function:
    // e1 represents the first expression to be multiplied
    // e2 represents the second expression to be multiplied
    // Rep Invariant:
    // e1, e2 !=null
    // Safety from Rep Exposure:
    // instance variables are private and final, never returned
    
    private final Expression e1;
    private final Expression e2;
    
    /**
     * Represents a multiplication of two valid expressions. 
     * @param e1 first expression to be multiplied
     * @param e2 second expression to be multiplied
     */
    public Multiply(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
        checkRep();
    }

    @Override
    public Expression differentiate(String var) {
        //d(u*v)/dx = u*dv/dx + v*du/dx
        return new Add(new Multiply(this.e1.differentiate(var), this.e2), 
                new Multiply(this.e1, this.e2.differentiate(var)));
    }

    @Override
    public List<Term> simplify() {
        SimplifyExpression simp = new SimplifyExpression(e1.simplify());
        
        return new ArrayList<Term>(simp.multiply(e2.simplify()));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Multiply)) return false;
        Multiply that = (Multiply) obj;
        return (this.e1.equals(that.e1) && this.e2.equals(that.e2));
    }
    
    @Override
    public int hashCode() {
        return this.e1.hashCode();
    }
    
    @Override
    public String toString() {
        return "(" + this.e2.toString() + ")*(" + this.e1.toString()+")";
    }

    private void checkRep(){
        assert !this.e1.equals(null);
        assert !this.e2.equals(null);
    }
}
