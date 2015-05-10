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
        //System.out.println(e1);
        //System.out.println(e2);
        for (Term t1: e1.simplify()){
            //boolean added = false;
            for (Term t2: e2.simplify()){
                List<String> vars = new ArrayList<>(t1.getVariables());
                //List<String> vars2 = new ArrayList<>(t2.getVariables());
                //Collections.sort(vars1);
                //Collections.sort(vars2);
                
                if (t1.equals(t2)){
                    //added = true;
                    simplifyTerms.add(new Term(t1.getConstant() + t2.getConstant(), vars));
                    /*if(simplifyTerms.contains(t1)){
                        simplifyTerms.remove(t1);
                    }
                    if(simplifyTerms.contains(t2)){
                        simplifyTerms.remove(t2);
                    }
                }else{
                    simplifyTerms.add(t2);
                    simplifyTerms.add(t1);*/
                }
            }
            
        }
        for (Term t: e1.simplify()) {
            if (!simplifyTerms.contains(t)) {
                simplifyTerms.add(t);
            }
        }
        
        for (Term t: e2.simplify()) {
            if (!simplifyTerms.contains(t)) {
                simplifyTerms.add(t);
            }
        }
        //System.out.println(simplifyTerms);
        return simplifyTerms;
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
