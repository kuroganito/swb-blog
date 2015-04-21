
package org.semanticwb.portal.blog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.semanticwb.portal.agenda.Agenda;
import org.semanticwb.portal.api.GenericResource;
import org.semanticwb.portal.api.SWBActionResponse;
import org.semanticwb.portal.api.SWBParamRequest;
import org.semanticwb.portal.api.SWBResourceException;
import org.semanticwb.portal.api.SWBResourceURL;

public class Blog extends GenericResource{

    @Override
    public void doView(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) 
            throws SWBResourceException, IOException {
        Iterator<Comentario> iCometarios = Comentario.listarComentarios();
        String urlAgregar = paramRequest.getActionUrl().setAction(SWBResourceURL.Action_ADD).toString();
        RequestDispatcher rd = request.getRequestDispatcher("/work/models/demo/jsp/blog.jsp");
        request.setAttribute("paramRequest", paramRequest);
        request.setAttribute("iCometarios", iCometarios);
        request.setAttribute("urlAgregar", urlAgregar);
        try {
            rd.include(request, response);
        } catch (ServletException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void processAction(HttpServletRequest request, SWBActionResponse response) throws SWBResourceException, IOException {
        if(response.getAction().equals(SWBResourceURL.Action_ADD)){
            String mensaje = request.getParameter("comentario");
            String nombre = request.getParameter("nombre");
            if(mensaje != null && !mensaje.trim().isEmpty() && nombre != null && !nombre.trim().isEmpty()){
                Comentario.agregraComentario(new Comentario(nombre,mensaje));
            }
            response.setMode(SWBResourceURL.Mode_VIEW);
        }else{
            super.processAction(request, response); //To change body of generated methods, choose Tools | Templates.
        }
    }
   
}

