package com.kang.batch2.ibmBlog.listener;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

@Slf4j
public class PayStepCheckingListener  extends StepExecutionListenerSupport  {

	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		String exitCode = stepExecution.getExitStatus().getExitCode();
		if (!exitCode.equals(ExitStatus.FAILED.getExitCode())
				&& stepExecution.getSkipCount() > 0) {
			
			log.info("payStep had completed!");
			return new ExitStatus("COMPLETED WITH SKIPS");
			
		} else {
			return null;
		}
	}
	
}
