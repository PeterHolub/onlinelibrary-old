# Online Library
Implementation of Online Library using Java SE, JSP, JSTL tag library, SQLite database and Jetty.
## Prerequisites
* **JDK >= 1.8.0_131**;
* **Apache Maven >= 3.5.2**
## Project structure 
  **Java classes:**
* src\main\java\onlinelibrary.dao - interface that provides  design, and API to implement, to work with Database
* src\main\java\onlinelibrary.daoimpl - concrete classes that is an implementation of the interfaces from dao package
* src\main\java\onlinelibrary.enums - Enums for search options
* src\main\java\onlinelibrary.filter - Web filters for loading data before app start & invalidate session
* src\main\java\onlinelibrary.models - POJO classes pack
* src\main\java\onlinelibrary.servlets - Web servlets for navigation, registration, authentication, CRUD operations for administrators and others
* src\main\java\onlinelibrary.util.DatabaseConnection - Initializer of Database connection


**Properties files:**
* src\main\resources\databaseconnection.properties

**Database files:**

* onlinelibrary\db\onlinelibrary.db - all books in data base are free available from thevirtuallibrary.org & 8freebooks.com

**Web files:**
* src\main\webapp - contains jsp & jspf files & css files with images folder deploying descriptor web.xml
 
## Run the App

Run script for server local deploy:
```
mvn jetty:run
```
Then, open http://localhost:8080/ in your browser
