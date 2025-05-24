# Smart Inventory Management System

A robust inventory management application designed for efficient product tracking and employee coordination. It enables stock monitoring, generates low-stock alerts, and provides tailored dashboards for admins and employees to ensure seamless warehouse operations.

## 🔍 Features
- Real-time product inventory monitoring
- Role-based access (Admin & Employee)
- Employee dashboard with personalized alerts
- Low-stock alerts and auto-notification on login
- CRUD operations for products and employee records
- Secure login and session management

## 🛠️ Tech Stack
- **Frontend**: JSP, HTML, CSS
- **Backend**: Java Servlets
- **Database**: MySQL

## 🚀 Getting Started

### ✅ Prerequisites
- JDK 8 or higher
- Apache Tomcat server
- MySQL server
- IDE like NetBeans or Eclipse

### 📦 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/smart-inventory-management.git

2. **Import the project**

Open the project in NetBeans or your preferred IDE.

Make sure Apache Tomcat is configured.

3. **Set up the database**

Create a new MySQL database.

Import the inventory.sql file located in the project directory.

4. **Configure database connection**

Update the DB credentials in the DBConnection.java file.

5. **Run the application**

Deploy the project to Apache Tomcat.

Visit:

arduino
Copy
Edit
http://localhost:8080/SmartInventory




**Folder Structure**

smart-inventory-management/
├── src/
│   ├── controller/
│   ├── model/
│   ├── view/
│   └── DBConnection.java
├── WebContent/
│   ├── login.jsp
│   ├── dashboard.jsp
│   └── styles/
├── inventory.sql
└── README.md
