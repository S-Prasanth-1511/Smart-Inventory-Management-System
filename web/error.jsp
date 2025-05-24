<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            color: #333;
            text-align: center;
            padding: 50px;
            margin: 0;
        }
        h1 {
            color: #444;
            font-size: 28px;
            margin-bottom: 20px;
        }
        p {
            font-size: 18px;
            color: #666;
            margin-bottom: 30px;
        }
        a {
            text-decoration: none;
            color: white;
            background-color: #444;
            padding: 10px 15px;
            border-radius: 5px;
            font-weight: bold;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }
        a:hover {
            background-color: #555;
            transform: scale(1.05);
        }
    </style>
</head>
<body>
    <h1>Error</h1>
    <p>${param.message}</p>
    <a href="employeeDashboard.jsp">Back to Dashboard</a>
</body>
</html>
