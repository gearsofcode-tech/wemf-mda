package tech.gearsofcode.emft.thymeleaf;

import org.eclipse.emf.ecore.ETypedElement;
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

import tech.gearsofcode.emft.metamodel.TypeMapper;


/**
 * Tag to help print the Java type correspondent to a EMF Type.
 * @author SamuraiCharlie
 *
 */
public class TypescriptTypeTagProcessor extends AbstractAttributeTagProcessor{

	private static final String ATTR_NAME = "typescriptType";
	private static final int PRECEDENCE = 10000;
	
	public TypescriptTypeTagProcessor(final String dialectPrefix) {
		super(TemplateMode.TEXT, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue, IElementTagStructureHandler handler) {
		
		final IEngineConfiguration config = context.getConfiguration();
		
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(config);
		
		final IStandardExpression expression = parser.parseExpression(context, attributeValue);
		
		final ETypedElement emfTypedElement = (ETypedElement) expression.execute(context);
		
		String typeName = emfTypedElement.getEType().getName();
		
		final IModelFactory modelFactory = context.getModelFactory();
		
		final IModel model = modelFactory.createModel();
		
		String javaType = TypeMapper.getTypescriptType(typeName);
		
	
		model.add(modelFactory.createText(javaType));
		
		handler.replaceWith(model, false);
	}

}
