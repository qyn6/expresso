package expresso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public List<Term> simplify() {
        List<Term> simplifyTerms = new ArrayList<>();
        for (Term t1: e1.simplify()){
            for (Term t2: e2.simplify()){
                List<String> vars = new ArrayList<String>(t1.getVariables());
                vars.addAll(t2.getVariables());
                Collections.sort(vars);
                simplifyTerms.add(new Term(t1.getConstant()*t2.getConstant(), vars));
            }
        }
        return simplifyTerms;
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
