package expresso;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import expresso.parser.ExpressionLexer;
import expresso.parser.ExpressionParser;

/**
 * This class provides an interface to parse an expression containing:
 * 
 *   + and * symbols (for addition and multiplication)
 *   nonnegative integer and floating-point numbers (e.g. 7 and 4.2)
 *   variables, which are any sequence of letters (e.g. y and foo), cannot have whitespace between one variable
 *   parentheses (for grouping)
 */
public interface Expression {
    
    // Datatype definition
    //  Expression = Variable( x : String)
    //    + Constant ( n : int, double )
    //    + Add (e1, e2: Expression)
    //    + Multiply(e1, e2: Expression)
    
    /*walking tree
     * do in order traversal
     * initiate constant or var when it sees it on the root with the children as values
     * initiate operation when it sees mul or add
     * if it sees parens on the root, then it creates the term first and adds parenthesis on the side
     */
    /**
     * Parse an expression. All possible operations are represented by the following symbols:
     *      +,*
     *      Any input that does not have an operation character between two expressions is invalid.
     *      Ex: (3 + x)(3 - x) does not work. It has to be (3 + x)*(3 - x).
     * @param input expression to parse
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) throws IllegalArgumentException{
        CharStream stream = new ANTLRInputStream(input);
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new ExceptionThrowingErrorListener());
        lexer.removeErrorListeners();
        lexer.addErrorListener(new ExceptionThrowingErrorListener());
        
        ParseTree tree = parser.line();
        //System.err.println(tree.toStringTree(parser));
        //((RuleContext)tree).inspect(parser);
        
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionsTreeListener treeWalker = new ExpressionsTreeListener();
        walker.walk(treeWalker, tree);
        return treeWalker.exp;
    }
   
    class ExceptionThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            throw new IllegalArgumentException(msg);
        }
    }
    
    /**
    * @return an expression equal to the input that is a sum of terms without parentheses,
    *         where for all variables var_i in the expression, for all exponents e_i, the
    *         term (var_1^e_1 x var_2^e_2 x ... x var_n^e_n) appears at most once; each
    *         term may be multiplied by a non-zero, non-identity constant factor; and read
    *         left-to-right, the largest exponent in each term is non-increasing
    */
    public List<Term> simplify();
    
    /**
     * Differentiate an expression with respect to a variable.
     * @param var the variable to differentiate by
     * @return expression's derivative with respect to variable; will be a valid expression
     **/
    public Expression differentiate(String var);

}
