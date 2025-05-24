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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/EarningsAnalysisServlet")
public class EarningsAnalysisServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        String earningsQuery = 
            "SELECT inv.item_name, SUM(ii.quantity * inv.price) AS total_earnings " +
            "FROM issued_items ii " +
            "JOIN inventory inv ON ii.item_id = inv.id " +
            "GROUP BY ii.item_id, inv.item_name";
        
        JSONArray earningsData = new JSONArray();
        
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(earningsQuery)) {
            while (rs.next()) {
                JSONObject itemData = new JSONObject();
                try {
                    itemData.put("itemName", rs.getString("item_name"));
                    itemData.put("totalEarnings", rs.getDouble("total_earnings"));
                } catch (JSONException ex) {
                    Logger.getLogger(EarningsAnalysisServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                earningsData.put(itemData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"error\": \"Failed to fetch earnings data.\"}");
            return;
        }
        
        out.print(earningsData.toString());
    }
    
    private Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:mysql://localhost:3306/inventory_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "2022506018";
        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }
}
