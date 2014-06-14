package org.dejava.component.i18n.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.TypeMirror;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.component.i18n.source.processor.MessageSourceEntryProcessor;
import org.dejava.component.i18n.source.util.SortedProperties;

/**
 * Annotation processor that processes and creates the defined message source bundles (and keys).
 */
@SupportedSourceVersion(value = SourceVersion.RELEASE_7)
@SupportedAnnotationTypes(value = { "org.dejava.component.i18n.source.annotation.MessageSources" })
public class MessageSourceCreator extends AbstractProcessor {

	/**
	 * The Unicode charset.
	 */
	private static final Charset UNICODE_CHARSET = Charset.forName("UTF-8");

	/**
	 * The Latin-1 charset.
	 */
	private static final Charset LATIN1_CHARSET = Charset.forName("ISO-8859-1");

	/**
	 * Gets the message source properties file.
	 * 
	 * @param sourcePath
	 *            The source path where the source files should be created.
	 * @param bundleBaseName
	 *            The base name of the message source bundle.
	 * @param localeText
	 *            The locale of the message source bundle.
	 * @return The message source properties file.
	 */
	private File getMessageSourceFile(final String sourcePath, final String bundleBaseName,
			final String localeText) {
		// Creates the file object for the properties file.
		return new File(sourcePath + "/" + bundleBaseName.replace('.', '/') + "_" + localeText
				+ ".properties");
	}

	/**
	 * Gets the properties with its current file content.
	 * 
	 * @param sourcePath
	 *            The source path where the source files should be created.
	 * @param bundleBaseName
	 *            The base name of the message source bundle.
	 * @param localeText
	 *            The locale of the message source bundle.
	 * @return The properties with its current file content.
	 */
	private Properties getPropertiesContent(final String sourcePath, final String bundleBaseName,
			final String localeText) {
		// Creates a new properties object.
		final Properties msgSrcProps = new SortedProperties();
		// Gets the file for the message source.
		final File msgSrcFile = getMessageSourceFile(sourcePath, bundleBaseName, localeText);
		// Tries to get the current properties file content.
		try {
			// Tries to get the properties file.
			final Reader propsReader = new InputStreamReader(new FileInputStream(msgSrcFile), LATIN1_CHARSET);
			// Loads the properties object with the file content.
			msgSrcProps.load(propsReader);
			// Closes the input stream.
			propsReader.close();
		}
		// If no file is found.
		catch (final IOException exception) {
			// Ignores it.
		}
		// Returns the properties object.
		return msgSrcProps;
	}

	/**
	 * Saves the properties content into the appropriate file.
	 * 
	 * @param sourcePath
	 *            The source path where the source files should be created.
	 * @param bundleBaseName
	 *            The base name of the message source bundle.
	 * @param localeText
	 *            The locale of the message source bundle.
	 * @param msgSrcProps
	 *            Message properties content.
	 * @param description
	 *            Description of the message properties file.
	 */
	private void savePropertiesContent(final String sourcePath, final String bundleBaseName,
			final String localeText, final Properties msgSrcProps, final String description) {
		// Gets the file for the message source.
		final File msgSrcFile = getMessageSourceFile(sourcePath, bundleBaseName, localeText);
		// Gets the message source directory path.
		final String msgSrcDirPath = msgSrcFile.getPath()
				.substring(0, msgSrcFile.getPath().lastIndexOf('\\'));
		// If the directory path is not empty.
		if (!msgSrcDirPath.isEmpty()) {
			// Creates the message directory object.
			final File msgSrcDir = new File(msgSrcDirPath);
			// Creates the directories (if necessary).
			msgSrcDir.mkdirs();
		}
		// Tries to update the properties file content.
		try {
			// Creates the writer for the current properties file.
			final Writer propsOutputStream = new OutputStreamWriter(new FileOutputStream(msgSrcFile),
					LATIN1_CHARSET);
			// Store the new data for the properties file.
			msgSrcProps.store(propsOutputStream, description);
			// Closes the output stream.
			propsOutputStream.close();
		}
		// If no file is found.
		catch (final IOException exception) {
			// Ignores it.
		}
	}

	/**
	 * Adds the given entries to the desired message source properties file.
	 * 
	 * @param sourcePath
	 *            The source path where the source files should be created.
	 * @param bundleBaseName
	 *            The base name of the message source bundle.
	 * @param localeText
	 *            The locale of the message source bundle.
	 * @param entries
	 *            The entries for the message source properties.
	 * @param description
	 *            Description of the message properties file.
	 */
	private void addEntriesToFile(final String sourcePath, final String bundleBaseName,
			final String localeText, final Set<String> entries, final String description) {
		// Get the properties content (if any).
		final Properties msgSrcProps = getPropertiesContent(sourcePath, bundleBaseName, localeText);
		// For each entry in the set.
		for (String currentEntry : entries) {
			// The current entry must be lower case.
			currentEntry = currentEntry.toLowerCase();
			// If the entry does not exist.
			if (msgSrcProps.get(currentEntry) == null) {
				// Creates the entry.
				msgSrcProps.put(currentEntry, "");
			}
		}
		// Saves the properties file content.
		savePropertiesContent(sourcePath, bundleBaseName, localeText, msgSrcProps, description);
	}

	/**
	 * Gets the classes (given class e possible super classes) that should be processed.
	 * 
	 * @param clazz
	 *            The class being processed.
	 * @param processSuperClasses
	 *            If the super classes should be processed.
	 * @return The classes (given class e possible super classes) that should be processed.
	 */
	private Collection<TypeElement> getClassesToProcess(final TypeElement clazz,
			final Boolean processSuperClasses) {
		// Creates a list with the classes to process.
		final ArrayList<TypeElement> classesToProcess = new ArrayList<>();
		// Adds the given class to the list.
		classesToProcess.add(clazz);
		// If the super classes should be processed.
		if (processSuperClasses) {
			// For each super class,
			for (TypeMirror currentSuperClassMirror = clazz.getSuperclass(); (currentSuperClassMirror == null)
					|| !(currentSuperClassMirror instanceof NoType);) {
				// If the type is a class.
				if (currentSuperClassMirror instanceof DeclaredType) {
					// Gets the class element.
					final TypeElement currentSuperClass = (TypeElement) ((DeclaredType) currentSuperClassMirror)
							.asElement();
					// If the class is not Object.
					if (!Object.class.getName().equals(currentSuperClass.getQualifiedName().toString())) {
						// Adds the super class in the list.
						classesToProcess.add(currentSuperClass);
					}
					// The current super class is now the next one.
					currentSuperClassMirror = currentSuperClass.getSuperclass();
				}
				// If it is not.
				else {
					// The next super class is null.
					currentSuperClassMirror = null;
				}
			}
		}
		// Returns the classes to be processed.
		return classesToProcess;
	}

	/**
	 * Process the original entries to append the given affix to each entry.
	 * 
	 * @param originalEntries
	 *            The original entry set.
	 * @param entriesAffix
	 *            The affix to be used.
	 * @return The entries in the original set appended with each given affix.
	 */
	private Set<String> processEntriesAffix(final Set<String> originalEntries, final String[] entriesAffix) {
		// Creates a new entry set.
		Set<String> finalEntries = new TreeSet<>();
		// If the set is not null.
		if (originalEntries != null) {
			// If the affix array is null or empty.
			if ((entriesAffix == null) || (entriesAffix.length == 0)) {
				// The final set is the original set.
				finalEntries = originalEntries;
			}
			// If there is any affix.
			else {
				// For each entry in the set.
				for (final String currentEntry : originalEntries) {
					// Adds the current entry without an affix.
					finalEntries.add(currentEntry);
					// For each given affix.
					for (final String currentAffix : entriesAffix) {
						// Adds the current entry with current affix.
						finalEntries.add(currentEntry + currentAffix);
					}
				}
			}
		}
		// Returns the final set.
		return finalEntries;
	}

	/**
	 * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set,
	 *      javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
		// For each class annotated with the message source annotation.
		for (final Element currentClass : roundEnv.getElementsAnnotatedWith(MessageSources.class)) {
			// Gets the annotation information for this class.
			final MessageSources msgSrcs = currentClass.getAnnotation(MessageSources.class);
			// If there are message sources.
			if (msgSrcs != null) {
				// For each message source.
				for (final MessageSource currentMsgSrc : msgSrcs.sources()) {
					// Creates an entry set for the message source.
					final Set<String> entries = new LinkedHashSet<>();
					// For each processor defined for the current message source.
					for (final String currentProcessorClassName : currentMsgSrc.processors()) {
						// Tries to add the processed entries for the message source.
						try {
							// Creates an instance for the current processor.
							final MessageSourceEntryProcessor currentProcessor = (MessageSourceEntryProcessor) Class
									.forName(currentProcessorClassName).newInstance();
							// For each class to be processed.
							for (final TypeElement currentClassToProcess : getClassesToProcess(
									(TypeElement) currentClass, currentMsgSrc.processSuperclasses())) {
								// Gets the processed entries.
								final Set<String> processedEntries = currentProcessor.processClass(
										(TypeElement) currentClass, currentClassToProcess);
								// Adds the entries for the current processor to the entry set.
								entries.addAll(processEntriesAffix(processedEntries,
										currentMsgSrc.entriesAffix()));
								// For each defined available locale.
								for (final String currentLocaleText : currentMsgSrc.availableLocales()) {
									// Adds the entries to the current properties file.
									addEntriesToFile(currentMsgSrc.sourcePath(),
											currentMsgSrc.bundleBaseName(), currentLocaleText, entries,
											currentMsgSrc.description());
								}
							}
						}
						// If any exception is raised.
						catch (final Exception exception) {
							// Ignores and continues with the left processors and sources.
						}
					}
				}
			}
		}
		// Mark that the message sources annotations have been processed.
		return true;
	}
}
