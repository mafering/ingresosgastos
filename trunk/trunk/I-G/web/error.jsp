<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.io.PrintWriter,org.apache.myfaces.shared_impl.util.ExceptionUtils" isErrorPage="true" %>
<head>
<title>Ingresos-Gastos</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />

</head>
<html>
<body>
<style>
.errorMessage {
  color: red;
  font-weight: bold;
  font-size: 0.7em;
}
.errorExceptionStack {
  margin-top: 5px;
  padding: 3px;
  border-style: solid;
  border-width: 1px;
  border-color: #9F9F9F;
  background-color: #FFFFFF;
}
.errorException {
  font-size: 0.7em;
}
</style>

<div id="pagina">
<div id="cabecera">
<div id="cabLogo"></div>
<h1>INGRESOS - GASTOS</h1>
<h2>I-G</h2>
</div>

<div id="rastroMigas"></div>

<div id="franjaMenu">&nbsp;</div>

<div id="contenido">



<div id="lineaPuntosStitulo" class="lineaPuntos">
<h3>Ha ocurrido un error en la aplicación</h3>
</div>

<div id="caja">
<div id="cajaIzda">
<div id="cajaSup"></div>
<div id="cajaContenido">

<%
if (exception != null)
{
  List exceptions = ExceptionUtils.getExceptions(exception);
  Throwable throwable = (Throwable) exceptions.get(exceptions.size()-1);
  String exceptionMessage = ExceptionUtils.getExceptionMessage(exceptions);
%>
  <p> El error es: <span class="errorMessage"><%=exceptionMessage%></span> </p>
  <%
  PrintWriter pw = new PrintWriter(out);
   %>
<br/>

  <span id="errorDetails" class="errorExceptions">

<input type="button" value="Detalles del error para Soporte tecnico>>" onclick="document.getElementById('errorMoreDetails').style.display=''"/>
<div id="errorMoreDetails" style="display:none" class="errorExceptionStack">
  <%
      throwable = (Throwable) exceptions.get(0);
      %><pre class="errorException"><%
      throwable.printStackTrace(pw);
      %></pre>
</div>
</span><%
}
else
{
  %>Recurso no encontrado.<%
}
%>
</div>
<div id="cajaInf">
<div id="cajaEsq"></div>
</div>
</div>
</div>

<div id="lineaPuntosBotones" class="lineaPuntos">&nbsp;</div>

</div>

</div>

</body>
</html>
