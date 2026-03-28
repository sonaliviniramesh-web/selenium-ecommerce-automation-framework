# 🛒 Selenium E-Commerce Automation Framework

## 📌 Project Overview
This project is a **Selenium-based test automation framework** built using **Java, TestNG, and Page Object Model (POM)**.

It automates key user flows of an e-commerce application (AutomationExercise), focusing on **end-to-end functional validation** and **test stability**.

---

## 🛠 Tech Stack
- Java
- Selenium WebDriver
- TestNG
- Maven

---

## 🏗 Framework Design
- Page Object Model (POM) for scalability
- Utility-based architecture for reusability
- Explicit waits for synchronization
- Dynamic test data generation

---

## 📂 Project Structure
base/ → WebDriver setup & test base
pages/ → Page classes (Login, Signup, Product)
tests/ → Test classes
utils/ → Waits, Config, DriverFactory, Screenshots
resources/ → Configuration files

---

## ✅ Features Implemented
- User Signup (dynamic email handling)
- Login & Logout functionality
- Add Product to Cart
- Cart Validation
- Error handling for invalid login
- Ad handling using JavaScript DOM removal
- Explicit waits for stable execution

---

## 🧪 Test Scenarios

### 🔹 End-to-End Flow
- Signup → Login → Add to Cart → Verify → Logout

### 🔹 Cart Validation
- Add product and verify it appears in cart

### 🔹 Negative Testing
- Login with invalid credentials

---

## ▶️ How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/sonaliviniramesh-web/selenium-ecommerce-automation-framework
2. Open in IntelliJ IDEA
3. Run the test:
   tests.AutomationExerciseTest

---

## 🚀 Future Enhancements
Extent Reports (test reporting)
Parallel execution
CI/CD integration (GitHub Actions)
Docker support

---

👤 Author
Sonali Ramesh
