package etu2023.framework.servlet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import etu2023.framework.Mapping;
import etu2023.framework.ModelView;
import etu2023.framework.annotation.Annotation;
import etu2023.framework.utils.Utils;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Benji
 */
public class FrontServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    HashMap<String, Mapping> MappingUrls;
    
    private ArrayList<Class> classList = new ArrayList<>();

    public ArrayList<Class> getClassList() {
        return classList;
    }

    public void setClassList(ArrayList<Class> classList) {
        this.classList = classList;
    }
    
    
    
    public HashMap<String, Mapping> getMappingUrls() {
        return MappingUrls;
    }

    public void setMappingUrls(HashMap<String, Mapping> MappingUrls) {
        this.MappingUrls = MappingUrls;
    }
    
    public void setMappingUrls(String path) {
        try {
            List<Class> lc = Utils.getClassFrom(path);
            setMappingUrls(new HashMap<String, Mapping>());
            for (Class c : lc) {
                for (Method m : c.getDeclaredMethods()) {
                    Annotation u = m.getAnnotation(Annotation.class);
                    if (u != null) {
                        getMappingUrls().put(u.value() , new Mapping(c.getSimpleName(), m.getName()));
                    }
                }
                getClassList().add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    

    @Override
    public void init() throws ServletException {
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        setMappingUrls("etu2023.framework.model.");
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FrontServlet at " + request.getContextPath() + "</h1>");
            out.println("<h1>" +request.getRequestURI   () + "</h1>");
            out.println("<h1>"+getNom(request, response) + "</h1>");
            
            Method m = getMethodFromUrl(getNom(request, response));
            Class c = getClass(getNom(request, response));
            Object o = m.invoke(c.newInstance(),null);
            
            if(o instanceof ModelView){
                ModelView mv = (ModelView)o;
                RequestDispatcher dispather = request.getRequestDispatcher(mv.getView());
                dispather.forward(request, response);
            }
//            out.println(getMappingUrls().size());
            for (Map.Entry<String, Mapping> entry : MappingUrls.entrySet()) {
                Object key = entry.getKey();
                Object val = entry.getValue();
            }
//            out.println(getMethodFromUrl(getNom(request, response)).getName());
    
            out.println("</body>");
            out.println("</html>");
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public String getNom(HttpServletRequest request, HttpServletResponse response){
        String result;
        String path = request.getContextPath();
        String uri = request.getRequestURI();
        result = uri.split(path )[1];
        return result;
    }
    
    public Method getMethodFromUrl(String url) throws Exception {
        
        List<Class> lc = Utils.getClassFrom("etu2023.framework.model.");
        for (Class c : lc) {
            if (c.getSimpleName() == getMappingUrls().get(url).getClassName()){
                for (Method m : c.getDeclaredMethods()) {
                    if (m.getName() == getMappingUrls().get(url).getMethod()){
                        return m;
                    }
                }
            }
        }
        throw new Exception("Method not found");
    }
    
    public Class getClass(String url) throws Exception{
        List<Class> lc = getClassList();
        for(Class c : lc){
            if(c.getSimpleName().equals(getMappingUrls().get(url).getClassName())) {
                for(Method m : c.getDeclaredMethods()){
                    if(m.getName().equals(getMappingUrls().get(url).getMethod())) {
                        return c;
                    }
                }
            }
        }
        throw new Exception("Class not found");
    }
    
    public ModelView getUrlDispatcher(String key) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Mapping m = MappingUrls.get(key);
        ModelView mv = null;
        if(m != null){
            String className = "etu2023.model."+m.getClassName();
            Class c;
            c = Class.forName(className);
            mv = (ModelView)c.getDeclaredMethod(m.getMethod(), null).invoke(c.getConstructor(null).newInstance(), null);
        }
        return mv;
    }
    
    
    public void loadView(String key, HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            ModelView mv = getUrlDispatcher(key);
            if(mv.getData().size() != 0){
                for(Map.Entry<String, Object> entry : mv.getData().entrySet()) {
                    String key1 = entry.getKey();
                    Object value = entry.getValue();
                    request.setAttribute(key1, value);
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(mv.getView());
            rd.forward(request, response);
        }catch(Exception e){
            e.printStackTrace(response.getWriter());
        }
    }
}