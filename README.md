# Quantity Measurement App: Daily Activity Tracking Backend (Spring Boot)

A **Spring Boot REST API** for performing **unit conversion, comparison, and arithmetic operations** across multiple measurement domains such as **Length, Weight, Temperature, and Volume**.

The application is designed using **clean architecture, Strategy Pattern (no switch-case), dynamic unit handling, and database-backed history tracking**.

---

# 🚀 Key Features

* ✅ Convert units (Feet, Inches, Meter, Kg, Gram, Celsius, Fahrenheit, etc.)
* ✅ Compare quantities across compatible units
* ✅ Add quantities (Length & Weight supported)
* ❌ Prevent invalid operations (e.g., Temperature addition)
* ✅ Strategy Pattern (extensible & scalable design)
* ✅ Dynamic unit system (no enums)
* ✅ Global exception handling
* ✅ REST APIs with JSON responses
* ✅ MySQL database integration
* ✅ History tracking & filtering API

---

# 🧱 Tech Stack

| Layer      | Technology      |
| ---------- | --------------- |
| Language   | Java 17         |
| Framework  | Spring Boot     |
| API        | Spring Web      |
| ORM        | Spring Data JPA |
| Database   | MySQL           |
| Build Tool | Maven           |
| Testing    | JUnit 5         |
| Utilities  | Lombok          |

---

# 📂 Project Structure

```
src/main/java/bridgeLabz/quantity_measurement/

├── controller        → REST APIs
├── service           → Business logic interface
├── service/impl      → Service implementations
├── strategy          → Strategy interface
├── strategy/impl     → Unit-specific logic (Length, Weight, Temperature)
├── factory           → StrategyFactory
├── dto               → Data Transfer Objects
├── request           → API request models
├── response          → API response wrapper
├── entity            → JPA entities (DB mapping)
├── repository        → JPA repositories
├── exception         → Global exception handling
```

---

# 📌 Supported Use Cases (UC1 → UC15)

### 🟢 Core Functionality

* Represent quantities using value + unit + type
* Convert between units (Length, Weight, Temperature)
* Compare quantities (e.g., 1 ft == 12 inches)
* Add quantities (only valid for Length & Weight)

### 🔴 Business Rules

* Cross-category operations not allowed (Length ≠ Weight)
* Temperature addition is not allowed

### ⚙️ Advanced Features

* Strategy Pattern (no switch-case)
* Dynamic unit handling (String-based)
* Case-insensitive units (e.g., "feet", "FEET", "Feet")

### 🌐 REST APIs

* Add
* Compare
* Convert

### 🗄️ Database Integration

* Store all operations
* Track errors
* Maintain history

---

# 🔌 API Endpoints

## ➤ Add Quantities

```http
POST /api/quantities/add
```

## ➤ Compare Quantities

```http
POST /api/quantities/compare
```

## ➤ Convert Units

```http
POST /api/quantities/convert?targetUnit=INCHES
```

## ➤ Get History

```http
GET /api/history
GET /api/history?operation=ADD
GET /api/history?operation=COMPARE
GET /api/history?operation=CONVERT
```

---

# 🧪 Sample Request

```json
{
  "firstQuantity": {
    "value": 1,
    "unitName": "FEET",
    "measurementType": "LengthUnit"
  },
  "secondQuantity": {
    "value": 12,
    "unitName": "INCHES",
    "measurementType": "LengthUnit"
  }
}
```

---

# 📦 Sample Response

```json
{
  "success": true,
  "message": "Addition successful",
  "data": {
    "value": 2.0,
    "unitName": "FEET",
    "measurementType": "LengthUnit"
  }
}
```

---

# ⚠️ Error Response Example

```json
{
  "success": false,
  "message": "Addition not supported for temperature"
}
```

---

# 🗄️ Database Design

### Main Table

* Stores operation details (input, output, error, timestamps)

### History Table

* Tracks operation count and references main entity

---

# 🧪 Running Tests

Run all tests:

```bash
mvn test
```

Test coverage includes:

* Service Layer (logic)
* Controller Layer (API)
* Integration Tests (end-to-end)

---

# ▶️ How to Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

App will start at:

```plaintext
http://localhost:8080
```

---

# 🧠 Design Patterns Used

* ✅ Strategy Pattern → Unit-specific logic
* ✅ Factory Pattern → Strategy selection
* ✅ Layered Architecture → Clean separation

---

# 🚀 Future Enhancements

* 🔹 Swagger API documentation
* 🔹 Pagination & filtering for history
* 🔹 Authentication (JWT)
* 🔹 Frontend (React Dashboard)
* 🔹 Analytics (top operations, usage stats)

---

# 👨‍💻 Author

**Digambar Singh**
B.Tech Computer Science Engineering

---

# ⭐ Why This Project?

This project demonstrates:

* Clean backend architecture
* Real-world API design
* Scalable system design
* Proper error handling
* Database integration

---


