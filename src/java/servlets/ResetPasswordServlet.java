/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;

/**
 *
 * @author 799470
 */
public class ResetPasswordServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        
        if(uuid != null)
            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
        else
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        AccountService ac = new AccountService();
        
        if(uuid != null) {
            String newPassword = request.getParameter("newpassword");
            boolean res = ac.changePassword(uuid, newPassword);
            
            if(res)
                request.setAttribute("msg", "Your password has been successfully changed.");
            else
                request.setAttribute("msg", "Error in changing password. Please contact your administrator.");
            
            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);  
        }
        else {
            String email = request.getParameter("email");
            String path = getServletContext().getRealPath("/WEB-INF");
            String url = request.getRequestURL().toString();
            boolean res = ac.resetPassword(email, path, url);

            if(res)
                request.setAttribute("msg", "An e-mail has been sent to you with instructions on how to reset your password.");
            else
                request.setAttribute("msg", "E-mail sending not successful. Please contact your administrator.");

            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
