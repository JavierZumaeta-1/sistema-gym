<%@page import="java.util.List"%>
<%@page import="Modelo.Asistencia"%>
<%@page import="Modelo.Dashboard"%>
<%@page import="Modelo.UsuarioSistema"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
UsuarioSistema usuario = (UsuarioSistema) session.getAttribute("usuarioActivo");
Dashboard d = (Dashboard) request.getAttribute("dashboard");
List<Asistencia> asistencias = (List<Asistencia>) request.getAttribute("asistencias");
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>SIGA-Gym | Dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css2?family=Manrope:wght@400;500;600;700;800&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/main.css" rel="stylesheet">
</head>
<body>
<div class="layout">
    <aside class="sidebar">
        <div class="brand">
            <div class="brand-mark">
                <span class="material-symbols-rounded">fitness_center</span>
            </div>
            <div>
                SIGA-GYM
                <span>Fitness Manager</span>
            </div>
        </div>
        <nav class="nav-menu">
            <a class="active" href="DashboardServlet"><span class="material-symbols-rounded">dashboard</span>Panel</a>
            <a href="SocioServlet"><span class="material-symbols-rounded">groups</span>Socios</a>
            <a href="MembresiaServlet"><span class="material-symbols-rounded">workspace_premium</span>Membresías</a>
            <a href="AsistenciaServlet"><span class="material-symbols-rounded">login</span>Asistencia</a>
            <a href="ReporteServlet"><span class="material-symbols-rounded">bar_chart</span>Reportes</a>
        </nav>
        <a class="logout-link" href="LogoutServlet"><span class="material-symbols-rounded">logout</span>Salir</a>
    </aside>
    <main class="content">
        <div class="topbar">
            <div>
                <span class="eyebrow">Resumen operativo</span>
                <h2>Panel principal</h2>
                <p>Bienvenido, <%=usuario.getNombres()%>. Aquí se muestra el movimiento general del gimnasio.</p>
            </div>
            <a class="btn btn-primary" href="SocioServlet?accion=nuevo">
                <span class="material-symbols-rounded">person_add</span>
                Nuevo socio
            </a>
        </div>

        <div class="row g-4">
            <div class="col-md-3">
                <div class="card-soft metric-blue">
                    <div class="metric-icon"><span class="material-symbols-rounded">groups</span></div>
                    <span>Socios activos</span>
                    <h3><%=d.getSociosActivos()%></h3>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card-soft metric-green">
                    <div class="metric-icon"><span class="material-symbols-rounded">task_alt</span></div>
                    <span>Asistencias hoy</span>
                    <h3><%=d.getAsistenciasHoy()%></h3>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card-soft metric-orange">
                    <div class="metric-icon"><span class="material-symbols-rounded">event_busy</span></div>
                    <span>Membresías vencidas</span>
                    <h3><%=d.getMembresiasVencidas()%></h3>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card-soft metric-purple">
                    <div class="metric-icon"><span class="material-symbols-rounded">payments</span></div>
                    <span>Ingresos estimados</span>
                    <h3>S/ <%=d.getIngresosEstimados()%></h3>
                </div>
            </div>
        </div>

        <div class="panel mt-4">
            <div class="panel-head">
                <div>
                    <span class="eyebrow">Movimientos recientes</span>
                    <h4>Historial de asistencia</h4>
                </div>
                <a href="AsistenciaServlet">Ver todo</a>
            </div>
            <div class="table-responsive">
                <table class="table align-middle">
                    <thead>
                        <tr>
                            <th>Hora</th>
                            <th>Socio</th>
                            <th>DNI</th>
                            <th>Estado</th>
                            <th>Observación</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Asistencia a : asistencias){ %>
                        <tr>
                            <td><%=a.getHoraIngreso()%></td>
                            <td><%=a.getSocio()%></td>
                            <td><%=a.getDni()%></td>
                            <td><span class="badge <%=a.getEstadoAcceso().equals("Permitido") ? "text-bg-success" : "text-bg-danger"%>"><%=a.getEstadoAcceso()%></span></td>
                            <td><%=a.getObservacion()%></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
</div>
<script src="assets/js/app.js"></script>
</body>
</html>