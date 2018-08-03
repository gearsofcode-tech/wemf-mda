package com.gearsofcode.emft.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

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
		processors.add(new IdJavaTypeTagProcessor(PREFIX));
		processors.add(new ImportJavaListTagProcessor(PREFIX));
		processors.add(new JavaAnnotationsTagProcessor(PREFIX));
		processors.add(new DescriptionTagProcessor(PREFIX));
		processors.add(new URLPrimaryKeyTagProcessor(PREFIX));
		processors.add(new JavaPrimaryKeyParameterTagProcessor(PREFIX));
		return processors;
	}



}
