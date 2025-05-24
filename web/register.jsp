<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }
        form {
            width: 40%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #444;
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #444;
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
            background-color: #f9f9f9;
            box-sizing: border-box; 
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #444;
            color: white;
            border: none;
            border-radius: 4px;
            font-weight: bold;
        }
        button:hover {
            background-color: #555;
        }
        p {
            text-align: center;
        }
        a {
            text-decoration: none;
            color: #444;
            font-weight: bold;
        }
        a:hover {
            color: #007bff;
        }
        .error-message {
            color: red;
            text-align: center;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <form action="RegisterServlet" method="post">
        <h2>Register</h2>

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <label for="role">Role:</label>
        <select id="role" name="role" required>
            <option value="admin">Admin</option>
            <option value="employee">Employee</option>
        </select><br>

        <label for="first_name">First Name:</label>
        <input type="text" id="first_name" name="first_name" required><br>

        <label for="last_name">Last Name:</label>
        <input type="text" id="last_name" name="last_name" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>

        <button type="submit">Register</button>

        <p>Already have an account? <a href="login.jsp">Login here</a></p>

        <p class="error-message">
            ${error != null ? error : ""}
        </p>
    </form>
</body>
</html>
