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

@WebServlet("/ViewReportsServlet")
public class ViewReportsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>View Reports</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f1f1f1; color: #333; margin: 0; padding: 0; }");
        out.println("h1 { color: #444; text-align: center; margin-top: 50px; }");
        out.println("table { width: 80%; margin: 20px auto; border-collapse: collapse; background-color: white; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
        out.println("th, td { border: 1px solid #ccc; padding: 12px; text-align: center; }");
        out.println("th { background-color: #e0e0e0; font-weight: bold; color: #333; }");
        out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
        out.println("tr:hover { background-color: #f2f2f2; }");
        out.println("button { padding: 10px 20px; font-size: 16px; cursor: pointer; background-color: #555; color: white; border: none; border-radius: 5px; margin-top: 20px; }");
        out.println("button:hover { background-color: #333; }");
        out.println("a { text-decoration: none; color: #555; font-weight: bold; padding: 10px 20px; border-radius: 5px; background-color: #e0e0e0; margin: 5px; }");
        out.println("a:hover { text-decoration: underline; background-color: #d6d6d6; color: #333; }");
        out.println(".actions { text-align: center; margin-top: 20px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Reports</h1>");
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Report Type</th>");
        out.println("<th>Report Date</th>");
        out.println("<th>Report Data</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        String sql = "SELECT report_type, report_date, report_data FROM reports";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                out.println("<tr>");
                out.println("<td>" + rs.getString("report_type") + "</td>");
                out.println("<td>" + rs.getDate("report_date") + "</td>");
                out.println("<td>" + rs.getString("report_data") + "</td>");
                out.println("</tr>");
            }
            if (!hasData) {
                out.println("<tr>");
                out.println("<td colspan='3'>No reports available.</td>");
                out.println("</tr>");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("<tr>");
            out.println("<td colspan='3'>Error retrieving reports. Please try again later.</td>");
            out.println("</tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("<div class='actions'>");
        out.println("<a href='adminDashboard.jsp'>Back to Dashboard</a>");
        out.println("<a href='LogoutServlet'>Logout</a>");
        out.println("<form action='DownloadReportServlet' method='post' style='display: inline;'>");
        out.println("<button type='submit'>Download Report</button>");
        out.println("</form>");
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

