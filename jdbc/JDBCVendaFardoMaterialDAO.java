/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.jdbc;

import br.com.pucminas.psi.dao.VendaFardoMaterialDAO;
import br.com.pucminas.psi.model.VendaFardoMaterial;
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
public class JDBCVendaFardoMaterialDAO implements VendaFardoMaterialDAO{
    
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
    public JDBCVendaFardoMaterialDAO() {        
        connection = ConnectionFactory.getConnection(); 
    }
    

    @Override
    public void insert(VendaFardoMaterial vendafardomaterial) {
        sql = "INSERT INTO venda_fardo_material (id_venda_material, id_fardo, matricula_funcionario, valor_total_venda, id_comprador_material, kilo_venda_material) "
                + "VALUES(?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,vendafardomaterial.getId_venda_material());
            ps.setInt(2,vendafardomaterial.getId_fardo());
            ps.setInt(3,vendafardomaterial.getMatricula_funcionario());
            ps.setDouble(4,vendafardomaterial.getValor_total_venda());
            ps.setString(5,vendafardomaterial.getId_comprador_material());
            ps.setDouble(6,vendafardomaterial.getKilo_venda_material());  
            ps.executeUpdate();
            ps.close();
            System.out.println("Insert Venda Fardo de Material Ok!!!!");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCVendaFardoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(int id_venda_material) {
        try {
            sql = "DELETE FROM venda_fardo_material WHERE id_venda_material = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id_venda_material);
            ps.executeUpdate();
            ps.close();
            System.out.println("Remove Venda Fardo de Material Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCVendaFardoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Excluir.", ex);
        }
    }

    @Override
    public List<VendaFardoMaterial> toList() {
        
        List<VendaFardoMaterial> vendafardo = new ArrayList();
        try {

            sql = "SELECT * FROM venda_fardo_material";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                VendaFardoMaterial vfm = new VendaFardoMaterial();                
                vfm.setId_venda_material(rs.getInt("id_venda_material"));
                vfm.setId_fardo(rs.getInt("id_fardo"));
                vfm.setMatricula_funcionario(rs.getInt("matricula_funcionario"));
                vfm.setValor_total_venda(rs.getDouble("valor_total_venda"));
                vfm.setId_comprador_material(rs.getString("id_comprador_material"));
                vfm.setKilo_venda_material(rs.getDouble("kilo_venda_material"));  
                vendafardo.add(vfm);
            }
            ps.close();
            rs.close();
            return vendafardo;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCVendaFardoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar Venda Fardo de Material", ex);
        }
    }

    @Override
    public VendaFardoMaterial search(int id_venda_material) {
        sql = "SELECT * FROM venda_fardo_material WHERE id_venda_material = ?";
        VendaFardoMaterial vfm = new VendaFardoMaterial();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id_venda_material);
            rs = ps.executeQuery();
            rs.next();
            vfm.setId_venda_material(rs.getInt("id_venda_material"));
            vfm.setId_fardo(rs.getInt("id_fardo"));
            vfm.setMatricula_funcionario(rs.getInt("matricula_funcionario"));
            vfm.setValor_total_venda(rs.getDouble("valor_total_venda"));
            vfm.setId_comprador_material(rs.getString("id_comprador_material"));
            vfm.setKilo_venda_material(rs.getDouble("kilo_venda_material"));        
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCVendaFardoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Uma falha ocorreu ao buscar a Venda Fardo de Material", ex);
        }
        return vfm;
    }


    @Override
    public void update(VendaFardoMaterial vendafardomaterial) {
        sql = "UPDATE venda_fardo_material SET id_venda_material = ?, id_fardo = ?, matricula_funcionario = ?, valor_total_venda = ?, id_comprador_material = ?, kilo_venda_material = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,vendafardomaterial.getId_venda_material());
            ps.setInt(2,vendafardomaterial.getId_fardo());
            ps.setInt(3,vendafardomaterial.getMatricula_funcionario());
            ps.setDouble(4,vendafardomaterial.getValor_total_venda());
            ps.setString(5,vendafardomaterial.getId_comprador_material());
            ps.setDouble(6,vendafardomaterial.getKilo_venda_material());       
            ps.executeUpdate();
            System.out.println("Update Ordem Processamento Material Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCVendaFardoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao atualizar registro de Venda Fardo de Material", ex);
        }
    }
    
}
