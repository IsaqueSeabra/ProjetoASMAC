/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.dao;

import br.com.pucminas.psi.model.Despesa;
import java.util.List;

/**
 *
 * @author Isaque Felix
 */
public abstract interface DespesaDAO {
    public void insert(Despesa d);
    public void remove(int id_desp);
    public List<Despesa> toList();
    public Despesa search(int id_desp);
    public void update(Despesa d);
    
}