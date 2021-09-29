package tech.gearsofcode.nail.thymeleaf;

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
 * Tag to decapitalize the first letter of a string.
 * @author SamuraiCharlie
 *
 */
public class LowerCamelCaseTagProcessor extends AbstractAttributeTagProcessor{

	private static final String ATTR_NAME = "lcc";
	private static final int PRECEDENCE = 10000;
	
	public LowerCamelCaseTagProcessor(final String dialectPrefix) {
		super(TemplateMode.TEXT, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue, IElementTagStructureHandler handler) {
		
		final IEngineConfiguration config = context.getConfiguration();
		
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(config);
		
		final IStandardExpression expression = parser.parseExpression(context, attributeValue);
		
		final String elementName = (String) expression.execute(context);
		
		final IModelFactory modelFactory = context.getModelFactory();
		
		final IModel model = modelFactory.createModel();
		
		model.add(modelFactory.createText(elementName.substring(0, 1).toLowerCase()+elementName.substring(1)));
		
		handler.replaceWith(model, false);
	}

}
