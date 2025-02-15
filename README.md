# Personal Blog API

🚀 **Personal Blog API** is a Spring Boot-based backend application that allows users to create, manage, and view blog posts. It supports user authentication, post management, and provides a structured REST API for easy integration.

## 📌 Features

- User registration and authentication  
- Create, read, update, and delete (CRUD) operations for blog posts  
- Retrieve posts with associated user details  
- Count the number of posts per user  

## 🛠 Tech Stack

- **Spring Boot** (Java)  
- **Spring Data JPA** (Hibernate & MySQL)  
- **Spring Web** (REST API)  
- **Postman** (API Testing)  
- **Git & GitHub** (Version Control)  

## 🚀 Getting Started

```sh
1️⃣ Clone the Repository  
git clone https://github.com/your-username/personal-blog-api.git
cd personal-blog-api

2️⃣ Configure the Database
Update application.properties with your MySQL database credentials:
spring.datasource.url=jdbc:mysql://localhost:3306/blog_db
spring.datasource.username=root
spring.datasource.password=yourpassword

3️⃣ Run the Application
mvn spring-boot:run
```

4️⃣ API Endpoints

**User Endpoints**
- **Sign Up User** → ```POST /api/users/signup```
- **Login User** → ```POST /api/users/login```
- **Get All Users** → ```GET /api/users```
- **Get User by ID** → ```GET /api/users/{user_id}```
- **Get User by Username** → ```GET /api/users/{username}```
- **Delete User** → ```DELETE /api/users/{user_id}```
  
**Post Endpoints**
- **Create Post** → ```POST /api/posts```
- **Get All Posts** → ```GET /api/posts```
- **Update Post** → ```PUT /api/posts/{post_id}```
- **Delete Post** → ```DELETE /api/posts/{post_id}```

## 📌 Contribution

Contributions are welcome! Feel free to fork the repo, raise issues, and submit pull requests.

## 📢 Connect with Me

💼 Sharing this project on LinkedIn! Let's connect: www.linkedin.com/in/naveen-kovagana-2439951ba
