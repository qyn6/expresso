package expresso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SimplifyExpression {
    
    
    public Expression simplify(List<Term> terms) {
        Map<Integer, List<Term>> highestPower = new TreeMap<>();
        for (Term t: terms){
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
        simplifiedExp = simplifiedExp.substring(0,simplifiedExp.length()-1);
        return Expression.parse(simplifiedExp);
    }
    
    public List<Term> add(List<Term> terms) {
        List<Term> simplifyTerms = new ArrayList<>();

        for (int i = 0; i < terms.size(); i ++){
            Term t1 = terms.get(i);
            Set<Term> removeTerms = new HashSet<Term>();
            for (int j = i+1; j < terms.size(); j++){
                Term t2 = terms.get(j);
                List<String> vars = new ArrayList<>(t1.getVariables());
                //List<String> vars2 = new ArrayList<>(t2.getVariables());
                
                if (t1.equals(t2)){
                    simplifyTerms.add(new Term(t1.getConstant() + t2.getConstant(), vars));
                    removeTerms.add(t1);
                    removeTerms.add(t2);
                }
            }
            terms.removeAll(removeTerms);
        }
        simplifyTerms.addAll(terms);
        return simplifyTerms;
    }
    
    public List<Term> multiply(List<Term> terms1, List<Term> terms2) {
        List<Term> simplifyTerms = new ArrayList<>();
        for (Term t1: terms1){
            for (Term t2: terms2){
                List<String> vars = new ArrayList<String>(t1.getVariables());
                vars.addAll(t2.getVariables());
                Collections.sort(vars);
                simplifyTerms.add(new Term(t1.getConstant()*t2.getConstant(), vars));
            }
        }
        SimplifyExpression simp = new SimplifyExpression();
        return simp.add(simplifyTerms);
    }
    

}
