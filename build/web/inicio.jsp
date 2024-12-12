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
            <h1>Jugar</h1>
            <h2>Introduce tu nombre:</h2>
            <form action="" method="get" class="formularioInicio">
                <input type="text" name="nombreJugador" placeholder="Introduce un nombre..." required class="inputNombre">
                <input type="submit" value="Enviar" class="botonEnviar">
            </form>
        </div>
        <div class="contenedorInicio">
            <h2>¡Bienvenido a la ESCAPADA DEL GRINCH!</h2>
            <h3>OBJETIVO</h3>
            <p>Tienes que crear un camino continuo para que Santa pueda repartir los iphone con tiktok a los niños tristes y aburridos.
               No puedes permitir que el Grinch te pille.</p>
            <h3>REGLAS</h3>
            <p>1. Si seleccionas una pieza, esta se cambiará por la de abajo. Solo puedes mover una pieza por turno. 
                No se podrá cambiar la pieza donde se encuentre Santa</p>
            <p>2. Santa avanzará si la pieza en la que se encuentra conecta con la siguiente según la direcciones que tengan estas.</p>
            <p>3. Santa no puede retroceder.</p>
            <p>4. Las piezas de arriba del tablero pueden conectar con las de abajo.</p>
            <p>5. Una vez esperado 2 turnos, el Grinch empezará a buscar a Santa. Tendra un movimiento por turno, y cómo es un tramposo
                ignorará los caminos.</p>
            <p>6. No se puede cambiar la pieza donde se encuentra el Grinch.</p>
        </div>
    </body>
</html>
