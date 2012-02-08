package xdi2.io;


import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.util.Properties;

import xdi2.Graph;
import xdi2.exceptions.ParseException;

/**
 * Provides methods for reading an XDI graph.
 *
 * @author markus
 */
public interface XDIReader extends Serializable {

	/**
	 * Reads an XDI graph from a string.
	 * @param graph A graph that will hold the read data.
	 * @param string A string from which to read.
	 * @param parameters Optional parameters for the reader.
	 */
	public void read(Graph graph, String string, Properties parameters) throws IOException, ParseException;

	/**
	 * Reads an XDI graph from a character stream.
	 * @param graph A graph that will hold the read data.
	 * @param reader A character stream from which to read.
	 * @param parameters Optional parameters for the reader.
	 */
	public void read(Graph graph, Reader reader, Properties parameters) throws IOException, ParseException;

	/**
	 * Reads an XDI graph from a byte stream.
	 * @param graph A graph that will hold the read data.
	 * @param stream A byte stream from which to read.
	 * @param parameters Optional parameters for the reader.
	 */
	public void read(Graph graph, InputStream stream, Properties parameters) throws IOException, ParseException;

	/**
	 * Returns the format this XDIReader can read, e.g.
	 * <ul>
	 * <li>X3</li>
	 * <li>X-TRIPLES</li>
	 * <li>XDI/XML</li>
	 * </ul>
	 * @return The format of this XDIReader.
	 */
	public String getFormat();

	/**
	 * Returns the mime type of format this XDIReader can read, e.g.
	 * <ul>
	 * <li>text/xdi+x3</li>
	 * <li>text/plain</li>
	 * <li>application/xdi+xml</li>
	 * </ul>
	 * @return The mime type of this XDIReader.
	 */
	public String[] getMimeTypes();

	/**
	 * Returns the default file extension of this XDIReader, e.g.
	 * <ul>
	 * <li>.xml</li>
	 * <li>.x3</li>
	 * <li>.txt</li>
	 * </ul>
	 * @return The default file extension of this XDIReader.
	 */
	public String getDefaultFileExtension();
}