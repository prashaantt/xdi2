package xdi2.messaging;

import xdi2.core.Relation;
import xdi2.messaging.constants.XDIMessagingConstants;

/**
 * A $add XDI operation, represented as a relation.
 * 
 * @author markus
 */
public final class AddOperation extends Operation {

	private static final long serialVersionUID = 4228081189426184704L;

	protected AddOperation(Message message, Relation relation) {

		super(message, relation);
	}

	/*
	 * Static methods
	 */

	/**
	 * Checks if an relation is a valid XDI $add operation.
	 * @param relation The relation to check.
	 * @return True if the relation is a valid XDI operation.
	 */
	public static boolean isValid(Relation relation) {

		if (! XDIMessagingConstants.XRI_SS_ADD.equals(relation.getArcXri())) return false;
		if (! XDIMessagingConstants.XRI_SS_DO.equals(relation.getContextNode().getArcXri())) return false;

		return true;
	}

	/**
	 * Factory method that creates an XDI operation bound to a given relation.
	 * @param relation The relation that is an XDI $add operation.
	 * @return The XDI $add operation.
	 */
	public static AddOperation fromMessageAndRelation(Message message, Relation relation) {

		if (! isValid(relation)) return null;

		return new AddOperation(message, relation);
	}
}
