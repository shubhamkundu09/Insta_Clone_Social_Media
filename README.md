Just Open this project into your intellij idea and make sure to change your db password






- Built a full-stack Instagram clone using Java and Spring Boot, replicating all major social media features.
- Designed and implemented RESTful APIs for user authentication,post management, likes, comments, sharing, saving, and story uploads.
- Developed robust backend architecture with layered design (Controller, Service, Repository) and secure authentication mechanisms.
- Enabled users to upload stories and posts, with full CRUD operations and real-time interaction features.
- Implemented profile functionalities including bio section, profile photo, followers/following list, and activity feed.






I've successfully developed a complete Instagram-like backend API using Spring Boot 3, Spring Security, and JWT authentication. This robust, production-ready backend handles all core social media functionalities including user management, posts, stories, reels, comments, and real-time chat.


🏗️ Technology Stack
Backend: Spring Boot 3.2.0, Java 17
Security: Spring Security 6 + JWT Authentication
Database: MySQL with Spring Data JPA
Authentication: JWT (JSON Web Tokens)
Architecture: RESTful API, Layered Architecture
Tools: Lombok, Maven, Spring Validation


🔐 Security Features
Stateless JWT Authentication with Bearer tokens
BCrypt password encoding
Role-based endpoint protection
Custom JWT validator filter
CORS configuration for frontend integration
Session-less architecture for scalability




📊 Database Schema
The system manages 7 core entities:
User - User profiles with followers/following system
Post - Image/Video posts with likes and comments
Story - 24-hour ephemeral content
Reel - Short video content
Comment - Post comments with likes
Chat - Direct messaging conversations
Message - Individual chat messages



📱 Complete API Endpoints Reference


🔓 Authentication APIs (Public)
POST /auth/signup  - Register new user
POST /auth/signin  - Login & get JWT token


👥 User Management (Protected - JWT Required)
GET  /user/get-all-user      - Get all users
GET  /user/get-user-by-id/{id}   - Get user by ID
GET  /user/get-user-by-email/{email} - Get user by email
GET  /user/search-user?name={query} - Search users by name
GET  /user/user-by-jwt       - Get current user from token
PUT  /user/update-user       - Update user profile
PUT  /user/follow/{userId2}    - Follow/Unfollow user


📸 Post Management (Protected)
POST  /post/create-post       - Create new post
GET  /post/post/{postId}      - Get post by ID
GET  /post/all-post         - Get current user's posts
GET  /post/all-posts-of-users    - Get all posts from all users
PUT  /post/like-post/{postId}    - Like/Unlike a post
PUT  /post/save-post/{postId}    - Save/Unsave post
DELETE /post/delete-post/{postId}   - Delete post


💬 Comment System (Protected)
POST  /comment/add-comment/{postId}   - Add comment to post
GET  /comment/get-comment-by-id/{commentId} - Get comment by ID
PUT  /comment/like-comment/{commentId} - Like/Unlike comment


🎬 Reel System (Protected)
POST  /reel/create-reel       - Create new reel
GET  /reel/all-reels        - Get all reels
GET  /reel/reel-by-userId/{userId} - Get user's reels

📖 Story System (Protected)
POST  /story/add           - Create new story
GET  /story/get-by-userId/{userId} - Get user's stories

💬 Chat & Messaging (Protected)
POST  /chat/add/{userId2}      - Create/Get chat with user
GET  /chat/chat-by-id/{chatId}   - Get chat by ID
GET  /chat/find-user-chat      - Get current user's chats
POST  /message/create/{chatId}    - Send message in chat
GET  /message/get-msg-by-chatId/{chatId} - Get chat messages
