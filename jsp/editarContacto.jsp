<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="name" scope="request" class="java.lang.String"/>
<jsp:useBean id="phone" scope="request" class="java.lang.String"/>
<jsp:useBean id="id" scope="request" class="java.lang.String"/>
<jsp:useBean id="urlActionEdit" scope="request" class="java.lang.String"/>
<jsp:useBean id="urlModeView" scope="request" class="java.lang.String"/>
<jsp:useBean id="urlModeChage" scope="request" class="java.lang.String"/>    
<jsp:useBean id="paramRequest" scope="request" class="org.semanticwb.portal.api.SWBParamRequest"/>   
        
<h1><%=paramRequest.getLocaleString("title") %></h1> 
<form method='post' action='<%=urlActionEdit%>'>
    <label for='name'><%=paramRequest.getLocaleString("name") %>: </label>
    <input name='name' value='<%=name%>'/>
    <br><br>
    <label for='telefono'><%=paramRequest.getLocaleString("phone") %> </label>
    <input name='phone' value='<%=phone%>'>
    <input type='hidden' name='id' value='<%=id%>'>
    <br><br>
    <a href='<%=urlModeView%>'><%=paramRequest.getLocaleString("cancel") %></a>
    <input type='submit' value='<%=paramRequest.getLocaleString("save") %>'/>
</form> 

<br><br>

<p><%=paramRequest.getLocaleString("select") %></p>
<select  id="selectNumber">
    <option value="_blank"></option>
    <option  value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
    <option value="4">4</option>
    <option value="5">5</option>
</select>
<p id="respuesta"><%=paramRequest.getLocaleString("answer") %></p>

<script type="text/javascript">
    var selectNumber = document.getElementById("selectNumber");
    var respuesta = document.getElementById("respuesta");
    
    selectNumber.addEventListener("change", function(){
        http = new XMLHttpRequest();
        url = "<%=urlModeChage%>" + "?value="+ selectNumber.value;
        http.open("POST",url,true);
        http.onreadystatechange = function(){
            if(http.readyState == 4 && http.status == 200){
                respuesta.innerHTML = "Respuesta: " + http.responseText;
            }
        };
        http.send();
    });
    
</script>