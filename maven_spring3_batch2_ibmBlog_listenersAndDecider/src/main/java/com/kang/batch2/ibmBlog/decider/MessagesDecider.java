package com.kang.batch2.ibmBlog.decider;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

public class MessagesDecider implements JobExecutionDecider  {

	public FlowExecutionStatus decide(JobExecution jobExecution,
			StepExecution stepExecution) {

		String exitCode = stepExecution.getExitStatus().getExitCode();
		
		if (!exitCode.equals(ExitStatus.FAILED.getExitCode())
				&& stepExecution.getSkipCount() > 0) {
			return new FlowExecutionStatus("COMPLETED WITH SKIPS");
		} else {
			return FlowExecutionStatus.COMPLETED;
		}
	}

	
	
	
}
