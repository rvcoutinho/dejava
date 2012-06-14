package org.dejava.component.util.i18n.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.dejava.component.util.i18n.source.annotation.MessageSource;
import org.dejava.component.util.i18n.source.annotation.MessageSources;
import org.dejava.component.util.i18n.source.processor.MessageSourceEntryProcessor;
import org.dejava.component.util.reflection.ClassMirror;
import org.dejava.component.util.reflection.exception.InvocationException;

/**
 * Annotation processor that processes and creates the defined message source bundles.
 */
@SupportedSourceVersion(value = SourceVersion.RELEASE_7)
@SupportedAnnotationTypes(value = { "org.dejava.component.util.i18n.source.annotation.MessageSources" })
public class MessageSourceCreator extends AbstractProcessor {
	
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
		final Properties msgSrcProps = new Properties();
		// Gets the file for the message source.
		final File msgSrcFile = getMessageSourceFile(sourcePath, bundleBaseName, localeText);
		// Tries to get the current properties file content.
		try {
			// Tries to get the properties file.
			final FileInputStream propsInputStream = new FileInputStream(msgSrcFile);
			// Loads the properties object with the file content.
			msgSrcProps.load(propsInputStream);
			// Closes the input stream.
			propsInputStream.close();
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
			// Creates the output stream for the current properties file.
			final FileOutputStream propsOutputStream = new FileOutputStream(msgSrcFile);
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
	private void addEntries(final String sourcePath, final String bundleBaseName, final String localeText,
			final Set<String> entries, final String description) {
		// Get the properties content (if any).
		final Properties msgSrcProps = getPropertiesContent(sourcePath, bundleBaseName, localeText);
		// For each entry in the set.
		for (final String currentEntry : entries) {
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
	 * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set,
	 *      javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
		// For each class annotated with the message source annotation.
		for (final Element currentClass : roundEnv.getElementsAnnotatedWith(MessageSources.class)) {
			// Gets the annotation information for this class.
			final MessageSources msgSrcs = currentClass.getAnnotation(MessageSources.class);
			// For each message source.
			for (final MessageSource currentMsgSrc : msgSrcs.sources()) {
				// Creates an entry set for the message source.
				final Set<String> entries = new LinkedHashSet<>();
				// For each processor defined for the current message source.
				for (final Class<? extends MessageSourceEntryProcessor> currentProcessorClass : currentMsgSrc
						.processors()) {
					// Tries to add the processed entries for the message source.
					try {
						// Creates an instance for the current processor.
						final MessageSourceEntryProcessor currentProcessor = new ClassMirror<>(
								currentProcessorClass).getConstructor(null).newInstance(null, true);
						// Adds the entries for the current processor to the entry set.
						entries.addAll(currentProcessor.processClass(currentClass));
						// For each defined available locale.
						for (final String currentLocaleText : currentMsgSrc.availableLocales()) {
							// Adds the entries to the current properties file.
							addEntries(currentMsgSrc.sourcePath(), currentMsgSrc.bundleBaseName(),
									currentLocaleText, entries, currentMsgSrc.description());
						}
					}
					// If the current processor cannot be instantiated.
					catch (final InvocationException exception) {
						// Ignores and continues with the left processors and sources.
					}
				}
			}
		}
		// Mark that the message sources annotations have been processed.
		return true;
	}
	
	/**
	 * 
	 * @param args
	 *            cd
	 */
	public static void main(final String... args) {
		// Get an instance of java compiler
		final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		
		// Get a new instance of the standard file manager implementation
		final StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		
		// Get the list of java file objects, in this case we have only one file, TestClass.java
		final Iterable<? extends JavaFileObject> compilationUnits1 = fileManager
				.getJavaFileObjects("test/org/dejava/component/util/i18n/test/constant/InformationKeys.java");
		
		final CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits1);
		
		// Create a list to hold annotation processors
		final LinkedList<AbstractProcessor> processors = new LinkedList<AbstractProcessor>();
		
		// Add an annotation processor to the list
		processors.add(new MessageSourceCreator());
		
		// Set the annotation processor to the compiler task
		task.setProcessors(processors);
		
		// Perform the compilation task.
		task.call();
	}
}
