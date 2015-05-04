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
    public Expression simplify() {
        return new Variable(this.var);
    }
    @Override
    public Expression differentiate(String varDiff) {
        if (varDiff.equals(var)){
            return new Constant(1);
        }
        else{
            return new Constant(0);
        }
    }

    
    
}
