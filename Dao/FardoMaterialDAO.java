/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucminas.psi.dao;

import br.com.pucminas.psi.model.FardoMaterial;
import java.util.List;

/**
 *
 * @author Vinicius Rodrigues
 */
public abstract interface FardoMaterialDAO {
    public void insert(FardoMaterial fm);
    public void remove(int id);
    public List<FardoMaterial> toList();
    public FardoMaterial search(int id);
    public void update(FardoMaterial fm);
}
