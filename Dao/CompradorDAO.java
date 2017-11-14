/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.dao;

import br.com.pucminas.psi.model.Comprador;
import java.util.List;

/**
 *
 * @author Rafael Augusto
 */
public abstract interface CompradorDAO {
    
    public void insert(Comprador comprador);
    public void remove(String cpf);
    public List<Comprador> toList();
    public Comprador search(String cpf);
    public void update(Comprador comprador);    
        
    
}
