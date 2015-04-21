/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.semanticwb.portal.curso;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.semanticwb.model.User;
import org.semanticwb.model.base.ResourceBase;
import org.semanticwb.portal.api.GenericResource;
import org.semanticwb.portal.api.SWBParamRequest;
import org.semanticwb.portal.api.SWBResourceException;

/**
 *
 * @author UTIC
 */
public class ResourceExample  extends GenericResource {

    @Override
    public void doView(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        PrintWriter out  = response.getWriter();
        ResourceBase rb = paramRequest.getResourceBase();
        User usr = paramRequest.getUser();
        
        out.print("<table>");
        out.print("<tr><th>Objeto Resource</th><tr>");
        out.print("<tr>");
        out.print(  "<td>Id:</td>");
        out.print(  "<td>"+rb.getId()+"</td>");
        out.print("<tr>");
        out.print("<tr>");
        out.print(  "<td>Titulo</td>");
        out.print(  "<td>"+rb.getTitle()+"</td>");
        out.print("<tr>");
        out.print("<tr>");
        out.print(  "<td>Descripcion</td>");
        out.print(  "<td>"+rb.getDescription()+"</td>");
        out.print("<tr>");
        out.print("<tr>");
        out.print(  "<td>Ruta</td>");
        out.print(  "<td>"+rb.getWorkPath()+"</td>");
        out.print("<tr>");
        out.print("<tr>");
        out.print(  "<td>Fecha de creacion:</td>");
        out.print(  "<td>"+rb.getCreated()+"</td>");
        out.print("<tr>");
        out.print("<tr>");
        out.print(  "<td>Autor</td>");
        out.print(  "<td>"+rb.getCreator()+"</td>");
        out.print("<tr>");
        out.print("</table>");
        
        out.print("<table>");
        out.print("<tr><th>Objeto User</th><tr>");
        out.print("<tr>");
        out.print(  "<td>Id:</td>");
        out.print(  "<td>"+usr.getId()+"</td>");
        out.print("<tr>");
        out.print("<tr>");
        out.print(  "<td>Nombre completo</td>");
        out.print(  "<td>"+usr.getFullName()+"</td>");
        out.print("<tr>");
        out.print("<tr>");
        out.print(  "<td>Pais</td>");
        out.print(  "<td>"+usr.getCountry()+"</td>");
        out.print("<tr>");
        out.print("<tr>");
        out.print(  "<td>Lenguaje</td>");
        out.print(  "<td>"+usr.getLanguage()+"</td>");
        out.print("<tr>");
        out.print("<tr>");
        out.print(  "<td>Usuario:</td>");
        out.print(  "<td>"+usr.getLogin()+"</td>");
        out.print("<tr>");
        out.print("<tr>");
        out.print(  "<td>Contraseña</td>");
        out.print(  "<td>"+usr.getPassword()+"</td>");
        out.print("<tr>");
        out.print("</table>");
      
    }
    
    
    
}
