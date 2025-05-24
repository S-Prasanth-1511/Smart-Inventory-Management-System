<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        header {
            background-color: #333;
            color: white;
            padding: 20px 0;
            text-align: center;
        }
        main {
            max-width: 600px;
            margin: 30px auto;
            padding: 20px;
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        
        h1{
            text-align: center;
            color:#ffffff;
        }
        h2 {
            text-align: center;
            color: #555;
        }
        p {
            text-align: center;
            color: #666;
            line-height: 1.6;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        ul li {
            margin: 15px 0;
        }
        ul li a {
            text-decoration: none;
            color: white;
            font-size: 18px;
            display: block;
            padding: 12px;
            background-color: #444;
            border-radius: 5px;
            text-align: center;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }
        ul li a:hover {
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
    <header>
        <h1>Inventory Management System</h1>
    </header>
    <main>
        <h2>Welcome!</h2>
        <p>Manage your inventory efficiently with our comprehensive system. Sign in below to access different functionalities.</p>
        <ul>
            <li><a href="login.jsp">Admin / Employee Login</a></li>
            <li><a href="register.jsp">Register New User</a></li>
        </ul>
    </main>
    <footer>
        <p>&copy; 2024 Inventory Management System. All rights reserved.</p>
    </footer>
</body>
</html>
