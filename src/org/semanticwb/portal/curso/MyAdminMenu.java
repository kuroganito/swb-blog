

package org.semanticwb.portal.curso;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.semanticwb.model.Resource;
import org.semanticwb.model.User;
import org.semanticwb.model.WebPage;
import org.semanticwb.portal.api.GenericAdmResource;
import org.semanticwb.portal.api.SWBParamRequest;
import org.semanticwb.portal.api.SWBResourceException;


public class MyAdminMenu  extends GenericAdmResource {
   
    @Override
    public void doView(HttpServletRequest request, HttpServletResponse response, SWBParamRequest paramRequest) throws SWBResourceException, IOException {
        PrintWriter out  = response.getWriter();
        Resource rb = paramRequest.getResourceBase();
        
        WebPage wp = paramRequest.getWebPage();
        User usr = paramRequest.getUser();
        Iterator<WebPage> listVisibleChilds = wp.listVisibleChilds(usr.getLanguage());
        
        float fontSize = Float.valueOf(rb.getAttribute("font_size",paramRequest.getArgument("font_size", "16")));
        String fontFamily = rb.getAttribute("font_family",paramRequest.getArgument("font_family", "Arial"));
        String fontColor = rb.getAttribute("font_color",paramRequest.getArgument("font_color", "black"));
        



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
