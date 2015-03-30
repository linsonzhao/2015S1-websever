/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.ConnectionPool;

/**
 *
 * @author victor
 */
@WebServlet("/issubscribed")
public class IsSubscribed extends HttpServlet {
	
	private ConnectionPool cp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IsSubscribed() {
		super();
		// TODO Auto-generated constructor stub
		cp = ConnectionPool.getInstance();
	}
	
	
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/xml");
                
        final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
	final String DB_URL="jdbc:mysql://localhost:3306/videotracking";
        
    	final String USER = "root";
	final String PASS = "Admin123";

	response.setContentType("text/xml;charset=UTF-8");
	PrintWriter writer = response.getWriter();
	    
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        
	Connection conn = null;
	Statement stmt = null;
	    	    
	try{
	    Class.forName("com.mysql.jdbc.Driver");

//	    conn = DriverManager.getConnection(DB_URL,USER,PASS);
	    conn = cp.getVideotrackingConn();

	    stmt = conn.createStatement();
	    	
	    String sql = "SELECT CASE WHEN COUNT(*) = 0 THEN 'false' ELSE 'true'"
                    + " END AS value FROM `Subscription` "
                    + "WHERE animal_id = (SELECT id FROM `animal` WHERE `name` LIKE '" + request.getParameter("videoid")
                    + "') AND user_id = '" + request.getParameter("userid") + "';";
	    ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            
            writer.append("\n<video>\n"
                        + " <videoId>" + request.getParameter("videoid") + "</videoId>\n"
                        + " <isSubscribed>" + rs.getBoolean("value") 
                        + "</isSubscribed>\n"
                        + "</video>");
            
            out.println(writer);
	    
	    rs.close();
	    stmt.close();
	    conn.close();
	}catch(SQLException se){
	    se.printStackTrace();
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	    	if(stmt!=null)
                    stmt.close();
	    }catch(SQLException se2){ se2.printStackTrace(); }
	    try{
	    	if(conn!=null)
                    conn.close();
	    }catch(SQLException se){
	    	se.printStackTrace();
            }
	}    
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
