/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2023.framework.model;

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
    public void setNom() {
        this.nom = "Ramaroson";
        System.out.println("Mon nom");
    }

    @Annotation(value = "/prenom")
    public String getPrenom() {
        System.out.println("Mon prenom");
        return prenom;
    }

    public void setPrenom() {
        this.prenom = "Benja";
        System.out.println("Mon prenom");
    }

    public Personne() {
    }
}