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



}
