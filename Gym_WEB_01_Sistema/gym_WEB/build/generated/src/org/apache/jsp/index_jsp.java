package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"es\">\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<title>SIGA-Gym | Login</title>\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("<link href=\"https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap\" rel=\"stylesheet\">\n");
      out.write("<link href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded\" rel=\"stylesheet\">\n");
      out.write("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("<link href=\"assets/css/main.css\" rel=\"stylesheet\">\n");
      out.write("</head>\n");
      out.write("<body class=\"login-body\">\n");
      out.write("<div class=\"login-card\">\n");
      out.write("    <div class=\"login-icon\">\n");
      out.write("        <span class=\"material-symbols-rounded\">fitness_center</span>\n");
      out.write("    </div>\n");
      out.write("    <h1>SIGA-Gym</h1>\n");
      out.write("    <p>Control de asistencias y membresías</p>\n");
      out.write("    ");
 if(request.getAttribute("mensaje") != null){ 
      out.write("\n");
      out.write("    <div class=\"alert alert-danger py-2\">");
      out.print(request.getAttribute("mensaje"));
      out.write("</div>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("    <form action=\"LoginServlet\" method=\"post\">\n");
      out.write("        <div class=\"mb-3\">\n");
      out.write("            <label class=\"form-label\">Usuario</label>\n");
      out.write("            <input type=\"text\" name=\"usuario\" class=\"form-control\" required autocomplete=\"off\">\n");
      out.write("        </div>\n");
      out.write("        <div class=\"mb-3\">\n");
      out.write("            <label class=\"form-label\">Contraseña</label>\n");
      out.write("            <input type=\"password\" name=\"clave\" class=\"form-control\" required>\n");
      out.write("        </div>\n");
      out.write("        <button class=\"btn btn-primary w-100\">Ingresar</button>\n");
      out.write("    </form>\n");
      out.write("</div>\n");
      out.write("<script src=\"assets/js/app.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
