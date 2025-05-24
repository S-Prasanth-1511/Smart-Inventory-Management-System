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

@WebServlet("/ViewIssuedItemsServlet")
public class ViewIssuedItemsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>View Issued Items</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f9f9f9; color: #333; margin: 0; padding: 0; }");
        out.println("h1 { color: #333; text-align: center; margin-top: 50px; }");
        out.println("table { width: 80%; margin: 20px auto; border-collapse: collapse; }");
        out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println(".actions a { margin: 0 5px; text-decoration: none; color: #4CAF50; }");
        out.println("button {");
        out.println("    padding: 10px 20px;");
        out.println("    background-color: #808080; /* Grey */");
        out.println("    color: white;");
        out.println("    border: none;");
        out.println("    border-radius: 5px;");
        out.println("    font-weight: bold;");
        out.println("    cursor: pointer;");
        out.println("}");
        out.println("button:hover {");
        out.println("    background-color: #696969; /* Darker Grey */");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Issued Items</h1>");
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Issued ID</th>");
        out.println("<th>Item ID</th>");
        out.println("<th>Quantity</th>");
        out.println("<th>Issued To</th>");
        out.println("<th>Issued By</th>");
        out.println("<th>Issue Date</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        String sql = "SELECT id, item_id, quantity, issued_to, issued_by, issue_date FROM issued_items";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getInt("item_id") + "</td>");
                out.println("<td>" + rs.getInt("quantity") + "</td>");
                out.println("<td>" + rs.getString("issued_to") + "</td>");
                out.println("<td>" + rs.getString("issued_by") + "</td>");
                out.println("<td>" + rs.getDate("issue_date") + "</td>");
                out.println("</tr>");
            }
            if (!hasData) {
                out.println("<tr>");
                out.println("<td colspan='6'>No issued items found.</td>");
                out.println("</tr>");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("<tr>");
            out.println("<td colspan='6'>Error retrieving issued items. Please try again later.</td>");
            out.println("</tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("<div style='text-align: center;'>");
        out.println("<a href='employeeDashboard.jsp' style='padding: 10px 20px; background-color: #808080; color: white; text-decoration: none; border-radius: 5px;'>Back to Dashboard</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    private Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:mysql://localhost:3306/inventory_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "2022506018";
        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }
}
