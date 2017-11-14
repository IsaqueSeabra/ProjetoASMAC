/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.jdbc;

import br.com.pucminas.psi.dao.BalanceteDAO;
import br.com.pucminas.psi.model.Balancete;
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
public class JDBCBalanceteDAO implements BalanceteDAO{
    
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
    public JDBCBalanceteDAO() {        
        connection = ConnectionFactory.getConnection(); 
    }
    

    @Override
    public void insert(Balancete balancete) {
        sql = "INSERT INTO Balancete (id_balancete, id_despesa, id_receita, mes_referencia, total_balancete) "
                + "VALUES(?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,balancete.getId_balancete());
            ps.setInt(2,balancete.getId_despesa());
            ps.setInt(3, balancete.getId_receita());
            ps.setDate(4, balancete.getMes_referencia());
            ps.setDouble(5, balancete.getTotal_balancete());
            ps.executeUpdate();
            ps.close();
            System.out.println("Insert Balancete Ok!!!!");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCBalanceteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(int id_balancete, int id_despesa) {
        try {
            sql = "DELETE FROM Balancete WHERE id_balancete = ? and id_despesa = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id_balancete);
            ps.setInt(2, id_despesa);
            ps.executeUpdate();
            ps.close();
            System.out.println("Remove Balancete Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCBalanceteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Excluir.", ex);
        }
    }

    @Override
    public List<Balancete> toList() {
        
        List<Balancete> balancetes = new ArrayList();
        try {

            sql = "SELECT * FROM Balancete";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Balancete b = new Balancete();                
                b.setId_balancete(rs.getInt("id_balancete"));
                b.setId_despesa(rs.getInt("id_despesa"));
                b.setId_receita(rs.getInt("id_receita"));
                b.setMes_referencia(rs.getDate("mes_referencia"));
                b.setTotal_balancete(rs.getDouble("total_balancete"));
                balancetes.add(b);
            }
            ps.close();
            rs.close();
            return balancetes;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCBalanceteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar Pessoas", ex);
        }
    }

    @Override
    public Balancete search(int id_balancete, int id_despesa) {
        sql = "SELECT * FROM Balancete WHERE id_balancete = ? and id_despesa = ?";
        Balancete b = new Balancete();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id_balancete);
            ps.setInt(2, id_despesa);
            rs = ps.executeQuery();
            rs.next();
            b.setId_balancete(rs.getInt("id_balancete"));
            b.setId_despesa(rs.getInt("id_despesa"));
            b.setId_receita(rs.getInt("id_receita"));
            b.setMes_referencia(rs.getDate("mes_referencia"));
            b.setTotal_balancete(rs.getDouble("total_balancete")); 
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCBalanceteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Uma falha ocorreu ao buscar o Balancete", ex);
        }
        return b;
    }

    @Override
    public void update(Balancete balancete) {
        sql = "UPDATE Balancete SET id_balancete = ?, id_despesa = ?, id_receita = ?, mes_referencia = ?, total_balancete = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,balancete.getId_balancete());
            ps.setInt(2,balancete.getId_despesa());
            ps.setInt(3, balancete.getId_receita());
            ps.setDate(4, balancete.getMes_referencia());
            ps.setDouble(5, balancete.getTotal_balancete());           
            ps.executeUpdate();
            System.out.println("Update Balancete Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCBalanceteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao atualizar registro de Balancete", ex);
        }
    }
    
}
