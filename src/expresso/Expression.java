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
    //    + Operation(e1, e2: Expression) //TODO TA do we need to indicate that +, * are options? Or keep them as methods.

    /**
     * Parse an expression. All possible operations are represented by the following symbols:
     *      +, -, *, ^
     *      Any input that does not have an operation character between two expressions is invalid.
     *      Ex: (3 + x)(3 - x) does not work. It has to be (3 + x)*(3 - x).
     * @param input expression to parse
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
        throw new RuntimeException("unimplemented");
    }
    
  
    // TODO Instance methods
    
}
