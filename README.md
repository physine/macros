
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

## Clone the Repository
```bash
git clone https://github.com/physine/macros.git
cd macros
```

## Prerequisites
- Java JDK 8 or later.
- PostgreSQL database, with a table defined like so (see persistence.xml for DB setup details):
    ```sql
    CREATE TABLE macros (
    id SERIAL PRIMARY KEY,
    trigger VARCHAR(255) NOT NULL UNIQUE,
    target VARCHAR(255) NOT NULL
    );
    ```
- Set an environment variable `AES_ENCRYPTION_KEY` of 32 bytes. Generate a key using:
```bash
python -c "import os, binascii; print(binascii.hexlify(os.urandom(16)).decode())"
```

## Package the Project

Once in the `macros` project directory:

```bash
cd path\to\macros
mvn package
```

Create a batch file to simplify the execution of the macros project, it will look like.

```batch
@echo off
java -jar target/macros-1.0.jar %*
```

and run it from the command line or add it to the start-up folder.

## Future Features

- active window macros maybe

### Contributing

Contributions are welcome! Feel free to submit pull requests, open issues, or suggest improvements.
