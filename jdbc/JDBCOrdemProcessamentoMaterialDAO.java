/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.jdbc;

import br.com.pucminas.psi.dao.OrdemProcessamentoMaterialDAO;
import br.com.pucminas.psi.model.OrdemProcessamentoMaterial;
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
 * @author Francisco
 */
public class JDBCOrdemProcessamentoMaterialDAO implements OrdemProcessamentoMaterialDAO{
    
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
    public JDBCOrdemProcessamentoMaterialDAO() {        
        connection = ConnectionFactory.getConnection(); 
    }
    

    @Override
    public void insert(OrdemProcessamentoMaterial ordemprocessamentomaterial) {
        sql = "INSERT INTO OrdemProcessamentoMaterial (id_ordem, matricula_funcionario, id_fardo, data) "
                + "VALUES(?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,ordemprocessamentomaterial.getId_ordem());
            ps.setInt(2,ordemprocessamentomaterial.getMatricula_funcionario());
            ps.setInt(3,ordemprocessamentomaterial.getId_fardo());
            ps.setDate(4,ordemprocessamentomaterial.getData());
            ps.executeUpdate();
            ps.close();
            System.out.println("Insert OrdemProcessamentoMaterial Ok!!!!");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemProcessamentoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(int id_ordem) {
        try {
            sql = "DELETE FROM OrdemProcessamentoMaterial WHERE id_ordem = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id_ordem);
            ps.executeUpdate();
            ps.close();
            System.out.println("Remove OrdemProcessamentoMaterial Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemProcessamentoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Excluir.", ex);
        }
    }

    @Override
    public List<OrdemProcessamentoMaterial> toList() {
        
        List<OrdemProcessamentoMaterial> ordem = new ArrayList();
        try {

            sql = "SELECT * FROM OrdemProcessamentoMaterial";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrdemProcessamentoMaterial o = new OrdemProcessamentoMaterial();                
                o.setId_ordem(rs.getInt("id_ordem"));
                o.setMatricula_funcionario(rs.getInt("matricula_funcionario"));
                o.setId_fardo(rs.getInt("id_fardo"));
                o.setData(rs.getDate("data"));
                ordem.add(o);
            }
            ps.close();
            rs.close();
            return ordem;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemProcessamentoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar Ordem de Processamento de Material", ex);
        }
    }

    @Override
    public OrdemProcessamentoMaterial search(int id_ordem) {
        sql = "SELECT * FROM OrdemProcessamentoMaterial WHERE id_ordem = ?";
        OrdemProcessamentoMaterial o = new OrdemProcessamentoMaterial();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id_ordem);
            rs = ps.executeQuery();
            rs.next();
            o.setId_ordem(rs.getInt("id_ordem"));
            o.setMatricula_funcionario(rs.getInt("matricula_funcionario"));
            o.setId_fardo(rs.getInt("id_fardo"));
            o.setData(rs.getDate("data"));           
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemProcessamentoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Uma falha ocorreu ao buscar a Ordem de Processamento de Material", ex);
        }
        return o;
    }

    @Override
    public void update(OrdemProcessamentoMaterial ordemprocessamentomaterial) {
        sql = "UPDATE OrdemProcessamentoMaterial SET id_ordem = ?, matricula_funcionario = ?, id_fardo = ?, data = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,ordemprocessamentomaterial.getId_ordem());
            ps.setInt(2,ordemprocessamentomaterial.getMatricula_funcionario());
            ps.setInt(3,ordemprocessamentomaterial.getId_fardo());
            ps.setDate(4,ordemprocessamentomaterial.getData());            
            ps.executeUpdate();
            System.out.println("Update Ordem Processamento Material Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCOrdemProcessamentoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao atualizar registro de Ordem Processamento Material", ex);
        }
    }
    
}
