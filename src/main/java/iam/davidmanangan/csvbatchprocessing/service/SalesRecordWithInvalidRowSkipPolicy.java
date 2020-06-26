package iam.davidmanangan.csvbatchprocessing.service;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;

import iam.davidmanangan.csvbatchprocessing.exception.DateParsingException;
import iam.davidmanangan.csvbatchprocessing.exception.NumberParsingException;

public class SalesRecordWithInvalidRowSkipPolicy implements SkipPolicy{
	private static final Logger log = LoggerFactory.getLogger(SalesRecordWithInvalidRowSkipPolicy.class);
	
	private static final int MAX_SKIP_COUNT = 10;
	
	@Override
	public boolean shouldSkip(Throwable throwable, int skipCount) throws SkipLimitExceededException {
		
		if (throwable instanceof FlatFileParseException && skipCount < MAX_SKIP_COUNT ) {
            
			log.error("Detected Invalid Rows. Please Ensure Sales Records Are In Correct Format.");
			
			return true;
            
        }
		if (throwable instanceof DateParsingException && skipCount < MAX_SKIP_COUNT) {
			
			log.error("Detected Invalid Date Format. Please Ensure Dates follow the Format 'dd.MM.yy'.");
			
			return true;
		}
		if(throwable instanceof NumberParsingException && skipCount < MAX_SKIP_COUNT) {
			log.error("Detected Invalid Number Format. Please Ensure Numbers Are In Their Respective Format.");
			
			return true;			
		}
		
		return false;
	}

}
