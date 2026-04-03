# Library management application

## Project overview

The application provides basic functions for the management of a library, such as registering users or borrowing books.

## Deployment instructions
### Prerequisites
JDK 17+ and Maven (and Postman optionally, for testing) are needed to be installed locally.


### Running the application
Clone the project from the public GitHub repository. Then:

- *Option 1:* Open the folder in your editor and run the main function of src/main/java/com/library/library_management/LibraryManagementApplication.java

- *Option 2:* Open a terminal in the project folder, and use the .\mvnw.cmd spring-boot:run command to run the application.

### Accessing the H2 console
The application uses an in-memory database, which can only be accessed while the application is running.

The console can be reached from: localhost:8080/h2-console

The following parameters are also needed:
- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:mem:librarydb
- User Name: sa

After connecting to the database, the tables can be loaded.

### Running the tests
In order to run the tests:

- *Option 1:* The project includes basic unit test cases in order to verify functionality.
The tests can be executed by running src/test/java/com/library/library_management/LibraryManagementApplicationTests.java

- *Option 2:* Run .\mvnw.cmd test in the terminal.

### Testing with Postman

The project includes a postman collection so in case of running tests with
 postman, the commands do not have to be set up manually. After opening
postman, the collection can be imported in the top left corner.