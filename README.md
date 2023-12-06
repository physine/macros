
<div align="center">

# Java Macros Project
![logo (1) (1).png](src%2Fmain%2Fresources%2Flogo%20%281%29%20%281%29.png)

</div>

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
- Set an environment variable `AES_ENCRYPTION_KEY` of 32 bytes. Generate a key using:
```bash
    python -c "import os, binascii; print(binascii.hexlify(os.urandom(16)).decode())"
```


### Clone the Repository
```bash
    git clone https://github.com/physine/macros.git
    cd macros
```

### Build the Project
```bash
    mvn clean install
```

### Run the Application
```bash
    java -jar target/macros-1.0-SNAPSHOT.jar
```

### In Progress
A Command Line Interface (CLI) for creation and management of macros directly from the command line.

Stay tuned for updates on these features!

### Future Features

- active window macros

### TODOs

- create better exceptions
- create a cli
