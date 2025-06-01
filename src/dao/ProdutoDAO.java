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
import javax.swing.JOptionPane;

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
        
        String sql = "INSERT INTO produtos (nome, descricao, procedencia, quantidade, ativo) VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        try{
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setString(3, p.getProceder());
            stmt.setInt(4, p.getQuantidade());
            stmt.setString(5, p.getAtivo());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new Exception (e);
        }
        
    }
    
    public Produto consultProd (String IDouNOME) throws Exception{
        
        Produto p = new Produto();
        
        PreparedStatement stmt;
        
        try {
            try {
                String sql = "SELECT * from produtos where idProdutos = ?";
        
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(IDouNOME));
                
            } catch (NumberFormatException e) {
                
                String sql = "SELECT * from produtos where nome LIKE ?";
                stmt = con.prepareStatement(sql);
                
                stmt.setString(1, IDouNOME);
                
            }
            
            ResultSet rs  = stmt.executeQuery();
            
            if (rs.next()) {
                p.setIdProduto(rs.getInt("idProdutos"));
                 p.setNome(rs.getString("nome"));
                 p.setDescricao(rs.getString("descricao"));
                 p.setProceder(rs.getString("procedencia"));
                 p.setQuantidade(rs.getInt("quantidade"));
                 p.setAtivo(rs.getString("ativo"));
                
            }
            
        } catch (SQLException e) {
            throw new Exception(e);
        }
        
        return p;
    }
    
    public void updateProdu (Produto p) throws Exception{
        
        String sql = "UPDATE produtos set nome = ?, descricao = ?, procedencia = ?, quantidade = ?, ativo = ? where idProdutos = ?";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        try {
            
            stmt.setInt(6, p.getIdProduto());
            
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setString(3, p.getProceder());
            stmt.setInt(4, p.getQuantidade());
            stmt.setString(5, p.getAtivo());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}
