/*
 * Controlador Turno
 */
package controlador;

import java.io.IOException;
import static java.lang.Integer.parseInt;
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
@WebServlet(name = "ControladorTurno", urlPatterns = {"/ControladorTurno"})
public class ControladorTurno extends HttpServlet {

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
        HttpSession sesion = request.getSession();
        Partida partida = (Partida) sesion.getAttribute("partida");

        if (request.getParameter("indiceFila") == null && request.getParameter("indiceColumna") == null) {//Si ambos parámetros son null(Primera vez que carga la página)
            getServletContext().getRequestDispatcher("/turno.jsp").forward(request, response);//Carga la vista
            return;
        }

        //Recoje los indices de la pieza a mover
        int indiceFila = Integer.parseInt(request.getParameter("indiceFila"));
        int indiceColumna = Integer.parseInt(request.getParameter("indiceColumna"));
        partida.cambiarPieza(indiceFila, indiceColumna);
        partida.moverSanta();//Santa se mueve
        partida.moverGrinch();//El Grinch se mueve
        
        //Comprueba en cada turno si la partida ha finalizado
        if(partida.comprobarEstado() == 1){//Si Santa gana
            response.sendRedirect("victoria.jsp");//Salta a la vista de victoria
            return;
        } else if(partida.comprobarEstado() == -1){//Si El Grinch pilla a Santa
            response.sendRedirect("derrota.html");//Salta a la vista de derrota
            return; 
        }
        
        //Carga la vista
        getServletContext().getRequestDispatcher("/turno.jsp").forward(request, response);
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
