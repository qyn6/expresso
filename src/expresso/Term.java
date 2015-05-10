package expresso;

import java.util.ArrayList;
import java.util.List;

public class Term {
    
    private final Double constant;
    private final List<String> variables;
    
    public Term(Double constant, List<String> variables){
        this.constant = constant;
        this.variables = variables;
    }

    public Double getConstant(){
        return this.constant;
    }
    public List<String> getVariables(){
        return new ArrayList<String>(this.variables);
    }
    
    @Override
    public String toString(){
        //this.variables.removeAll(Arrays.asList(""));
        //String.join("*", this.variables)

        Constant c = new Constant(this.constant);
        String term = c.toString() + "*";
        if (this.constant == 0) {
            return "" + 0;
        }
        if (this.variables.get(0).equals("")) {
            return "" + c;
        }
        if (this.constant == 1.0) {
            term = "";
        }
        for (String var: this.variables) {
            term += var + "*";
        }
        return term.substring(0,term.length() - 1);
        //return this.constant.toString() + "*" + this.variables;
    }
    
    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Term)) return false;
        Term that = (Term) obj;
        
        //return this.constant == that.constant && this.variables.equals(that.variables);
        return this.variables.equals(that.variables);
    }
    
}
