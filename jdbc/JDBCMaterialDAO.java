/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.jdbc;

import br.com.pucminas.psi.dao.MaterialDAO;
import br.com.pucminas.psi.model.Material;
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
public class JDBCMaterialDAO implements MaterialDAO{
    
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
    public JDBCMaterialDAO() {        
        connection = ConnectionFactory.getConnection(); 
    }
    

    @Override
    public void insert(Material material) {
        sql = "INSERT INTO Material (id_material, func_cadastro_material, data_cadastro_material)" + "VALUES(?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, material.getId_material());
            ps.setInt(2, material.getFunc_cadastro_material());
            ps.setDate(3, material.getData_cadastro_material());       
            ps.executeUpdate();
            ps.close();
            System.out.println("Insert Material Ok!!!!");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(int id_material) {
        try {
            sql = "DELETE FROM Material WHERE id_material = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id_material);            
            ps.executeUpdate();
            ps.close();
            System.out.println("Remove Material Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Excluir.", ex);
        }
    }

    @Override
    public List<Material> toList() {
        
        List<Material> materiais = new ArrayList();
        try {

            sql = "SELECT * FROM Material";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Material b = new Material();                
                b.setId_material(rs.getInt("id_material"));
                b.setFunc_cadastro_material(rs.getInt("func_cadastro_material"));
                b.setData_cadastro_material(rs.getDate("data_cadastro_material"));                
                materiais.add(b);
            }
            ps.close();
            rs.close();
            return materiais;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar Pessoas", ex);
        }
    }

    @Override
    public Material search(int id_material) {
        sql = "SELECT * FROM Material WHERE id_material = ?";
        Material b = new Material();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id_material);
            rs = ps.executeQuery();
            rs.next();
            b.setId_material(rs.getInt("id_material"));
            b.setFunc_cadastro_material(rs.getInt("func_cadastro_material"));            
            b.setData_cadastro_material(rs.getDate("data_cadastro_material"));            
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Uma falha ocorreu ao buscar o Material", ex);
        }
        return b;
    }

    @Override
    public void update(Material material) {
        sql = "UPDATE Material SET id_material = ?, func_cadastro_material = ?, data_cadastro_material = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,material.getId_material());
            ps.setInt(2,material.getFunc_cadastro_material());
            ps.setDate(3, material.getData_cadastro_material());
            ps.executeUpdate();
            System.out.println("Update Material Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao atualizar registro de Material", ex);
        }
    }
    
}
