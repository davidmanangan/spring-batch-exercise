package iam.davidmanangan.csvbatchprocessing;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;
import org.springframework.batch.core.BatchStatus;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import iam.davidmanangan.csvbatchprocessing.service.CsvBatchProcessingListener;


/**
 * Test Cases Include:
 * 1. salestest.csv having corrupted line
 * 2. salestest.csv having invalid date format
 * 
 * @author davidmanangan
 *
 */
@RunWith(SpringRunner.class)
@SpringBatchTest
@EnableAutoConfiguration
@ContextConfiguration(classes = { SpringBatchConfig.class,CsvBatchProcessingListener.class })
@TestPropertySource(properties ="file.input=salestest.csv")
@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:/schema-all.sql")
@ExtendWith(SpringExtension.class)
class CsvBatchProcessingApplicationTests {
    
    @Value("${file.input}")
    private String fileInput = "salesample.csv";
    

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    
    @After
    public void cleanUp() {
        jobRepositoryTestUtils.removeJobExecutions();
    }
    
    /**
     * Ensure Test Configurations are stable
     */
    @Test
    void testJobLauncherIsNotNull() throws Exception{
        assertNotNull(jobLauncherTestUtils,"job launcher util should not be null");
    }
	
    /**
     * Individual testing of Step "step1"
     */
    @Test
    void testStep1() {
    	JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");
        Collection<StepExecution> actualStepExecutions = jobExecution.getStepExecutions();
        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();

        // then
        assertThat(actualStepExecutions.size(), is(1));
        assertThat(actualJobExitStatus.getExitCode(), is("COMPLETED"));
    }
    
    
    /**
     * End-to-end testing of Job "importStoreOrderJob"
     */
	@Test
	void testJob_ValidCsvFileWithSomeFormatError_thenSuccess() throws Exception {
        
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        JobInstance actualJobInstance = jobExecution.getJobInstance();        
        ExitStatus actualExitStatus = jobExecution.getExitStatus();
        
        assertThat(actualJobInstance.getJobName(), is("importStoreOrderJob"));
        assertThat(actualExitStatus.getExitCode(), is("COMPLETED"));
	}
	

	
	
	

}
