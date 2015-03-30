package com.kang.batch2.partition.partitioner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.BufferedReaderFactory;
import org.springframework.batch.item.file.DefaultBufferedReaderFactory;
import org.springframework.batch.item.file.separator.RecordSeparatorPolicy;
import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component("txtPartitioner")
@Scope("step")
@Slf4j
public class TxtPartitioner implements Partitioner {

	
	@Value("#{jobParameters['inputFilePath']}")
	private Resource resource;
	
	public void setFile(Resource resource) {
		this.resource = resource;
	}

	public Map<String, ExecutionContext> partition(int gridSize) {
		
		
		Map<String,ExecutionContext> result = new HashMap<String,ExecutionContext>();
		
		int range = 501;
		int fromId =0;
		int toId = range;
		int countLine = 0;
		
		try {
			countLine = countLine(resource);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log.info(resource.getFilename()+"总行数:"+countLine);
		
		gridSize = countLine/range + (countLine%range == 0?0:1);
		
		log.info(resource.getFilename()+"分配executionContext个数:"+gridSize);
		
		for(int i =0;i<gridSize;i++){
			
			ExecutionContext ex = new ExecutionContext();
			
			/*log.info("starting thread no." + i);
			log.info("fromId:" + fromId);
			log.info("toId:"+toId);*/
		    
			ex.putInt("fromId", fromId);
			ex.putInt("toId", toId);
			
			ex.putInt("FlatFileItemReader.read.count", fromId);
			ex.putInt("FlatFileItemReader.read.count.max", toId);
			
			ex.putString("name",""+i);
			
			result.put("partition"+i, ex);
			
			/*fromId = toId + 1;
			toId +=range;*/
			
			fromId = fromId+range;
			
			toId = fromId + range;
			
		}
		
		return result;
	
		
		
		
	}

	
	private int countLine(Resource file) throws UnsupportedEncodingException, IOException{

		 Assert.notNull(resource, "Input resource must be set");
		//Assert.notNull(recordSeparatorPolicy, "RecordSeparatorPolicy must be set");
		
		if (!resource.exists()) {
			
			log.warn("Input resource does not exist " + resource.getDescription());
			return 0;
		}

		if (!resource.isReadable()) {
			log.warn("Input resource is not readable " + resource.getDescription());
			return 0;
		}
		
		BufferedReaderFactory bufferedReaderFactory = new DefaultBufferedReaderFactory();

		BufferedReader reader = bufferedReaderFactory.create(resource,"UTF-8");
		
		int linenumber = 0;
		
		while (reader.readLine() != null) {
            linenumber++;
        }
		
		reader.close();
		
		return linenumber;
	}
	
	
}
