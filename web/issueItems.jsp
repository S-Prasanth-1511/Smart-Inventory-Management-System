<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Issue Items</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eaeaea;
            padding: 50px;
            text-align: center;
            color: #333;
        }
        h2 {
            color: #444;
        }
        form {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 50%;
            margin: 0 auto;
        }
        table {
            width: 100%;
            margin-bottom: 20px;
        }
        td {
            padding: 10px;
            text-align: left;
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #444;
        }
        input, select {
            width: 100%;
            padding: 10px;
            border-radius: 4px;
            border: 1px solid #bbb;
            background-color: #f9f9f9;
            color: #333;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #808080; 
            color: white;
            border: none;
            border-radius: 4px;
            font-weight: bold;
            cursor: pointer;
        }
        button:hover {
            background-color: #696969; 
        }
        a {
            color: #555; 
            text-decoration: none;
            font-weight: bold;
        }
        a:hover {
            color: #333; 
        }
        .actions {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h2>Issue Items</h2>

    <form action="IssueItemsServlet" method="post">
        <table>
            <tr>
                <td><label for="item_id">Select Item:</label></td>
                <td>
                    <select id="item_id" name="item_id">
                        <option value="1">Item 1</option>
                        <option value="2">Item 2</option>
                        <option value="3">Item 3</option>
                        <option value="4">Item 4</option>
                        <option value="5">Item 5</option>
                        <option value="6">Item 6</option>
                        <option value="7">Item 7</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="quantity">Quantity:</label></td>
                <td><input type="number" id="quantity" name="quantity" min="1" required></td>
            </tr>
            <tr>
                <td><label for="issued_to">Issued To:</label></td>
                <td><input type="text" id="issued_to" name="issued_to" required></td>
            </tr>
        </table>

        <input type="hidden" name="issued_by" value="${sessionScope.employeeName}">

        <button type="submit">Issue Item</button>
    </form>

    <div class="actions">
        <a href="employeeDashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
