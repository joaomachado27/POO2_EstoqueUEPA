package dao;

import java.sql.Connection;
import factory.ConnectionFactory;
import factory.PasswordHasher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import javax.swing.JOptionPane;
import utils.Sessao;

public class LoginDAO {

    private Connection conn;

    public LoginDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public boolean fazerLogin(String email, String senha) {
        String sql = "SELECT nome, senha, salt, isAdmin, ativo FROM usuario WHERE email = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    String ativo = rs.getString("ativo");
                    if (ativo.equalsIgnoreCase("N")) {
                        JOptionPane.showMessageDialog(null, "Usuário inativo!");
                        return false;
                    }

                    String nome = rs.getString("nome");
                    String senhadb = rs.getString("senha");
                    String salt = rs.getString("salt");
                    boolean admin = rs.getString("isAdmin").equalsIgnoreCase("S");

                    byte[] saltdb = Base64.getDecoder().decode(salt);

                    boolean senhaValida = PasswordHasher.verificarSenha(senha, senhadb, saltdb);

                    if (senhaValida) {
                        Sessao.iniciarSessao(nome, email, admin);
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha incorreta!: \n", "Erro", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado!: \n", "Erro", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro!: \n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
