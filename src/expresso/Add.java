package expresso;

import java.util.ArrayList;
import java.util.List;

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
    public List<Term> simplify() {
        List<Term> simplifyTerms = new ArrayList<>();
        for (Term t: e1.simplify()) {
            simplifyTerms.add(t);
        }
        
        for (Term t: e2.simplify()) {
            simplifyTerms.add(t);
        }
        SimplifyExpression simplify = new SimplifyExpression();
        
        return simplify.add(simplifyTerms);
    }
    @Override
    public Expression differentiate(String var) {
        return new Add(this.e1.differentiate(var), this.e2.differentiate(var));
    }
 
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Add)) return false;
        Add that = (Add) obj;
        return (this.e1.equals(that.e1) && this.e2.equals(that.e2));
    }
    
    @Override
    public int hashCode() {
        return this.e2.hashCode();
    }
    
    @Override
    public String toString() {
        return this.e1.toString() + "+" + this.e2.toString();
    }
}
