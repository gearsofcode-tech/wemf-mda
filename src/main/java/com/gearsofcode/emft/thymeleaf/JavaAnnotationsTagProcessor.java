package com.gearsofcode.emft.thymeleaf;

import java.util.Iterator;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Tag that prints all the EMF Annotations as Java Annotations.
 * @author Carlos Padoa
 *
 */
public class JavaAnnotationsTagProcessor extends AbstractAttributeTagProcessor{

	private static final Logger logger = LoggerFactory.getLogger(IdJavaTypeTagProcessor.class);
	private static final String ATTR_NAME = "javaAnnotations";
	private static final int PRECEDENCE = 10000;
	
	public JavaAnnotationsTagProcessor(final String dialectPrefix) {
		super(TemplateMode.TEXT, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue, IElementTagStructureHandler handler) {
		
		final IEngineConfiguration config = context.getConfiguration();
		
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(config);
		
		final IStandardExpression expression = parser.parseExpression(context, attributeValue);
		
		final EModelElement eElem = (EModelElement) expression.execute(context);
		
		StringBuilder strb = new StringBuilder();
		for (EAnnotation eAnnot : eElem.getEAnnotations()) {
			if (eElem instanceof EClass) {
				strb.append("\n");
			}
			else {
				strb.append("\n\t");
			}
			strb.append("@").append(eAnnot.getSource());
			if (!eAnnot.getDetails().isEmpty()) {
				strb.append("(");
				Iterator<Entry<String, String>> it = eAnnot.getDetails().iterator();
				Entry<String, String> entry;
				while (it.hasNext()) {
					entry = it.next();
					strb.append(entry.getKey());
					strb.append("=\"");
					strb.append(entry.getValue());
					strb.append("\"");
					if (it.hasNext()) strb.append(", ");
				}
				strb.append(")");
			}
		}
		
		final IModelFactory modelFactory = context.getModelFactory();
		
		final IModel model = modelFactory.createModel();
		
		model.add(modelFactory.createText(strb.toString()));
		
		handler.replaceWith(model, false);
	}

}
