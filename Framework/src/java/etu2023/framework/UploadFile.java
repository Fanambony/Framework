/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2023.framework;

/**
 *
 * @author Benji
 */
public class UploadFile {
    String fileName;
    String path;
    Byte[] bytes;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Byte[] getBytes() {
        return bytes;
    }

    public void setBytes(Byte[] bytes) {
        this.bytes = bytes;
    }

    public UploadFile(String fileName, String path, Byte[] bytes) {
        this.fileName = fileName;
        this.path = path;
        this.bytes = bytes;
    }

    public UploadFile() {
    }   
}
