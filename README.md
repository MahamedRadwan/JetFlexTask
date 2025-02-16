# JetFlexTask
JetFlexTask is an Appium automation framework built with **Java**, **TestNG**, and the **Page Object Model (POM)**. It integrates **Allure Reports** for test reporting and is designed for **Android automation testing** on an emulator. The framework reads desired capabilities from a **properties file** and manages the **Appium server programmatically**.

## Features
✅ **Page Object Model (POM)**  for better maintainability  
✅ **TestNG** for structured test execution  
✅ **Allure Reports** for detailed test reporting  
✅ **Driver Manager** that loads capabilities from a properties file  
✅ **Appium Server Manager** to start/stop the Appium server programmatically  
✅ **Dynamic Search & Interaction** for locating movies and applying filters  
✅ **Test Scenarios** covering movie search validation and release date filtering

## Run Instructions

### 1. Configure the Framework
Modify the configuration parameters in the **resources** package:
- **Device Name**
- **Appium Main JS Path**
- **App Path**

### 2. Set the Movie Name in Tests
Update the desired **movie name** in the **data provider object** inside the **Movie Name Test file**.

### 3. Execute the Tests
Run the **TestNG** test suite file.

### 4. Generate and View Allure Reports
After test execution, generate and serve the Allure report using:
```sh
allure serve allure-results
```

## Note
Ensure that either:
- The **Android Emulator** is up and running, **or**
- A **real device** is connected and authorized.

