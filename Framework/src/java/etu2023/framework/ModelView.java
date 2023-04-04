/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2023.framework;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 *
 * @author Benji
 */
public class ModelView {
    String View;
    HashMap data;

    public String getView() {
        return View;
    }

    public HashMap getData() {
        return data;
    }
    

    public void setView(String View) {
        this.View = View;
    }

    public void setData(HashMap data) {
        this.data = data;
    }
    

    public ModelView() {
    }

    public ModelView(String View, HashMap data) {
        this.View = View;
        this.data = data;
    }
    
    public void addItem(String variable, Object o) {
        
    }
}
