    package expresso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Converts a list of terms into a simplified expression.
 *
 */
public class SimplifyExpression {
    
    private final List<Term> terms;
    
    /**
     * @param terms list of terms to be simplified in order of non increasing exponents
     * list must not have multiple terms with the same variables, each term must be in its own simplified form
     * each term in the list must have variables in sorted order 
     */
    public SimplifyExpression(List<Term> terms) {
        this.terms = terms;
    }
    
    /**
     * Main simplify function: Operates on the field 'terms'
     * @return the Expression that represents the field 'terms'. Variables with the highest
     *         order will be on the left, and the power will decrease with each
     *         term to the right. There will be only one constant, if there are any at all.
     *         Constants will be on the right.
     */
    public Expression simplifyExpression() {
        Map<Integer, List<Term>> highestPower = new TreeMap<>();
        for (Term t: terms){
            int max = 0;
            List<String> variables = t.getVariables();
            int count = 1;
            variables.removeAll(Arrays.asList(""));
            if (variables.size() == 1) {
                max = 1;
            }
            for (int i = 0; i<variables.size()-1; i++){
                
                if (variables.get(i+1).equals(variables.get(i))) {
                    count ++;
                }
                else {
                    if(max<count){
                        max = count;
                    }
                    count = 1;
                }
                if(max<count){
                    max = count;
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
                    simplifiedExp =  t.toString() + "+" + simplifiedExp;
                }
            }
        }
        if (simplifiedExp.equals("")) {
            simplifiedExp = "0";
            return Expression.parse(simplifiedExp);
        }
        simplifiedExp = simplifiedExp.substring(0,simplifiedExp.length()-1);
        return Expression.parse(simplifiedExp);
    }
    
    /**
     * Adds the terms in this.terms that have the same variables
     * @return simplified list of terms
     */
    public List<Term> add(SimplifyExpression s2) {
        List<Term> simplifyTerms = new ArrayList<>();
        
        simplifyTerms.addAll(this.terms);
        simplifyTerms.addAll(s2.getTerms());
        
        SimplifyExpression simplify = new SimplifyExpression(simplifyTerms);
        
        return new ArrayList<Term>(simplify.reduce());
    }
    
    /**
     * Multiply one simplified expression to another
     * @param terms2 list of valid terms to which this.terms is multiplied
     * @return simplified list of terms after multiplication of the two lists
     */
    public List<Term> multiply(SimplifyExpression s2) {
        List<Term> terms2 = s2.getTerms();
        List<Term> simplifyTerms = new ArrayList<>();
        for (Term t1: terms){
            for (Term t2: terms2){
                List<String> vars = new ArrayList<String>(t1.getVariables());
                List<String> vars2 = new ArrayList<String>(t2.getVariables());
                
                if (vars.contains("")) {
                    simplifyTerms.add(new Term(t1.getConstant()*t2.getConstant(), vars2));
                }
                else if (vars2.contains("")) {
                    simplifyTerms.add(new Term(t1.getConstant()*t2.getConstant(), vars));
                }
                else {
                    vars.addAll(vars2);
                    simplifyTerms.add(new Term(t1.getConstant()*t2.getConstant(), vars));
                }
            }
        }
        SimplifyExpression simp = new SimplifyExpression(simplifyTerms);
        return new ArrayList<Term>(simp.reduce());
    }
    
    /**
     * 
     * @return
     */
    private List<Term> reduce() {
        List<Term> simplifyTerms = new ArrayList<>();

        Set<Integer> removeTerms = new HashSet<Integer>();
        for (int i = 0; i < terms.size(); i ++){
            Term t1 = terms.get(i);
            if (!removeTerms.contains(i)) {
                for (int j = i+1; j < terms.size(); j++){
                    Term t2 = terms.get(j);
                    List<String> vars = new ArrayList<>(t1.getVariables());
                    if (t1.getVariables().equals(t2.getVariables())){
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
        return new ArrayList<Term>(simplifyTerms);
    }
    
    /**
     * Retrieve list of terms
     * @return list of terms
     */
    private List<Term> getTerms() {
        return new ArrayList<Term>(this.terms);
    }
    
}
