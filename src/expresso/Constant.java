package expresso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a constant number in the expression. 
 *
 */
public class Constant implements Expression {
    
    // Abstraction Function:
    //      constant represents the double value in a expression
    // Rep Invariant:
    //      constant >= 0.0
    // Safety from Rep Exposure:
    //      constant is private and final, it cannot be changed by the client
    
    private final double constant;
    /**
     * create constant with double
     * @param constant, must be non negative
     */
    public Constant(double constant){
        this.constant = constant;
        checkRep();
    }
    
    @Override
    public List<Term> simplify() {
        checkRep();
        return new ArrayList<Term>(Arrays.asList(new Term(this.constant, Arrays.asList(""))));
    }
    
    @Override
    public Expression differentiate(String var) {
        return new Constant(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Constant)) return false;
        Constant that = (Constant) obj;
        return this.constant == that.constant;
    }
    
    @Override
    public String toString() {
        if (this.constant % 1 == 0) {
            return Integer.toString((int) this.constant);
        }
        else {
            return Double.toString(this.constant);
        }
    }
    
    @Override
    public int hashCode() {
        return (int) this.constant;
    }

    private void checkRep() {
        assert this.constant >= 0.0;
    }
}
