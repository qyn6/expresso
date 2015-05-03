// Generated from Warmup.g4 by ANTLR 4.5

package expresso.parser;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link WarmupParser}.
 */
public interface WarmupListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link WarmupParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(WarmupParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link WarmupParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(WarmupParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link WarmupParser#legal_expr}.
	 * @param ctx the parse tree
	 */
	void enterLegal_expr(WarmupParser.Legal_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link WarmupParser#legal_expr}.
	 * @param ctx the parse tree
	 */
	void exitLegal_expr(WarmupParser.Legal_exprContext ctx);
}