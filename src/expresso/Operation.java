package expresso;

/**
 * This class represents an operation of * or + on two expressions
 */
public class Operation implements Expression {
    
    private Expression e;
    
    public Operation (Expression e) {
        this.e = e;
    }

    @Override
    public Expression add(Expression e1) {
        // TODO Auto-generated method stub
        return e.add(e1);
    }    
    
    @Override
    public Expression multiply(Expression e1) {
        // TODO Auto-generated method stub
        return e.add(e1);
    }

}
