/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2023.framework.model;

import etu2023.framework.ModelView;
import etu2023.framework.UploadFile;
import etu2023.framework.annotation.Annotation;
import etu2023.framework.annotation.Auth;
import java.sql.Date;

/**
 *
 * @author Benji
 */
public class Personne {
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

    public String[] getLangue() {
        return langue;
    }

    public void setLangue(String[] langue) {
        this.langue = langue;
    }
    
    public Personne() {
    }

    public Personne(String nom, String prenom, Date dtn) {
        this.nom = nom;
        this.prenom = prenom;
        this.dtn = dtn;
    }

    public Personne(String nom, String prenom, Date dtn, String[] langue) {
        this.nom = nom;
        this.prenom = prenom;
        this.dtn = dtn;
        this.langue = langue;
    }

    public Personne(String nom, String prenom, Date dtn, String[] langue, UploadFile file) {
        this.nom = nom;
        this.prenom = prenom;
        this.dtn = dtn;
        this.langue = langue;
        this.file = file;
    }
  
    @Auth(value = "")
    @Annotation(value = "/findAll")
    public ModelView findAll(){
        Object[] o = new Object[]{"Benjamina"};
        ModelView modelView = new ModelView("index.jsp");
        modelView.addItem("value", o);
        return modelView;
    }
    
    @Annotation(value = "/save")
    public ModelView save(String nom, String prenom, Date dtn, String[] langue){
        Personne ob = new Personne(nom, prenom, dtn, langue, file);
        Object[] o = new Object[]{ob, this};
        ModelView modelView = new ModelView("new.jsp");
        modelView.addItem("personne", o);
        modelView.setIsJson(true);
        return modelView;
    }
    
    @Annotation(value = "/login")
    public ModelView login() {
        ModelView mv = new ModelView("Login.jsp");
        mv.addSession("profil", "admin");
        mv.addSession("isconnected", "true");
        return mv;
    }
}