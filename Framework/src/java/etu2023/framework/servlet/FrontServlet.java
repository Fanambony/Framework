package etu2023.framework.servlet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.google.gson.Gson;
import etu2023.framework.Mapping;
import etu2023.framework.ModelView;
import etu2023.framework.UploadFile;
import etu2023.framework.annotation.Annotation;
import etu2023.framework.annotation.Auth;
import etu2023.framework.utils.Utils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
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
    private HashMap<String, Object> Singleton = new HashMap<>();
    private String isConnected;
    private String profile;

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

    public HashMap<String, Object> getSingleton() {
        return Singleton;
    }
    public void setSingleton(HashMap<String, Object> Singleton) {
        this.Singleton = Singleton;
    }

    public String getIsConnected() {
        return isConnected;
    }

    public void setIsConnected(String isConnected) {
        this.isConnected = isConnected;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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
            //ex.printStackTrace();
        }
    }
    

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        String packageModel = this.getInitParameter("model-package");
        setClassList(new ArrayList<>());
        //setMappingUrls("etu2023.framework.model.");        
        setMappingUrls(packageModel);
        setIsConnected(config.getInitParameter("session-name-isconnected"));
        setProfile(config.getInitParameter("session-name-profile"));
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
            
            for (Map.Entry<String, Mapping> entry : MappingUrls.entrySet()) {
                Object key = entry.getKey();
                Object val = entry.getValue();
            }
              
            out.println("</body>");
            out.println("</html>");
            
        }catch (Exception ex) {
            //ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            String url=getNom(request, response);
            Mapping mapping=MappingUrls.get(url);
            if(mapping!=null){
                ModelView mv = getInput(url, request, response);
                loadView(mv, request, response);
//                Class c = getClass(getNom(request, response));
                //Object singleton = traitementSingleton(c);
            }else{
                processRequest(request, response);
            }
            out.println(request.getParameterMap().keySet());
            out.close();
        } 
        catch (Exception ex) {
            ex.getStackTrace();
        }
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
        try {
            PrintWriter out = response.getWriter();
            String url=getNom(request, response);
            Mapping mapping=MappingUrls.get(url);
            if(mapping!=null){
                ModelView mv = getInput(url, request, response);
                loadView(mv, request, response);
                //Class c = getClass(getNom(request, response));
                //Object singleton = traitementSingleton(c);
            }else {
                processRequest(request, response);
            }
            out.println(request.getParameterMap().keySet());
            out.close();
        } 
        catch (Exception ex) {
            ex.getStackTrace();
        }
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
            if (c.getSimpleName().equals(getMappingUrls().get(url).getClassName())){
                for (Method m : c.getDeclaredMethods()) {
                    if (m.getName().equals(getMappingUrls().get(url).getMethod())){
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
        ModelView mv = new ModelView();
        if(m != null){
            String className = "etu2023.framework.model."+m.getClassName();
            Class c;
            c = Class.forName(className);
            mv = (ModelView)c.getDeclaredMethod(m.getMethod(), null).invoke(c.getConstructor(null).newInstance(), null);
        }
        return mv;
    }
    
    public void loadView(ModelView mv, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(mv.getView());
            if (mv == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
                return;
            }
            for (Map.Entry<String, Object> entry : mv.getData().entrySet()) {
                String key1 = entry.getKey();
                Object value = entry.getValue();
                request.setAttribute(key1, value);
            }
            for (Map.Entry<String, Object> entry : mv.getSession().entrySet()) {
                String key1 = entry.getKey();
                Object value = entry.getValue();
                request.getSession().setAttribute(key1, value);
            }
            if(mv.getIsJson()) {
                response.getWriter().println(new Gson().toJson(mv.getData()));
            } else {
                rd.forward(request, response);
            }
        } catch (Exception e) {
            //e.printStackTrace(response.getWriter());
        }
    }

    public String Maj(String mot){
        if(mot.length() == 0 || mot == null){
            return mot;
        }
        char PremierCaractere = Character.toUpperCase(mot.charAt(0));
        return PremierCaractere + mot.substring(1);
    }
    
    public double toDouble(String valeur){
        return parseDouble(valeur);
    }
    
    public int toInt(String valeur){
        return parseInt(valeur);
    }
    
    public float toFloat(String valeur){
        return parseFloat(valeur);
    }
    
    public Date toDate(String valeur){
        return Date.valueOf(valeur);
    }
    
    public ModelView getInput(String s, HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        PrintWriter pw = response.getWriter();
        ModelView mv = null;
        try{
            Enumeration<String> allName = request.getParameterNames();
            Mapping mp = MappingUrls.get(s);
            String classe = "etu2023.framework.model." +mp.getClassName();
            Class c = Class.forName(classe);
            Object ob = traitementSingleton(c);
            
            Method[] methods = c.getDeclaredMethods();
            
            traitementSession(request, ob);
            
            Method md = null;
            for(Method method : methods){
                if(method.getName().equals(mp.getMethod())){
                    md = method;
                    break;
                }
            }
            Class<?>[] parameterTypes = md.getParameterTypes();
            Parameter[] parameter = md.getParameters();
            Object[] parameterValues = new Object[parameterTypes.length];
            int paramCount = md.getParameterCount();
            
            if(paramCount == 0){
                while(allName.hasMoreElements()){
                    String valeur = allName.nextElement();
                    String setter = Maj(valeur);
                    String value = request.getParameter(valeur);
                    String set = "set"+setter;

                    for(Method m : methods){
                        if(m.getName().equals(set)){
                            if(Arrays.toString(m.getParameterTypes()).contains("String")) {
                                m.invoke(ob, value);
                            } else if(Arrays.toString(m.getParameterTypes()).contains("int")) {
                                m.invoke(ob, toInt(value));
                            } else if(Arrays.toString(m.getParameterTypes()).contains("double")) {
                                m.invoke(ob, toDouble(value));
                            } else if(Arrays.toString(m.getParameterTypes()).contains("float")) {
                                m.invoke(ob, toFloat(value));
                            } else if(Arrays.toString(m.getParameterTypes()).contains("Date") || Arrays.toString(m.getParameterTypes()).contains("date")) {
                                m.invoke(ob, toDate(value));
                            }else if(Arrays.toString(m.getParameterTypes()).contains("String[]")) {
                                String[] values = request.getParameterValues(valeur);
                                m.invoke(ob, (Object) values);
                            } else {
                            }
                            break;
                        }
                    }
                }
            }else{
                String queryString = request.getQueryString();
                String[] querySeparator = queryString.split("&");
                for(int i = 0; i < querySeparator.length; i++){
                    String[] paramSepar = querySeparator[i].split("=");
                    String nom = paramSepar[0];
                    String valeur = paramSepar[1];
                    

                    for(int j = 0; j < parameter.length; j++){
                        String parameterName = parameter[j].getName();
                        Class<?> parameterType = parameterTypes[j];
                        if(parameterName.equalsIgnoreCase(nom)){
                            if(parameterType==String.class) {
                                parameterValues[j]= valeur;
                            } else if(parameterType==int.class) {
                                parameterValues[j]= toInt(valeur) ;
                            } else if(parameterType==double.class) {
                                parameterValues[j]= toDouble(valeur) ;
                            } else if(parameterType==float.class){
                                parameterValues[j]= toFloat(valeur) ;
                            } else if(parameterType==Date.class) {
                                parameterValues[j]= toDate(valeur) ;
                            } else if(parameterType == String[].class) {
                                String[] values = request.getParameterValues(nom);
                                parameterValues[j] = (Object) values;
                            } else {
                                UploadFile uploadFile = new UploadFile();
                                //setUploadedFile(request, nom, uploadFile);
                                //parameterValues[j] = uploadFile;
                                Part file = getPart(request, nom);
                                uploadFile.setFileName(UploadFile.getFileName(file));
                                parameterValues[j] = uploadFile;
                            }
                            break;
                        }if(j == parameter.length-1){
                            parameterValues[j] = null;
                        }
                    }
                }
            }
            
            Object objet = null;
            if(md.getReturnType() == ModelView.class){
                if(paramCount == 0){
                    if (!traitementAuth(request, md)) throw new Exception("AUTH");
                    objet = c.getDeclaredMethod(md.getName(), null).invoke(ob, null);
                }else{
                    if (!traitementAuth(request, md)) throw new Exception("AUTH");
                    objet = c.getDeclaredMethod(md.getName(), parameterTypes).invoke(ob, parameterValues);
                }
                loadView((ModelView)objet, request, response);
                if(traitementJson(md) == true) {
                    out.print(new Gson().toJson(ob));
                }else {
                    loadView((ModelView)ob, request, response);
                }
                if(mv.isInvalidateSession()) {
                    this.deleteSession(request, mv);
                    RequestDispatcher d = request.getRequestDispatcher(mv.getView());
                    d.forward(request, response);
                }
                
            }
            return mv;
        }catch(Exception e){
            //e.printStackTrace(response.getWriter());
        }
        return mv;
    }
    
    public void setUploadedFile(HttpServletRequest request, String paramName, UploadFile uploadFile) throws IOException, ServletException {
        Part filePart = request.getPart(paramName);
        if (filePart != null) {
            String fileName = uploadFile.getFileName(filePart);
            byte[] fileBytes = uploadFile.getFileBytes(filePart);

            uploadFile.setFileName(fileName);
            uploadFile.setBytes(fileBytes);
        }
    }
    
    public Part getPart(HttpServletRequest request, String key) throws Exception{
        Collection<Part> collection = request.getParts();
        for(Part part : collection){
            if(part.getName().equals(key)) {
                return part;
            }
        }
        throw new Exception("Not exist");
    }
    
    public Object traitementSingleton(Class<?> clazz) throws InstantiationException, IllegalAccessException{
        if(Utils.isSingleton(clazz)) {
            if(!getSingleton().containsKey(clazz.getName())) {
                getSingleton().put(clazz.getName(), clazz.newInstance());
            }
            return getSingleton().get(clazz.getName());
        }
        return clazz.newInstance();
    } 
    
    public boolean traitementAuth(HttpServletRequest request, Method m){
        Auth a = m.getAnnotation(Auth.class);
        if( a != null) {
            HttpSession session = request.getSession();
            String profil = (String) session.getAttribute(getProfile());
            String connecter = (String) session.getAttribute(getIsConnected());
            if(connecter != null) {
                if(profil != null) {
                    if(!a.value().equals("") && !a.value().equals(profil)) {
                        return false;
                    }
                }
            }else{
                return false;
            }
            /*if(a.value().equals("")) {
                return isConnected;
            } else {
                return isConnected && a.value().equals(profil);
            }*/
        }
        return true;
    }
    
    public void traitementSession(HttpServletRequest request, Object ob) throws Exception {
        HashMap<String, Object> session = new HashMap<>();
        HttpSession hs = request.getSession();
        List<String> liste = Collections.list(hs.getAttributeNames());
        Method m = ob.getClass().getDeclaredMethod("setSession", HashMap.class);
        for(String s:liste){
            session.put(s, hs.getAttribute(s));
        }
        m.invoke(ob, session);
    }
    public boolean traitementJson(Method m) {
        RestAPI api = m.getAnnotation(RestAPI.class);
        if(api != null) {
            return true;
        }
        return false;
    }
    
    public void deleteSession(HttpServletRequest request, ModelView view) {
        HttpSession session = request.getSession();
        for(String s: view.getDeleteSession()) {
            session.removeAttribute(s);
        }
        if(view.isInvalidateSession()) {
            session.invalidate();
        }
    }
}