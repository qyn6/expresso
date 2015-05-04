package expresso;

/**
 * This class represents a constant number in the expression. 
 *
 */
public class Constant implements Expression {
    
    private double constant;
    /**
     * create constant with double
     * @param constant
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
}
