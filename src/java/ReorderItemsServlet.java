import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/ReorderItemsServlet")
public class ReorderItemsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dbUrl = "jdbc:mysql://localhost:3306/inventory_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "2022506018";

        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String reorderDate = request.getParameter("reorderDate"); 

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            conn.setAutoCommit(false);

            String insertReorderSQL = "INSERT INTO reorders (item_id, quantity, reorder_date) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(insertReorderSQL);
            stmt.setInt(1, itemId);
            stmt.setInt(2, quantity);
            stmt.setString(3, reorderDate);
            stmt.executeUpdate(); 

            String updateInventorySQL = "UPDATE inventory SET quantity = quantity + ? WHERE id = ?";
            stmt = conn.prepareStatement(updateInventorySQL);
            stmt.setInt(1, quantity); 
            stmt.setInt(2, itemId); 
            stmt.executeUpdate(); 

            String itemName = getItemName(conn, itemId);
            if (itemName == null) {
                throw new SQLException("Item with ID " + itemId + " not found.");
            }

            String reportType = "Reordered items";
            String reportData = "Reordered " + quantity + " units of " + itemName;
            String reportDate = LocalDate.now().toString();

            String insertReportSQL = "INSERT INTO reports (report_type, report_date, report_data) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(insertReportSQL);
            stmt.setString(1, reportType);
            stmt.setString(2, reportDate);
            stmt.setString(3, reportData);
            stmt.executeUpdate(); 

            conn.commit();

            response.sendRedirect("success1.jsp");

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
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

    private String getItemName(Connection conn, int itemId) throws SQLException {
        String sql = "SELECT item_name FROM inventory WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("item_name");
            }
        }
        return null;
    }
}
