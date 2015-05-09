package expresso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a constant number in the expression. 
 *
 */
public class Constant implements Expression {
    
    private double constant;
    /**
     * create constant with double
     * @param constant, must be non negative
     */
    public Constant(double constant){
        this.constant = constant;
    }
    
    @Override
    public Expression simplify() {
        return new Constant(this.constant);
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

    @Override
    public List<Double> getConstant() {
        return new ArrayList<Double>(Arrays.asList( this.constant));
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>(Arrays.asList(""));
    }
}
