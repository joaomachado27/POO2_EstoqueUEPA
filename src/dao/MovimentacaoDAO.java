package dao;

import factory.ConnectionFactory;
import modelo.Movimentacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Produto;

public class MovimentacaoDAO {

    private Connection conn;

    public MovimentacaoDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public void inserir(Movimentacao movimentacao) throws Exception {
        String sql = "INSERT INTO movimentacao (tipo, idProduto, data, hora, quantidade, usuario) VALUES (?, ?, date(now()), time(now()), ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, movimentacao.getTipo());
            ps.setInt(2, movimentacao.getIdProduto());
            ps.setInt(3, movimentacao.getQuantidade());
            ps.setString(4, movimentacao.getUsuarioResponsavel());

            ps.executeUpdate();
        } catch (SQLException e) {
            if (e.toString().contains("Unhandled")) {
                
                throw new Exception(e);
            } else {
            JOptionPane.showMessageDialog(null, "Falha ao registrar movimentação: \n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } 
        }

    }

    @FunctionalInterface
    interface PreparadorStatement {

        void preparar(PreparedStatement ps) throws SQLException;
    }

    public List<Movimentacao> consultarUsuario(String user) {
        String sql = "SELECT m.*, p.nome AS nomeProduto FROM movimentacao m "
                + "JOIN produtos p ON m.idProduto = p.idProdutos "
                + "WHERE p.ativo = 'S' AND usuario LIKE ?";

        return executarConsulta(sql, ps -> ps.setString(1, user + "%"));
    }

    public List<Movimentacao> consultPod(String prod) {
        String sql = "SELECT m.*, p.nome AS nomeProduto FROM movimentacao m "
                + "JOIN produtos p ON m.idProduto = p.idProdutos "
                + "WHERE p.ativo = 'S' AND p.nome LIKE ?";
        return executarConsulta(sql, ps -> ps.setString(1, prod + "%"));
    }

    public List<Movimentacao> consultDATA(int variavel) {
        String sql = "SELECT m.*, p.nome AS nomeProduto FROM movimentacao m "
                + "JOIN produtos p ON m.idProduto = p.idProdutos "
                + "WHERE p.ativo = 'S' AND DATEDIFF(NOW(), data) <= ?";
        List<Integer> escopo = List.of(1, 7, 30, 180, 365);

        if (variavel < 0 || variavel >= escopo.size()) {
            JOptionPane.showMessageDialog(null, "Índice de período inválido.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }

        int dias = escopo.get(variavel);
        return executarConsulta(sql, ps -> ps.setInt(1, dias));
    }

    private List<Movimentacao> executarConsulta(String sql, PreparadorStatement preparador) {
        List<Movimentacao> movs = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            preparador.preparar(ps);

            try (ResultSet rs = ps.executeQuery()) {

                movs = listarMovs(rs);
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao executar consulta: \n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

        }
        return movs;
    }

    private List<Movimentacao> listarMovs(ResultSet rs) throws SQLException {
        ProdutoDAO dao = new ProdutoDAO();
        Produto p = new Produto();
        List<Movimentacao> movs = new ArrayList<>();
        int count = 0;
        while (rs.next() && count < 20) {

            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setIdMov(rs.getInt("idMov"));
            movimentacao.setTipo(rs.getString("tipo"));
            movimentacao.setNomeProduto(rs.getString("nomeProduto"));
            movimentacao.setData(rs.getDate("data").toString());
            movimentacao.setHora(rs.getTime("hora").toString());
            movimentacao.setQuantidade(rs.getInt("quantidade"));
            movimentacao.setUsuarioResponsavel(rs.getString("usuario"));
            movs.add(movimentacao);
            count++;
        }
        return movs;
    }

    public List<Movimentacao> listar() {
        String sql = "SELECT m.*, p.nome AS nomeProduto FROM movimentacao m "
                + "JOIN produtos p ON m.idProduto = p.idProdutos "
                + "WHERE p.ativo = 'S'";
        List<Movimentacao> movimentacoes = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Movimentacao movimentacao = new Movimentacao();
                movimentacao.setIdMov(rs.getInt("idMov"));
                movimentacao.setTipo(rs.getString("tipo"));
                movimentacao.setNomeProduto(rs.getString("nomeProduto"));
                movimentacao.setData(rs.getDate("data").toString());
                movimentacao.setHora(rs.getTime("hora").toString());
                movimentacao.setQuantidade(rs.getInt("quantidade"));
                movimentacao.setUsuarioResponsavel(rs.getString("usuario"));
                
                movimentacoes.add(movimentacao);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produto: \n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return movimentacoes;
    }
}
