# branch-take-home
A REST API to gather user information from github

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/markshivers/branch-take-home.git
   ```
2. Navigate to the project directory:
   ```sh
   cd branch-take-home
   ```
3. Build the project using Maven:
   ```sh
   mvn clean install
   ```

## Usage
1. Run the application:
   ```sh
   mvn spring-boot:run
   ```
2. Access the API at `http://localhost:8080/compositeUserInformation/{username}`

## Running Tests
To run the unit tests, use the following command:
   ```sh
      mvn test
   ```

The testing approach is a mixture of unit and integration tests to test the core functionality of the application. The tests are written using JUnit 5 and Mockito.

## Architecture
The application is built using Spring Boot and follows a layered architecture:
- **Controller Layer**: Handles HTTP requests and responses.
- **Service Layer**: Contains the business logic.
- **Model Layer**: Defines the data structures and entities.
- **Integration Layer**: Manages communication with external APIs.
- **Configuration Layer**: Contains configuration classes and properties.
- **Testing**: Contains unit and integration tests.

### Schema Generation
This project uses [jsonschema2pojo](http://www.jsonschema2pojo.org/) to generate Java classes from JSON schemas. The schemas are located in the `src/main/resources/schema` directory. The generated classes are placed in the `target/generated-sources/java/com/markshivers/models` directory. The JSON schemas were acquired from github here: 
- https://docs.github.com/en/rest/users/users?apiVersion=2022-11-28#get-a-user 
- https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#list-repositories-for-a-user
 
This architecture ensures separation of concerns, making the application more maintainable and testable. It also focuses on immutability and functional programming principles where applicable. This can help prevent bugs if the application grows. I wanted to have a view representation, so I could easily construct the final result without being tightly coupled to the format github provided.

## Dependencies
- Java 21
- Spring Boot
- Maven
- JUnit 5
- Mockito
- jsonschema2pojo
- Jackson

## Future Enhancements
- Logging Strategy
- Deployment Configuration
- Integrating JSR Validation
- Abstraction layer to allow for different data sources
- Using Redis for caching