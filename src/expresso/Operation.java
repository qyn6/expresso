package expresso;

/**
 * This class represents an operation of * or + on two expressions
 */
public class Operation implements Expression {
    
    private Expression e;
    
    public Operation (Expression e) {
        this.e = e;
    }

    /**
     * Add this expression together with another valid expression, without any simplifications
     * @param e1 - expression to add
     * @return Expression that represents e + e1
     */
    public Expression add(Expression e1) {
        // TODO Auto-generated method stub
        return e.add(e1);
    }    
    /**
     * Multiply this expression together with another valid expression, without any simplifications
     *     Edge case: There must always be a '*' symbol between expressions
     *     Ex: (3 + x)(3 - x) is not valid, but (3 + x)*(3 - x) is.
     * @param e1 - expression to multiply
     * @return Expression that represents e * e1
     */
    public Expression multiply(Expression e1) {
        // TODO Auto-generated method stub
        return e.add(e1);
    }

}
