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

@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {

    private Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:mysql://localhost:3306/inventory_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "2022506018";
        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, id);
            int rowsDeleted = pst.executeUpdate();

            if (rowsDeleted > 0) {
                response.sendRedirect("ManageEmployeesServlet");
            } else {
                response.getWriter().println("<p>Employee not found or deletion failed.</p>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<p>Database error occurred.</p>");
        }
    }
}
