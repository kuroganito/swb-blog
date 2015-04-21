<%@page import="org.semanticwb.portal.blog.Comentario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="iCometarios" scope="request" class="java.util.Iterator<org.semanticwb.portal.blog.Comentario>"/>    
<jsp:useBean id="urlAgregar" scope="request" class="java.lang.String"/>    
<jsp:useBean id="paramRequest" scope="request" class="org.semanticwb.portal.api.SWBParamRequest"/>   

<style>
    h2{
        margin: 30px;
    }
    #textArea{
        display: block;
        width: 100%;
        height: 150px;
    }
    input[type="submit"]{
        margin:  1em 0;
        float: right;
        border-radius: .5em;
        font-size: 1.1rem;
        background-color: white;
    }
    #comentarios div{
        margin-left: 2em;
        padding: 1em;
        border-top: solid #999 1px;
    }
    .clear{
        clear: both;
    }
    .label{
        margin: 0px!important;
        line-height: 2em;
        font-size: 1rem!important;
    }
</style>

<h2><%=paramRequest.getLocaleString("title")%></h2>
<form action="<%=urlAgregar%>" method="POST">
    <span class="label"><%=paramRequest.getLocaleString("name") %></span>
    <br>
    <input name="nombre"/>
    <br><br>
     <span class="label"><%=paramRequest.getLocaleString("coment") %></span>
    <textArea id="textArea" name="comentario"></textarea>
    <input type="submit" multiple="true" value="<%=paramRequest.getLocaleString("save") %>"/>
</form>
<div class="clear"></div>
<div id="comentarios">
    <%while(iCometarios.hasNext()){ Comentario c =  iCometarios.next();  %>
        <div>
            <strong><span><%=c.getNombre()%>:</span></strong>
            <br>
            <br>
            <span><%=c.getComentario()%></span>
        </div>
    <%}%>
</div>
