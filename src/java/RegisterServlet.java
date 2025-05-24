import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/inventory_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "2022506018";
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        
        String sql = "INSERT INTO employees (username, password, role, first_name, last_name, email) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, role);
            pst.setString(4, firstName);
            pst.setString(5, lastName);
            pst.setString(6, email);

            int rowsAffected = pst.executeUpdate();
            
            if (rowsAffected > 0) {
                response.setContentType("text/html");
                response.getWriter().println("<html><body>");
                response.getWriter().println("<style>");
                response.getWriter().println("body { font-family: Arial, sans-serif; background-color: #f9f9f9; color: #333; margin: 0; padding: 0; }");
                response.getWriter().println("h3 { color: #4CAF50; text-align: center; margin-top: 50px; }");
                response.getWriter().println("</style>");
                response.getWriter().println("<h3>Registration successful! Redirecting to login page...</h3>");
                response.getWriter().println("<script>setTimeout(function(){ window.location.href='login.jsp'; }, 3000);</script>");
                response.getWriter().println("</body></html>");
            } else {
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Database error. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
