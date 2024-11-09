# Blogging Platform

## Overview
This project is a blog management system built using Java, Spring Boot, and Maven. It allows users to create, read, update, and delete posts, comments, and categories. The system also manages user accounts.

## Technologies Used
- Java 17
- Spring Boot 3.0.0
- Maven 3.8.1
- MySQL 8.0
- Lombok 1.18.20
- MapStruct 1.4.2
- Swagger 3.0.0

## Business Logic
The application follows a typical CRUD (Create, Read, Update, Delete) workflow for managing users, posts, comments, and categories. The business logic is encapsulated in the service layer, which interacts with the repository layer to perform database operations. The controller layer handles HTTP requests and responses, using DTOs to transfer data between the client and the server.

### Workflow
1. **User Management**: Users can be created, updated, retrieved, and deleted.
2. **Post Management**: Posts can be created, updated, retrieved, and deleted. Each post is associated with a user, comments, and categories.
3. **Comment Management**: Comments can be created, updated, retrieved, and deleted. Each comment is associated with a post and a user.
4. **Category Management**: Categories can be created, updated, retrieved, and deleted. Each category can be associated with multiple posts.

### Logical Flow
1. **Client sends a request**: The client sends a request to create a user or a post, including all necessary information in the request body.
2. **Controller receives the request**: The controller receives the request and passes the DTO to the service layer.
3. **Service layer processes the request**: The service layer processes the request, using the mapper to convert the DTO to an entity, and then saves the entity to the database.
4. **Service layer returns a response**: The service layer uses the mapper to convert the saved entity back to a DTO and returns it to the controller.
5. **Controller sends the response**: The controller sends the DTO back to the client as the response.

## Entities and Relationships
### User
- **Attributes**: id, username, password, email
- **Relationships**: One-to-Many with Post, One-to-Many with Comment

### Post
- **Attributes**: id, title, content, createdAt, updatedAt
- **Relationships**: Many-to-One with User, One-to-Many with Comment, Many-to-Many with Category

### Comment
- **Attributes**: id, content, createdAt, updatedAt, isApproved
- **Relationships**: Many-to-One with Post, Many-to-One with User

### Category
- **Attributes**: id, name, description, createdAt, updatedAt, active
- **Relationships**: Many-to-Many with Post

These relationships ensure that:
- A Post can have multiple Comments and belong to multiple Categories.
- A Comment is linked to a single Post and a single User.
- A Category can have multiple Posts.
- A User can have multiple Posts and Comments.

## DTOs
DTOs (Data Transfer Objects) are used to transfer data between the client and the server.

### PostDTO
- **Attributes**: id, title, content, createdAt, updatedAt, user, comments, categories

### CommentDTO
- **Attributes**: id, content, createdAt, updatedAt, post, user, approved

### CategoryDTO
- **Attributes**: id, name, description, createdAt, updatedAt, active, posts

## Service Layer
The service layer contains the business logic and interacts with the repository layer.

### PostService
- **Methods**: savePost, getAllPosts, getPostById, updatePost, deletePost

### PostServiceImpl
- **Methods**: savePost, getAllPosts, getPostById, updatePost, deletePost

## Controller Layer
The controller layer handles HTTP requests and responses.

### MainController
- **Endpoints**:
    - `POST /api/users`: Create a new user.
    - `GET /api/users`: Get all users.
    - `GET /api/users/{id}`: Get a user by ID.
    - `PUT /api/users/{id}`: Update a user by ID.
    - `DELETE /api/users/{id}`: Delete a user by ID.
    - `POST /api/posts`: Create a new post.
    - `GET /api/posts`: Get all posts.
    - `GET /api/posts/{id}`: Get a post by ID.
    - `PUT /api/posts/{id}`: Update a post by ID.
    - `DELETE /api/posts/{id}`: Delete a post by ID.
    - `POST /api/comments`: Create a new comment.
    - `GET /api/comments`: Get all comments.
    - `GET /api/comments/{id}`: Get a comment by ID.
    - `PUT /api/comments/{id}`: Update a comment by ID.
    - `DELETE /api/comments/{id}`: Delete a comment by ID.
    - `POST /api/categories`: Create a new category.
    - `GET /api/categories`: Get all categories.
    - `GET /api/categories/{id}`: Get a category by ID.
    - `PUT /api/categories/{id}`: Update a category by ID.
    - `DELETE /api/categories/{id}`: Delete a category by ID.

## Mapper
The mapper converts between entity classes and DTOs.

### MainMapper
- **Methods**: postToPostDTO,    userToUserDTO, commentToCommentDTO, categoryToCategoryDTO


## Running the Application
1. Clone the repository:
   ```sh
   git clone https://github.com/Ahmet-Acik/blogging_platform.git
   cd blogging_platform
   ```

2. Install dependencies:
   ```sh
   mvn clean install
   ```

3. Run the application:
   ```sh
   mvn spring-boot:run
   ```

4. The application will be available at `http://localhost:8080`.
5. You can access the Swagger UI at `http://localhost:8080/swagger-ui/index.html.


## Running Tests
To run the tests, use the following command:
```sh
mvn test
```

## Changing MySQL Credentials
To change the MySQL credentials, update the `application.properties` file located in `src/main/resources` with your MySQL database details:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```