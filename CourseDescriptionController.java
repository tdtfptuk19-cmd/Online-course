

package controllers;

import dal.CategoryDAO;
import dal.CourseDAO;
import dal.EnrollmentDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Account;
import models.Category;
import models.Course;

@WebServlet(name="CourseDescriptionController", urlPatterns={"/description"})
public class CourseDescriptionController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomeSerlvet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeSerlvet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categoryList = categoryDAO.getAllCategory();
        
        int id = Integer.parseInt(request.getParameter("id"));
        CourseDAO dao = new CourseDAO();
        Course course = dao.GetCourseById(id);
        
  
        Account acc = (Account) request.getSession().getAttribute("user");
        boolean enrolled = false; 

        if (acc != null) { 
            EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
            enrolled = enrollmentDAO.isEnrolled(acc.getAccountId(), id);
        }
        
        request.setAttribute("enrolled", enrolled);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("course", course);
        
        RequestDispatcher rd = request.getRequestDispatcher("/views/CourseDescription.jsp");
        rd.forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
 