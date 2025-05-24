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

@WebServlet("/ViewReorderServlet")
public class ViewReorderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Reorder Items</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #eaeaea; color: #333; margin: 0; padding: 50px; }");
        out.println("h1 { color: #444; text-align: center; margin-bottom: 40px; }");
        out.println("table { width: 80%; margin: 20px auto; border-collapse: collapse; background-color: white; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
        out.println("th, td { border: 1px solid #ddd; padding: 10px; text-align: center; }");
        out.println("th { background-color: #808080; color: white; }");
        out.println("td { background-color: #f9f9f9; }");
        out.println(".actions a { padding: 10px 20px; border-radius: 5px; text-decoration: none; color: white; font-weight: bold; }");
        out.println(".actions a:nth-child(1) { background-color: #4CAF50; }");
        out.println(".actions a:nth-child(2) { background-color: #f44336; }");
        out.println(".actions a:hover { background-color: #555; }");
        out.println("button {");
        out.println("    padding: 10px 20px;");
        out.println("    background-color: #808080; /* Grey */");
        out.println("    color: white;");
        out.println("    border: none;");
        out.println("    border-radius: 4px;");
        out.println("    font-weight: bold;");
        out.println("    cursor: pointer;");
        out.println("}");
        out.println("button:hover {");
        out.println("    background-color: #696969; /* Darker Grey */");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Reorder List</h1>");
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Item ID</th>");
        out.println("<th>Quantity</th>");
        out.println("<th>Reorder Date</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        String sql = "SELECT id, item_id, quantity, reorder_date FROM reorders";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getInt("item_id") + "</td>");
                out.println("<td>" + rs.getInt("quantity") + "</td>");
                out.println("<td>" + rs.getDate("reorder_date") + "</td>");
                out.println("</tr>");
            }
            if (!hasData) {
                out.println("<tr>");
                out.println("<td colspan='4'>No reorder items available.</td>");
                out.println("</tr>");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("<tr>");
            out.println("<td colspan='4'>Error retrieving reorder list. Please try again later.</td>");
            out.println("</tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("<div style='text-align: center; margin-top: 20px;'>");
        out.println("<button onclick=\"window.location.href='employeeDashboard.jsp';\">Back to Dashboard</button>");
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
