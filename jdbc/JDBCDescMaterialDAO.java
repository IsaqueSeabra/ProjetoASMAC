/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.jdbc;

import br.com.pucminas.psi.dao.DescMaterialDAO;
import br.com.pucminas.psi.model.DescMaterial;
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
 * @author Marcel Vinicius
 */
public class JDBCDescMaterialDAO implements DescMaterialDAO{
    
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
    public JDBCDescMaterialDAO() {        
        connection = ConnectionFactory.getConnection(); 
    }
    

    @Override
    public void insert(DescMaterial descmaterial) {
        sql = "INSERT INTO DescMaterial (id_material, descricao_material)" + "VALUES(?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, descmaterial.getId_material());
            ps.setString(2, descmaterial.getDescricao_material());
            ps.executeUpdate();
            ps.close();
            System.out.println("Insert DescMaterial Ok!!!!");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDescMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(int id_material) {
        try {
            sql = "DELETE FROM DescMaterial WHERE id_material = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id_material);            
            ps.executeUpdate();
            ps.close();
            System.out.println("Remove DescMaterial Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCDescMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Excluir.", ex);
        }
    }

    @Override
    public List<DescMaterial> toList() {
        
        List<DescMaterial> descmateriais = new ArrayList();
        try {

            sql = "SELECT * FROM DescMaterial";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DescMaterial b = new DescMaterial();                
                b.setId_material(rs.getInt("id_material"));
                b.setDescricao_material(rs.getString("descricao_material"));
                descmateriais.add(b);
            }
            ps.close();
            rs.close();
            return descmateriais;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDescMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar Pessoas", ex);
        }
    }

    @Override
    public DescMaterial search(int id_material) {
        sql = "SELECT * FROM DescMaterial WHERE id_material = ?";
        DescMaterial b = new DescMaterial();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id_material);
            rs = ps.executeQuery();
            rs.next();
            b.setId_material(rs.getInt("id_material"));
            b.setDescricao_material(rs.getString("descricao_material"));                  
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDescMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Uma falha ocorreu ao buscar o DescMaterial", ex);
        }
        return b;
    }

    @Override
    public void update(DescMaterial descmaterial) {
        sql = "UPDATE DescMaterial SET id_material = ?, descricao_material = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, descmaterial.getId_material());
            ps.setString(2, descmaterial.getDescricao_material());            
            ps.executeUpdate();
            System.out.println("Update DescMaterial Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCDescMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao atualizar registro de DescMaterial", ex);
        }
    }
    
}
