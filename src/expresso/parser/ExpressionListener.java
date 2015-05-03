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
	 * Enter a parse tree produced by {@link ExpressionParser#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(ExpressionParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(ExpressionParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#mul}.
	 * @param ctx the parse tree
	 */
	void enterMul(ExpressionParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#mul}.
	 * @param ctx the parse tree
	 */
	void exitMul(ExpressionParser.MulContext ctx);
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
	 * Enter a parse tree produced by {@link ExpressionParser#parens}.
	 * @param ctx the parse tree
	 */
	void enterParens(ExpressionParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#parens}.
	 * @param ctx the parse tree
	 */
	void exitParens(ExpressionParser.ParensContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(ExpressionParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(ExpressionParser.VarContext ctx);
}