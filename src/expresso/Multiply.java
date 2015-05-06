package expresso;

public class Multiply implements Expression {
    
    private final Expression e1;
    private final Expression e2;
    
    /**
     * Represents a multiplication of two valid expressions. 
     * @param e1 first expression to be multiplied
     * @param e2 second expression to be multiplied
     */
    public Multiply(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Expression differentiate(String var) {
        return new Add(new Multiply(this.e1.differentiate(var), this.e2), new Multiply(this.e1, this.e2.differentiate(var)));
    }

    @Override
    public Expression simplify() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Multiply)) return false;
        Multiply that = (Multiply) obj;
        return (this.e1.equals(that.e1) && this.e2.equals(that.e2));
    }
    
    @Override
    public int hashCode() {
        return this.e1.hashCode();
    }
    
    @Override
    public String toString() {
        return "(" + this.e1.toString() + ")*(" + this.e2.toString()+")";
    }
}
