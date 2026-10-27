package Servlet;

import DAO.SocioDAO;
import Modelo.Socio;
import Modelo.UsuarioSistema;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SocioServlet", urlPatterns = {"/SocioServlet"})
public class SocioServlet extends HttpServlet {

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
        SocioDAO dao = new SocioDAO();
        if (accion == null) {
            accion = "listar";
        }
        if (accion.equals("nuevo")) {
            request.getRequestDispatcher("/WEB-INF/vistas/form_socio.jsp").forward(request, response);
        } else if (accion.equals("editar")) {
            int idSocio = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("socio", dao.obtenerPorId(idSocio));
            request.getRequestDispatcher("/WEB-INF/vistas/form_socio.jsp").forward(request, response);
        } else if (accion.equals("eliminar")) {
            int idSocio = Integer.parseInt(request.getParameter("id"));
            dao.borrar(idSocio);
            response.sendRedirect("SocioServlet");
        } else if (accion.equals("buscar")) {
            String dato = request.getParameter("dato");
            request.setAttribute("socios", dao.buscar(dato));
            request.setAttribute("dato", dato);
            request.getRequestDispatcher("/WEB-INF/vistas/socios.jsp").forward(request, response);
        } else {
            request.setAttribute("socios", dao.obtenerTodos());
            request.getRequestDispatcher("/WEB-INF/vistas/socios.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!sesionActiva(request, response)) {
            return;
        }
        String accion = request.getParameter("accion");
        SocioDAO dao = new SocioDAO();
        Socio s = new Socio();
        String id = request.getParameter("idSocio");
        if (id != null && !id.trim().isEmpty()) {
            s.setIdSocio(Integer.parseInt(id));
        }
        s.setDni(request.getParameter("dni"));
        s.setNombres(request.getParameter("nombres"));
        s.setApellidos(request.getParameter("apellidos"));
        s.setTelefono(request.getParameter("telefono"));
        s.setCorreo(request.getParameter("correo"));
        String fecha = request.getParameter("fechaRegistro");
        if (fecha == null || fecha.trim().isEmpty()) {
            s.setFechaRegistro(new Date(System.currentTimeMillis()));
        } else {
            s.setFechaRegistro(Date.valueOf(fecha));
        }
        s.setEstado(request.getParameter("estado"));
        if ("actualizar".equals(accion)) {
            dao.actualizar(s);
        } else {
            dao.crear(s);
        }
        response.sendRedirect("SocioServlet");
    }
}