package expresso;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a term in the expression. 
 * A term is represented by a constant and zero or more variables
 *
 */
public class Term {
    
    private final Double constant;
    private final List<String> variables;
    
    /**
     * Creates a Term in the expression
     * @param constant the constant in the term, must be non negative
     * @param variables the variable(s) in the term. a term with no variables is represented by the empty string ""
     * list may not be empty
     */
    public Term(Double constant, List<String> variables){
        this.constant = constant;
        this.variables = variables;
    }

    /**
     * @return the constant in the term
     */
    public Double getConstant(){
        return this.constant;
    }
    /**
     * @return the variables in the term
     */
    public List<String> getVariables(){
        return new ArrayList<String>(this.variables);
    }
    
    @Override
    public String toString(){
        Constant c = new Constant(this.constant);
        String term = c.toString() + "*";
        if (this.constant == 0) {
            return "" + 0;
        }
        if (this.variables.get(0).equals("")) {
            return c.toString();

        }
        if (this.constant == 1.0) {
            term = "";
        }
        for (String var: this.variables) {
            term += var + "*";
        }
        return term.substring(0,term.length() - 1);
    }
    
    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Term)) return false;
        Term that = (Term) obj;
        
        return this.variables.equals(that.variables);
    }
    
}
