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

    /*walking tree
     * do in order traversal
     * initiate constant or var when it sees it on the root with the children as values
     * initiate operation when it sees mul or add
     * if it sees parens on the root, then it creates the term first and adds parenthesis on the side
     */
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
    
    /**
     * Add this expression together with another valid expression, without any simplifications
     * @param e1 - expression to add
     * @return Expression that represents e + e1
     */
    public Expression add(Expression e);
    
    /**
     * Multiply this expression together with another valid expression, without any simplifications
     *     Edge case: There must always be a '*' symbol between expressions
     *     Ex: (3 + x)(3 - x) is not valid, but (3 + x)*(3 - x) is.
     * @param e1 - expression to multiply
     * @return Expression that represents e * e1
     */
    public Expression multiply(Expression e);

    
}
