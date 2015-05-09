package expresso;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import expresso.parser.ExpressionBaseListener;
import expresso.parser.ExpressionParser;

public class ExpressionsTreeListener extends ExpressionBaseListener {
    
    public Expression exp;
    private List<List<Expression>> terms = new ArrayList<List<Expression>>();
    private Stack<Expression> stack = new Stack<Expression>();
    
    public void checkRep() {
        if (stack == null) {
            throw new RuntimeException();
        }
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterLine(ExpressionParser.LineContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitLine(ExpressionParser.LineContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterLegal(ExpressionParser.LegalContext ctx) {
        List<Expression> legalTerms = new ArrayList<Expression>();
        terms.add(legalTerms);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitLegal(ExpressionParser.LegalContext ctx) {
        exp = stack.pop();
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterAdd(ExpressionParser.AddContext ctx) {
        
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitAdd(ExpressionParser.AddContext ctx) {
        int numChildren = ctx.getChildCount();
        Expression addExp = stack.pop();
        for (int i = 2; i < numChildren; i += 2) {
            addExp = new Add(addExp, stack.pop());
        }
        
        stack.push(addExp);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterMul(ExpressionParser.MulContext ctx) {
        
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitMul(ExpressionParser.MulContext ctx) { 
        int numChildren = ctx.getChildCount();
        Expression mulExp = stack.pop();
        for (int i = 2; i < numChildren; i += 2) {
            mulExp = new Multiply(mulExp, stack.pop());
        }
        
        stack.push(mulExp);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterTerm(ExpressionParser.TermContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitTerm(ExpressionParser.TermContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterParens(ExpressionParser.ParensContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitParens(ExpressionParser.ParensContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterConstant(ExpressionParser.ConstantContext ctx) {
        //System.out.println("created new Constant : " + Double.parseDouble(ctx.getChild(0).getText()));
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitConstant(ExpressionParser.ConstantContext ctx) {
//        List<Expression> latest = terms.get(terms.size() - 1);
//        latest.add();
        stack.push(new Constant(Double.parseDouble(ctx.getText())));
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterVar(ExpressionParser.VarContext ctx) {
        //System.out.println("created new Variable :" + ctx.getChild(0).getText());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitVar(ExpressionParser.VarContext ctx) { 
//        List<Expression> latest = terms.get(terms.size() - 1);
//        latest.add();
        stack.push(new Variable(ctx.getText()));
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterEveryRule(ParserRuleContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitEveryRule(ParserRuleContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void visitTerminal(TerminalNode node) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void visitErrorNode(ErrorNode node) { }
}
