/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.jdbc;

import br.com.pucminas.psi.dao.FuncionarioDAO;
import br.com.pucminas.psi.model.Comprador;
import br.com.pucminas.psi.model.Funcionario;
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
public class JDBCFuncionarioDAO implements FuncionarioDAO{
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
    public JDBCFuncionarioDAO(){
        connection = ConnectionFactory.getConnection();        
    }

    @Override
    public void insert(Funcionario f) {
        sql = "INSERT INTO funcionario (matricula_funcionario, cpf_funcionario, nome_funcionario, "
                + "telefone_funcionario, email_funcionario, logradouro_funcionario, "
                + "cidade_funcionario, uf_funcionario, id_gerente_funcionario)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            //ps.setString(1, doador.getId_doador());
            ps.setInt(1, f.getMatricula_funcionario());
            ps.setString(2, f.getCpf_funcionario());
            ps.setString(3, f.getNome_funcionario());
            ps.setString(4, f.getTelefone_funcionario());
            ps.setString(5, f.getEmail_funcionario());
            ps.setString(6, f.getLogradouro_funcionario());
            ps.setString(7, f.getCidade_funcionario());
            ps.setString(8, f.getUf_funcionario());
            ps.setInt(9, f.getId_gerente_funcionario());            
            ps.executeUpdate();
            ps.close();
            System.out.println("Insert Funcionario Ok!!!!");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(int matr) {
        try {
            sql = "DELETE FROM funcionario WHERE matricula_funcionario = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, matr);
            ps.executeUpdate();
            ps.close();
            System.out.println("Remove Funcionario Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Excluir.", ex);
        }
    }

    @Override
    public List<Funcionario> toList() {
        List<Funcionario> funcionarios = new ArrayList();
        try {
            sql = "SELECT * FROM funcionario";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Funcionario f = new Funcionario();                
                //c.setCpf_comprador(rs.getString("cpf_comprador"));
                f.setMatricula_funcionario(rs.getInt("matricula_funcionario"));
                f.setCpf_funcionario(rs.getString("cpf_funcionario"));
                f.setNome_funcionario(rs.getString("nome_funcionario"));
                f.setEmail_funcionario(rs.getString("email_funcionario"));
                f.setTelefone_funcionario(rs.getString("telefone_funcionario"));
                f.setLogradouro_funcionario(rs.getString("logradouro_funcionario"));
                f.setCidade_funcionario(rs.getString("cidade_funcionario"));
                f.setUf_funcionario(rs.getString("uf_funcionario"));
                f.setId_gerente_funcionario(rs.getInt("id_gerente_funcionario"));
                
                funcionarios.add(f);
            }
            ps.close();
            rs.close();
            return funcionarios;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar Pessoas", ex);
        }
    }

    @Override
    public void update(Funcionario f) {
        sql = "UPDATE funcionario SET matricula_funcionario=?, cpf_funcionario=?, nome_funcionario=?, "
                + "email_funcionario=? , telefone_funcionario=?, logradouro_funcionario=?, "
                + "cidade_funcionario=?, uf_funcionario=?, id_gerente_funcionario=? "
                + " WHERE matricula_funcionario=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, f.getMatricula_funcionario());
            ps.setString(2, f.getCpf_funcionario());
            ps.setString(3, f.getNome_funcionario());
            ps.setString(4, f.getEmail_funcionario());
            ps.setString(5, f.getTelefone_funcionario());            
            ps.setString(6, f.getLogradouro_funcionario());
            ps.setString(7, f.getCidade_funcionario());
            ps.setString(8, f.getUf_funcionario());
            ps.setInt(9, f.getId_gerente_funcionario());
            ps.setInt(10, f.getMatricula_funcionario());
            System.out.print(ps.toString());
            ps.executeUpdate();
            System.out.println("Update Funcionario Ok!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao atualizar registro de Funcionario", ex);
        }
    }

    @Override
    public Funcionario search(int matr) {
        sql = "SELECT * FROM funcionario WHERE matricula_funcionario=?";
        Funcionario f = new Funcionario();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, matr);
            rs = ps.executeQuery();
            rs.next();
            f.setMatricula_funcionario(rs.getInt("matricula_funcionario"));
            f.setCpf_funcionario(rs.getString("cpf_funcionario"));
            f.setNome_funcionario(rs.getString("nome_funcionario"));
            f.setEmail_funcionario(rs.getString("email_funcionario"));
            f.setTelefone_funcionario(rs.getString("telefone_funcionario"));
            f.setLogradouro_funcionario(rs.getString("logradouro_funcionario"));
            f.setCidade_funcionario(rs.getString("cidade_funcionario"));
            f.setUf_funcionario(rs.getString("uf_funcionario"));
            f.setId_gerente_funcionario(rs.getInt("id_gerente_funcionario"));           
        
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Uma falha ocorreu ao buscar a Doador", ex);
        }
        return f;
    }
    
}
