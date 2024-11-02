**Download Gson Library:**

The ShamirSecretSharing.java code requires the Gson library for JSON parsing, which is not included in the zip file.
Download gson-2.8.9.jar from Gson GitHub Releases and place it in the same folder as the extracted files.
Compiling and Running the Code: Once gson-2.8.9.jar is added, open a terminal or command prompt in the extracted folder and follow these commands:

**Compile:**

bash
javac -cp .:gson-2.8.9.jar ShamirSecretSharing.java
This compiles the Java code with Gson as a dependency.

Run:

bash
java -cp .:gson-2.8.9.jar ShamirSecretSharing
This command runs the compiled code.

**Expected Output:**
If everything is set up correctly, the program will print the calculated constant term 
�
c (the "secret") to the console.
By following these steps, the extracted project should work as intended. Let me know if you encounter any issues!
