package dao;

import factory.ConnectionFactory;
import factory.PasswordHasher;
import modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.swing.JOptionPane;

public class AdminDAO {

    private Connection conn;

    public AdminDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public void cadastrar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, salt, isAdmin) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql);) {
            byte[] salt = PasswordHasher.gerarSalt();
            String senhaHashed = PasswordHasher.hashSenha(usuario.getSenha(), salt);
            String saltstring = Base64.getEncoder().encodeToString(salt);

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, senhaHashed);
            ps.setString(4, saltstring);
            ps.setString(5, usuario.getIsAdmin());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: \n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void alterar(Usuario usuario) throws Exception {
        String sql = "UPDATE usuario SET nome = ?, senha = ?, isAdmin = ? WHERE email = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getIsAdmin());
            ps.setString(4, usuario.getEmail());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados do usuário: \n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void remover(Usuario usuario) {
        String sql = "UPDATE usuario SET ativo = 'N' WHERE email=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getEmail());

            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao desativar usuário: \n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Usuario buscar(String email) {
        String sql = "SELECT * from usuario WHERE email = ? AND ativo = 'S'";
        Usuario usuario = new Usuario();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario.setNome(rs.getString("nome"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setIsAdmin(rs.getString("isAdmin"));
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário informado não existe \n", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro!: \n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return usuario;
    }
    
        public List<Usuario> listar() {
        String sql = "SELECT * FROM usuario WHERE ativo='S'";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setIsAdmin(rs.getString("isAdmin"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuários: \n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return usuarios;
    }
}
