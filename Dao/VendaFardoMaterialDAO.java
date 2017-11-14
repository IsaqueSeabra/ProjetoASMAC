/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.dao;

import java.util.List;
import br.com.pucminas.psi.model.VendaFardoMaterial;

/**
 *
 * @author Rafael Augusto
 */
public abstract interface VendaFardoMaterialDAO {
    
    public void insert(VendaFardoMaterial vendafardomaterial);
    public void remove(int id_ordem);
    public List<VendaFardoMaterial> toList();
    public VendaFardoMaterial search(int id_venda_material);
    public void update(VendaFardoMaterial vendafardomaterial);    
        
    
}
