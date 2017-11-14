/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.dao;

import br.com.pucminas.psi.model.Doador;
import java.util.List;

/**
 *
 * @author Dielson
 */
public abstract interface DoadorDAO {
    
    public void insert(Doador doador);
    public void remove(String id);
    public List<Doador> toList();
    public Doador search(int id);
    public void update(Doador doador);
    
}
