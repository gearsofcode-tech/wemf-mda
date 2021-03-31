package com.gearsofcode.emft.thymeleaf.pk;

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

import com.gearsofcode.emft.metamodel.AttributeAnnotations;




/**
 * Tag to help print the JPA query to find by primary keys.
 * @author Carlos Padoa
 *
 */
public class FindByPrimaryKeyTagProcessor extends AbstractAttributeTagProcessor{

	private static final String ATTR_NAME = "findbypk";
	private static final int PRECEDENCE = 10000;
	
	public FindByPrimaryKeyTagProcessor(final String dialectPrefix) {
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
		
		String label = getFindByPrimaryKeys(eClass);
		
	
		model.add(modelFactory.createText(label));
		
		handler.setBody(model, false);
		
	}

	private String getFindByPrimaryKeys(EClass eClass) {
		String typedQueryFormat = String.format("TypedQuery<%1$s> typedQuery = em.createQuery(\"from %1$s x where ", eClass.getName());
		StringBuilder strb = new StringBuilder(typedQueryFormat);
		StringBuilder strbWhere = new StringBuilder();
		for (EAttribute attr:  eClass.getEAllAttributes()) {
			if (attr.getEAnnotation(AttributeAnnotations.ID)!=null) {
				if (strbWhere.length()>0)strb.append(" and ");
				strbWhere.append("x.").append(attr.getName()).append(" = :").append(attr.getName());
			}
		}
		strb.append(strbWhere.toString());
		strb.append("\", ").append(eClass.getName()).append(".class);");
		
		for (EAttribute attr:  eClass.getEAllAttributes()) {
			if (attr.getEAnnotation(AttributeAnnotations.ID)!=null) {
				strb.append("\n\t\ttypedQuery.setParameter(\"").append(attr.getName()).append("\", ").append(attr.getName()).append(");");
			}
		}		
		
		strb.append("\n\t\treturn typedQuery.getSingleResult();");
		return strb.toString();
	}
}
