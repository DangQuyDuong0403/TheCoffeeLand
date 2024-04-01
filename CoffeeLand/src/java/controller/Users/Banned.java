/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Users;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Users.Users;
import model.Users.UsersDAO;

/**
 *
 * @author Admin
 */
public class Banned extends HttpServlet {
   
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
            out.println("<title>Servlet Banned</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Banned at " + request.getContextPath () + "</h1>");
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
        doPost(request, response);
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
    throws ServletException, IOException  {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
//        PrintWriter pr = response.getWriter();
        UsersDAO u = new UsersDAO();
        int PPP=7;
        int noPage= u.CountBannedUsers()/PPP+1;
        int page=1;

        String xpage = request.getParameter("page");
        if(xpage!= null){
            page=Integer.parseInt(xpage);
        }
        session.setAttribute("currentPage", page);
        session.setAttribute("totalPages", noPage);
        List<Users> lst = u.getListBannedUsers(page, PPP);
        List<Users> lstU = u.getListUsers(1, PPP);
        session.setAttribute("lstUsers", lstU);
        session.setAttribute("lstBanned", lst);
        List<Users> lstRole = u.getListRoleName();
        session.setAttribute("lstRoles", lstRole);
        response.sendRedirect("/CoffeeLand/server/Users/BannedAccount.jsp");
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
