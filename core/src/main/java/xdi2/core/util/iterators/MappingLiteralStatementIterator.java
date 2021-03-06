package xdi2.core.util.iterators;

import java.util.Iterator;

import xdi2.core.Literal;
import xdi2.core.Statement;

/**
 * A MappingIterator that maps XDI literals to their statements.
 * 
 * @author markus
 */
public class MappingLiteralStatementIterator extends MappingIterator<Literal, Statement> {

	public MappingLiteralStatementIterator(Iterator<Literal> literals) {

		super(literals);
	}

	@Override
	public Statement map(Literal literal) {

		return literal.getStatement();
	}
}
