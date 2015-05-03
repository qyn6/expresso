package expresso;

/**
 * This class provides an interface to parse an expression containing:
 * 
 *   + and * symbols (for addition and multiplication)
 *   nonnegative integer and floating-point numbers (e.g. 7 and 4.2)
 *   variables, which are any sequence of letters (e.g. y and foo)
 *   parentheses (for grouping)
 */
public interface Expression {
    
    // Datatype definition
    //  Expression = Variable( x : String)
    //    + Constant ( n : int, double )
    //    + Operation(e1, e2: Expression, o: Operation)

    /**
     * Parse an expression.
     * @param input expression to parse
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
        throw new RuntimeException("unimplemented");
    }
    
  
}
