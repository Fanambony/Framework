/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2023.framework.model;

import etu2023.framework.ModelView;
import etu2023.framework.annotation.Annotation;
import java.sql.Date;

/**
 *
 * @author Benji
 */
public class Personne {
    String nom;
    String prenom;
    Date dtn;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDtn() {
        return dtn;
    }
    
    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Personne() {
    }

    public Personne(String nom, String prenom, Date dtn) {
        this.nom = nom;
        this.prenom = prenom;
        this.dtn = dtn;
    }
    
    @Annotation(value = "/findAll")
    public ModelView findAll(){
        Object[] o = new Object[]{"Benjamina"};
        ModelView modelView = new ModelView("index.jsp");
        modelView.addItem("value", o);
        return modelView;
    }
    
    @Annotation(value = "/save")
    public ModelView save(String nom, String prenom, Date dtn){
        Personne ob = new Personne(nom, prenom, dtn);
        Object[] o = new Object[]{ob};
        ModelView modelView = new ModelView("new.jsp");
        modelView.addItem("personne", o);
        return modelView;
    }
}