package Servlet;

import DAO.AsistenciaDAO;
import Modelo.UsuarioSistema;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AsistenciaServlet", urlPatterns = {"/AsistenciaServlet"})
public class AsistenciaServlet extends HttpServlet {

    private UsuarioSistema usuarioActivo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession(false);
        UsuarioSistema u = sesion == null ? null : (UsuarioSistema) sesion.getAttribute("usuarioActivo");
        if (u == null) {
            response.sendRedirect("index.jsp");
        }
        return u;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioSistema u = usuarioActivo(request, response);
        if (u == null) {
            return;
        }
        AsistenciaDAO dao = new AsistenciaDAO();
        request.setAttribute("asistencias", dao.obtenerTodos());
        request.getRequestDispatcher("/WEB-INF/vistas/asistencia.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioSistema u = usuarioActivo(request, response);
        if (u == null) {
            return;
        }
        String dni = request.getParameter("dni");
        AsistenciaDAO dao = new AsistenciaDAO();
        String mensaje = dao.registrarPorDni(dni, u.getIdUsuario());
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("asistencias", dao.obtenerTodos());
        request.getRequestDispatcher("/WEB-INF/vistas/asistencia.jsp").forward(request, response);
    }
}