/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2023.framework;

/**
 *
 * @author Benji
 */
public class ModelView {
    String view;

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public ModelView(String view) {
        this.view = view;
    }

    public ModelView() {
    }
    
    public ModelView fonction(){
        System.out.println("Bienvenue dans le model view");
        ModelView mv = new ModelView("index.jsp");
        return mv;
    }
    
}
