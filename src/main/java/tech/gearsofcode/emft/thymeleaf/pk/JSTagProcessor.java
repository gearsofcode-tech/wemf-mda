package tech.gearsofcode.emft.thymeleaf.pk;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * Tag to help print the concatenation of primary keys as Javascript code.
 * @author SamuraiCharlie
 *
 */
public class JSTagProcessor extends AbstractAttributeTagProcessor{

	private static final String ATTR_NAME = "jspk";
	private static final int PRECEDENCE = 10000;
	
	public JSTagProcessor(final String dialectPrefix) {
		super(TemplateMode.TEXT, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue, IElementTagStructureHandler handler) {
		
		final IEngineConfiguration config = context.getConfiguration();
		
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(config);
		
		final IStandardExpression expression = parser.parseExpression(context, attributeValue);
		
		final EClass eClass = (EClass) expression.execute(context);
		
		final IModelFactory modelFactory = context.getModelFactory();
		
		final IModel model = modelFactory.createModel();
		
		String label = getJavaPrimaryKeys(eClass);
		
	
		model.add(modelFactory.createText(label));
		
		handler.setBody(model, false);
		
	}

	private String getJavaPrimaryKeys(EClass eClass) {
		String entityName = eClass.getName().substring(0,1).toLowerCase()+eClass.getName().substring(1);
		StringBuilder strb = new StringBuilder();
		for (EAttribute attr:  eClass.getEAllAttributes()) {
			if (attr.getEAnnotation(AttributeAnnotations.ID)!=null) {
				if (strb.length()>0)strb.append("+");
				strb.append(entityName).append(".").append(attr.getName());
			}
		}
		return strb.toString();
	}
}
