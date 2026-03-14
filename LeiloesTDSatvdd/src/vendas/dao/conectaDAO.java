package vendas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    public Connection connectDB(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11", "root", "");
            return conn;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro Conexão: " + erro.getMessage());
            return null;
        }
    }
}