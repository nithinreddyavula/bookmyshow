🎬 BookMyShow Backend System

A scalable movie ticket booking backend system that supports user authentication, movie management, and ticket booking with optimized performance using caching.

🚀 Tech Stack
Backend: Java 17, Spring Boot 3.x
Security: Spring Security 6.x, JWT
Database: MySQL 8.x
Caching: Redis 7.x
Containerization: Docker 24.x, Docker Compose 2.x
Build Tool: Maven 3.x
API Testing: Postman
Version Control: Git, GitHub
✨ Features
User Registration & Login with JWT authentication
Movie Management (Add, Update, Delete, Fetch movies)
Ticket Booking System (basic flow)
Redis Caching for faster movie retrieval
Cache Eviction using @CacheEvict to avoid stale data
JWT-secured endpoints
Clean layered architecture (Controller → Service → Repository)
🧠 System Design Decisions (WHY)

JWT for Authentication

Stateless → no session storage required
Scales horizontally

Layered Architecture

Improves maintainability and testability
Easy to extend

Redis Caching

Reduces DB load for read-heavy operations
Improves response time

Cache Eviction

Ensures cache stays consistent with DB

BCrypt Password Encoding

Secure password hashing

Docker

Consistent environment across systems
Easy setup and deployment
📡 API Endpoints
Authentication

POST /api/auth/register → Register user
POST /api/auth/login → Login and get JWT

Movies

GET /api/movies → Get all movies
GET /api/movies/{id} → Get movie by ID
POST /api/movies → Add movie
PUT /api/movies/{id} → Update movie
DELETE /api/movies/{id} → Delete movie

Bookings

POST /api/bookings → Book ticket
GET /api/bookings/user/{id} → Get user bookings

🛠️ Run with Docker

Clone repo:

git clone https://github.com/your-username/bookmyshow-backend.git

cd bookmyshow-backend

Run containers:

docker-compose up --build

Stop containers:

docker-compose down

🐳 docker-compose.yml

version: '3.8'

services:

mysql:
image: mysql:8
container_name: bms-mysql
environment:
MYSQL_ROOT_PASSWORD: root
MYSQL_DATABASE: bookmyshow
ports:
- "3306:3306"

redis:
image: redis:7
container_name: bms-redis
ports:
- "6379:6379"

app:
build: .
container_name: bms-app
ports:
- "8080:8080"
  depends_on:
- mysql
- redis