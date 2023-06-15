/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2023.framework;

import java.util.HashMap;

/**
 *
 * @author Benji
 */
public class ModelView {
    String View;
    HashMap<String, Object> data = new HashMap<String, Object>();
    private HashMap<String, Object> session = new HashMap<>();

    public String getView() {
        return View;
    }
    public HashMap<String, Object> getData() {
        return data;
    }

    public void setView(String View) {
        this.View = View;
    }
    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public HashMap<String, Object> getSession() {
        return session;
    }

    public void setSession(HashMap<String, Object> session) {
        this.session = session;
    }
    
    public void addSession(String key, Object value) {
        getSession().put(key, value);
    }
    
    public ModelView() {
    }

    public ModelView(String View) {
        this.View = View;
    }
    
    public ModelView(String View, HashMap<String, Object> data) {
        this.View = View;
        this.data = data;
    }
    
    public void addItem(String key,Object value){
        this.getData().put(key,value);
    }
}
