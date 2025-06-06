package dao;

import factory.ConnectionFactory;
import modelo.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutoDAO {

    private Connection conn;

    public ProdutoDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public void inserir(Produto produto) {

        String sql = "INSERT INTO produtos (nome, descricao, procedencia, quantidade) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setString(3, produto.getProcedencia());
            ps.setInt(4, produto.getQuantidade());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir produto: \n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void atualizar(Produto produto) {

        String sql = "UPDATE produtos SET nome = ?, descricao = ?, procedencia = ? WHERE idprodutos = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setString(3, produto.getProcedencia());
            ps.setInt(4, produto.getIdProduto());

            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: \n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void remover(Produto produto) {
        String sql = "UPDATE produtos SET ativo = 'N' WHERE idprodutos = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, produto.getIdProduto());

            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover produto: \n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Produto consultarID(int id) {
        String sql = "SELECT * FROM produtos WHERE idprodutos = ? AND ativo='S'";
        Produto produto = new Produto();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    produto.setIdProduto(rs.getInt("idprodutos"));
                    produto.setNome(rs.getString("nome"));
                    produto.setDescricao(rs.getString("descricao"));
                    produto.setProcedencia(rs.getString("procedencia"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                } else {
                    JOptionPane.showMessageDialog(null, "Produto com o ID informado não existe \n", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produto: \n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return produto;
    }

    public Produto consultarNome(String nome) {
        String sql = "SELECT * FROM produtos WHERE nome = ? AND ativo='S'";
        Produto produto = new Produto();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    produto.setIdProduto(rs.getInt("idprodutos"));
                    produto.setNome(rs.getString("nome"));
                    produto.setDescricao(rs.getString("descricao"));
                    produto.setProcedencia(rs.getString("procedencia"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                } else {
                    JOptionPane.showMessageDialog(null, "Produto com o Nome informado não existe \n", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produto: \n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return produto;
    }

    public List<Produto> listar() {
        String sql = "SELECT * FROM produtos WHERE ativo='S'";
        List<Produto> produtos = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idProdutos"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setProcedencia(rs.getString("procedencia"));
                produto.setQuantidade(rs.getInt("quantidade"));
                
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produto: \n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return produtos;
    }
}
