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
    public Expression add(Expression e) {
        throw new RuntimeException();
    }

    @Override
    public Expression multiply(Expression e) {
        throw new RuntimeException();
    }


}
