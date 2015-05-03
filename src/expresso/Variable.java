package expresso;

/**
 * This class represents strings that are variables in the expression
 */
public class Variable implements Expression {
    
    private String var;
    public Variable(String var) {
        this.var = var;
    }

}
