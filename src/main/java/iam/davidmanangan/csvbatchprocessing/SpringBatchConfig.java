package iam.davidmanangan.csvbatchprocessing;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import iam.davidmanangan.csvbatchprocessing.model.Sales;
import iam.davidmanangan.csvbatchprocessing.model.StoreOrder;
import iam.davidmanangan.csvbatchprocessing.service.CsvBatchProcessingListener;
import iam.davidmanangan.csvbatchprocessing.service.SalesRecordWithInvalidRowSkipPolicy;
import iam.davidmanangan.csvbatchprocessing.service.StoreOrderItemProcessor;

@Configuration
@EnableBatchProcessing
@PropertySource("classpath:application.properties")
public class SpringBatchConfig {
	
	private static final Logger log = LoggerFactory.getLogger(SpringBatchConfig.class);
	
	@Autowired
	public JobBuilderFactory jobBuilder;
	
	@Autowired
	public StepBuilderFactory stepBuilder;
	
    @Value("${file.input}")
    private String fileInput;
    
	@Bean
	public FlatFileItemReader<Sales> reader() {
		log.info("[Process Information] File Input: "+fileInput);
		FlatFileItemReader<Sales> reader = new FlatFileItemReaderBuilder<Sales>()
				.name("storeOrderReader")
				.resource(new ClassPathResource(fileInput))
				.delimited()
				.names(new String[] 
						{
							"rowid"
							,"orderid"
							,"orderdate"
							,"shipdate"
							,"shipmode"
							,"customerid"
							,"customername"
							,"segment"
							,"country"
							,"city"
							,"state"
							,"postalcode"
							,"region"
							,"productid"
							,"category"
							,"subCategory"
							,"productname"
							,"sales"
							,"quantity"
							,"discount"
							,"profit"
						})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Sales>() {{
					setTargetType(Sales.class);
				}})
				.build();
		
		//skip header
		reader.setLinesToSkip(1);
		
		
		return reader;
	}
	
	@Bean
	public StoreOrderItemProcessor processor(){
		return new StoreOrderItemProcessor();
	}
	
	@Bean
	public JdbcBatchItemWriter<StoreOrder> writer(DataSource datasource){
		return new JdbcBatchItemWriterBuilder<StoreOrder>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO STORE_ORDER "
						+ " (ORDER_ID,ORDER_DATE,SHIP_DATE,SHIP_MODE,QUANTITY,DISCOUNT,PROFIT,PRODUCT_ID,CUSTOMER_NAME,CATEGORY,CUSTOMER_ID,PRODUCT_NAME)"
						+ " VALUES "
						+ " (:orderId, :orderDate, :shipDate, :shipMode, :quantity, :discount, :profit, :productId, :customerName, :category, :customerId, :productName)")
				.dataSource(datasource)
				.build();
	}
	
	@Bean
	public Job importStoreOrderJob(CsvBatchProcessingListener listener, Step step1) {
		return jobBuilder.get("importStoreOrderJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1)
				.end()
				.build();
	}
	
	@Bean
	public Step step1(JdbcBatchItemWriter<StoreOrder> writer) {
		return stepBuilder.get("step1")
				.<Sales,StoreOrder> chunk(1)
				.reader(reader())
				.processor(processor())
				.writer(writer)
                .faultTolerant()
                .skipPolicy(new SalesRecordWithInvalidRowSkipPolicy())
				.build();
	}
	
	
	@Primary
	@Bean(name = "salesDatasource")
	public DataSource salesDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		dataSource.setUrl("jdbc:hsqldb:mem:testdb;DB_CLOSE_DELAY=-1");
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");

		return dataSource;
	}
	 
	 @Bean(name = "salesJdbcTemplate")
	 public JdbcTemplate salesJdbcTemplate(@Qualifier("salesDatasource") DataSource ds) {
	  return new JdbcTemplate(ds);
	 }
}
