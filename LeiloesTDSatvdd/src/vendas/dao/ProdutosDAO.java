package vendas.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import vendas.dto.ProdutosDTO; // Importante para reconhecer o DTO

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    
    public void cadastrarProduto (ProdutosDTO produto){
        // 1. Conectar ao banco usando a sua classe conectaDAO
        conn = (Connection) new conectaDAO().connectDB();
        
        // 2. SQL de inserção (ajuste os nomes se o seu script for diferente)
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            // 3. Executar o comando
            prep.execute();
            
            // 4. Mensagem de sucesso (Requisito da atividade)
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        } catch (SQLException ex) {
            // Mensagem de erro caso algo falhe
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + ex.getMessage());
        }
    }
}