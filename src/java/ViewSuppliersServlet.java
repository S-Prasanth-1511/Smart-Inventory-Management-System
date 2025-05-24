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

@WebServlet("/ViewSuppliersServlet")
public class ViewSuppliersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sql = "SELECT id, supplier_name, contact_name, phone, email FROM suppliers";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>View Suppliers</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f1f1f1; color: #333; margin: 0; padding: 0; }");
            out.println("h2 { text-align: center; color: #444; margin-top: 20px; }");
            out.println("table { width: 80%; margin: 20px auto; border-collapse: collapse; background-color: white; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
            out.println("th, td { border: 1px solid #ccc; padding: 12px; text-align: center; }");
            out.println("th { background-color: #e0e0e0; font-weight: bold; color: #333; }");
            out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
            out.println("tr:hover { background-color: #f2f2f2; }");
            out.println(".actions { text-align: center; margin-top: 20px; }");
            out.println(".actions a, .actions button { display: inline-block; padding: 10px 20px; background-color: #808080; color: white; text-decoration: none; border-radius: 5px; font-weight: bold; border: none; cursor: pointer; margin: 0 10px; }");
            out.println(".actions a:hover, .actions button:hover { background-color: #696969; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Supplier Details</h2>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Supplier Name</th>");
            out.println("<th>Contact Name</th>");
            out.println("<th>Phone</th>");
            out.println("<th>Email</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("supplier_name") + "</td>");
                out.println("<td>" + rs.getString("contact_name") + "</td>");
                out.println("<td>" + rs.getString("phone") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("</tr>");
            }

            if (!hasData) {
                out.println("<tr><td colspan='5'>No suppliers found.</td></tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("<div class='actions'>");
            out.println("<a href='adminDashboard.jsp'>Back to Dashboard</a>");
            out.println("<form action='logout.jsp' method='post' style='display: inline;'>");
            out.println("<button type='submit'>Logout</button>");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.getWriter().println("<p>Error retrieving supplier data. Please try again later.</p>");
        }
    }

    private Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:mysql://localhost:3306/inventory_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "2022506018";
        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }
}
