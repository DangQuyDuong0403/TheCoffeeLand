/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Order;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.OrderDetail.OrderDetailDAO;
import model.OrderDetail.OrderDetailEntity;
import model.Orders.Orders;
import model.Orders.OrdersDAO;
import model.ProductSaler.ProductSaler;
import model.ProductSaler.ProductSalerDAO;

/**
 *
 * @author Admin
 */
public class OrderHistory extends HttpServlet {
   
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
            out.println("<title>Servlet OrderHistory</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderHistory at " + request.getContextPath () + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        PrintWriter pr = response.getWriter();
        OrdersDAO u = new OrdersDAO();
        String id = request.getParameter("orderId");
        Orders lst = u.getOrdersById(id);
        OrderDetailDAO od= new OrderDetailDAO();
        List<OrderDetailEntity> xOrderDetails= od.getListProductByOrderId(id);
        List<ProductSaler> listProductSalers= new ArrayList<>();
        ProductSalerDAO proDetail= new ProductSalerDAO();
        for(OrderDetailEntity x: xOrderDetails){
            listProductSalers.add(proDetail.getProductById(x.getProductId(), id));
        }
        session.setAttribute("lstProductDetail", listProductSalers);
        session.setAttribute("OrderDetail", lst);
        response.sendRedirect("/CoffeeLand/server/Order/OrderHistoryDetail.jsp");
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
        
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        String id = request.getParameter("userId");
        OrdersDAO od= new OrdersDAO();
        List<Orders> x= od.getListOrdersByUserId(id);
        session.setAttribute("customerId", id);
        session.setAttribute("lstOrder", x);
        response.sendRedirect("/CoffeeLand/server/Order/OrderHistory.jsp");
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
