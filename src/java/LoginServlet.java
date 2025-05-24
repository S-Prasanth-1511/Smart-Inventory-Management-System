import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String dbUrl = "jdbc:mysql://localhost:3306/inventory_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "2022506018";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            String query = "SELECT role FROM employees WHERE username = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");

                HttpSession session = request.getSession();
                session.setAttribute("employeeName", username);

                if ("admin".equalsIgnoreCase(role)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("adminDashboard.jsp");
                    dispatcher.forward(request, response);
                } else if ("employee".equalsIgnoreCase(role)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("employeeDashboard.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                request.setAttribute("error", "Invalid username or password");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw new ServletException("Database error: " + e.getMessage(), e); 
        }
    }
}
