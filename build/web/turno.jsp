<%-- 
    Document   : turno
    Created on : 8 dic 2024, 17:45:16
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Arrays"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Santa Juego</title>
        <link rel="stylesheet" type="text/css" href="estilos/estilo.css"/>
    </head>
    <body>
        <div class="contenedorPrincipal">
            <h1>Santa juego</h1>
            <h2>Jugador: ${sessionScope.nombreJugador}</h2>
            <div class="contenedorTablas">

                <!-- Tabla que contendrá la posición inicial de Grinch y Santa -->
                <table class="tablaInicio">
                    <c:forEach var="piezaInicio" begin="0" end="4" step="1">
                        <tr>
                            <td>
                                <c:if test="${partida.santa[0] == piezaInicio && partida.santa[1] == -1}">
                                    <img src="Imagenes/santa.gif" alt="alt" class="santaInicio"/>
                                </c:if>
                                <c:if test="${partida.grinch[0] == piezaInicio && partida.grinch[1] == -1}">
                                    <img src="Imagenes/grinch.gif" alt="alt" class="santaInicio"/>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <!-- Tabla de la partida -->
                <table class="tablaPartida">
                    <c:forEach var="fila" items="${sessionScope.partida.tablero}" varStatus="indiceFila">
                        <tr>
                            <c:forEach var="pieza" items="${fila}" varStatus="indiceColumna">
                                <td>
                                    <form action="" method="get">
                                        <%-- Valores de la posición en fila y columna de la casilla. Se encuentra oculto y es lo que se enviará al controlador --%>
                                        <input value="${indiceFila.index}" name="indiceFila" hidden class="inputOculto">
                                        <input value="${indiceColumna.index}" name="indiceColumna" hidden class="inputOculto">
                                        
                                        <%-- Comprueba si Santa se encuentra en esa pieza --%>
                                        <c:if test="${partida.santa[0] == indiceFila.index && partida.santa[1] == indiceColumna.index}">
                                            <img src="Imagenes/santa.gif" class="santa"><%-- Muestra la imagen de Santa --%>
                                        </c:if>
                                        <%-- Comprueba si Grinch se encuentra en esa pieza --%>
                                        <c:if test="${partida.grinch[0] == indiceFila.index && partida.grinch[1] == indiceColumna.index}">
                                            <img src="Imagenes/grinch.gif" class="santa"><%-- Muestra la imagen del Grinch --%>
                                        </c:if>
                                            
                                        <%-- Comprueba y deshabilita la pieza si: 
                                             1. Santa se encuentra en la misma posición que esta.
                                             2. La pieza está arriba de Santa.
                                             3. Santa se encuentra en la primera fila y la pieza en la misma columna pero en la última fila
                                        --%>
                                        <c:set var="mismaPosicion" value="${indiceFila.index == partida.santa[0] && indiceColumna.index == partida.santa[1]}"/>
                                        <c:set var="posicionArriba" value="${indiceFila.index+1 == partida.santa[0] && indiceColumna.index == partida.santa[1]}"/>
                                        <c:set var="posicionUltimaFila" value="${partida.santa[0] == 0 && indiceFila.last && indiceColumna.index == partida.santa[1]}"/>
                                        
                                        <input type="image" src="Imagenes/${pieza}.png" class="imagen" ${mismaPosicion || posicionArriba || posicionUltimaFila?'disabled':''}>
                                    </form>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
