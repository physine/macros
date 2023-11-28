# Java Macros Project

## Overview
The Java Macros Project is a versatile tool designed to automate repetitive tasks and enhance productivity. It allows users to define and execute custom macros in a Java-based environment, streamlining workflows and simplifying complex processes.

## Features
- **Custom Macro Definitions:** Create macros with specific triggers and actions.
- **Environment Variable Integration:** Use AES-256 to secure sensitive data on disk.
- **User-Friendly Interface:** Easy-to-navigate interface for managing and executing macros.
- **Cross-Platform Support:** Compatible with various operating systems.
- **Robust Database Management:** Uses a PostgreSQL database for storing and managing macro configurations.

## Getting Started

### Prerequisites
- Java JDK 8 or later.
- PostgreSQL database.
- Set environment variables `AES_ENCRYPTION_KEY` of 32 bytes.

  `python -c "import os, binascii; print(binascii.hexlify(os.urandom(16)).decode())"`

### Installation and Running
1. **Clone the Repository:**

   `git clone https://github.com/your-username/java-macros.git`

   `cd java-macros`

### Build the Project

   `mvn clean install`

### Run the Application
    
    java -jar target/macros-1.0-SNAPSHOT.jar
