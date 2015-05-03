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
     * Multiply together two valid expressions, without any simplifications
     * @param e1 - first expression
     * @param e2 - second expression
     * @return Expression that represents e1 * e2
     */
    public Expression multiply(Expression e) {
        // TODO Auto-generated method stub
        return null;
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

}
