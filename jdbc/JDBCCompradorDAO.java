/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.jdbc;

import br.com.pucminas.psi.dao.CompradorDAO;
import br.com.pucminas.psi.model.Comprador;
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
 * @author Rafael Augusto
 */
public class JDBCCompradorDAO implements CompradorDAO{
    
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
    public JDBCCompradorDAO() {        
        connection = ConnectionFactory.getConnection(); 
    }
    

    @Override
    public void insert(Comprador comprador) {
        sql = "INSERT INTO Comprador (cpf_comprador, nome_comprador, telefone_cliente, email_comprador) "
                + "VALUES(?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            //ps.setString(1, doador.getId_doador());
            ps.setString(1,comprador.getCpf_comprador());
            ps.setString(2,comprador.getNome_comprador());
            ps.setString(3, comprador.getTelefone_cliente());
            ps.setString(4, comprador.getEmail_comprador());
            ps.executeUpdate();
            ps.close();
            System.out.println("Insert Comprador Ok!!!!");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCompradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(String cpf) {
        try {
            sql = "DELETE FROM Comprador WHERE cpf_comprador = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.executeUpdate();
            ps.close();
            System.out.println("Remove Comprador Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCompradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Excluir.", ex);
        }
    }

    @Override
    public List<Comprador> toList() {
        
        List<Comprador> compradores = new ArrayList();
        try {

            sql = "SELECT * FROM Comprador";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comprador c = new Comprador();                
                c.setCpf_comprador(rs.getString("cpf_comprador"));
                c.setNome_doador(rs.getString("nome_comprador"));
                c.setTelefone_cliente(rs.getString("telefone_cliente"));
                c.setEmail_comprador(rs.getString("email_comprador"));
                compradores.add(c);
            }
            ps.close();
            rs.close();
            return compradores;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCompradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar Pessoas", ex);
        }
    }

    @Override
    public Comprador search(String cpf) {
        sql = "SELECT * FROM Comprador WHERE cpf_comprador=?";
        Comprador c = new Comprador();
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            rs.next();
            c.setCpf_comprador(rs.getString("cpf_comprador"));
            c.setNome_doador(rs.getString("nome_comprador"));
            c.setTelefone_cliente(rs.getString("telefone_cliente"));
            c.setEmail_comprador(rs.getString("email_comprador"));           
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCompradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Uma falha ocorreu ao buscar a Doador", ex);
        }
        return c;
    }

    @Override
    public void update(Comprador comprador) {
        sql = "UPDATE Comprador SET cpf_comprador=?, nome_comprador=?, telefone_cliente=?,"
                + "email_comprador=? WHERE cpf_comprador=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,comprador.getCpf_comprador());            
            ps.setString(2,comprador.getNome_comprador());
            ps.setString(3, comprador.getTelefone_cliente());
            ps.setString(4, comprador.getEmail_comprador());
            ps.setString(5,comprador.getCpf_comprador());            
            ps.executeUpdate();
            System.out.println("Update Comprador Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCompradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao atualizar registro de Comprador", ex);
        }
    }
    
    
    
}
