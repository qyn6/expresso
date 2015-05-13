package expresso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Console interface to the expression system.
 */
public class Main {
    
    private static final String COMMAND_PREFIX = "!";
    private static Expression exp;
    
    /**
     * Read expression and command inputs from the console and output results.
     * An empty input terminates the program.
     * @param args unused
     * @throws IOException if there is an error reading the input
     */
    public static void main(String[] args) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            System.out.print("> ");
            final String input = in.readLine();

            if (input.isEmpty()) {
                return;
            }
            
            try {
                final String output;
                final String inputNoSpaces = input.replaceAll(" ", "");
                if (inputNoSpaces.startsWith(COMMAND_PREFIX)) {
                    System.out.println(inputNoSpaces.substring(COMMAND_PREFIX.length()));
                    output = handleCommand(inputNoSpaces.substring(COMMAND_PREFIX.length()));
                } else {
                    output = handleExpression(input);
                }
                System.out.println(output);
            } catch (RuntimeException re) {
                System.out.println(re.getClass().getName() + ": " + re.getMessage());
            }
        }
    }
    
    /**
     * Handle an input expression from the console, with operations * and +
     *      and optional parenthesis. Save the latest expression in case
     *      operations are performed on it.
     * @param input String representing the expression
     * @return a String representing the expression
     */
    private static String handleExpression(String input) {
        String out = "";
        try{
            exp = Expression.parse(input); //make this current Expression
        }catch(Exception e){
            return "ParseError: Invalid input";
        }
        if (exp != null) {
            return exp.toString();
        } else {
            return "error";
        }
    }
    
    /**
     * Handle an input command from the console, denoted by a '!' character followed
     *      by either a 'simplify' or 'd/dx' where x is the variable to differentiate
     *      by.
     * @param substring the input command
     * @return a String representing the expression created by performing the input
     *      command on the last saved expression. If there is no valid expression, or
     *      if there is a missing variable in the derivative command, or if the substring
     *      is invalid, we return "Error" with an optiona explanation.
     */
    private static String handleCommand(String substring) {
        if (exp == null) {
            return "error: You haven't entered a valid expression yet!";
        }
            
        if (substring.equals("simplify")){
            List<Term> terms = exp.simplify();
            SimplifyExpression simplifyExpression = new SimplifyExpression(terms);
            exp = simplifyExpression.simplifyExpression();
            return exp.toString().replaceAll("\\(|\\)", "");
            
        }else if (substring.startsWith("d/d")){
            
            String diffVar = substring.replace("d/d", "");
            if (diffVar.equals("")){
                return "ParseError: missing variable in derivative command";
            }
            exp = exp.differentiate(diffVar);
            return exp.toString();
        }else{
            return "error";
        }
    }
    
}
