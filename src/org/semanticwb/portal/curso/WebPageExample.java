/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.semanticwb.portal.curso;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.semanticwb.model.User;
import org.semanticwb.model.WebPage;
import org.semanticwb.model.base.ResourceBase;
import org.semanticwb.portal.api.GenericResource;
import org.semanticwb.portal.api.SWBParamRequest;
import org.semanticwb.portal.api.SWBResourceException;

/**
 *
 * @author UTIC
 */
public class WebPageExample  extends GenericResource {

    @Override
    public void doView(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        PrintWriter out  = response.getWriter();
        ResourceBase rb = paramRequest.getResourceBase();
        WebPage wp = paramRequest.getWebPage();
        User usr = paramRequest.getUser();
        Iterator<WebPage> listVisibleChilds = wp.listVisibleChilds(usr.getLanguage());
        
        float fontSize = Float.valueOf(paramRequest.getArgument("font_size", "16"));
        String fontFamily = paramRequest.getArgument("font_family", "Arial");
        String fontColor = paramRequest.getArgument("font_color", "black");
        



        out.print("<ul style='border: black solid;padding: 1em; border-radius: 1em; display: inline;' >");
        if(wp.getParent() !=null){
            out.print("<li style='display:inline-block; margin:1em;'><a style='text-decoration:none;' href="+wp.getParent().getUrl()+">");
            out.print("<span style='font-size: "+fontSize*1.5+"px;font-family: "+fontFamily+" ; color: "+fontColor+" ;'>");
            out.print(wp.getParent().getDisplayName(usr.getLanguage()));
            out.print("</span>");
            out.print("</a></li>");
        }        
        out.print("<li style='display:inline-block; margin:1em;'><a style='text-decoration:none;' href="+wp.getUrl()+">");
        out.print("<span style='font-size: "+fontSize+"px;font-family: "+fontFamily+" ; color: "+fontColor+" ;'>");
        out.print(wp.getDisplayName(usr.getLanguage()));
        out.print("</span>");
        out.print("</a></li>");
        
        while(listVisibleChilds.hasNext()){
            WebPage child = listVisibleChilds.next();
            out.print("<li style='display:inline-block; margin:1em;'><a style='text-decoration:none;' href="+child.getUrl()+">");
            out.print("<span style='font-size: "+fontSize*.8+"px;font-family: "+fontFamily+" ; color: "+fontColor+" ;'>");
            out.print(child.getDisplayName());
            out.print("</span>");
            out.print("</a></li>");
        }
        out.print("</ul>");
    }
    
    
    
}
