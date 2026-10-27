<%@page import="java.util.List"%>
<%@page import="Modelo.Membresia"%>
<%@page import="Modelo.Socio"%>
<%@page import="Modelo.TipoMembresia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
List<Membresia> membresias = (List<Membresia>) request.getAttribute("membresias");
List<Socio> socios = (List<Socio>) request.getAttribute("socios");
List<TipoMembresia> tipos = (List<TipoMembresia>) request.getAttribute("tipos");
Membresia editar = (Membresia) request.getAttribute("membresiaEditar");
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>SIGA-Gym | Membresías</title>
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
            <a class="active" href="MembresiaServlet"><span class="material-symbols-rounded">workspace_premium</span>Membresías</a>
            <a href="AsistenciaServlet"><span class="material-symbols-rounded">login</span>Asistencia</a>
            <a href="ReporteServlet"><span class="material-symbols-rounded">bar_chart</span>Reportes</a>
        </nav>
        <a class="logout-link" href="LogoutServlet"><span class="material-symbols-rounded">logout</span>Salir</a>
    </aside>
    <main class="content">
        <div class="topbar">
            <div>
                <span class="eyebrow">Planes y vigencias</span>
                <h2>Membresías</h2>
                <p>Control de planes activos, vencidos e inactivos por socio.</p>
            </div>
        </div>

        <div class="panel mb-4 form-panel">
            <div class="panel-head">
                <div>
                    <span class="eyebrow"><%=editar != null ? "Edición de membresía" : "Nuevo plan"%></span>
                    <h4><%=editar != null ? "Actualizar membresía" : "Registrar membresía"%></h4>
                </div>
            </div>
            <form action="MembresiaServlet" method="post" class="row g-3">
                <input type="hidden" name="idMembresia" value="<%=editar != null ? editar.getIdMembresia() : ""%>">
                <div class="col-md-3">
                    <label class="form-label">Socio</label>
                    <select class="form-select" name="idSocio" required>
                        <% for(Socio s : socios){ %>
                        <option value="<%=s.getIdSocio()%>" <%=editar != null && editar.getIdSocio() == s.getIdSocio() ? "selected" : ""%>><%=s.getDni()%> - <%=s.getNombreCompleto()%></option>
                        <% } %>
                    </select>
                </div>
                <div class="col-md-3">
                    <label class="form-label">Plan</label>
                    <select class="form-select" name="idTipoMembresia" required>
                        <% for(TipoMembresia t : tipos){ %>
                        <option value="<%=t.getIdTipoMembresia()%>" <%=editar != null && editar.getIdTipoMembresia() == t.getIdTipoMembresia() ? "selected" : ""%>><%=t.getNombre()%> - S/ <%=t.getPrecio()%></option>
                        <% } %>
                    </select>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Inicio</label>
                    <input type="date" class="form-control" name="fechaInicio" value="<%=editar != null ? editar.getFechaInicio() : ""%>" required>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Fin</label>
                    <input type="date" class="form-control" name="fechaFin" value="<%=editar != null ? editar.getFechaFin() : ""%>" required>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Estado</label>
                    <select class="form-select" name="estado">
                        <option value="Activo" <%=editar != null && editar.getEstado().equals("Activo") ? "selected" : ""%>>Activo</option>
                        <option value="Vencido" <%=editar != null && editar.getEstado().equals("Vencido") ? "selected" : ""%>>Vencido</option>
                        <option value="Inactivo" <%=editar != null && editar.getEstado().equals("Inactivo") ? "selected" : ""%>>Inactivo</option>
                    </select>
                </div>
                <div class="col-12 pt-2">
                    <button class="btn btn-primary">
                        <span class="material-symbols-rounded">save</span>
                        <%=editar != null ? "Actualizar membresía" : "Registrar membresía"%>
                    </button>
                    <% if(editar != null){ %>
                    <a class="btn btn-dark" href="MembresiaServlet">
                        <span class="material-symbols-rounded">close</span>
                        Cancelar
                    </a>
                    <% } %>
                </div>
            </form>
        </div>

        <div class="panel">
            <div class="panel-head">
                <div>
                    <span class="eyebrow">Listado general</span>
                    <h4>Membresías registradas</h4>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table align-middle">
                    <thead>
                        <tr>
                            <th>Socio</th>
                            <th>DNI</th>
                            <th>Plan</th>
                            <th>Inicio</th>
                            <th>Fin</th>
                            <th>Precio</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Membresia m : membresias){ %>
                        <tr>
                            <td><%=m.getSocio()%></td>
                            <td><%=m.getDni()%></td>
                            <td><%=m.getTipoMembresia()%></td>
                            <td><%=m.getFechaInicio()%></td>
                            <td><%=m.getFechaFin()%></td>
                            <td>S/ <%=m.getPrecio()%></td>
                            <td><span class="badge <%=m.getEstado().equals("Activo") ? "text-bg-success" : "text-bg-secondary"%>"><%=m.getEstado()%></span></td>
                            <td>
                                <div class="action-group">
                                    <a class="btn btn-sm btn-outline-primary" href="MembresiaServlet?accion=editar&id=<%=m.getIdMembresia()%>">
                                        <span class="material-symbols-rounded">edit</span>
                                        Editar
                                    </a>
                                    <a class="btn btn-sm btn-outline-danger" href="MembresiaServlet?accion=eliminar&id=<%=m.getIdMembresia()%>">
                                        <span class="material-symbols-rounded">block</span>
                                        Inactivar
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