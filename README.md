# Questions and Answers Website
## Table of Contents
- [Description](#description)
- [Setup](#setup)
- [Technologies Used](#technologies-used)

## Description
This is a full stack web-application that creates a questions and answers website. Currently in development, only about ~70-80% finished.
### Features:
* Site secured by Spring Security
* Registration, Login, Logout handled
* Only users can post or comment, but quests can look through already written content
* Each post and comment tracks the time passed since writing it (sorting by date of writing coming soon)
* Each post and comment tracks upvotes and downvotes (sorting by score coming soon)
* Infinite Scroll and selectable data saving mode(preloads less content on each scroll to save data)
## Setup
### Prerequisites
#### Have Maven and Java 17+ (or intellij) and Node.js installed. Create a postgresql database named qna 
1. Navigate to the scheduler folder and create a build
```
mvn package
```
2. Set the following environment variables: DB_USER, PASSWORD, SECRET_KEY
3. Run the .jar file
```
java -jar <YOUR_BUILD_NAME>.jar
```
In a separate terminal navigate to the frontend folder.
```
npm i
npm start
``` 
## Technologies Used
* React.js
* Material UI
* PostgreSQL
* Spring Boot
* Java
