package Servlet;

import DAO.MembresiaDAO;
import DAO.SocioDAO;
import DAO.TipoMembresiaDAO;
import Modelo.Membresia;
import Modelo.UsuarioSistema;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MembresiaServlet", urlPatterns = {"/MembresiaServlet"})
public class MembresiaServlet extends HttpServlet {

    private boolean sesionActiva(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession(false);
        UsuarioSistema u = sesion == null ? null : (UsuarioSistema) sesion.getAttribute("usuarioActivo");
        if (u == null) {
            response.sendRedirect("index.jsp");
            return false;
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!sesionActiva(request, response)) {
            return;
        }
        String accion = request.getParameter("accion");
        MembresiaDAO membresiaDAO = new MembresiaDAO();
        SocioDAO socioDAO = new SocioDAO();
        TipoMembresiaDAO tipoDAO = new TipoMembresiaDAO();
        if (accion != null && accion.equals("editar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("membresiaEditar", membresiaDAO.obtenerPorId(id));
        }
        if (accion != null && accion.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            membresiaDAO.borrar(id);
            response.sendRedirect("MembresiaServlet");
            return;
        }
        request.setAttribute("membresias", membresiaDAO.obtenerTodos());
        request.setAttribute("socios", socioDAO.obtenerTodos());
        request.setAttribute("tipos", tipoDAO.obtenerTodos());
        request.getRequestDispatcher("/WEB-INF/vistas/membresias.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!sesionActiva(request, response)) {
            return;
        }
        MembresiaDAO dao = new MembresiaDAO();
        Membresia m = new Membresia();
        String id = request.getParameter("idMembresia");
        if (id != null && !id.trim().isEmpty()) {
            m.setIdMembresia(Integer.parseInt(id));
        }
        m.setIdSocio(Integer.parseInt(request.getParameter("idSocio")));
        m.setIdTipoMembresia(Integer.parseInt(request.getParameter("idTipoMembresia")));
        m.setFechaInicio(Date.valueOf(request.getParameter("fechaInicio")));
        m.setFechaFin(Date.valueOf(request.getParameter("fechaFin")));
        m.setEstado(request.getParameter("estado"));
        if (id != null && !id.trim().isEmpty()) {
            dao.actualizar(m);
        } else {
            dao.crear(m);
        }
        response.sendRedirect("MembresiaServlet");
    }
}