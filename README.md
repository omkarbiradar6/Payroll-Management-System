Payroll Management System – Full Stack (Advanced Java + SQL):-
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
A complete Payroll Management System built using Advanced Java (Servlets + JSP/HTML), HttpSession-based authentication, and SQL database.
This project was developed as part of the Developer Internship Assignment.

🚀 Features
🔐 Authentication (Admin + Employee)
================================
Signup & Login (Role-based)

Session-based authentication

Demo seeded user included

Email: hire-me@anshumat.org

Password: HireMe@2025!

🧑‍💼 Admin Features
================
✔ Create salary slips
✔ Update salary slips
✔ Manage employee payroll data

👨‍🏭 Employee Features
================
✔ Submit monthly expenses
✔ View own salary slips (history table)
✔ View own expense submissions
✔ Dashboard with salary + expense charts

📡 API Endpoints
================
🔐 Authentication
Method	Endpoint	Description
POST	/auth/signup	Register admin/employee
POST	/auth/login	Login & start session
GET	/auth/me	Get logged-in user details


🧑‍💼 Admin Endpoints
===================
Method	Endpoint	Description
POST	/salary-slip	Create salary slip for employee
PUT	/salary-slip	Update salary slip


👨‍🏭 Employee Endpoints
====================
Method	Endpoint	Description
GET	/salary-slip	View own salary slips
POST	/expense	Submit expense
GET	/expense	View expense history


🛠 Tech Stack
============
Backend
-------

Advanced Java (Servlets)

HttpSession Authentication

JDBC + SQL Database

Postman for API Testing

Frontend
--------

JSP / HTML / CSS (Optional UI)

Google Charts / Chart.js for dashboard charts

Database
-------
SQL (Oracle/MySQL/PostgreSQL supported)

Tables:
======
payroll_users

payroll_employees

salary_slips

expenses

📂 Project Structure
====================
/backend
    ├── src/
    │   ├── servlets/
    │   ├── dao/
    │   ├── models/
    │   └── utils/
    ├── webapp/
    │   ├── WEB-INF/
    │   └── views/
README.md

▶️ How to Run the Project
================
1. Import Project

Open Eclipse


File → Import → Existing Dynamic Web Project

2. Set Up Database
================
Run SQL scripts for tables:

users

employees

salary_slips

expenses

Insert seeded demo user:

INSERT INTO payroll_users (email, password, role)
VALUES ('hire-me@anshumat.org', 'HireMe@2025!', 'admin');

3. Configure Connection
================================
Update DB details in:

/src/utils/DBConnection.java

4. Run the Server
================
Use Apache Tomcat

URL: http://localhost:8080/Payroll-Management-System

📬 Postman Collection

The project includes Postman APIs for:

Authentication

Expense management

Salary slip CRUD operations
================================
👨‍💻 Why Advanced Java? (Tech Justification)

Servlets provide low-level control of HTTP, ideal for learning backend fundamentals

HttpSession makes session/login management simple and secure

JDBC is lightweight, fast, and perfect for internship-level backend APIs

SQL relational databases ensure structured payroll data integrity

Postman helps test REST APIs cleanly and document endpoints

This stack demonstrates:

Backend API development

Authentication logic

SQL joins, CRUD operations

Secure session handling

Clean code layering (Servlet → Service/DAO → DB)

🎯 Outcome
===========
This project demonstrates the ability to:

Build real-world REST APIs using Java

Implement role-based security

Work with SQL databases

Handle payroll + expense modules

Produce clean, production-style backend code
