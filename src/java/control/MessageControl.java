/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import enity.Message;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Win 10
 */
@WebServlet(name = "MessageControl", urlPatterns = {"/MessageControl"})
public class MessageControl extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        response.setContentType("text/html;charset=UTF-8");
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String message = request.getParameter("message");
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("message", message);
//
            String errorName = "";
            String errorEmail = "";
            String errorMessage = "";
            int error = 0;
            message = message.trim();
            name = name.trim();
            email = email.trim();
            String name_form="^[a-zA-Z\\ ]{1,50}$";
            String email_form="^[a-zA-Z][a-zA-Z0-9-_]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,3}$";
            String message_form="^[a-zA-Z0-9-_\\ \\(\\)] {1,200}$";
            
            if (!name.matches(name_form)) {
                errorName += "Name is Invalid!";
                error++;
            }
            if (!email.matches(email_form)) {
                errorEmail += "Email is Invalid!";
                error++;
            }
            if (!message.matches(message_form)) {
                errorMessage += "Mesage is Invalid!";
                error++;
            }

            if (error == 0 && name != null && email != null && message != null) {
                Message m = new Message(name, email, message);              
                new DAO().insertMessage(m);
                String e = "Message is submitted!";
                request.removeAttribute("name");
                request.removeAttribute("email");
                request.removeAttribute("message");
                request.setAttribute("success", e);
                request.getRequestDispatcher("ContactControl").include(request, response);
            } else {
                request.setAttribute("errorName", errorName);
                request.setAttribute("errorEmail", errorEmail);
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("ContactControl").include(request, response);
            }
//String name_form="^[a-zA-Z ]{1,50}$";
            //String email_form="^[a-zA-z][a-zA-Z0-9-_]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,3}$";
            //String message_form="^[a-zA-Z0-9-_\\(\\ )]{1,200}$";
        } catch (Exception ex) {
            response.sendRedirect("errorpage.jsp");
        }
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
