package expresso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * String-based interface to the expression system.
 */
public class Expressions {
    
    /**
     * simplyfy first
     * differentiate each term
     * Differentiate an expression with respect to a variable.
     * @param expression the expression to differentiate
     * @param variable the variable to differentiate by
     * @return expression's derivative with respect to variable; will be a valid expression
     * @throws IllegalArgumentException if the expression or variable is invalid
     */
    public static String differentiate(String expression, String variable) {
        Expression e = Expression.parse(expression);
        Expression diffe = e.differentiate(variable);
        return diffe.toString();
    }
    
    /**
     * Simplify an expression.
     * @param expression the expression to simplify
     * @return an expression equal to the input that is a sum of terms without parentheses,
     *         where for all variables var_i in the expression, for all exponents e_i, the
     *         term (var_1^e_1 x var_2^e_2 x ... x var_n^e_n) appears at most once; each
     *         term may be multiplied by a non-zero, non-identity constant factor; and read
     *         left-to-right, the largest exponent in each term is non-increasing
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static String simplify(String expression) {
        Expression e = Expression.parse(expression);
        /*List<Term> terms = new ArrayList<Term>(e.simplify());
        Map<Integer, List<Term>> highestPower = new TreeMap<>();
        
        for (Term t: terms){
            int max = 0;
            List<String> vars = t.getVariables();
            int count = 1;
            for (int i = 0; i<vars.size()-1; i++){
                if (vars.get(i+1).equals(i)){
                    count ++;
                }else{
                    if(max<count){
                        max = count;
                    }
                    count = 1;
                }
            }
            if(highestPower.keySet().contains(max)){
                highestPower.get(max).add(t);
            }else{
                highestPower.put(max, Arrays.asList(t));
            }
        }
        
        String simplifiedExp = "";
        for (Integer key: highestPower.keySet()){
            for (Term t: highestPower.get(key)){
                simplifiedExp = t.toString() + simplifiedExp;
            }
        }*/
        SimplifyExpression simplifyExpression = new SimplifyExpression(e);
        String simplifiedExpression = simplifyExpression.simplify();
        //System.out.println(simplifiedExp.simplify());
        
        return simplifiedExpression;
        
    }
    
}
