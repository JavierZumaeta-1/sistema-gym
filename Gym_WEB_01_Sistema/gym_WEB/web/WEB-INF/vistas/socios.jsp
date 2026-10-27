<%@page import="java.util.List"%>
<%@page import="Modelo.Socio"%>
<%@page import="Modelo.UsuarioSistema"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
UsuarioSistema usuario = (UsuarioSistema) session.getAttribute("usuarioActivo");
List<Socio> socios = (List<Socio>) request.getAttribute("socios");
String dato = request.getAttribute("dato") == null ? "" : request.getAttribute("dato").toString();
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>SIGA-Gym | Socios</title>
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
            <a class="active" href="SocioServlet"><span class="material-symbols-rounded">groups</span>Socios</a>
            <a href="MembresiaServlet"><span class="material-symbols-rounded">workspace_premium</span>Membresías</a>
            <a href="AsistenciaServlet"><span class="material-symbols-rounded">login</span>Asistencia</a>
            <a href="ReporteServlet"><span class="material-symbols-rounded">bar_chart</span>Reportes</a>
        </nav>
        <a class="logout-link" href="LogoutServlet"><span class="material-symbols-rounded">logout</span>Salir</a>
    </aside>
    <main class="content">
        <div class="topbar">
            <div>
                <span class="eyebrow">Base de clientes</span>
                <h2>Gestión de socios</h2>
                <p>Administra registros, estados y datos de contacto de los socios.</p>
            </div>
            <a class="btn btn-primary" href="SocioServlet?accion=nuevo">
                <span class="material-symbols-rounded">person_add</span>
                Registrar socio
            </a>
        </div>

        <div class="panel mb-4">
            <form action="SocioServlet" method="get" class="row g-3">
                <input type="hidden" name="accion" value="buscar">
                <div class="col-md-10">
                    <label class="form-label">Búsqueda rápida</label>
                    <div class="input-shell">
                        <span class="material-symbols-rounded">search</span>
                        <input class="form-control" name="dato" value="<%=dato%>" placeholder="Buscar por DNI, nombre o apellido">
                    </div>
                </div>
                <div class="col-md-2 d-flex align-items-end">
                    <button class="btn btn-dark w-100">
                        <span class="material-symbols-rounded">search</span>
                        Buscar
                    </button>
                </div>
            </form>
        </div>

        <div class="panel">
            <div class="panel-head">
                <div>
                    <span class="eyebrow">Listado general</span>
                    <h4>Socios registrados</h4>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table align-middle">
                    <thead>
                        <tr>
                            <th>DNI</th>
                            <th>Socio</th>
                            <th>Teléfono</th>
                            <th>Correo</th>
                            <th>Registro</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Socio s : socios){ %>
                        <tr>
                            <td><%=s.getDni()%></td>
                            <td><%=s.getNombreCompleto()%></td>
                            <td><%=s.getTelefono()%></td>
                            <td><%=s.getCorreo()%></td>
                            <td><%=s.getFechaRegistro()%></td>
                            <td><span class="badge <%=s.getEstado().equals("Activo") ? "text-bg-success" : "text-bg-secondary"%>"><%=s.getEstado()%></span></td>
                            <td>
                                <div class="action-group">
                                    <a class="btn btn-sm btn-outline-primary" href="SocioServlet?accion=editar&id=<%=s.getIdSocio()%>">
                                        <span class="material-symbols-rounded">edit</span>
                                        Editar
                                    </a>
                                    <a class="btn btn-sm btn-outline-danger" href="SocioServlet?accion=eliminar&id=<%=s.getIdSocio()%>">
                                        <span class="material-symbols-rounded">block</span>
                                        Desactivar
                                    </a>
                                </div>
                            </td>
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