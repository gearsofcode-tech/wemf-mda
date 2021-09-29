package tech.gearsofcode.nail.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

/**
 * 
 * Thymeleaf dialect with helper tags to generate code.
 * @author SamuraiCharlie
 *
 */
public class NAILDialect extends AbstractProcessorDialect{

	private static final String DIALECT_NAME = "EMFT Dialect";
	private static final String PREFIX = "nail";
	
	public NAILDialect() {
		super(DIALECT_NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String arg0) {
		final Set<IProcessor> processors = new HashSet<IProcessor>();
		processors.add(new LowerCamelCaseTagProcessor(PREFIX));
		processors.add(new CamelCaseTagProcessor(PREFIX));
		return processors;
	}



}
