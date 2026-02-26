DataBase Setup: 
In application.property change the user_name and password.
the Table will be automatically created through the hibernate  based on the Entity layer  
(Optional) If you want manually to select the data use command: SELECT * FROM recipes
spring.application.name=recipe
spring.datasource.url=jdbc:postgresql://localhost:5432/Recipe
spring.datasource.username=root
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080

Requirements
Before you start, make sure you have these installed on your computer:

Java (JDK 17 or later)
Maven
PostgreSQL
Postman (for testing the API) or you can test end points any were tools you might confortable 

API Testing: 
To run the spring application use the command: mvn spring-boot:run
Use Postman to parse the JSON data/file:Open the Postman and go to the POST request 
Add a new Recipe: 
In Postman change to POST and use: http://localhost:8080/recipe, Select body and then choose raw then give the input as JSON.
Example:
{
  "title": "Chocolate Cake",
  "rating": 3.5,
  "cuisine": "Dessert",
  "prep_time": 20,
  "cook_time": 40,
  "description": "A rich chocolate cake...",
  "nutrients": {
    "calories": "500 kcal",
    "carbohydrateContent": "60 g",
    "proteinContent": "6 g",
    "fatContent": "25 g"
  },
  "serves": "6 servings"
}

To get the top rating food with limit value use the API: http://localhost:8080/recipes/top ,Then in key = limit, value = "any number"
Or use: http://localhost:8080/recipes/top?limit=2
Use this link for top 2 according to the Rating.

for adding any new json file you need to add in your meashine and change the path in the Service layer nothing more .
