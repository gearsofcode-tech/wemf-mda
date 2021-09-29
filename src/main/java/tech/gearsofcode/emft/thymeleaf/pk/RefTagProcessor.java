package tech.gearsofcode.emft.thymeleaf.pk;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
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


/**
 * Tag to help print the primary keys as method parameters.
 * @author SamuraiCharlie
 *
 */
public class RefTagProcessor extends AbstractAttributeTagProcessor{

	private static final String ATTR_NAME = "refpk";
	private static final int PRECEDENCE = 10000;
	
	public RefTagProcessor(final String dialectPrefix) {
		super(TemplateMode.TEXT, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue, IElementTagStructureHandler handler) {
		
		final IEngineConfiguration config = context.getConfiguration();
		
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(config);
		
		final IStandardExpression expression = parser.parseExpression(context, attributeValue);
		
		final EReference eRef = (EReference) expression.execute(context);
		
		final IModelFactory modelFactory = context.getModelFactory();
		
		final IModel model = modelFactory.createModel();
		
		String label = getJavaPrimaryKeys(eRef);
		
	
		model.add(modelFactory.createText(label));
		
		handler.setBody(model, false);
		
	}

	private String getJavaPrimaryKeys(EReference eRef) {
		final EClass eClass = eRef.getEReferenceType();
		StringBuilder strb = new StringBuilder();
		String refName = eRef.getName();
		String dtoVariable = refName.substring(0,1).toLowerCase() + refName.substring(1) + "DTO";
		for (EAttribute attr:  eClass.getEAllAttributes()) {
			if (attr.getEAnnotation(AttributeAnnotations.ID)!=null) {
				if (strb.length()>0)strb.append(", ");
				strb.append(dtoVariable).append(".").append(getter(attr.getName())).append("()");
			}
		}
		return strb.toString();
	}

	private String getter(String name) {
		return "get" + name.substring(0,1).toUpperCase()+name.substring(1);
	}

}
