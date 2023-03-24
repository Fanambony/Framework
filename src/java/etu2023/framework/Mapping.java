/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2023.framework;

/**
 *
 * @author Benji
 */
public class Mapping {
    String className;
    String Method;

    public String getClassName() {
        return this.className;
    }

    public String getMethod() {
        return this.Method;
    }

    public Mapping(String className, String Method) {
        this.className = className;
        this.Method = Method;
    }
}
