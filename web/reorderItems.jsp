<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reorder Items</title>
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
        input, select {
            width: 100%;
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #bbb;
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
    </style>
</head>
<body>
    <h2>Reorder Items</h2>

    <form action="ReorderItemsServlet" method="POST">
        <table>
            <tr>
                <td>Item:</td>
                <td>
                    <select name="itemId">
                        <option value="1">id1</option>
                        <option value="2">id2</option>
                        <option value="3">id3</option>
                        <option value="4">id4</option>
                        <option value="5">id5</option>
                        <option value="6">id6</option>
                        <option value="7">id7</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Quantity:</td>
                <td><input type="number" name="quantity" required></td>
            </tr>
            <tr>
                <td>Reorder Date:</td>
                <td><input type="date" name="reorderDate" required></td>
            </tr>
        </table>
        <div>
            <button type="submit">Reorder Item</button>
        </div>
    </form>

    <div style="margin-top: 20px;">
        <a href="employeeDashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
