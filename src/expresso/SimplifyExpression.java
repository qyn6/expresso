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
    
    private List<Term> terms;
    
    public SimplifyExpression(List<Term> terms) {
        this.terms = terms;
    }
    
    public Expression simplify() {
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
                if (!t.toString().equals("0")) {
                    simplifiedExp = simplifiedExp + "+" + t.toString();
                }
            }
        }
        simplifiedExp = simplifiedExp.substring(1,simplifiedExp.length());
        return Expression.parse(simplifiedExp);
    }
    
    public List<Term> add() {
        /*List<Term> simplifyTerms = new ArrayList<>();

        for (int i = 0; i < terms.size(); i ++){
            Term t1 = terms.get(i);
            System.out.println(t1);
            Set<Term> removeTerms = new HashSet<Term>();
            for (int j = i+1; j < terms.size(); j++){
                Term t2 = terms.get(j);
                List<String> vars = new ArrayList<>(t1.getVariables());
                //List<String> vars2 = new ArrayList<>(t2.getVariables());

                System.out.println(terms);
                if (t1.equals(t2)){
                    simplifyTerms.add(new Term(t1.getConstant() + t2.getConstant(), vars));
                    removeTerms.add(t1);
                    removeTerms.add(t2);
                }
            }
            terms.removeAll(removeTerms);
        }
        simplifyTerms.addAll(terms);
        return simplifyTerms;*/
        
        List<Term> simplifyTerms = new ArrayList<>();

        Set<Integer> removeTerms = new HashSet<Integer>();
        for (int i = 0; i < terms.size(); i ++){
            Term t1 = terms.get(i);
            //System.out.println(t1);
            if (!removeTerms.contains(i)) {
                for (int j = i+1; j < terms.size(); j++){
                    Term t2 = terms.get(j);
                    List<String> vars = new ArrayList<>(t1.getVariables());
                    //List<String> vars2 = new ArrayList<>(t2.getVariables());

                    //System.out.println(terms);
                    if (t1.equals(t2)){
                        simplifyTerms.add(new Term(t1.getConstant() + t2.getConstant(), vars));
                        removeTerms.add(i);
                        removeTerms.add(j);
                    }
                }
            }
        }
        for (int i = 0; i < terms.size(); i++) {
            if (!removeTerms.contains(i)) {
                simplifyTerms.add(terms.get(i));
            }
        }
        return simplifyTerms;
    }
    
    public List<Term> multiply(List<Term> terms2) {
        List<Term> simplifyTerms = new ArrayList<>();
        for (Term t1: terms){
            for (Term t2: terms2){
                List<String> vars = new ArrayList<String>(t1.getVariables());
                List<String> vars2 = new ArrayList<String>(t2.getVariables());
                Collections.sort(vars);
                Collections.sort(vars2);
                if (vars.contains("")) {
                    simplifyTerms.add(new Term(t1.getConstant()*t2.getConstant(), vars2));
                }
                else if (vars2.contains("")) {
                    simplifyTerms.add(new Term(t1.getConstant()*t2.getConstant(), vars));
                }
                else {
                    vars.addAll(vars2);
                    Collections.sort(vars);
                    simplifyTerms.add(new Term(t1.getConstant()*t2.getConstant(), vars));
                }
            }
        }
        SimplifyExpression simp = new SimplifyExpression(simplifyTerms);
        return simp.add();
    }
    

}
