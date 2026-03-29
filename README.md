                                     Selenium E-Commerce Automation Framework

This project is a Selenium-based automation framework built to simulate real user behavior on an e-commerce application.  
It focuses on stability, maintainability, and real-world test scenarios.


🔗 Project Overview

Automates core user flows such as account creation, login, and cart functionality using a structured and scalable framework design.


🧩 Architecture

Test Framework: Java + Selenium + TestNG  
Design Pattern: Page Object Model (POM)  
Build Tool: Maven  
CI/CD: GitHub Actions


⚙️ Key Features

• End-to-end test flow (Signup → Login → Cart → Logout)  
• Dynamic test data (unique email generation)  
• Explicit wait handling for dynamic elements  
• Ad handling using JavaScript  
• Screenshot capture on failures  
• Extent HTML reporting  
• Headless execution for CI stability  
• GitHub Actions integration


🧪 Test Coverage

Complete Flow Test  
→ Signup → Login → Add to Cart → Verify → Logout  

Cart Validation  
→ Add product and verify cart contents  

Negative Testing  
→ Invalid login scenario


▶️ How to Run

Clone the repository: git clone https://github.com/sonaliviniramesh-web/selenium-ecommerce-automation-framework

Run tests: mvn test


📊 Reporting

Execution report available at:
test-output/ExtentReport.html


🚀 Continuous Integration

Tests run automatically on every push using GitHub Actions.


💡 Highlights

• Built with scalability and reusability in mind  
• Handles real-world UI challenges like ads and dynamic loading  
• Designed for maintainable and stable automation


👤 Author

Sonali Ramesh
