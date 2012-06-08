package org.dejava.component.util.i18n.source.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.dejava.component.util.exception.localized.InvalidParameterException;
import org.dejava.component.util.i18n.source.annotation.MessageSource;
import org.dejava.component.util.i18n.source.annotation.MessageSources;
import org.dejava.component.util.i18n.source.model.MessageSourceClassType;
import org.dejava.component.util.reflection.handler.FieldHandler;

/**
 * Annotation processor that processes and creates the defined message source bundles.
 */
@SupportedSourceVersion(value = SourceVersion.RELEASE_7)
@SupportedAnnotationTypes(value = { "org.dejava.component.util.i18n.source.annotation.MessageSources" })
public class MessageSourceProcessor extends AbstractProcessor {
	
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
				// For each defined available locale.
				for (final String currentLocaleText : currentMsgSrc.availableLocales()) {
					// Get the properties content (if any).
					final Properties msgSrcProps = getPropertiesContent(currentMsgSrc.sourcePath(),
							currentMsgSrc.bundleBaseName(), currentLocaleText);
					// For each enclosed elements of the class.
					for (final Element currentClassElement : currentClass.getEnclosedElements()) {
						// For each type of the source defined for the current class.
						for (final MessageSourceClassType currentSrcType : currentMsgSrc.types()) {
							// Current entry.
							String currentEntry = null;
							// Depending on the current type.
							switch (currentSrcType) {
							// If the source type is the name of the annotated class.
								case NAME:
									// The current entry is the class name.
									currentEntry = currentClass.getSimpleName().toString();
									// Finishes the case.
									break;
								// If the source type are the public constants of the class.
								case PUBLIC_CONSTANTS_VALUES:
									// If the element is a public final field.
									if ((currentClassElement.getKind() == ElementKind.FIELD)
											&& (currentClassElement.getModifiers().contains(Modifier.FINAL))
											&& (currentClassElement.getModifiers().contains(Modifier.PUBLIC))) {
										// The current entry is the field value.
										currentEntry = ((VariableElement) currentClassElement)
												.getConstantValue().toString();
									}
									// Finishes the case.
									break;
								// If the source type are the fields of the class.
								case OBJECT_FIELDS:
									// If the element is an object field.
									if ((currentClassElement.getKind() == ElementKind.FIELD)
											&& (!currentClassElement.getModifiers().contains(Modifier.STATIC))) {
										// The current entry is the class name followed by field name.
										currentEntry = currentClass.getSimpleName().toString() + '.'
												+ currentClassElement.getSimpleName().toString();
									}
									// Finishes the case.
									break;
								// If the source type are the public getters of the class.
								case PUBLIC_GETTERS:
									// If the element is a public method.
									if ((currentClassElement.getKind() == ElementKind.METHOD)
											&& (currentClassElement.getModifiers().contains(Modifier.PUBLIC))) {
										// The current entry is the class name followed by the field name.
										try {
											currentEntry = currentClass.getSimpleName().toString()
													+ '.'
													+ FieldHandler.getFieldName(currentClassElement
															.getSimpleName().toString());
										}
										// If it is not a getter.
										catch (final InvalidParameterException exception) {
											// Ignores it.
										}
									}
									// Finishes the case.
									break;
								// If the source type are the public methods of the class.
								case PUBLIC_METHODS:
									// If the element is a public method.
									if ((currentClassElement.getKind() == ElementKind.METHOD)
											&& (currentClassElement.getModifiers().contains(Modifier.PUBLIC))) {
										// The current entry is the class name followed by the method name.
										currentEntry = currentClass.getSimpleName().toString() + '.'
												+ currentClassElement.getSimpleName().toString();
									}
									// Finishes the case.
									break;
								// If the source type are the constants of the enumerated.
								case ENUM_TYPES:
									// If the element is a enumerated type.
									if (currentClassElement.getKind() == ElementKind.ENUM_CONSTANT) {
										// The current entry is the enumerated name followed by the constant
										// name.
										currentEntry = currentClass.getSimpleName().toString() + '.'
												+ currentClassElement.getSimpleName().toString();
									}
									// Finishes the case.
									break;
							}
							// If the entry is defined.
							if (currentEntry != null) {
								// Puts the current entry in lower case.
								currentEntry = currentEntry.toLowerCase();
								// If there is no entry in the properties file for current entry.
								if (msgSrcProps.get(currentEntry) == null) {
									// Creates the entry.
									msgSrcProps.put(currentEntry, "");
								}
							}
						}
					}
					// Saves the properties file content.
					savePropertiesContent(currentMsgSrc.sourcePath(), currentMsgSrc.bundleBaseName(),
							currentLocaleText, msgSrcProps, currentLocaleText);
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
				.getJavaFileObjects("sample/org/dejava/component/util/i18n/sample/constant/ErrorKeys.java");
		
		final CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits1);
		
		// Create a list to hold annotation processors
		final LinkedList<AbstractProcessor> processors = new LinkedList<AbstractProcessor>();
		
		// Add an annotation processor to the list
		processors.add(new MessageSourceProcessor());
		
		// Set the annotation processor to the compiler task
		task.setProcessors(processors);
		
		// Perform the compilation task.
		task.call();
	}
}
