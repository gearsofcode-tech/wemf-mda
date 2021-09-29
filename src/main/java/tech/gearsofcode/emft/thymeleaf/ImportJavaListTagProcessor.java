package tech.gearsofcode.emft.thymeleaf;

import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
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

/**
 * Tag that imports java.util.List only if it is necessary.
 * @author SamuraiCharlie
 *
 */
public class ImportJavaListTagProcessor extends AbstractAttributeTagProcessor{

	private static final String ATTR_NAME = "importJavaList";
	private static final int PRECEDENCE = 10000;
	
	public ImportJavaListTagProcessor(final String dialectPrefix) {
		super(TemplateMode.TEXT, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue, IElementTagStructureHandler handler) {
		
		final IEngineConfiguration config = context.getConfiguration();
		
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(config);
		
		final IStandardExpression expression = parser.parseExpression(context, attributeValue);
		
		final EClass eClass = (EClass) expression.execute(context);
		
		Optional<EStructuralFeature> optFeat = eClass.getEStructuralFeatures().stream().filter(feat -> feat.getUpperBound()>1 || feat.getUpperBound()==ETypedElement.UNBOUNDED_MULTIPLICITY).findFirst();
		
		if (optFeat.isPresent()) {
			final IModelFactory modelFactory = context.getModelFactory();
			
			final IModel model = modelFactory.createModel();
			
			model.add(modelFactory.createText("import java.util.List;"));
			
			handler.replaceWith(model, false);
		}
	}

}
