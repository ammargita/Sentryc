Date: 23 Oct 2023:
Submitted by: Ammar

Project structure for Sentryc Java Project.

1. We created the project on MVC model and used GraphQL APIs.

2. We have used MySQL database. 

3. Created 4 model class, namely 
	a. market_places
	b. producers
	c. seller_infos
	d. sellers  

4. Created repositories for fetching data for each model class.
5. Created Controller for GraphQL API.
6. Created Services for initialization and implement the method.
7. Created request package for passing paramters in APIs.
8. Response is captured using response classes.
9. Schema file for GraphQL is created in Resource folder called "schema.graphqls".
10. Configuration file for GraphQL and MySQL databases are in application properties file.
11. GraphQLs test cases are captured in test package "GraphQLSellerInfosControllerTest". 
12. In order to insert test records, I have provided stored procedures, these procedure can be inserted in MySQL database and can be executed to create test records. Before inserting these stored procedure, you can make changes according to your requirements.
13. Name of the file stored_procedure.txt which contains script to create 1000 dummy records for 4 tables.

Note: We have used JPQL query to fetch the response, and not native queries.
