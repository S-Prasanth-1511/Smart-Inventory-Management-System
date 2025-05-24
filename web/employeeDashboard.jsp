<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        h1 {
            background-color: #333;
            color: white;
            padding: 20px;
            margin: 0;
            text-align: center;
        }
        .container {
            margin: 30px auto;
            padding: 20px;
            max-width: 600px;
            background: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        .container p {
            margin: 0 0 20px;
            color: #555;
            font-size: 16px;
        }
        .actions {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        .actions a {
            text-decoration: none;
            color: white;
            background-color: #444;
            padding: 10px 15px;
            border-radius: 5px;
            font-weight: bold;
            text-align: center;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }
        .actions a:hover {
            background-color: #555;
            transform: scale(1.05);
        }
    </style>
</head>
<body>
    <h1>Employee Dashboard</h1>
    <div class="container">
        <p>Welcome, Employee! Select an action below:</p>
        <div class="actions">
            <a href="ViewInventoryServlet1">View Inventory</a>
            <a href="reorderItems.jsp">Reorder Items</a>
            <a href="ViewReorderServlet">View Reorder Details</a>
            <a href="issueItems.jsp">Issue Items</a>
            <a href="ViewIssuedItemsServlet">View Issued Items</a>
            <a href="LogoutServlet">Logout</a>
        </div>
    </div>
</body>
</html>
