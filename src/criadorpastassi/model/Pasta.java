/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criadorpastassi.model;

import java.util.ArrayList;

/**
 *
 * @author thiag
 */
public class Pasta {
    private String nome;
    private ArrayList<Pasta> subPastas = new ArrayList<>();
    
    public Pasta (){}
    
    public Pasta (String nome){
        this.nome = nome;
    }
    
    public Pasta (String nome, ArrayList<Pasta> subPastas){
        this.nome = nome;
        this.subPastas = subPastas;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the subPastas
     */
    public ArrayList<Pasta> getSubPastas() {
        return subPastas;
    }

    /**
     * @param subPastas the subPastas to set
     */
    public void setSubPastas(ArrayList<Pasta> subPastas) {
        this.subPastas = subPastas;
    }
    
    
    
}
