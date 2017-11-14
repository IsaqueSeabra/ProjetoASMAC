/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.dao;

import java.util.List;
import br.com.pucminas.psi.model.DescMaterial;

/**
 *
 * @author Marcel Vinicius
 */
public abstract interface DescMaterialDAO {
    
    public void insert(DescMaterial descmaterial);
    public void remove(int id_material);
    public List<DescMaterial> toList();
    public DescMaterial search(int id_material);
    public void update(DescMaterial descmaterial);    
        
    
}
