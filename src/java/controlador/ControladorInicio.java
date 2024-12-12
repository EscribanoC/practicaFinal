/*
 * Controlador Inicio
 */
package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Partida;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "ControladorInicio", urlPatterns = {"/ControladorInicio"})
public class ControladorInicio extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession();//Recoge la sesión
        String nombreJugador = (String) request.getParameter("nombreJugador");//Recoge el parámetro nombre que se añadirá a la sesión
        
        if ( nombreJugador!= null && !nombreJugador.isEmpty()) {//Si es la primera vez que entre el parámetro nombre es igual a null por lo que no entra
            //Añade el nombre del jugador a la sesión
            sesion.setAttribute("nombreJugador", nombreJugador);
            //Crea la partida y la añade a la sesión
            Partida partida = new Partida();
            sesion.setAttribute("partida", partida);
            //El controlador redirige al usuario a ControladorTurno
            response.sendRedirect("ControladorTurno");
            return;
        }
        getServletContext().getRequestDispatcher("/inicio.jsp").forward(request, response);//Forward a la vista
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
