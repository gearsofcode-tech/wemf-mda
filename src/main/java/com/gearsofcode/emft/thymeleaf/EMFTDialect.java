package com.gearsofcode.emft.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.gearsofcode.emft.thymeleaf.pk.IdJavaTypeTagProcessor;
import com.gearsofcode.emft.thymeleaf.pk.JSTagProcessor;
import com.gearsofcode.emft.thymeleaf.pk.MethodDeclarationTagProcessor;
import com.gearsofcode.emft.thymeleaf.pk.RestPathTagProcessor;
import com.gearsofcode.emft.thymeleaf.pk.RestPathVariableTagProcessor;
import com.gearsofcode.emft.thymeleaf.pk.MethodParameterTagProcessor;
import com.gearsofcode.emft.thymeleaf.pk.DTOTagProcessor;
import com.gearsofcode.emft.thymeleaf.pk.FindByPrimaryKeyTagProcessor;

/**
 * 
 * Thymeleaf dialect with helper tags to generate code.
 * @author Carlos Padoa
 *
 */
public class EMFTDialect extends AbstractProcessorDialect{

	private static final String DIALECT_NAME = "EMFT Dialect";
	private static final String PREFIX = "emft";
	
	public EMFTDialect() {
		super(DIALECT_NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String arg0) {
		final Set<IProcessor> processors = new HashSet<IProcessor>();
		processors.add(new LabelTagProcessor(PREFIX));
		processors.add(new JavaTypeTagProcessor(PREFIX));
		processors.add(new JavaDTOTypeTagProcessor(PREFIX));
		processors.add(new CamelCaseTagProcessor(PREFIX));
		
		processors.add(new ImportJavaListTagProcessor(PREFIX));
		processors.add(new JavaAnnotationsTagProcessor(PREFIX));
		processors.add(new DescriptionTagProcessor(PREFIX));
		processors.add(new URLPrimaryKeyTagProcessor(PREFIX));
		
		processors.add(new LblTagProcessor(PREFIX));
		processors.add(new ConstantTagProcessor(PREFIX));
		processors.add(new LowerCamelCaseTagProcessor(PREFIX));
		processors.add(new DashCaseTagProcessor(PREFIX));
		processors.add(new FieldValueTagProcessor(PREFIX));
		processors.add(new HasAnnotationTagProcessor(PREFIX));
		processors.add(new HasNoAnnotationTagProcessor(PREFIX));
		processors.add(new ReactPrimaryKeyParameterTagProcessor(PREFIX));
		
		//Primary key
		processors.add(new DTOTagProcessor(PREFIX));
		processors.add(new IdJavaTypeTagProcessor(PREFIX));
		processors.add(new MethodDeclarationTagProcessor(PREFIX));
		processors.add(new MethodParameterTagProcessor(PREFIX));
		processors.add(new RestPathTagProcessor(PREFIX));
		processors.add(new RestPathVariableTagProcessor(PREFIX));
		processors.add(new FindByPrimaryKeyTagProcessor(PREFIX));
		processors.add(new JSTagProcessor(PREFIX));
		return processors;
	}



}
