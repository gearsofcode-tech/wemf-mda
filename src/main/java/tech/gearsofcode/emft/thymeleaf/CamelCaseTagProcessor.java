package tech.gearsofcode.emft.thymeleaf;

import org.eclipse.emf.ecore.ENamedElement;
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
 * Tag to capitalize the first letter of a string.
 * @author Carlos Padoa
 *
 */
public class CamelCaseTagProcessor extends AbstractAttributeTagProcessor{

	private static final String ATTR_NAME = "cc";
	private static final int PRECEDENCE = 10000;
	
	public CamelCaseTagProcessor(final String dialectPrefix) {
		super(TemplateMode.TEXT, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue, IElementTagStructureHandler handler) {
		
		final IEngineConfiguration config = context.getConfiguration();
		
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(config);
		
		final IStandardExpression expression = parser.parseExpression(context, attributeValue);
		
		final ENamedElement emfNamedElement = (ENamedElement) expression.execute(context);
		
		final String elementName = emfNamedElement.getName();
		
		final IModelFactory modelFactory = context.getModelFactory();
		
		final IModel model = modelFactory.createModel();
		
		model.add(modelFactory.createText(elementName.substring(0, 1).toUpperCase()+elementName.substring(1)));
		
		handler.replaceWith(model, false);
	}

}
