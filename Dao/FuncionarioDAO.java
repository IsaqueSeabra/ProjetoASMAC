/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.dao;

import br.com.pucminas.psi.model.Funcionario;
import java.util.List;

/**
 *
 * @author Rafael Augusto
 */
public abstract interface FuncionarioDAO {
    public void insert(Funcionario f);
    public void remove(int matr);
    public List<Funcionario> toList();
    public Funcionario search(int matr);
    public void update(Funcionario f);
    
}
