<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>SIGA-Gym | Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css2?family=Manrope:wght@400;500;600;700;800&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/main.css" rel="stylesheet">
</head>
<body class="login-body">
<div class="login-shell">
    <div class="login-card">
        <div class="login-brand">
            <div class="login-icon">
                <span class="material-symbols-rounded">fitness_center</span>
            </div>
            <div>
                <h1>SIGA-Gym</h1>
                <p>Control inteligente de asistencias y membresías</p>
            </div>
        </div>
        <% if(request.getAttribute("mensaje") != null){ %>
        <div class="alert alert-danger"><%=request.getAttribute("mensaje")%></div>
        <% } %>
        <form action="LoginServlet" method="post">
            <div class="mb-3">
                <label class="form-label">Usuario</label>
                <div class="input-shell">
                    <span class="material-symbols-rounded">person</span>
                    <input type="text" name="usuario" class="form-control" required autocomplete="off" placeholder="Ingrese su usuario">
                </div>
            </div>
            <div class="mb-4">
                <label class="form-label">Contraseña</label>
                <div class="input-shell">
                    <span class="material-symbols-rounded">lock</span>
                    <input type="password" name="clave" class="form-control" required placeholder="Ingrese su contraseña">
                </div>
            </div>
            <button class="btn btn-primary w-100">
                <span class="material-symbols-rounded">login</span>
                Ingresar al sistema
            </button>
        </form>
    </div>
    <div class="login-info">
        <span class="material-symbols-rounded">verified</span>
        <h2>Gestión rápida para gimnasios modernos</h2>
        <p>Administra socios, membresías, asistencias y reportes desde una interfaz limpia, clara y preparada para trabajo diario.</p>
    </div>
</div>
<script src="assets/js/app.js"></script>
</body>
</html>