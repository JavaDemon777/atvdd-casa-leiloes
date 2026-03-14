package vendas.dao;

import java.sql.ResultSet; 
import java.util.ArrayList; 
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import vendas.dto.ProdutosDTO; 

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        conn = (Connection) new conectaDAO().connectDB();
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            
            prep.execute();
            
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + ex.getMessage());
        
        }
        
    }

     public ArrayList<ProdutosDTO> listarProdutos() {
String sql = "SELECT * FROM produtos";
    conn = new conectaDAO().connectDB();
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    try {
        prep = conn.prepareStatement(sql);
        ResultSet rs = prep.executeQuery();
        
        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));
            
            listagem.add(produto);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar: " + e.getMessage());
    }
    return listagem;
}
     
     public void venderProduto(int id) {
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
    conn = new conectaDAO().connectDB();
    
    try {
        prep = conn.prepareStatement(sql);
        prep.setInt(1, id);
        
        prep.executeUpdate(); // Executa a alteração
        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
    }
}
}