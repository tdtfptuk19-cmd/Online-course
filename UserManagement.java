

package controllers;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Account;


@WebServlet(name="UserManagement", urlPatterns={"/UserManagement"})
public class UserManagement extends HttpServlet {
   
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
            out.println("<title>Servlet UseManagement</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UseManagement at " + request.getContextPath () + "</h1>");
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
    private void loadAndForward(HttpServletRequest request, HttpServletResponse response,
                                AccountDAO dao, Account selected, String mode)
            throws ServletException, IOException {
        List<Account> users = dao.listUserAndStaff();
        request.setAttribute("users", users);
        request.setAttribute("selected", selected);
        request.setAttribute("mode", mode);
        request.getRequestDispatcher("/views/UserManagement.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO dao = new AccountDAO();
        String idParam = request.getParameter("id");
        Account selected = null;
        String mode = "create";

        if (idParam != null && !idParam.isBlank()) {
            try {
                int id = Integer.parseInt(idParam);
                selected = dao.getById(id);
                if (selected != null) mode = "update";
            } catch (NumberFormatException ignored) {}
        }
        loadAndForward(request, response, dao, selected, mode);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        AccountDAO dao = new AccountDAO();

        String action = request.getParameter("action"); // "create" | "update" | "delete"
        Account selected = null;
        String mode = "create";

        try {
            if ("delete".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.deleteAccountById(id);
       
                selected = null;
                mode = "create";

            } else if ("create".equalsIgnoreCase(action)) {
 
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String fullname = request.getParameter("fullname");
                String email    = request.getParameter("email");
                Account a = new Account();
                a.setUsername(username);
                a.setPassword(password);
                a.setFullName(fullname);
                a.setEmail(email);

                dao.InsertAccout(a);

                selected = null;
                mode = "create";

            } else if ("update".equalsIgnoreCase(action)) {
                int accountId   = Integer.parseInt(request.getParameter("account_id"));
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String fullname = request.getParameter("fullname");
                String email    = request.getParameter("email");

                Account a = new Account();
                a.setAccountId(accountId);
                a.setUsername(username);
                a.setPassword(password);
                a.setFullName(fullname);
                a.setEmail(email);
                dao.updateAccountInfo(a);


                selected = dao.getById(accountId);
                mode = (selected != null) ? "update" : "create";

            } else {

                String idParam = request.getParameter("id");
                if (idParam != null && !idParam.isBlank()) {
                    int id = Integer.parseInt(idParam);
                    selected = dao.getById(id);
                    if (selected != null) mode = "update";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadAndForward(request, response, dao, selected, mode);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
