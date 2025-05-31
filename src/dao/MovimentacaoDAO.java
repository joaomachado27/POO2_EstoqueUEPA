/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import factory.ConnectionFactory;
import modelo.Movimentacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author cacom
 */
public class MovimentacaoDAO {
    Connection con;

    public MovimentacaoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void cadastroMov (Movimentacao m) throws Exception {
        
        String sql = "INSERT INTO movimentacao (tipo, idProduto, dia, hora, quantidade, usuario) VALUES (?, ?, date(now()), time(now()), ?, ?)";  

        PreparedStatement stmt = con.prepareStatement(sql);
        
        try {
            
            stmt.setString(1, m.getTipo());
            stmt.setInt(2, m.getIdProduto());
            stmt.setInt(3, m.getQuantidade());
            stmt.setString(4, m.getUsuarioResponsavel());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
    
    public Movimentacao consultMov() throws Exception{
        Movimentacao m = new Movimentacao();
        
        String sql = "SELECT * from movimentacao";
        //buscar pelo que?
        try {
        PreparedStatement stmt = con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            m.setIdMov(rs.getInt("idMov"));
            m.setTipo(rs.getString("tipo"));
            m.setIdProduto(rs.getInt("idProduto"));
            m.setData(String.valueOf(rs.getDate("data")));
            m.setHora(String.valueOf(rs.getTime("hora")));
            m.setQuantidade(rs.getInt("quantidade"));
            m.setUsuarioResponsavel(rs.getString("usuario"));
        }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return m;
    }
    
}

