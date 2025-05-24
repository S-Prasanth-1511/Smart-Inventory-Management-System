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
import java.util.*;

@WebServlet("/ViewInventoryServlet1")
public class ViewInventoryServlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        
        String sql = "SELECT id, item_name, quantity, price FROM inventory";
        List<String> lowStockAlerts = new ArrayList<>();
        List<Map<String, Object>> inventoryList = new ArrayList<>();

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int quantity = rs.getInt("quantity");
                int itemId = rs.getInt("id");
                String itemName = rs.getString("item_name");

                Map<String, Object> item = new HashMap<>();
                item.put("id", itemId);
                item.put("itemName", itemName);
                item.put("quantity", quantity);
                item.put("price", rs.getBigDecimal("price"));
                inventoryList.add(item);

                if (quantity < 20) {
                    lowStockAlerts.add("Item Id " + itemId + " (" + itemName + ") is very low on stock. Please reorder.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            lowStockAlerts.add("Error retrieving inventory. Please try again later.");
        }

        request.setAttribute("inventoryList", inventoryList);
        request.setAttribute("lowStockAlerts", lowStockAlerts);
        request.getRequestDispatcher("viewInventory_emp.jsp").forward(request, response);
    }

    private Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:mysql://localhost:3306/inventory_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "2022506018";
        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }
}

