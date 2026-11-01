package vn.edu.ptit.oop.nhom08;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Font;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.StandardChartTheme;
import vn.edu.ptit.oop.nhom08.ui.MainWindow;

public class Main {

    public static void main(String[] args) {
        // Tắt native library của FlatLaf để tương thích ổn định trên Java 21+
        System.setProperty("flatlaf.useNativeLibrary", "false");

        try {
            FlatLightLaf.setup();
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
        }

        // Cấu hình font Segoe UI cho JFreeChart hiển thị tiếng Việt có dấu chuẩn xác
        StandardChartTheme theme = (StandardChartTheme) StandardChartTheme.createJFreeTheme();
        theme.setExtraLargeFont(new Font("Segoe UI", Font.BOLD, 18));
        theme.setLargeFont(new Font("Segoe UI", Font.BOLD, 14));
        theme.setRegularFont(new Font("Segoe UI", Font.PLAIN, 12));
        ChartFactory.setChartTheme(theme);

        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}
