/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import factory.ConnectionFactory;
import factory.PasswordHasher;
import modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Base64;
/**
 *
 * @author cacom
 */
public class AdminDAO {
    Connection con;

    public AdminDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public boolean fazerLogin(String email, String senha) throws Exception{
        String sql = "SELECT * FROM usuario WHERE email = ?";  

        String senhadb;
        String salt;
        byte[] saltdb;
        
        try{
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            stmt.setString(1, email);
            
            if(rs.next()){
                senhadb = rs.getString("senha");
                salt = rs.getString("salt");
                saltdb = Base64.getDecoder().decode(salt);
                String senhaHashed = PasswordHasher.hashSenha(senha, saltdb);
                return PasswordHasher.verificarSenha(senhaHashed, senhadb, saltdb);               
            }
            
            
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return false;
    }
    
    
    public void cadastroUser (Usuario user) throws Exception {
        String sql = "INSERT INTO usuario VALUES (?, ?, ?, ?)";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        try {
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getSenha());
            stmt.setBoolean(4, user.getStatus());
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
    
    public Usuario consultUser(String email) throws Exception{
        
        Usuario u = new Usuario();
        
        String sql = " SELECT * from usuario where email = ?";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        try {
            
            stmt.setString(1, email);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setStatus(rs.getBoolean("status"));
                u.setIsAdmin(rs.getBoolean("isAdmin"));
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return u;
    }
    
    public void alterUser(Usuario u) throws Exception{
        
        String sql = "UPDATE usuario set nome = ?, senha = ?, status = ?,isAdmin = ? where email = ?";
        
        
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(5, u.getEmail());
            
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getSenha());
            stmt.setBoolean(3, u.getStatus());
            stmt.setBoolean(4, u.getIsAdmin());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new Exception(e);
        }
        
    }
}
