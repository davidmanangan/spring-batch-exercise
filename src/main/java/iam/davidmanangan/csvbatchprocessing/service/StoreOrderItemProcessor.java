package iam.davidmanangan.csvbatchprocessing.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import iam.davidmanangan.csvbatchprocessing.exception.NumberParsingException;
import iam.davidmanangan.csvbatchprocessing.model.Sales;
import iam.davidmanangan.csvbatchprocessing.model.StoreOrder;
import iam.davidmanangan.csvbatchprocessing.utility.DateUtility;

public class StoreOrderItemProcessor implements ItemProcessor<Sales,StoreOrder>{

	private static final Logger log = LoggerFactory.getLogger(StoreOrderItemProcessor.class);
	
	@Override
	public StoreOrder process(final Sales sales) throws Exception {
		
		String orderId= StringUtils.hasText(sales.getOrderid()) ? sales.getOrderid()+"-"+sales.getRowid() : "NO_ORDER_ID-"+sales.getRowid();
		String productId = StringUtils.hasText(sales.getProductid()) ? sales.getProductid()+"-"+sales.getRowid() : "NO_PRODUCT_ID-"+sales.getRowid();
		String customerId = StringUtils.hasText(sales.getCustomerid()) ? sales.getCustomerid()+"-"+sales.getRowid() : "NO_CUSTOMER_ID-"+sales.getRowid();

		log.info("[StoreOrderItemProcessor] Order Id : "+orderId);	
		
		final StoreOrder transformedStoreOrder;

		try {
			transformedStoreOrder 
				= new StoreOrder(
					orderId,
					sales.getOrderdate() != null ? DateUtility.parseDate(sales.getOrderdate())  : null,
					sales.getShipdate() != null ? DateUtility.parseDate(sales.getShipdate()) : null,
					sales.getShipmode(),
					Integer.valueOf(sales.getQuantity()),
					new BigDecimal(sales.getDiscount()),
					new BigDecimal(sales.getProfit()),
					productId,
					sales.getCustomername(),
					sales.getCategory(),
					customerId,
					sales.getProductname()
			);
			
		}catch(NumberFormatException e) {
			throw new NumberParsingException("Number Parsing Error");
		}

		
				
		return transformedStoreOrder;
	}
	
}
