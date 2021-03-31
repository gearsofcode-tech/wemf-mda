package com.gearsofcode.emft.thymeleaf;

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



public class FieldValueTagProcessor extends AbstractAttributeTagProcessor {

	private static final String ATTR_NAME = "field";
	private static final int PRECEDENCE = 10000;



	public FieldValueTagProcessor(final String dialectPrefix) {
		super(TemplateMode.TEXT, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
	}



	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue, IElementTagStructureHandler handler) {

		final IEngineConfiguration config = context.getConfiguration();

		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(config);

		final IStandardExpression expression = parser.parseExpression(context, attributeValue);

		final ETypedElement emfTypedElement = (ETypedElement) expression.execute(context);

		String value = getRandomValueForField(emfTypedElement);

		final IModelFactory modelFactory = context.getModelFactory();

		final IModel model = modelFactory.createModel();

		model.add(modelFactory.createText(value));

		handler.replaceWith(model, false);
	}



	private static String getRandomValueForField(ETypedElement attr) {
		String typeName = attr.getEType().getName();
		if (attr.getEType() instanceof EEnum){
			String capitalized = typeName.substring(0, 1).toUpperCase()+typeName.substring(1);
			return capitalized + ".values()[0]";
		}
		switch (typeName) {
			case "EDate":
			case "EBigDecimal":
			case "EString":
				return "\"" + attr.getName() + "\"";
			case "EBoolean":
				return "Boolean.TRUE";
			case "EInt":
			case "EIntegerObject":
				return "999";
			case "ELong":
				return "888L";
		}
		if (attr.getUpperBound()==ETypedElement.UNBOUNDED_MULTIPLICITY||attr.getUpperBound()>1){
			return "new LinkedList<" + typeName + ">()";
		}
		return "new " + typeName + "()";
	}
}
