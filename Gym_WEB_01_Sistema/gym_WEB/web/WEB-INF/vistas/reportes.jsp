<%@page import="java.util.List"%>
<%@page import="Modelo.Reporte"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
List<Reporte> reportes = (List<Reporte>) request.getAttribute("reportes");
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>SIGA-Gym | Reportes</title>
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
            <a href="DashboardServlet"><span class="material-symbols-rounded">dashboard</span>Panel</a>
            <a href="SocioServlet"><span class="material-symbols-rounded">groups</span>Socios</a>
            <a href="MembresiaServlet"><span class="material-symbols-rounded">workspace_premium</span>Membresías</a>
            <a href="AsistenciaServlet"><span class="material-symbols-rounded">login</span>Asistencia</a>
            <a class="active" href="ReporteServlet"><span class="material-symbols-rounded">bar_chart</span>Reportes</a>
        </nav>
        <a class="logout-link" href="LogoutServlet"><span class="material-symbols-rounded">logout</span>Salir</a>
    </aside>
    <main class="content">
        <div class="topbar">
            <div>
                <span class="eyebrow">Administración</span>
                <h2>Reportes</h2>
                <p>Genera reportes administrativos por rango de fechas y tipo de control.</p>
            </div>
        </div>

        <div class="panel mb-4 form-panel">
            <div class="panel-head">
                <div>
                    <span class="eyebrow">Nuevo reporte</span>
                    <h4>Parámetros del reporte</h4>
                </div>
            </div>
            <form action="ReporteServlet" method="post" class="row g-3">
                <div class="col-md-3">
                    <label class="form-label">Tipo</label>
                    <select class="form-select" name="tipoReporte">
                        <option value="Asistencia diaria">Asistencia diaria</option>
                        <option value="Asistencia mensual">Asistencia mensual</option>
                        <option value="Membresías activas">Membresías activas</option>
                        <option value="Membresías vencidas">Membresías vencidas</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Desde</label>
                    <input type="date" class="form-control" name="fechaInicio" required>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Hasta</label>
                    <input type="date" class="form-control" name="fechaFin" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Descripción</label>
                    <input class="form-control" name="descripcion" required placeholder="Detalle breve del reporte">
                </div>
                <div class="col-md-1 d-flex align-items-end">
                    <button class="btn btn-primary w-100">
                        <span class="material-symbols-rounded">add</span>
                        Crear
                    </button>
                </div>
            </form>
        </div>

        <div class="panel">
            <div class="panel-head">
                <div>
                    <span class="eyebrow">Historial</span>
                    <h4>Reportes generados</h4>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table align-middle">
                    <thead>
                        <tr>
                            <th>Tipo</th>
                            <th>Desde</th>
                            <th>Hasta</th>
                            <th>Generado</th>
                            <th>Usuario</th>
                            <th>Descripción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Reporte r : reportes){ %>
                        <tr>
                            <td><%=r.getTipoReporte()%></td>
                            <td><%=r.getFechaInicio()%></td>
                            <td><%=r.getFechaFin()%></td>
                            <td><%=r.getFechaGeneracion()%></td>
                            <td><%=r.getUsuario()%></td>
                            <td><%=r.getDescripcion()%></td>
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