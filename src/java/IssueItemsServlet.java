import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/IssueItemsServlet")
public class IssueItemsServlet extends HttpServlet {

    private final String dbUrl = "jdbc:mysql://localhost:3306/inventory_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private final String dbUser = "root";
    private final String dbPass = "2022506018";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemId = Integer.parseInt(request.getParameter("item_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String issuedTo = request.getParameter("issued_to");
        String issuedBy = request.getParameter("issued_by");

        String issueDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            conn.setAutoCommit(false); 

            String checkStockSql = "SELECT item_name, quantity FROM inventory WHERE id = ?";
            stmt = conn.prepareStatement(checkStockSql);
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String itemName = rs.getString("item_name");
                int availableStock = rs.getInt("quantity");

                if (availableStock >= quantity) {
                    String insertSql = "INSERT INTO issued_items (item_id, quantity, issued_to, issued_by, issue_date) VALUES (?, ?, ?, ?, ?)";
                    stmt = conn.prepareStatement(insertSql);
                    stmt.setInt(1, itemId);
                    stmt.setInt(2, quantity);
                    stmt.setString(3, issuedTo);
                    stmt.setString(4, issuedBy);
                    stmt.setString(5, issueDate);
                    stmt.executeUpdate();

                    String updateStockSql = "UPDATE inventory SET quantity = quantity - ? WHERE id = ?";
                    stmt = conn.prepareStatement(updateStockSql);
                    stmt.setInt(1, quantity);
                    stmt.setInt(2, itemId);
                    stmt.executeUpdate();

                    String reportData = "Issued " + quantity + " units of " + itemName;
                    String insertReportSql = "INSERT INTO reports (report_type, report_date, report_data) VALUES (?, ?, ?)";
                    stmt = conn.prepareStatement(insertReportSql);
                    stmt.setString(1, "Issued items");
                    stmt.setString(2, issueDate);
                    stmt.setString(3, reportData);
                    stmt.executeUpdate();

                    conn.commit();

                    response.sendRedirect("success.jsp");
                } else {
                    response.sendRedirect("error.jsp?message=Not enough stock available");
                }
            } else {
                response.sendRedirect("error.jsp?message=Item not found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            response.sendRedirect("error.jsp");

        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
