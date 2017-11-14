/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.jdbc;

import br.com.pucminas.psi.dao.FardoMaterialDAO;
import br.com.pucminas.psi.model.Comprador;
import br.com.pucminas.psi.model.FardoMaterial;
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
 * @author Vinicius Rodrigues
 */
public class JDBCFardoMaterialDAO implements FardoMaterialDAO{
    
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
    public JDBCFardoMaterialDAO(){
        connection =  ConnectionFactory.getConnection();
    }

    @Override
    public void insert(FardoMaterial fm) {
        sql = "INSERT INTO fardo_material (id_fardo, id_material, peso_fardo, valor_fardo) "
                + "VALUES(?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            //ps.setString(1, doador.getId_doador());
            ps.setInt(1, fm.getId());
            ps.setInt(2, fm.getIdMaterial());
            ps.setDouble(3, fm.getPesoFardo());
            ps.setDouble(4, fm.getValorFardo());
            ps.executeUpdate();
            ps.close();
            System.out.println("Insert FardoMaterial Ok!!!!");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFardoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(int id) {
        try {
            sql = "DELETE FROM fardo_material WHERE id_fardo = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            System.out.println("Remove FardoMaterial Ok!!!!");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFardoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Excluir.", ex);
        }
    }

    @Override
    public List<FardoMaterial> toList() {
        List<FardoMaterial> fardos = new ArrayList();
        try {

            sql = "SELECT * FROM fardo_material";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                FardoMaterial fm = new FardoMaterial();                
                //c.setCpf_comprador(rs.getString("cpf_comprador"));
                fm.setId(rs.getInt("id_fardo"));
                fm.setIdMaterial(rs.getInt("id_material"));
                fm.setPesoFardo(rs.getDouble("peso_fardo"));
                fm.setValorFardo(rs.getDouble("valor_fardo"));
                fardos.add(fm);
            }
            ps.close();
            rs.close();
            return fardos;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFardoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar FardoMaterial", ex);
        }
    }

    @Override
    public FardoMaterial search(int id) {
        sql = "SELECT * FROM fardo_material WHERE id_fardo=?";
        FardoMaterial fm = new FardoMaterial();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            fm.setId(rs.getInt("id_fardo"));
            fm.setIdMaterial(rs.getInt("id_material"));
            fm.setPesoFardo(rs.getDouble("peso_fardo"));
            fm.setValorFardo(rs.getDouble("valor_fardo"));
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFardoMaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar FardoMaterial", ex);
        }
        return fm;
    }

    @Override
    public void update(FardoMaterial fm) {
        sql = "UPDATE fardo_material SET id_fardo=?, id_material=?, peso_fardo=?,"
                + "valor_fardo=? WHERE id_fardo=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, fm.getId());
            ps.setInt(2, fm.getIdMaterial());
            ps.setDouble(3, fm.getPesoFardo());
            ps.setDouble(4, fm.getValorFardo());            
            ps.executeUpdate();
            System.out.println("Update FardoMaterial Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCompradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao atualizar registro de FardoMaterial", ex);
        }
    }
    
}
