/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2023.framework.model;

import etu2023.framework.ModelView;
import etu2023.framework.UploadFile;
import etu2023.framework.annotation.Annotation;
import etu2023.framework.annotation.Scope;
import java.sql.Date;

/**
 *
 * @author Benji
 */
@Scope("singleton")
public class PersonneS {
    String nom;
    String prenom;
    Date dtn;
    String[] langue;
    UploadFile file;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDtn() {
        return dtn;
    }

    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }

    public String[] getLangue() {
        return langue;
    }

    public void setLangue(String[] langue) {
        this.langue = langue;
    }

    public UploadFile getFile() {
        return file;
    }

    public void setFile(UploadFile file) {
        this.file = file;
    }

    public PersonneS(String nom, String prenom, Date dtn, String[] langue, UploadFile file) {
        this.nom = nom;
        this.prenom = prenom;
        this.dtn = dtn;
        this.langue = langue;
        this.file = file;
        System.out.println("I am singleton");
    }
    
    public PersonneS(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        System.out.println("I am singleton");
    }

    public PersonneS() {
        System.out.println("I am singleton");
    }

    @Annotation(value = "/findAllS")
    public ModelView findAllS(){
        Object[] o = new Object[]{"Benjamina"};
        ModelView modelView = new ModelView("index.jsp");
        modelView.addItem("value", o);
        return modelView;
    }
    
    @Annotation(value = "/saveS")
    public ModelView save(String nom, String prenom, Date dtn, String[] langue){
        Personne ob = new Personne(nom, prenom, dtn, langue, file);
        Object[] o = new Object[]{ob};
        ModelView modelView = new ModelView("new.jsp");
        modelView.addItem("personne", o);
        return modelView;
    }
}
 