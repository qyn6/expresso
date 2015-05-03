package expresso;

/**
 * This class represents strings that are variables in the expression
 */
public class Variable implements Expression {
    
    private String e;
    public Variable(String e) {
        this.e = e;
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
