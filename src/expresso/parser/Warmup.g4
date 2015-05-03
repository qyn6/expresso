grammar Warmup;

/*
 * This puts "package differentiator.grammar;" at the top of the output Java
 * files. Do not change these lines.
 */
@header {
package expresso.parser;
}

/*
 * This adds code to the generated lexer and parser. Do not change these lines. 
 */
@members {
    // This method makes the lexer or parser stop running if it encounters
    // invalid input and throw a RuntimeException.
    public void reportErrorsAsExceptions() {
        removeErrorListeners();
        addErrorListener(new ExceptionThrowingErrorListener());
    }
    
    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            throw new RuntimeException(msg);
        }
    }
}

line        : legal_expr* EOF;
legal_expr	: LEFT_PAREN legal_expr* RIGHT_PAREN;
LEFT_PAREN  : '(';
RIGHT_PAREN : ')';
