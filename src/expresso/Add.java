package expresso;

/**
 * This class represents an addition of two expressions
 */
public class Add implements Expression {

    private final Expression e1;
    private final Expression e2;
    /**
     * creates an instance of addition
     * @param e1 first Expression to be added
     * @param e2 second Expression to be added
     */
    public Add(Expression e1, Expression e2){
        this.e1 = e1;
        this.e2 = e2;
    }
    
    @Override
    public Expression simplify() {
        return new Add(this.e1.simplify(), this.e2.simplify());
    }
    @Override
    public Expression differentiate(String var) {
        return new Add(this.e1.differentiate(var), this.e2.differentiate(var));
    }
 

}
