package iam.davidmanangan.csvbatchprocessing.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import iam.davidmanangan.csvbatchprocessing.model.StoreOrder;

@Component
public class CsvBatchProcessingListener extends JobExecutionListenerSupport{

	
	public static final Logger log = LoggerFactory.getLogger(CsvBatchProcessingListener.class);
	
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public CsvBatchProcessingListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}	
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("CHECK TABLE ROW SIZE: "+jdbcTemplate.query("SELECT COUNT(*) FROM STORE_ORDER", (rs,row) -> rs.getInt(1)).get(0));

		
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("Job Completed!");
			
			jdbcTemplate.query("SELECT ORDER_ID,ORDER_DATE,SHIP_DATE,SHIP_MODE,QUANTITY,DISCOUNT,PROFIT,PRODUCT_ID,CUSTOMER_NAME,CATEGORY,CUSTOMER_ID,PRODUCT_NAME FROM STORE_ORDER",
					(rs,row)->
						new StoreOrder(
								rs.getString(1),
								rs.getDate(2),
								rs.getDate(3),
								rs.getString(4),
								rs.getInt(5),
								rs.getBigDecimal(6),
								rs.getBigDecimal(7),
								rs.getString(8),
								rs.getString(9),
								rs.getString(10),
								rs.getString(11),
								rs.getString(12))
						).forEach(order ->
							log.info(String.format("Found <%s,%s,%s> in database",order.getOrderId(),order.getProductId(),order.getCustomerId()))
						);
				
		}
	}
	
	
	
	
}
