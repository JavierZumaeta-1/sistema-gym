package Servlet;

import DAO.AsistenciaDAO;
import DAO.DashboardDAO;
import Modelo.UsuarioSistema;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/DashboardServlet"})
public class DashboardServlet extends HttpServlet {

    private boolean sesionActiva(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession(false);
        UsuarioSistema u = sesion == null ? null : (UsuarioSistema) sesion.getAttribute("usuarioActivo");

        if (u == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return false;
        }

        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!sesionActiva(request, response)) {
            return;
        }

        DashboardDAO dashboardDAO = new DashboardDAO();
        AsistenciaDAO asistenciaDAO = new AsistenciaDAO();

        request.setAttribute("dashboard", dashboardDAO.obtenerResumen());
        request.setAttribute("asistencias", asistenciaDAO.obtenerRecientes(5));

        request.getRequestDispatcher("/WEB-INF/vistas/dashboard.jsp").forward(request, response);
    }
}