/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.dao;

import java.util.List;
import br.com.pucminas.psi.model.Material;

/**
 *
 * @author Marcel Vinicius
 */
public abstract interface MaterialDAO {
    
    public void insert(Material material);
    public void remove(int id_material);
    public List<Material> toList();
    public Material search(int id_material);
    public void update(Material material);    
        
    
}
