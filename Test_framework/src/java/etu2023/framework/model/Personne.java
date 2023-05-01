/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2023.framework.model;

import etu2023.framework.ModelView;
import etu2023.framework.annotation.Annotation;

/**
 *
 * @author Benji
 */
public class Personne {
    String nom;
    String prenom;

    public String getNom() {
        return nom;
    }

    @Annotation(value = "/nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Annotation(value = "/prenom")
    public ModelView getPrenom() {
        System.out.println("Voilaaaa");
        Object[] o = new Object[]{"Benjamina"};
        ModelView modelView = new ModelView("index.jsp");
        modelView.addItem("value", o);
        return modelView;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Personne() {
    }
    
    @Annotation(value = "/saveAll")
    public void save(){      
        System.out.println(this.getPrenom());
    }
}
