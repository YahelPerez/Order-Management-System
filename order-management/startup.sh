#!/bin/bash

# ==============================================================================
# Boot Script for the Order Management System
# ------------------------------------------------------------------------------
# This script automates two key steps:
# 1. Compile the source code of the project using the Gradle Wrapper.
# 2. Run the resulting.jar file to start the Spring Boot server.
# ==============================================================================

echo "--- Compiling the project with Gradle... ---"
./gradlew build

# Check if the previous build was successful before proceeding.
# The '$?' exit code will be 0 if the previous command had no errors.
if [ $? -eq 0 ]; then
    echo "--- Successful compilation. ---"
    echo "--- Running the app... (Press Ctrl+C to stop it) ---"
    # Run the JAR file found in the build directory.
    java -jar build/libs/order-management-0.0.1-SNAPSHOT.jar
else
    # If the build fails, it displays an error message and terminates the script.
    echo "--- ERROR: Gradle's compilation failed. Please check for errors. ---"
    exit 1
fi