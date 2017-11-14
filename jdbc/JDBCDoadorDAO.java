/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.jdbc;

import br.com.pucminas.psi.dao.DoadorDAO;
import br.com.pucminas.psi.model.Doador;
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
 * @author Dielson
 */
public class JDBCDoadorDAO implements DoadorDAO{
    
    Connection connection;
    PreparedStatement ps;
    String sql;    

    public JDBCDoadorDAO() {        
        connection = ConnectionFactory.getConnection(); 
    }
    @Override
    public void insert(Doador doador) {
        sql = "INSERT INTO doador (id_doador, tipo_doador, nome_doador, telefone_doador, email_cliente) VALUES "
                + "(?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, doador.getId_doador());
            ps.setString(2, String.valueOf(doador.getTipo_doador()));
            ps.setString(3, doador.getNome_doador());
            ps.setString(4, doador.getTelefone_doador());
            ps.setString(5, doador.getEmail_cliente());
            ps.executeUpdate();
            ps.close();
            System.out.println("Inseriu");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @Override
    public void remove(String id) {
        try {
            sql = "DELETE FROM doador WHERE id_doador = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Excluir.", ex);
        }
    }

    @Override
    public List<Doador> toList() {
        
        ResultSet rs;
        List<Doador> doadores = new ArrayList();
        try {

            sql = "SELECT * FROM doador";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Doador d = new Doador();
               
                d.setId_doador(rs.getString("id_doador"));
                d.setTipo_doador(rs.getInt("tipo_doador"));
                d.setNome_doador(rs.getString("nome_doador"));
                d.setTelefone_doador(rs.getString("telefone_doador"));
                d.setEmail_cliente(rs.getString("email_cliente"));                
                doadores.add(d);
            }

            ps.close();
            rs.close();
            return doadores;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar Pessoas", ex);
        }
    }

    @Override
    public Doador search(int id) {
        sql = "SELECT * FROM doador WHERE id_doador=?";
        Doador d = new Doador();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            d.setId_doador(rs.getString("id_doador"));
            d.setTipo_doador(rs.getInt("tipo_doador"));
            d.setNome_doador(rs.getString("nome_doador"));
            d.setTelefone_doador(rs.getString("telefone_doador"));
            d.setEmail_cliente(rs.getString("email_cliente"));
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Uma falha ocorreu ao buscar a Doador", ex);
        }
        return d;
    
    }

    @Override
    public void update(Doador doador) {
        sql = "UPDATE doador SET id_doador=?, tipo_doador=?, nome_doador=?,"
                + "telefone_doador=?,email_cliente=? WHERE id_doador=?";
        try {

            ps = connection.prepareStatement(sql);
            ps.setString(1,doador.getId_doador());
            ps.setInt(2,doador.getTipo_doador());
            ps.setString(3,doador.getNome_doador());
            ps.setString(4,doador.getTelefone_doador());
            ps.setString(5, doador.getEmail_cliente());
            

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao atualizar registro de Pessoa", ex);
        }
    }

    
    
    
   
    
    
}
