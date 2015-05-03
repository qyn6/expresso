// Generated from Expression.g4 by ANTLR 4.5

package expresso.parser;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParser}.
 */
public interface ExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(ExpressionParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(ExpressionParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#legal}.
	 * @param ctx the parse tree
	 */
	void enterLegal(ExpressionParser.LegalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#legal}.
	 * @param ctx the parse tree
	 */
	void exitLegal(ExpressionParser.LegalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#legal_expr}.
	 * @param ctx the parse tree
	 */
	void enterLegal_expr(ExpressionParser.Legal_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#legal_expr}.
	 * @param ctx the parse tree
	 */
	void exitLegal_expr(ExpressionParser.Legal_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#legal_paren}.
	 * @param ctx the parse tree
	 */
	void enterLegal_paren(ExpressionParser.Legal_parenContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#legal_paren}.
	 * @param ctx the parse tree
	 */
	void exitLegal_paren(ExpressionParser.Legal_parenContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(ExpressionParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(ExpressionParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(ExpressionParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(ExpressionParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(ExpressionParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(ExpressionParser.ConstantContext ctx);
}