BookMyShow Backend Clone

A backend system that allows users to browse movies, select seats, and book tickets with real-time seat locking and secure authentication.

Tech Stack

I used Spring Boot because it helps me build backend services quickly with a clean structure and minimal setup.
I used MySQL because it provides reliable storage for structured data like users, movies, and bookings.
I used Redis because it improves performance by caching and enabling real-time seat locking.
I used JWT because it enables secure authentication without maintaining server-side sessions.
I used Docker because it makes the application easy to run and deploy consistently across environments.

How to Run Locally

I clone the repository:

git clone https://github.com/nithinreddyavula/bookmyshow.git

I navigate to the project folder:

cd bookmyshow
I make sure Docker and Docker Compose are installed and running.

I start all services:

docker-compose up --build
I wait until MySQL, Redis, and the application are up.

I access the application at:

http://localhost:8080
I test APIs using tools like Postman or cURL.
API Endpoints
Auth APIs
POST /auth/register — Register a new user
POST /auth/login — Authenticate user and return token
Movie APIs
POST /movies — Add a new movie
GET /movies — Get all movies
GET /movies/{id} — Get movie by ID
Theatre APIs
POST /theatres — Create a theatre
Screen APIs
POST /screens — Create a screen
Show APIs
POST /shows — Create a show
GET /shows — Get all shows
Booking APIs
POST /bookings — Create booking (locks seat in Redis)
POST /bookings/confirm — Confirm booking after payment
POST /bookings/cancel — Cancel booking and release seat
Project Purpose

I built this project to understand how real-world ticket booking systems handle concurrency, seat locking, and payments, and to practice building scalable backend systems.

Open to Work

I am open to backend engineering roles where I can build scalable systems and solve real-world problems.