<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Inventory</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f1f1f1;
            color: #333;
            margin: 20px;
        }
        h1 {
            text-align: center;
            color: #444;
            margin-bottom: 20px;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #e0e0e0;
            font-weight: bold;
            color: #333;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f2f2f2;
        }
        .alert {
            color: #d9534f;
            background-color: #f9e2e1;
            border: 1px solid #d9534f;
            padding: 10px;
            margin: 20px auto;
            width: 80%;
            text-align: center;
            font-weight: bold;
            border-radius: 5px;
        }
        a {
            text-decoration: none;
            color: #555;
            margin: 0 10px;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
            color: #333;
        }
        div {
            text-align: center;
            margin-top: 20px;
        }
    </style>

</head>
<body>
    <h1>Inventory List</h1>

    <c:if test="${not empty lowStockAlerts}">
        <div class="alert">
            <c:forEach var="alert" items="${lowStockAlerts}">
                <p>${alert}</p>
            </c:forEach>
        </div>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Item Name</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${inventoryList}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.itemName}</td>
                    <td>${item.quantity}</td>
                    <td>${item.price}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty inventoryList}">
                <tr>
                    <td colspan="4">No inventory items available.</td>
                </tr>
            </c:if>
        </tbody>
    </table>

    <div style="text-align: center;">
        <a href="employeeDashboard.jsp">Back to Dashboard</a> | 
        <a href="LogoutServlet">Logout</a>
    </div>
</body>
</html>
