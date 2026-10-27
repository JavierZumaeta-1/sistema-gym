package Servlet;

import DAO.ReporteDAO;
import Modelo.Reporte;
import Modelo.UsuarioSistema;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ReporteServlet", urlPatterns = {"/ReporteServlet"})
public class ReporteServlet extends HttpServlet {

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
        ReporteDAO dao = new ReporteDAO();
        request.setAttribute("reportes", dao.obtenerTodos());
        request.getRequestDispatcher("/WEB-INF/vistas/reportes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioSistema u = usuarioActivo(request, response);
        if (u == null) {
            return;
        }
        Reporte r = new Reporte();
        r.setIdUsuario(u.getIdUsuario());
        r.setTipoReporte(request.getParameter("tipoReporte"));
        r.setFechaInicio(Date.valueOf(request.getParameter("fechaInicio")));
        r.setFechaFin(Date.valueOf(request.getParameter("fechaFin")));
        r.setDescripcion(request.getParameter("descripcion"));
        ReporteDAO dao = new ReporteDAO();
        dao.crear(r);
        response.sendRedirect("ReporteServlet");
    }
}