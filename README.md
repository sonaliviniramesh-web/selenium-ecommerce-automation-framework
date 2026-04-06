# Selenium Automation Framework

## Overview

This project is a Selenium-based automation framework built using Java and TestNG. It follows the Page Object Model (POM) design pattern to maintain clean separation between test logic and page interactions.

## Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven

## Framework Design

* Page classes handle UI interactions
* Test classes contain test scenarios and validations
* Utility classes manage waits, configuration, reporting, and screenshots

## Features

* Explicit wait handling using custom WaitUtils
* Centralized driver management using DriverFactory
* Configurable test data using ConfigReader
* Extent Reports for execution reporting

## Test Scenarios

* User Signup
* Invalid Login Validation
* Add Product to Cart

## How to Run

1. Clone the repository
2. Run: mvn clean test

## Notes

* Tests run in headless mode
* Configuration stored in config.properties
