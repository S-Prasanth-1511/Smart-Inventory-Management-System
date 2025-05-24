<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Employees</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        h2 {
            background-color: #5D5D5D;
            color: white;
            padding: 15px;
            margin: 0;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .actions a {
            margin: 0 5px;
            text-decoration: none;
            color: #007BFF;
        }
        .actions a:hover {
            color: #0056b3;
        }
        .btn-add {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin: 20px;
        }
        .btn-add:hover {
            background-color: #45a049;
        }
        .footer-links a {
            margin: 0 10px;
            text-decoration: none;
            color: #007BFF;
        }
        .footer-links a:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>Manage Employees</h2>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Role</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="employee" items="${employeeList}">
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.username}</td>
                    <td>${employee.role}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.email}</td>
                    <td class="actions">
                        <a href="EditEmployeeServlet?id=${employee.id}">Edit</a> |
                        <a href="DeleteEmployeeServlet?id=${employee.id}" onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div>
        <a href="register.jsp" class="btn-add">Add Employee</a>
    </div>

    <div class="footer-links">
        <a href="adminDashboard.jsp">Back to Dashboard</a> | 
        <a href="LogoutServlet">Logout</a>
    </div>
</body>
</html>
