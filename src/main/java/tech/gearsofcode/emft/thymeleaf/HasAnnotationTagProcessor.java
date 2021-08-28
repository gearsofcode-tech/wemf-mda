package tech.gearsofcode.emft.thymeleaf;

import org.eclipse.emf.ecore.ENamedElement;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.standard.processor.AbstractStandardConditionalVisibilityTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

public class HasAnnotationTagProcessor extends AbstractStandardConditionalVisibilityTagProcessor{

	private static final String attrName = "hasAnnotation";
	private static final int PRECEDENCE = 10000;
	
	protected HasAnnotationTagProcessor(final String dialectPrefix) {
		super(TemplateMode.TEXT, dialectPrefix, attrName, PRECEDENCE);
	}

	@Override
	protected boolean isVisible(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue) {
		final IEngineConfiguration config = context.getConfiguration();

		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(config);

		final IStandardExpression expression = parser.parseExpression(context, attributeValue);

		final ENamedElement emfTypedElement = (ENamedElement) expression.execute(context);
		
		String source = tag.getAttributeValue("source");
		
		return emfTypedElement.getEAnnotations().stream().filter(x -> source.equals(x.getSource())).findAny().isPresent();
	}

}
