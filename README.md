# SimpleWeatherAPI

SimpleWeatherAPI is a lightweight RESTful service built with Spring Boot that provides in-memory weather information for up to three cities. Users can add, retrieve, and update weather data via the API.

---

## Features

- Add or update weather data for a city.
- Retrieve weather information for a specific city.
- Retrieve a list of all stored weather data.
- Input validation to ensure data integrity.
- Custom error handling for better user experience.

---

## Technologies Used

- **Java 11**
- **Spring Boot 2.7.12**
- **JUnit 5** for unit testing
- **MockMvc** for integration testing
- **Postman** for API testing

---

## API Endpoints

### 1. Add or Update Weather
- **Method**: `POST`
- **Endpoint**: `/weather`
- **Request Body**:
  ```json
  {
    "city": "Auckland",
    "temp": "24",
    "unit": "C",
    "date": "2023-12-10",
    "weather": "cloudy"
  }
  ````

- **Response**:
201 Created: Weather data added/updated successfully.


2. **Get Weather for a City**:
   Method: GET
   Endpoint: /weather/{city}
   Path Parameter:
   city: The name of the city (case-insensitive).
   Response:
   200 OK:
 ```json

[
{
"city": "Auckland",
"temp": "24",
"unit": "C",
"date": "2023-12-10",
"weather": "cloudy"
},
{
"city": "Wellington",
"temp": "18",
"unit": "C",
"date": "2023-12-10",
"weather": "rainy"
}
]
  ````



Installation
Prerequisites
Java 11
Maven
Steps
Clone the repository:

bash
Copy code
git clone https://github.com/your-repo/SimpleWeatherAPI.git
cd SimpleWeatherAPI
Build the project:

```` 
mvn clean install
````
Run the application:

````
mvn spring-boot:run
````
The application will start at http://localhost:8080.


Testing the API
Using Postman
Open Postman and create a new request.
Use the endpoints specified in the API Endpoints section to test the application.
Running Tests
Run the unit and integration tests using Maven:

````
mvn test
````
Error Handling
404 Not Found: Returned if a city is not found. Example Response:

json
````
{
"message": "City not found: NonExistentCity",
"status": 404
}
400 Bad Request: Returned for invalid input data during POST requests.
````



