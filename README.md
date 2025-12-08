# RideShare Backend – Mini Project (Spring Boot + MongoDB)

A simple backend for a ride-sharing system built using **Spring Boot**, **MongoDB**, **JWT authentication**, DTOs, validation, and global exception handling.

---

##  Key Features

* **User & Driver registration + login (JWT)**
* **Ride request (USER)**
* **View user rides**
* **Driver view pending rides**
* **Driver accept ride**
* **Complete ride (USER or DRIVER)**
* **Input validation using Jakarta**
* **Global exception handler for errors**

---

## Folder Structure

```
src/main/java/org/example/rideshare/
 ├── model/
 ├── repository/
 ├── service/
 ├── controller/
 ├── config/
 ├── dto/
 ├── exception/
 └── util/
```

`src/main/resources/application.properties`

---

## Entities

### **User**

* id
* username
* password (BCrypt encrypted)
* role → `ROLE_USER` or `ROLE_DRIVER`

### **Ride**

* id
* userId (passenger)
* driverId (nullable)
* pickupLocation
* dropLocation
* status → `REQUESTED / ACCEPTED / COMPLETED`
* createdAt

---

## Authentication (JWT)

Login returns a **JWT token**.
All protected endpoints require:

```
Authorization: Bearer <token>
```

Token includes:

* username
* role
* issued time
* expiry

---

## API Endpoints

### **Public**

| Method | Endpoint             | Purpose              |
| ------ | -------------------- | -------------------- |
| POST   | `/api/auth/register` | Register User/Driver |
| POST   | `/api/auth/login`    | Login + get JWT      |

### **User**

| Method | Endpoint             | Purpose        |
| ------ | -------------------- | -------------- |
| POST   | `/api/v1/rides`      | Request a ride |
| GET    | `/api/v1/user/rides` | View own rides |

### **Driver**

| Method | Endpoint                           | Purpose       |
| ------ | ---------------------------------- | ------------- |
| GET    | `/api/v1/driver/rides/requests`    | Pending rides |
| POST   | `/api/v1/driver/rides/{id}/accept` | Accept ride   |

### **Shared**

| Method | Endpoint                      | Purpose       |
| ------ | ----------------------------- | ------------- |
| POST   | `/api/v1/rides/{id}/complete` | Complete ride |

---

## Sample Testing Commands (cURL)

### Register

```sh
curl -X POST http://localhost:8081/api/auth/register \
-H "Content-Type: application/json" \
-d '{"username":"john","password":"1234","role":"ROLE_USER"}'
```

### Login

```sh
curl -X POST http://localhost:8081/api/auth/login \
-H "Content-Type: application/json" \
-d '{"username":"john","password":"1234"}'
```

### Request Ride

```sh
curl -X POST http://localhost:8081/api/v1/rides \
-H "Authorization: Bearer <token>" \
-d '{"pickupLocation":"A","dropLocation":"B"}'
```

---
