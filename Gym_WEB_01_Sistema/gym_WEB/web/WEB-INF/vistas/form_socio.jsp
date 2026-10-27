<%@page import="Modelo.Socio"%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Socio s = (Socio) request.getAttribute("socio");
boolean editar = s != null;
String fecha = editar ? s.getFechaRegistro().toString() : new Date(System.currentTimeMillis()).toString();
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>SIGA-Gym | Socio</title>
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
                <span class="eyebrow">Ficha del socio</span>
                <h2><%=editar ? "Editar socio" : "Registrar socio"%></h2>
                <p>Completa los datos principales para mantener actualizado el registro.</p>
            </div>
            <a class="btn btn-dark" href="SocioServlet">
                <span class="material-symbols-rounded">arrow_back</span>
                Volver
            </a>
        </div>

        <div class="panel form-panel">
            <div class="panel-head">
                <div>
                    <span class="eyebrow"><%=editar ? "Actualización" : "Nuevo registro"%></span>
                    <h4>Datos personales</h4>
                </div>
            </div>
            <form action="SocioServlet" method="post" class="row g-3">
                <input type="hidden" name="accion" value="<%=editar ? "actualizar" : "guardar"%>">
                <input type="hidden" name="idSocio" value="<%=editar ? s.getIdSocio() : ""%>">
                <div class="col-md-4">
                    <label class="form-label">DNI</label>
                    <input class="form-control" name="dni" value="<%=editar ? s.getDni() : ""%>" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Nombres</label>
                    <input class="form-control" name="nombres" value="<%=editar ? s.getNombres() : ""%>" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Apellidos</label>
                    <input class="form-control" name="apellidos" value="<%=editar ? s.getApellidos() : ""%>" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Teléfono</label>
                    <input class="form-control" name="telefono" value="<%=editar ? s.getTelefono() : ""%>" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Correo</label>
                    <input class="form-control" name="correo" value="<%=editar ? s.getCorreo() : ""%>" required>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Registro</label>
                    <input type="date" class="form-control" name="fechaRegistro" value="<%=fecha%>" required>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Estado</label>
                    <select class="form-select" name="estado">
                        <option value="Activo" <%=editar && s.getEstado().equals("Activo") ? "selected" : ""%>>Activo</option>
                        <option value="Inactivo" <%=editar && s.getEstado().equals("Inactivo") ? "selected" : ""%>>Inactivo</option>
                    </select>
                </div>
                <div class="col-12 pt-2">
                    <button class="btn btn-primary">
                        <span class="material-symbols-rounded">save</span>
                        <%=editar ? "Actualizar" : "Guardar"%>
                    </button>
                </div>
            </form>
        </div>
    </main>
</div>
<script src="assets/js/app.js"></script>
</body>
</html>