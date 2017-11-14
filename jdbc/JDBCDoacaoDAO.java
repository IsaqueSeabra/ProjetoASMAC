/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.jdbc;

import br.com.pucminas.psi.dao.DoacaoDAO;
import br.com.pucminas.psi.model.Doacao;
import br.com.pucminas.psi.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robson Vieira
 */
public class JDBCDoacaoDAO implements DoacaoDAO{
    
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
    public JDBCDoacaoDAO() {        
        connection = ConnectionFactory.getConnection(); 
    }
    

    @Override
    public void insert(Doacao doacao) {
        sql = "INSERT INTO doacao (id_doacao, matricula_funcionario_doacao, id_doador_doacao, id_material_doacao, data_doacao, kilo_material_doacao) "
                + "VALUES(?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,doacao.getId_doacao());
            ps.setInt(2,doacao.getMatricula_funcionario_doacao());
            ps.setString(3,doacao.getId_doador_doacao());
            ps.setInt(4, doacao.getId_material_doacao());
            ps.setDate(5, doacao.getData_doacao());
            ps.setDouble(6, doacao.getKilo_material_doacao());
            ps.executeUpdate();
            ps.close();
            System.out.println("Insert Doacao Ok!!!!");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(int id_doacao) {
        try {
            sql = "DELETE FROM doacao WHERE id_doacao = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id_doacao);
            ps.executeUpdate();
            ps.close();
            System.out.println("Remove Doacao Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Excluir.", ex);
        }
    }

    @Override
    public List<Doacao> toList() {
        
        List<Doacao> doacao = new ArrayList();
        try {

            sql = "SELECT * FROM doacao";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doacao d = new Doacao(); 
                d.setId_doacao(rs.getInt("id_doacao"));
                d.setMatricula_funcionario_doacao(rs.getInt("matricula_funcionario_doacao"));
                d.setId_doador_doacao(rs.getString("id_doador_doacao"));
                d.setId_material_doacao(rs.getInt("id_material_doacao"));
                d.setData_doacao(rs.getDate("data_doacao"));
                d.setKilo_material_doacao(rs.getDouble("kilo_material_doacao"));
                doacao.add(d);
            }
            ps.close();
            rs.close();
            return doacao;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar Pessoas", ex);
        }
    }

    @Override
    public Doacao search(int id_doacao) {
        sql = "SELECT * FROM doacao WHERE id_doacao = ?";
        Doacao d = new Doacao();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id_doacao);
            rs = ps.executeQuery();
            rs.next();
            d.setId_doacao(rs.getInt("id_doacao"));
            d.setMatricula_funcionario_doacao(rs.getInt("matricula_funcionario_doacao"));
            d.setId_doador_doacao(rs.getString("id_doador_doacao"));
            d.setId_material_doacao(rs.getInt("id_material_doacao"));
            d.setData_doacao(rs.getDate("data_doacao"));
            d.setKilo_material_doacao(rs.getDouble("kilo_material_doacao"));
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Uma falha ocorreu ao buscar o Balancete", ex);
        }
        return d;
    }

    @Override
    public void update(Doacao doacao) {
        sql = "UPDATE Balancete SET id_balancete = ?, id_despesa = ?, id_receita = ?, mes_referencia = ?, total_balancete = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,doacao.getId_doacao());
            ps.setInt(2,doacao.getMatricula_funcionario_doacao());
            ps.setString(3,doacao.getId_doador_doacao());
            ps.setInt(4, doacao.getId_material_doacao());
            ps.setDate(5, doacao.getData_doacao());
            ps.setDouble(6, doacao.getKilo_material_doacao());    
            ps.executeUpdate();
            System.out.println("Update Doacao Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao atualizar registro de Balancete", ex);
        }
    }
    
}
