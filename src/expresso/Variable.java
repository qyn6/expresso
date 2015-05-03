package expresso;

/**
 * This class represents strings that are variables in the expression
 */
public class Variable implements Expression {
    
    private String var;
    public Variable(String var) {
        this.var = var;
    }
    
    @Override
    public Expression multiply() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Expression add() {
        // TODO Auto-generated method stub
        return null;
    }

}
