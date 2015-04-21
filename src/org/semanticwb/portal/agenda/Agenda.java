
package org.semanticwb.portal.agenda;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.semanticwb.portal.api.GenericResource;
import org.semanticwb.portal.api.SWBActionResponse;
import org.semanticwb.portal.api.SWBParamRequest;
import org.semanticwb.portal.api.SWBResourceException;
import org.semanticwb.portal.api.SWBResourceURL;


public class Agenda extends GenericResource{
    private static final String Mode_ADD = "doAdd";
    private  final String Mode_EDIT_CONTACT = "doEdit";
    private  final String Mode_CHANGE_NUMBER = "doChange";
    private final HashMap<Integer,Contacto> contactos = new HashMap();
    
    @Override
    public void doView(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        PrintWriter out = response.getWriter();
        SWBResourceURL renderUrl = paramRequest.getRenderUrl();
        SWBResourceURL actionUrl = paramRequest.getActionUrl();
        Set<Map.Entry<Integer, Contacto>> entrySet = contactos.entrySet();
        Iterator<Map.Entry<Integer, Contacto>> iterator = entrySet.iterator();
        
        out.print("<a href='" + renderUrl.setMode(SWBResourceURL.Mode_XML) + "'>XML</a><br>"
                + "<a href='"+ renderUrl.setMode(SWBResourceURL.Mode_HELP) +"'>Ayuda</a><br><br>"
                + "<table border='1'>"
                +   "<tr>"
                +       "<th>Nombre</th>"
                +       "<th>Telefono</th>"
                +       "<th>Opciones</th>"
                +   "</tr>");
        while(iterator.hasNext()){
            Map.Entry<Integer, Contacto> contacto = iterator.next();
            out.print("<tr><td>"+contacto.getValue().getName()+"</td>"
                +       "<td>"+contacto.getValue().getPhone()+"</td>"
                +       "<td><a href='"+actionUrl.setAction(SWBResourceURL.Action_REMOVE).setParameter("id",String.valueOf(contacto.getKey()))+"'>Borrar</a>"
                    + "<a href='"+renderUrl.setMode(Mode_EDIT_CONTACT).
                            setParameter("name",contacto.getValue().getName()).
                            setParameter("phone", contacto.getValue().getPhone()).
                            setParameter("id", String.valueOf(contacto.getKey()))+"'>Editar</a></td></tr>");
        }
        out.print( "</table><br>"
                + "<a href='" + renderUrl.setMode(Agenda.Mode_ADD) + "'>Agregar</a>"
                );
    }

    @Override
    public void doHelp(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        PrintWriter out = response.getWriter();
        SWBResourceURL renderUrl = paramRequest.getRenderUrl();
           out.print("<h1>Help</h1>"
                + "<a href='"+renderUrl.setMode(SWBResourceURL.Mode_VIEW)+"'>Atras</a>"
                );
    }

    @Override
    public void doXML(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        PrintWriter out = response.getWriter();
        SWBResourceURL renderUrl = paramRequest.getRenderUrl();
        out.print("<h1>XML</h1>"
                + "<a href='"+renderUrl.setMode(SWBResourceURL.Mode_VIEW)+"'>Atras</a>"
                );
    }

   

    private void doAdd(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws IOException {
        PrintWriter out = response.getWriter();
        SWBResourceURL renderUrl = paramRequest.getRenderUrl();
        SWBResourceURL actionUrl = paramRequest.getActionUrl();
       
        out.print("<h1>Agregar nuevo contacto</h1> "
                + "<form method='post' action='"+actionUrl.setAction(SWBResourceURL.Action_ADD)+"'>"
                + "  <label for='name'>Nombre: </label>"
                + "  <input name='name' />"
                + "  <br><br>"
                + "  <label for='telefono'>Telefono: </label>"
                + "  <input name='phone'>"
                + "  <br><br>"
                + "  <a href='"+renderUrl.setMode(SWBParamRequest.Mode_VIEW)+"'>Atras</a>"   
                + "  <input type='submit' value='Guardar'/>"
                + "</form> "
               );
    }
    
     private void doEditContact(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws IOException {
        
        String urlActionEdit = paramRequest.getActionUrl().setAction(SWBResourceURL.Action_EDIT).toString();
        String urlModeView = paramRequest.getRenderUrl().setMode(SWBParamRequest.Mode_VIEW).toString();
        String urlModeChage =  paramRequest.getRenderUrl().setMode(Mode_CHANGE_NUMBER).setCallMethod(SWBResourceURL.Call_DIRECT).toString();
        RequestDispatcher rd = request.getRequestDispatcher("/work/models/demo/jsp/editarContacto.jsp");
        
        request.setAttribute("urlActionEdit", urlActionEdit);
        request.setAttribute("urlModeView", urlModeView);
        request.setAttribute("urlModeChage", urlModeChage);
        request.setAttribute("name",request.getParameter("name") );
        request.setAttribute("phone",request.getParameter("phone") );
        request.setAttribute("id",request.getParameter("id") );
        request.setAttribute("paramRequest",paramRequest );
        
        try {
            rd.include(request, response);
        } catch (ServletException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
    private void doChange(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws IOException {
        PrintWriter out = response.getWriter();
        String valor = request.getParameter("value");
        if(valor != null  && !valor.trim().isEmpty() && !valor.equalsIgnoreCase("_blank")){
            out.print(Integer.valueOf(valor) *2);
        }else{
            out.print("Verificar informacion");
        }
        
        
    }
     
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        if(paramRequest.getMode().equals(Agenda.Mode_ADD)){
            doAdd(request, response, paramRequest);
        }else if(paramRequest.getMode().equals(Mode_EDIT_CONTACT)){
            doEditContact(request, response, paramRequest);
        }else if(paramRequest.getMode().equals(Mode_CHANGE_NUMBER)){
            doChange(request, response, paramRequest);
            
        }else{
            super.processRequest(request, response, paramRequest); 
        }
    }
    @Override
    public void processAction(HttpServletRequest request, SWBActionResponse response) throws SWBResourceException, IOException {
        if(response.getAction().equals(SWBResourceURL.Action_ADD)){
            System.out.println("Action add");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            if(name != null && !name.trim().isEmpty() &&  phone != null && !phone.trim().isEmpty()){
                contactos.put(contactos.size(), new Contacto(name,phone));
            }
            response.setMode(SWBResourceURL.Mode_VIEW);
        }else if(response.getAction().equals(SWBResourceURL.Action_EDIT)){
            System.out.println("Action edit");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String id = request.getParameter("id");
            if(name != null && !name.trim().isEmpty() &&  phone != null && !phone.trim().isEmpty()){
                contactos.replace(Integer.valueOf(id), new Contacto(name,phone));
            }
            response.setMode(SWBResourceURL.Mode_VIEW);
        }else if(response.getAction().equals(SWBResourceURL.Action_REMOVE)){
             System.out.println("Action remove");
             contactos.remove(Integer.valueOf(request.getParameter("id")));
        }else{
            super.processAction(request, response); //To change body of generated methods, choose Tools | Templates.
        }
    }
}

class Contacto{
    String name;
    String phone;

    public Contacto(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    } 
}