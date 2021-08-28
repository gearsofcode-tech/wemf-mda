package tech.gearsofcode.emft.thymeleaf.pk;

import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
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

import tech.gearsofcode.emft.metamodel.AttributeAnnotations;
import tech.gearsofcode.emft.metamodel.TypeMapper;

/**
 * Tag that determines what is the Java Type of the Id of a class.
 * @author Carlos Padoa
 *
 */
public class IdJavaTypeTagProcessor extends AbstractAttributeTagProcessor{

	private static final Logger logger = LoggerFactory.getLogger(IdJavaTypeTagProcessor.class);
	private static final String ATTR_NAME = "idJavaType";
	private static final int PRECEDENCE = 10000;
	
	public IdJavaTypeTagProcessor(final String dialectPrefix) {
		super(TemplateMode.TEXT, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue, IElementTagStructureHandler handler) {
		
		final IEngineConfiguration config = context.getConfiguration();
		
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(config);
		
		final IStandardExpression expression = parser.parseExpression(context, attributeValue);
		
		final EClass eClass = (EClass) expression.execute(context);
		
		Optional<EStructuralFeature> optFeat = eClass.getEStructuralFeatures().stream().filter(feat -> feat.getEAnnotation(AttributeAnnotations.ID)!=null).findFirst();
		
		String typeName = "Object";
		
		if (optFeat.isPresent()) {
			typeName = optFeat.get().getEType().getName();
		}
		else {
			logger.warn("Class '" + eClass.getName() +" does not declare an attribute with ID annotation.");
		}
		
		final IModelFactory modelFactory = context.getModelFactory();
		
		final IModel model = modelFactory.createModel();
		
		String javaType = TypeMapper.getJavaType(typeName);
	
		model.add(modelFactory.createText(javaType));
		
		handler.replaceWith(model, false);
	}

}
