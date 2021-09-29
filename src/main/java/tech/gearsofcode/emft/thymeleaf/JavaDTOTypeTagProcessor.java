package tech.gearsofcode.emft.thymeleaf;

import org.eclipse.emf.ecore.EEnum;
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
 * Tag to help print the Java type (of a Data Transfer Object) correspondent to a EMF Type.
 * @author SamuraiCharlie
 *
 */
public class JavaDTOTypeTagProcessor extends AbstractAttributeTagProcessor{

	private static final String ATTR_NAME = "javaDTOType";
	private static final int PRECEDENCE = 10000;
	
	public JavaDTOTypeTagProcessor(final String dialectPrefix) {
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
		
		String javaType = TypeMapper.getJavaDTOType(typeName);
		
		boolean hasDTOAnnotation = emfTypedElement.getEType().getEAnnotations().stream().filter(annot -> "DTO".equals(annot.getSource())).findAny().isPresent();
		
		if (emfTypedElement.getEType() instanceof EEnum || hasDTOAnnotation) javaType = typeName;
	
		model.add(modelFactory.createText(javaType));
		
		handler.replaceWith(model, false);
	}

}
