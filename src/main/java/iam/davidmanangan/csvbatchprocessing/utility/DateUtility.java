package iam.davidmanangan.csvbatchprocessing.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

import iam.davidmanangan.csvbatchprocessing.exception.DateParsingException;


public class DateUtility {

	
	public static final Date parseDate(String dateString) throws DateParsingException {
		
		try {
			if(StringUtils.hasText(dateString)) {
				
				SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yy");
				
				return dateFormatter.parse(dateString);				
			}

			
		} catch (ParseException e) {
			
			throw new DateParsingException("Date Parsing Error");
		}
		
		return null;
	}
	
}
