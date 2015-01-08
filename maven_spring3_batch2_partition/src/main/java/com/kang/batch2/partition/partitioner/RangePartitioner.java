package com.kang.batch2.partition.partitioner;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Component("rangePartitioner")
@Slf4j
public class RangePartitioner implements Partitioner {

	public Map<String, ExecutionContext> partition(int gridSize) {
		
		Map<String,ExecutionContext> result = new HashMap<String,ExecutionContext>();
		
		int range = 10;
		int fromId = 1;
		int toId = range;
		
		for(int i =0;i<gridSize;i++){
			
			ExecutionContext ex = new ExecutionContext();
			
			log.info("starting thread no." + i);
			log.info("fromId:" + fromId);
			log.info("toId:"+toId);
		    
			ex.putInt("fromId", fromId);
			ex.putInt("toId", toId);
			
			ex.putString("name","Thread."+i);
			
			result.put("partition"+i, ex);
			
			fromId = toId + 1;
			toId +=range;
		}
		
		return result;
	}

}
