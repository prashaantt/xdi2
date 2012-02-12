package xdi2;

import java.io.Serializable;
import java.util.Properties;

import xdi2.exceptions.Xdi2MessagingException;
import xdi2.xri3.impl.XRI3;
import xdi2.xri3.impl.XRI3Authority;
import xdi2.xri3.impl.XRI3Segment;

/**
 * This interface represents a whole XDI graph.
 * XDI graphs consist of context nodes, arcs, relations, and literals.
 * 
 * @author markus
 */
public interface Graph extends Serializable, Comparable<Graph> {

	/*
	 * General methods
	 */

	/**
	 * Gets the root context node of this graph.
	 * @return A context node.
	 */
	public ContextNode getRootContextNode();

	/**
	 * Closes the graph. This should be called when work on the graph is done.
	 */
	public void close();

	/**
	 * Finds a context node in this graph.
	 * @param xri The XRI of the context node.
	 * @param create Whether or not to create context nodes if they don't exist.
	 * @return A context node with the given XRI.
	 */
	public ContextNode findContextNode(XRI3Authority xri, boolean create);

	/**
	 * Checks if a context node exists in this graph.
	 * @param xri The XRI of the context node.
	 * @return True, if the context node exists.
	 */
	public boolean containsContextNode(XRI3Authority xri);

	/**
	 * Converts the graph to a string in the given serialization format.
	 * @param parameters Parameters for the serialization.
	 */
	public String toString(String format);

	/**
	 * Converts the graph to a string in the given serialization format.
	 * @param format The serialization format.
	 * @param parameters Parameters for the serialization.
	 */
	public String toString(String format, Properties parameters);

	/*
	 * Methods related to messages
	 */

	/**
	 * A simple way to apply an XDI operation to this graph,
	 * based on a given input graph.
	 */
	public Graph applyOperation(Graph operationGraph, XRI3Segment operationXri) throws Xdi2MessagingException;

	/**
	 * A simple way to apply an XDI operation to this graph,
	 * based on a given input address.
	 */
	public Graph applyOperation(XRI3 address, XRI3Segment operationXri) throws Xdi2MessagingException;

	/*
	 * Methods related to transactions
	 */

	/**
	 * Starts a new transaction.
	 */
	public void beginTransaction();

	/**
	 * Commits the changes made by the transaction.
	 */
	public void commitTransaction();

	/**
	 * Rolls back the changes made by the transaction.
	 */
	public void rollbackTransaction();
}