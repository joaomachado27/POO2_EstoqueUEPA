/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import factory.ConnectionFactory;
import modelo.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author cacom
 */
public class ProdutoDAO {
    Connection con;

    public ProdutoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void cadastroProd(Produto p) throws Exception{
        
        String sql = "INSERT INTO produtos (nome, descricao, procedencia, quantidaade, status) VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        try{
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setString(3, p.getProceder());
            stmt.setInt(4, p.getQuantidade());
            stmt.setBoolean(5, p.getStatus());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new Exception (e);
        }
        
    }
    
    public Produto consultProd (String IDouNOME) throws Exception{
        
        Produto p = new Produto();
        
        String sql = "SELECT * from produtos where ? = ?";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        try {
            try {
                
                stmt.setString(1, "idProduto");
                stmt.setInt(2, Integer.parseInt(IDouNOME));
            } catch (NumberFormatException e) {
                stmt.setString(1, "nome");
                stmt.setString(2, IDouNOME);
            }
            
            ResultSet rs  = stmt.executeQuery();
            
            if (rs.next()) {
                 p.setNome(rs.getString("nome"));
                 p.setDescricao(rs.getString("descricao"));
                 p.setProceder(rs.getString("procedencia"));
                 p.setQuantidade(rs.getInt("quantidade"));
                 
            }
            
        } catch (SQLException e) {
            throw new Exception(e);
        }
        
        return p;
    }
    
    public void updateProdu (Produto p) throws Exception{
        
        String sql = "UPDATE produtos set nome = ? descricao = ?, procedencia = ?, quantidade = ? where idProduto = ?";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        try {
            
            stmt.setInt(5, p.getIdProduto());
            
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setString(3, p.getProceder());
            stmt.setInt(4, p.getQuantidade());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new Exception (e);
        }
    }
}
