import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/DownloadReportServlet")
public class DownloadReportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Reports.xlsx");

        try (Workbook workbook = new XSSFWorkbook(); 
             OutputStream out = response.getOutputStream()) {

            Sheet sheet = workbook.createSheet("Reports");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Report Type");
            headerRow.createCell(1).setCellValue("Report Date");
            headerRow.createCell(2).setCellValue("Report Data");

            String sql = "SELECT report_type, report_date, report_data FROM reports";
            try (Connection conn = getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                int rowIndex = 1; 
                while (rs.next()) {
                    Row row = sheet.createRow(rowIndex++);
                    row.createCell(0).setCellValue(rs.getString("report_type"));
                    row.createCell(1).setCellValue(rs.getDate("report_date").toString());
                    row.createCell(2).setCellValue(rs.getString("report_data"));
                }
            }

            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to generate report. Please try again.");
        }
    }

    private Connection getConnection() throws Exception {
        String dbUrl = "jdbc:mysql://localhost:3306/inventory_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "2022506018";
        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }
}
