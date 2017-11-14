/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.dao;

import java.util.List;
import br.com.pucminas.psi.model.Doacao;

/**
 *
 * @author Robson Vieira
 */
public abstract interface DoacaoDAO {
    
    public void insert(Doacao doacao);
    public void remove(int id_doacao);
    public List<Doacao> toList();
    public Doacao search(int id_doacao);
    public void update(Doacao doacao);    
        
    
}
