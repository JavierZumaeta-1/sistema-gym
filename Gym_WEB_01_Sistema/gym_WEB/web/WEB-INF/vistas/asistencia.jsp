<%@page import="java.util.List"%>
<%@page import="Modelo.Asistencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
List<Asistencia> asistencias = (List<Asistencia>) request.getAttribute("asistencias");
String mensaje = request.getAttribute("mensaje") == null ? "" : request.getAttribute("mensaje").toString();
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>SIGA-Gym | Asistencia</title>
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
            <a class="active" href="AsistenciaServlet"><span class="material-symbols-rounded">login</span>Asistencia</a>
            <a href="ReporteServlet"><span class="material-symbols-rounded">bar_chart</span>Reportes</a>
        </nav>
        <a class="logout-link" href="LogoutServlet"><span class="material-symbols-rounded">logout</span>Salir</a>
    </aside>
    <main class="content">
        <div class="topbar">
            <div>
                <span class="eyebrow">Control de ingreso</span>
                <h2>Registro de asistencia</h2>
                <p>Valida el acceso del socio mediante DNI y registra el movimiento del día.</p>
            </div>
        </div>

        <div class="row g-4">
            <div class="col-lg-5">
                <div class="panel terminal">
                    <div class="terminal-icon">
                        <span class="material-symbols-rounded">badge</span>
                    </div>
                    <span class="eyebrow">Ingreso rápido</span>
                    <h4>Ingresar DNI</h4>
                    <% if(!mensaje.equals("")){ %>
                    <div class="alert <%=mensaje.equals("Acceso permitido") ? "alert-success" : "alert-danger"%>"><%=mensaje%></div>
                    <% } %>
                    <form action="AsistenciaServlet" method="post">
                        <input class="form-control dni-input" name="dni" maxlength="12" placeholder="00000000" required autocomplete="off">
                        <button class="btn btn-primary w-100 mt-3">
                            <span class="material-symbols-rounded">task_alt</span>
                            Registrar ingreso
                        </button>
                    </form>
                </div>
            </div>
            <div class="col-lg-7">
                <div class="panel">
                    <div class="panel-head">
                        <div>
                            <span class="eyebrow">Últimos accesos</span>
                            <h4>Historial reciente</h4>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table align-middle">
                            <thead>
                                <tr>
                                    <th>Hora</th>
                                    <th>Socio</th>
                                    <th>DNI</th>
                                    <th>Estado</th>
                                    <th>Detalle</th>
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
            </div>
        </div>
    </main>
</div>
<script src="assets/js/app.js"></script>
</body>
</html>