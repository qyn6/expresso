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
                if (input.startsWith(COMMAND_PREFIX)) {
                    System.out.println(input.substring(COMMAND_PREFIX.length()));
                    output = handleCommand(input.substring(COMMAND_PREFIX.length()));
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
     * TODO
     */
    private static String handleExpression(String input) {
        String out = "";
        try{
            exp = Expression.parse(input); //make this current Expression
        }catch(Exception e){
            return "Parse error";
        }
        if (exp != null) {
            return exp.toString();
        } else {
            return "error";
        }
    }
    
    /**
     * TODO
     */
    private static String handleCommand(String substring) {
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
