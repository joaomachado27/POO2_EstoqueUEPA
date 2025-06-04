package dao;

import factory.ConnectionFactory;
import factory.PasswordHasher;
import modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Base64;


public class AdminDAO {
    Connection con;
    public String status;

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
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            
            if(rs.next()){
                senhadb = rs.getString("senha");
                salt = rs.getString("salt");
                saltdb = Base64.getDecoder().decode(salt);
                return PasswordHasher.verificarSenha(senha, senhadb, saltdb);               
            }
            
            
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return false;
    }
    
    
    public void cadastrarUsuario (Usuario usuario) throws Exception {
        String sql = "INSERT INTO usuario (nome, email, senha, salt, isAdmin) VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        try {
            byte[] salt = PasswordHasher.gerarSalt();
            String senhaHashed = PasswordHasher.hashSenha(usuario.getSenha(), salt);
            String saltstring = Base64.getEncoder().encodeToString(salt);
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, senhaHashed);
            stmt.setString(4, saltstring);
            stmt.setString(5, usuario.getIsAdmin());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new Exception("Erro ao cadastrar o usuário\n\n" + e);
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
                u.setIsAdmin(rs.getString("isAdmin"));
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return u;
    }
    
    public void alterUser(Usuario u) throws Exception{
        
        String sql = "UPDATE usuario set nome = ?, senha = ?,isAdmin = ? where email = ?";
        
        
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(5, u.getEmail());
            
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getSenha());
            stmt.setString(3, u.getIsAdmin());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new Exception(e);
        }
        
    }
}
