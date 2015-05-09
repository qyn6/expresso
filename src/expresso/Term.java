package expresso;

import java.io.IOException;
import java.util.List;

public class Term {
    
    private final Double constant;
    private final List<String> variables;
    
    public Term(Double constant, List<String> variables){
        this.constant = constant;
        this.variables = variables;
    }

    @Override
    public String toString(){
        return this.constant.toString() + this.variables.toString();
    }
    
}
