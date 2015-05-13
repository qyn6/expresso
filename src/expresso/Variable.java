package expresso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents strings that are variables in the expression
 */
public class Variable implements Expression {
    
    // Abstraction Function:
    //      variable represents a valid variable in the expression
    // Rep Invariant:
    //      variable is a sequence of lower-case letters with no whitespace between them
    // Safety from Rep Exposure:
    //      variable is private and final and immutable, it cannot be changed by the client
    
    private final String variable;
    /**
     * Represents a valid variable, any sequence of letters, cannot have whitespace between one variable
     * @param variable the variable in the expression
     */
    public Variable(String variable) {
        this.variable = variable;
    }
    
    @Override
    public List<Term> simplify() {
        return new ArrayList<Term>(Arrays.asList(new Term(1.0, Arrays.asList(this.variable))));
    }
    
    @Override
    public Expression differentiate(String varDiff) {
        if (varDiff.equals(variable)){
            return new Constant(1);
        }
        else{
            return new Constant(0);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Variable)) return false;
        Variable that = (Variable) obj;
        return this.variable.equals(that.variable);
    }
    
    @Override
    public String toString() {
        return this.variable;
    }
    
    @Override
    public int hashCode() {
        return this.variable.hashCode();
    }


}
