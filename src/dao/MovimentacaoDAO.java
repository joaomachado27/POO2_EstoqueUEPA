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

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new Exception(e);
        }

    }

    @FunctionalInterface
    interface PreparadorStatement {

        void preparar(PreparedStatement ps) throws SQLException;
    }

    public List<Movimentacao> consultarUsuario(String user) {
        String sql = "SELECT * from movimentacao where usuario LIKE ?";

        return executarConsulta(sql, ps -> ps.setString(1, user + "%"));
    }

    public List<Movimentacao> consultPod(int prod) {
        String sql = "SELECT * FROM movimentacao where idProduto = ?";
        return executarConsulta(sql, ps -> ps.setInt(1, prod));
    }

    public List<Movimentacao> consultDATA(int variavel) {
        String sql = "SELECT * from movimentacao where DATEDIFF(NOW(), data) <= ?";
        List<Integer> escopo = List.of(1, 7, 30, 180, 365);

        if (variavel < 0 || variavel >= escopo.size()) {
            JOptionPane.showMessageDialog(null, "Índice de período inválido.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }

        int dias = escopo.get(variavel);
        return executarConsulta(sql, ps -> ps.setInt(1, dias));
    }

    public List<Movimentacao> executarConsulta(String sql, PreparadorStatement preparador) {
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
            movimentacao.setIdProduto(rs.getInt("idProduto"));
            movimentacao.setData(rs.getDate("data") != null ? rs.getDate("data").toString() : null);
            movimentacao.setHora(rs.getTime("hora") != null ? rs.getTime("hora").toString() : null);
            movimentacao.setQuantidade(rs.getInt("quantidade"));
            movimentacao.setUsuarioResponsavel(rs.getString("usuario"));
            movs.add(movimentacao);
            count++;
        }
        return movs;
    }
}
/*
    
    --TRIGGERS 
    
    --Trigger para alterar o estoque de produtos 
        CREATE DEFINER=`root`@`localhost` TRIGGER `TG_addEstoque` AFTER INSERT ON `movimentacao` FOR EACH ROW BEGIN
if new.tipo = 'ENTRADA' then
	update produtos p set p.quantidade = p.quantidade + new.quantidade where new.idProduto = p.idProdutos;
else 
	update produtos p set p.quantidade = p.quantidade - new.quantidade where new.idProduto = p.idProdutos;
END if;
end
    
    --Trigger para impedir que quantidade produto fique negativo
    CREATE DEFINER=`root`@`localhost` TRIGGER `TG_semestoque` BEFORE INSERT ON `movimentacao` FOR EACH ROW BEGIN
	if ((SELECT quantidade from produtos as p where p.idProdutos = new.idProduto) < new.quantidade) and (new.tipo = 'SAIDA')then
    SIGNAL SQLSTATE '45000';
	end IF;
END
 */
