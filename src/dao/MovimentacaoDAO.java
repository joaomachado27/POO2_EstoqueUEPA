package dao;

import factory.ConnectionFactory;
import modelo.Movimentacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class MovimentacaoDAO {

    private Connection conn;

    public MovimentacaoDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public void inserir(Movimentacao movimentacao) {

        String sql = "INSERT INTO movimentacao (tipo, idProduto, data, hora, quantidade, usuario) VALUES (?, ?, date(now()), time(now()), ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, movimentacao.getTipo());
            ps.setInt(2, movimentacao.getIdProduto());
            ps.setInt(3, movimentacao.getQuantidade());
            ps.setString(4, movimentacao.getUsuarioResponsavel());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar movimentação: \n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Movimentacao consultar() {
        String sql = "SELECT * from movimentacao";
        Movimentacao movimentacao = new Movimentacao();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    movimentacao.setIdMov(rs.getInt("idMov"));
                    movimentacao.setTipo(rs.getString("tipo"));
                    movimentacao.setIdProduto(rs.getInt("idProduto"));
                    movimentacao.setData(rs.getDate("data").toString());
                    movimentacao.setHora(rs.getTime("hora").toString());
                    movimentacao.setQuantidade(rs.getInt("quantidade"));
                    movimentacao.setUsuarioResponsavel(rs.getString("usuario"));
                }
            } catch (Exception e) {
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar produto: \n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return movimentacao;
    }

}
