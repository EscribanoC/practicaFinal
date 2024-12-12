<%-- 
    Document   : inicio
    Created on : 8 dic 2024, 17:04:49
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Inicio</title>
        <link rel="stylesheet" type="text/css" href="estilos/estilo.css"/>
    </head>
    <body>
        <div class="contenedorInicio">
            <h1>Inicio de sesión</h1>
            <h2>Introduce tu nombre:</h2>
            <form action="" method="get" class="formularioInicio">
                <input type="text" name="nombreJugador" placeholder="Introduce un nombre..." required class="inputNombre">
                <input type="submit" value="Enviar" class="botonEnviar">
            </form>
        </div>
    </body>
</html>
