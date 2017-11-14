/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.dao;

import java.util.List;
import br.com.pucminas.psi.model.OrdemProcessamentoMaterial;

/**
 *
 * @author Francisco
 */
public abstract interface OrdemProcessamentoMaterialDAO {
    
    public void insert(OrdemProcessamentoMaterial ordemprocessamentomaterial);
    public void remove(int id_ordem);
    public List<OrdemProcessamentoMaterial> toList();
    public OrdemProcessamentoMaterial search(int id_ordem);
    public void update(OrdemProcessamentoMaterial ordemprocessamentomaterial);    
        
    
}
