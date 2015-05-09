package expresso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents strings that are variables in the expression
 */
public class Variable implements Expression {
    
    private String var;
    /**
     * Represents a valid variable, any sequence of letters, cannot have whitespace between one variable
     * @param var the variable in the expression
     */
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Variable)) return false;
        Variable that = (Variable) obj;
        return this.var.equals(that.var);
    }
    
    @Override
    public String toString() {
        return this.var;
    }
    
    @Override
    public int hashCode() {
        return this.var.hashCode();
    }

    @Override
    public List<Double> getConstant() {
        return new ArrayList<Double>(Arrays.asList(1.0));
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<String>(Arrays.asList(this.var));
    }
}
