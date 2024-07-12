## Spring Batch Exercise

A practice project that uses Spring Batch

# Objective

Import the given CSV File "sales.csv" into a Database. The CSV file contains Sales records made in a Store.   


# System Pre-requisite
1. Maven 
2. Java (at least version 1.8)

# Installation

1. Unzip DAVID_MANANGAN_CsvBatchProcessing.zip
2. Go inside directory, `csvbatchprocessing`
3. Compile Code by running the command `mvn clean package`
4. Run `java -jar target/csvbatchprocessing-0.0.1-SNAPSHOT.jar`

# Directory Stucture

```
├── HELP.md
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── iam.davidmanangan.csvbatchprocessing
│   │   │               ├── App.java
│   │   │               ├── SpringBatchConfig.java
│   │   │               ├── exception
│   │   │               │   ├── DateParsingException.java
│   │   │               │   └── NumberParsingException.java
│   │   │               ├── model
│   │   │               │   ├── Sales.java
│   │   │               │   └── StoreOrder.java
│   │   │               ├── service
│   │   │               │   ├── CsvBatchProcessingListener.java
│   │   │               │   ├── SalesRecordWithInvalidRowSkipPolicy.java
│   │   │               │   └── StoreOrderItemProcessor.java
│   │   │               └── utility
│   │   │                   └── DateUtility.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── sales.csv
│   │       ├── salestest.csv
│   │       └── schema-all.sql
│   └── test
│       ├── java
│       │   └── iam.davidmanangan.csvbatchprocessing
│       │               └── CsvBatchProcessingApplicationTests.java
│       └── resource
│           ├── expected-output-one.json
│           ├── expected-output.json
│           ├── test-input-one.csv
│           └── test-input.csv
└── target
```
 

# Solution
 
The Framework used in the project is Spring Batch which an open source framework for Batch Processing. The language used is Java to implement the Batch Job that will parse and import rows of Sales Record in the CSV file into the Database.


# Assumptions

1. The CSV file may contain corrupted lines

2. It is possible that some dates and numbers are not in the correct format.

# Approach

1. If a corrupted line is detected, catch the error on runtime and log the error specifying that there is a corrupted line in the CSV file. Skip this line and continue processing the rest of the file.

2. If a date or number is not in the correct format, catch the format error on runtime and log the error indicating that a the batch program detected an invalid Date or Number. Skip the entire row and continue processing the rest of the file.

  
# Java Code

##### I. MAIN PACKAGE (iam.davidmanangan.csvbatchprocessing)  

###### App.java  
- Contains the Main method that will run the entire batch program.

###### SpringBatchConfig.java 
- Contains the Configuration of the Batch Job and Steps to be executed.


##### II. ENTITY MODELS (iam.davidmanangan.csvbatchprocessing.model)

###### Sales.java
- Model used for Sales Record in CSV File

###### StoreOrder.java
- Model used to import Store Order records into Database

 
##### III. SERVICE CLASSES (iam.davidmanangan.csvbatchprocessing.service)

###### StoreOrderItemProcessor.java
- Service Class that transforms Sales records into Store Order records. 

###### CsvBatchProcessingListener.java 
- Listener Class that triggers logs when Batch Job is done executing.

###### SalesRecordWithInvalidRowSkipPolicy.java
- Skip Policy Class that Handles Exceptions such as Parsing Error, Date Format Error and Number format error.

##### IV. ADAPTER OBJECTS (iam.davidmanangan.csvbatchprocessing.utility)
###### DateUtility.java
- Converts String to Date Object and throws custom exception for invalid date formats. 

##### V. CUSTOM EXCEPTION (iam.davidmanangan.csvbatchprocessing.exception)
###### * DateParsingException.java
###### * NumberParsingException.java



