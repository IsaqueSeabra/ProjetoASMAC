/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.jdbc;

import br.com.pucminas.psi.dao.DespesaDAO;
import br.com.pucminas.psi.model.Despesa;
import br.com.pucminas.psi.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

/**
 *
 * @author Isaque Felix
 */
public class JDBCDespesaDAO implements DespesaDAO{
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
    public JDBCDespesaDAO(){
        connection = ConnectionFactory.getConnection();        
    }

    @Override
    public void insert(Despesa d) {
        sql = "INSERT INTO despesas (id_despesa, valor, dt_vencimento_conta, "
                + "matricula_funcionario_despesa, descricao_despesa)"
                + "VALUES(?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, d.getId_despesa());
            ps.setDouble(2, d.getValor());
            ps.setDate(3, d.getDt_vencimento_conta());
            ps.setInt(4, d.getMatricula_funcionario_despesa());
            ps.setString(5, d.getDescricao_despesa());       
            ps.executeUpdate();
            ps.close();
            System.out.println("Insert Despesa Ok!!!!");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDespesaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(int id_desp) {
        try {
            sql = "DELETE FROM despesas WHERE id_despesa = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id_desp);
            ps.executeUpdate();
            ps.close();
            System.out.println("Remove despesa Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCDespesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Excluir.", ex);
        }
    }

    @Override
    public List<Despesa> toList() {
        List<Despesa> Despesas = new ArrayList();
        try {
            sql = "SELECT * FROM despesas";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Despesa d = new Despesa();                
                d.setId_despesa(rs.getInt("id_despesa"));
                d.setValor(rs.getDouble("valor"));
                d.setDt_vencimento_conta(rs.getDate("dt_vencimento_conta"));
                d.setMatricula_funcionario_despesa(rs.getInt("matricula_funcionario_despesa"));
                d.setDescricao_despesa(rs.getString("descricao_despesa"));
                
                Despesas.add(d);
            }
            ps.close();
            rs.close();
            return Despesas;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDespesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar Pessoas", ex);
        }
    }

    @Override
    public void update(Despesa d) {
        sql = "UPDATE despesas SET id_despesa=?, valor=?, dt_vencimento_conta=?, "
                + "matricula_funcionario_despesa=? , descricao_despesa=?"
                + " WHERE matricula_funcionario=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, d.getId_despesa());
            ps.setDouble(2, d.getValor());
            ps.setDate(3, d.getDt_vencimento_conta());
            ps.setInt(4, d.getMatricula_funcionario_despesa());
            ps.setString(5, d.getDescricao_despesa());       
            System.out.print(ps.toString());
            ps.executeUpdate();
            System.out.println("Update Despesa Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCDespesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao atualizar registro de Despesa", ex);
        }
    }

    @Override
    public Despesa search(int id_desp) {
        sql = "SELECT * FROM despesas WHERE id_despesa=?";
        Despesa d = new Despesa();  
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id_desp);
            rs = ps.executeQuery();
            rs.next();
            d.setId_despesa(rs.getInt("id_despesa"));
            d.setValor(rs.getDouble("valor"));
            d.setDt_vencimento_conta(rs.getDate("dt_vencimento_conta"));
            d.setMatricula_funcionario_despesa(rs.getInt("matricula_funcionario_despesa"));
            d.setDescricao_despesa(rs.getString("descricao_despesa"));
        
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDespesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Uma falha ocorreu ao buscar a Despesa", ex);
        }
        return d;
    }
    
}
