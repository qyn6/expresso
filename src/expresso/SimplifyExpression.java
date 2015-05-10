package expresso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SimplifyExpression {
    
    private List<Term> terms;
    
    public SimplifyExpression(Expression e) {
        this.terms = e.simplify();
    }
    
    public String simplify() {
        Map<Integer, List<Term>> highestPower = new TreeMap<>();
        for (Term t: this.terms){
            int max = 0;
            List<String> vars = t.getVariables();
            int count = 1;
            vars.removeAll(Arrays.asList(""));
            if (vars.size() == 1) {
                max = 1;
            }
            for (int i = 0; i<vars.size()-1; i++){
                
                if (vars.get(i+1).equals(i)) {
                    count ++;
                }
                else {
                    if(max<count){
                        max = count;
                    }
                    count = 1;
                }
            }
            if (highestPower.containsKey(max)) {
                highestPower.get(max).add(t);
            }
            else {
                List<Term> newList = new ArrayList<Term>();
                newList.add(t);
                highestPower.put(max, newList);
            }
        }
        String simplifiedExp = "";
        for (Integer key: highestPower.keySet()){
            for (Term t: highestPower.get(key)){
                simplifiedExp = t.toString() + "+" + simplifiedExp;
            }
        }
        
        return simplifiedExp.substring(0,simplifiedExp.length()-1);
    }
}
