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

    public MovimentacaoDAO()  {
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

    public void consultarUsuario(String user) {
        String sql = "SELECT * from movimentacao where usuarioResponsavel = ?";
        
        listarMovs(sql);
    }
    
    public void consultPod(String prod) {
        String sql = "SELECT * FROM movimentacao where idProduto = ?";
        
        listarMovs(sql);
    }
    
    public List<Movimentacao> listarMovs(String sql) {
        Movimentacao movimentacao = new Movimentacao();
        List<Movimentacao> MOVS = new ArrayList<>();
        
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
            JOptionPane.showMessageDialog(null, "Erro ao consultar Movimentação: \n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return MOVS;
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
}
