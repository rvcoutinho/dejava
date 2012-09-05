package org.dejava.component.serialization.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dejava.component.serialization.xml.constant.ErrorKeys;
import org.dejava.component.serialization.xml.exception.XMLCreationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Helps handling XML creation.
 */
public final class XMLCreator {
	
	/**
	 * Private constructor.
	 */
	private XMLCreator() {
	}
	
	/**
	 * Creates a XML document from an input stream.
	 * 
	 * @param xmlInputStream
	 *            Input stream with the XML content.
	 * @return A XML document from an input stream.
	 * @throws XMLCreationException
	 *             If the XML cannot be created. Either by an IO, parse or configuration error.
	 */
	public static Document createXMLDocument(final InputStream xmlInputStream) throws XMLCreationException {
		// Tries to create the document.
		try {
			// Gets the document builder factory.
			final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			// Creates a document builder.
			final DocumentBuilder documentBuilder = docBuilderFactory.newDocumentBuilder();
			// Parses the stream and returns the document.
			return documentBuilder.parse(xmlInputStream);
		}
		// If a parse error occurs.
		catch (final SAXException exception) {
			// Throws an exception.
			throw new XMLCreationException(ErrorKeys.IMPOSSIBLE_PARSE, exception, null);
		}
		// If an IO exception occurs.
		catch (final IOException exception) {
			// Throws an exception.
			throw new XMLCreationException(ErrorKeys.IO_EXCEPTION, exception, null);
		}
		// If a document builder creation exception occurs.
		catch (final ParserConfigurationException exception) {
			// Throws an exception.
			throw new XMLCreationException(ErrorKeys.INVALID_CONFIG, exception, null);
		}
	}
	
	/**
	 * Creates a new empty XML document.
	 * 
	 * @return A new empty XML document.
	 * @throws XMLCreationException
	 *             if the XML cannot be created for with default configuration.
	 */
	public static Document createXMLDocument() throws XMLCreationException {
		// Tries to create the document.
		try {
			// Gets the document builder factory.
			final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			// Creates a document builder.
			final DocumentBuilder documentBuilder = docBuilderFactory.newDocumentBuilder();
			// Returns a new document.
			return documentBuilder.newDocument();
		}
		// If an exception is thrown.
		catch (final ParserConfigurationException exception) {
			// Throws an exception.
			throw new XMLCreationException(ErrorKeys.INVALID_CONFIG, exception, null);
		}
	}
}
