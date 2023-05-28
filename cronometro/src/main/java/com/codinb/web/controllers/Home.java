package com.codinb.web.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

import com.coding.web.models.*;


/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Timer> timerList = new ArrayList<Timer>();
		
		if (request.getParameterMap().containsKey("start")) {
			//Timer timer = new Timer();
			String fecha = String.valueOf(request.getParameter("start"));
			session.setAttribute("inicio",fecha);
            response.sendRedirect("Home");
            
        }else if(request.getParameterMap().containsKey("stop")){
        	String fecha = String.valueOf(request.getParameter("stop"));
        	Date date;
        	String fecha_inicio = (String) session.getAttribute("inicio");
        	if(session.getAttribute("inicio") != null) {
        		try {
    				date = new SimpleDateFormat("MMMM dd, yyyy, HH:mm:ss a").parse(fecha);
    				Timer timer = new Timer();
    				timer.setInicio(new SimpleDateFormat("MMMM dd, yyyy, HH:mm:ss a").parse(fecha_inicio));
    				timer.setFin(date);
    				
    				if(session.getAttribute("listTimer") != null) {
    					timerList = (ArrayList<Timer>) session.getAttribute("listTimer");
        				timerList.add(timer);
        				session.removeAttribute("inicio");
        				session.setAttribute("listTimer",timerList);
    				}else {
    					ArrayList<Timer> timerLista = new ArrayList<Timer>();
    					timerLista.add(timer);
    					session.removeAttribute("inicio");
        				session.setAttribute("listTimer",timerLista);
    				}
    				
    			} catch (ParseException e) {
    				e.printStackTrace();
    			}  
    			
        	}
			
            response.sendRedirect("Home");
        	
        }else if(request.getParameterMap().containsKey("reset")) {
        	session.removeAttribute("inicio");
        	   response.sendRedirect("Home");
        }
        else {
        		timerList = (ArrayList<Timer>) session.getAttribute("listTimer");
        		String fechaInicio = (String) session.getAttribute("inicio");
        		if(session.getAttribute("inicio") != null) {
        			try {
						Date date = new SimpleDateFormat("MMMM dd, yyyy, HH:mm:ss a").parse(fechaInicio);
						request.setAttribute("fechaInicio",getTimeFormato(date));
						SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy, HH:mm:ss a");  
					    Date dateCurrent = new Date(); 
					    request.setAttribute("fechaCurrent",getTimeFormato(dateCurrent));
					    request.setAttribute("runningTime",getRunningTime(date,dateCurrent));
					} catch (ParseException e) {
						e.printStackTrace();
					}
        			
        		}
        		request.setAttribute("listTimer", timerList);
        	
        	RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/Index.jsp");
            view.forward(request, response);
        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public String getTimeFormato(Date date) {
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss a");
		return  localDateFormat.format(date);
	}
	public String getRunningTime(Date dateInicio, Date dateFin) {
		long total = (dateFin.getTime() - dateInicio.getTime())/1000;
		int minutes =(int) Math.floor((total) / 60);
		int seconds = (int)Math.floor(total %60);
		return minutes + "m "+ seconds+"s";
	}

}
