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
     * Add together two valid expressions, without any simplifications
     * @param e1 - first expression
     * @param e2 - second expression
     * @return Expression that represents e1 + e2
     */
    public Expression add(Expression e) {
        // TODO Auto-generated method stub
        return null;
    }    
    /**
     * Multiply together two valid expressions, without any simplifications
     *     Edge case: There must always be a '*' symbol between expressions
     *     Ex: (3 + x)(3 - x) is not valid, but (3 + x)*(3 - x) is.
     * @param e1 - first expression
     * @param e2 - second expression
     * @return Expression that represents e1 * e2
     */
    public Expression multiply(Expression e) {
        // TODO Auto-generated method stub
        return null;
    }

}
