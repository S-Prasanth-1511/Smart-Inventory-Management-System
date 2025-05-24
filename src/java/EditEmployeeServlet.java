import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/EditEmployeeServlet")
public class EditEmployeeServlet extends HttpServlet {

    private Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:mysql://localhost:3306/inventory_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "2022506018";
        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM employees WHERE id = ?")) {
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Edit Employee</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #f9f9f9; color: #333; margin: 0; padding: 0; }");
                out.println("h2 { color: #4CAF50; text-align: center; margin-top: 20px; }");
                out.println("form { margin: 0; padding: 20px; background-color: white; width: 60%; margin: 20px auto; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); box-sizing: border-box; }");
                out.println("label { display: block; margin-bottom: 10px; font-weight: bold; }");
                out.println("input[type='text'], input[type='password'], input[type='email'], select { width: calc(100% - 24px); padding: 12px; margin-bottom: 20px; border: 1px solid #ccc; border-radius: 5px; box-sizing: border-box; }");
                out.println("input[type='submit'] { background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; }");
                out.println("input[type='submit']:hover { background-color: #45a049; }");
                out.println("a { display: inline-block; margin-top: 20px; text-align: center; color: #4CAF50; text-decoration: none; }");
                out.println("a:hover { text-decoration: underline; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>Edit Employee Details</h2>");
                out.println("<form action='EditEmployeeServlet' method='POST'>");
                out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");
                out.println("<label>Username: <input type='text' name='username' value='" + rs.getString("username") + "' required></label><br>");
                out.println("<label>Password: <input type='password' name='password' value='" + rs.getString("password") + "' required></label><br>");
                out.println("<label>Role: <select name='role'>");
                out.println("<option value='admin'" + ("admin".equals(rs.getString("role")) ? " selected" : "") + ">Admin</option>");
                out.println("<option value='employee'" + ("employee".equals(rs.getString("role")) ? " selected" : "") + ">Employee</option>");
                out.println("</select></label><br>");
                out.println("<label>First Name: <input type='text' name='first_name' value='" + rs.getString("first_name") + "' required></label><br>");
                out.println("<label>Last Name: <input type='text' name='last_name' value='" + rs.getString("last_name") + "' required></label><br>");
                out.println("<label>Email: <input type='email' name='email' value='" + rs.getString("email") + "' required></label><br>");
                out.println("<input type='submit' value='Update'>");
                out.println("</form>");
                out.println("<a href='ManageEmployeesServlet'>Back to Employee List</a>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<p>Employee not found.</p>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error retrieving employee data.</p>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");

        String sql = "UPDATE employees SET username = ?, password = ?, role = ?, first_name = ?, last_name = ?, email = ? WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, role);
            pst.setString(4, firstName);
            pst.setString(5, lastName);
            pst.setString(6, email);
            pst.setString(7, id);

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                response.sendRedirect("ManageEmployeesServlet");
            } else {
                response.getWriter().println("<p>Error updating employee data.</p>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<p>Database error occurred.</p>");
        }
    }
}
