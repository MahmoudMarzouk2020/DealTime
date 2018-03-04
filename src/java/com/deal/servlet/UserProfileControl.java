/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deal.servlet;

import com.deal.base.control.CustomerDAO;
import com.deal.base.control.DbConn;
import com.deal.base.model.Customer;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ahmed_Mokhtar
 */
public class UserProfileControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
        Customer customer = new Customer();

        customer.setCustEmail("Ahmed@gmail.com");
        customer.setCustPassword("1a2b3c");
        customer.setCustFirstName("Ahmed");
        customer.setCustLastName("Mokhtar");
        customer.setCustAddress("Cairo");
        customer.setCustJob("Engineer");
        customer.setCustMobileNumber("0106633669");
        customer.setCustDateOfBirth(LocalDate.now());
        customer.setCustCreditLimit(1000.25);
        customer.setCustWishList("Soccer, Basketball, Swimming");
        
        request.setAttribute("currentCustomer", customer);
        request.getRequestDispatcher("WEB-INF/view/userProfileControlPanel.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean isValid = false;
        
        String firstNmae = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String job = request.getParameter("job");
        String address = request.getParameter("address");
        String credit = request.getParameter("credit");
        String mobileNumber = request.getParameter("mobileNumber");
        String email = request.getParameter("email");
        String interests = request.getParameter("interests");
        //Updating database with new values here.

        if (isValid) {
            //update customer info
            
            Customer updatedCustomer 
                    = new Customer(email, lastName, firstNmae, lastName, address, job, mobileNumber, LocalDate.MIN, 0, credit);
            
            CustomerDAO customer = new CustomerDAO((Connection) DbConn.getDbConn());
            customer.updateCustomer(updatedCustomer);
            
        } else {
            // do something else
            
        }
        
        //just for testing
        response.sendRedirect("index.html");

    }

    @Override
    public String getServletInfo() {
        return "User Profile";
    }

}
