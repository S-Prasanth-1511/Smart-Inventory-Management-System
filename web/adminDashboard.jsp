<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        h1 {
            color: #555;
            text-align: center;
            margin-bottom: 20px;
        }
        p {
            text-align: center;
            color: #666;
            margin-bottom: 30px;
        }
        .container {
            margin: 30px auto;
            padding: 20px;
            width: 70%;
            background: white;
            border: 1px solid #ddd;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        .nav-links {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            gap: 10px;
        }
        .nav-links a {
            text-decoration: none;
            color: white;
            background-color: #444;
            padding: 12px 20px;
            border-radius: 5px;
            font-size: 16px;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }
        .nav-links a:hover {
            background-color: #555;
            transform: scale(1.05);
        }
        footer {
            text-align: center;
            margin-top: 20px;
            color: #777;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Admin Dashboard</h1>
        <p>Welcome, Admin! Use the links below to manage the system.</p>
        <div class="nav-links">
            <a href="ManageEmployeesServlet">Manage Employees</a>
            <a href="ViewInventoryServlet">View Inventory</a>
            <a href="ViewReportsServlet">View Reports</a>
            <a href="ViewSuppliersServlet">View Suppliers</a>
            <a href="earnings_analysis.jsp">Earnings Analysis</a>
            <a href="LogoutServlet">Logout</a>
        </div>
    </div>
    <footer>
        <p>&copy; 2024 Inventory Management System</p>
    </footer>
</body>
</html>
