import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/ManageEmployeesServlet")
public class ManageEmployeesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sql = "SELECT id, username, role, first_name, last_name, email FROM employees";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Employee Management</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f1f1f1; color: #333; margin: 0; padding: 0; }");
            out.println("h2 { color: #444; text-align: center; margin-top: 20px; }");
            out.println("table { width: 80%; margin: 20px auto; border-collapse: collapse; background-color: white; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
            out.println("th, td { border: 1px solid #ccc; padding: 12px; text-align: center; }");
            out.println("th { background-color: #e0e0e0; font-weight: bold; color: #333; }");
            out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
            out.println("tr:hover { background-color: #f2f2f2; }");
            out.println(".actions a { margin: 0 5px; text-decoration: none; color: #555; font-weight: bold; }");
            out.println(".actions a:hover { text-decoration: underline; color: #333; }");
            out.println("a { color: #555; text-decoration: none; }");
            out.println("a:hover { text-decoration: underline; color: #333; }");
            out.println(".add-employee { text-align: center; margin: 20px; }");
            out.println(".add-employee a { padding: 10px 20px; background-color: #444; color: white; text-decoration: none; border-radius: 5px; font-weight: bold; }");
            out.println(".add-employee a:hover { background-color: #333; }");
            out.println(".footer { text-align: center; margin-top: 20px; padding: 10px; background-color: #e0e0e0; color: #555; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Manage Employees</h2>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Username</th>");
            out.println("<th>Role</th>");
            out.println("<th>First Name</th>");
            out.println("<th>Last Name</th>");
            out.println("<th>Email</th>");
            out.println("<th>Actions</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("username") + "</td>");
                out.println("<td>" + rs.getString("role") + "</td>");
                out.println("<td>" + rs.getString("first_name") + "</td>");
                out.println("<td>" + rs.getString("last_name") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td class='actions'>");
                out.println("<a href='EditEmployeeServlet?id=" + rs.getInt("id") + "'>Edit</a> | ");
                out.println("<a href='DeleteEmployeeServlet?id=" + rs.getInt("id") + "' onclick='return confirm(\"Are you sure you want to delete this employee?\");'>Delete</a>");
                out.println("</td>");
                out.println("</tr>");
            }

            if (!hasData) {
                out.println("<tr><td colspan='7'>No employees found.</td></tr>");
            }

            out.println("</tbody>");
            out.println("</table>");

            out.println("<div class='add-employee'>");
            out.println("<a href='register.jsp'>Add Employee</a>");
            out.println("</div>");

            out.println("<div style='text-align: center;'>");
            out.println("<a href='adminDashboard.jsp'>Back to Dashboard</a> | <a href='LogoutServlet'>Logout</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.getWriter().println("<p>Error retrieving employee data. Please try again later.</p>");
        }
    }

    private Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:mysql://localhost:3306/inventory_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "2022506018";
        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }
}
