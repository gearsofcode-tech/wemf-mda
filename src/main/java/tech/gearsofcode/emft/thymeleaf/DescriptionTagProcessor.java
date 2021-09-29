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
 * Tag to help print a label for a given field..
 * @author SamuraiCharlie
 *
 */
public class DescriptionTagProcessor extends AbstractAttributeTagProcessor{

	private static final String ATTR_NAME = "description";
	private static final int PRECEDENCE = 10000;
	
	public DescriptionTagProcessor(final String dialectPrefix) {
		super(TemplateMode.TEXT, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue, IElementTagStructureHandler handler) {
		
		final IEngineConfiguration config = context.getConfiguration();
		
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(config);
		
		final IStandardExpression expression = parser.parseExpression(context, attributeValue);
		
		final ENamedElement emfTypedElement = (ENamedElement) expression.execute(context);
		
		String typeName = emfTypedElement.getName();
		
		final IModelFactory modelFactory = context.getModelFactory();
		
		final IModel model = modelFactory.createModel();
		
		String label = getDescription(typeName);
		
	
		model.add(modelFactory.createText(label));
		
		handler.setBody(model, false);
		
	}

	private String getDescription(String typeName) {
		StringBuilder strb = new StringBuilder();
		for(int i=0; i<typeName.length();i++) {
			if(i==0) {
				strb.append(typeName.substring(0, 1).toUpperCase());
			}
			else {
				if(typeName.substring(i,i+1).matches("[A-Z]") && typeName.substring(i-1,i).matches("[a-z]")){
					strb.append(" ");
					strb.append(typeName.substring(i, i+1).toUpperCase());
				} 
				else {
					strb.append(typeName.substring(i, i+1).toLowerCase());
				}
			}
		}
		return strb.toString();
	}

}
