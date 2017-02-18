/*
 * Create a MVC2 structured JSP/Servlet stack
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbevans.bookingrate.*;
import com.rbevans.bookingrate.Rates.HIKE;
import javax.servlet.annotation.WebServlet;
import java.util.Calendar;
import javax.servlet.*;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import beans.*;


/**
 *
 * @author Nicholas Shefte
 */

@WebServlet(name = "NShefte_HW10", urlPatterns = {"/NShefte_HW10"})
public class HW_Controller extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        RatesBean outQuote = new RatesBean();
        // Login login = (Login) session.getAttribute(LOGIN);
      
        
        
        String tour = null;
        String raw_day = null;
        String raw_month = null;
        String raw_year = null;
        String raw_dur = null;
        String raw_party = null;
        
        int day = -1;
        int month = -1;
        int year = -1;
        int dur = -1;
        int party = -1;
        
        String valid = null;
        Rates quote = null;
            
            tour = request.getParameter("tours");
            raw_day = request.getParameter("day");  
            raw_month = request.getParameter("month");
            raw_year = request.getParameter("year");
            raw_dur = request.getParameter("duration");
            raw_party = request.getParameter("party");
            
            try{
                day = Integer.parseInt(raw_day);
                month = Integer.parseInt(raw_month);
                year = Integer.parseInt(raw_year);
                dur = Integer.parseInt(raw_dur);
                party = Integer.parseInt(raw_party);
            }catch(NumberFormatException e){
                valid = "Error: Not a number\n" + e;
            }
            
            valid = validate(tour, day, month, year, dur, party);
            
            if(valid == null){
                quote = calcQuote(tour, day, month, year, dur);
                outQuote.setCost(quote.getCost());
                outQuote.setNormalDays(quote.getNormalDays());
                outQuote.setPremiumDays(quote.getPremiumDays());
                if(!quote.isValidDates()){
                    outQuote.setDetails(quote.getDetails());
                }
            }
            
            session.setAttribute("outQuote", outQuote);
            RequestDispatcher rd = servletContext.getRequestDispatcher(
                "/Quote.jsp");
            rd.forward(request, response);
            
    }
    
    /**
     * Validate user-submitted values
     * @param tour
     * @param day
     * @param month
     * @param year
     * @param dur
     * @param party
     * @return null if data valid, else return error message
     */
    private String validate(String tour, int day, int month,
            int year, int dur, int party){
        
        String valid = null;
        int intTour = -1;
        boolean flag = false;
        Calendar now = Calendar.getInstance();
        
        final String[] ALL_TOURS = {
                "Gardiner_Lake", 
                "Hellroaring_Plateau", 
                "The_Beaten_Path"
            };      

        final int[][] ALL_DURATIONS = {
                {3,5},
                {2,3,4},
                {5,7}
            }; 

        //Check party size
        if(party < 1 || party > 10){
            valid = valid + "[Error: Invalid Party Size]";
        }
        
        //Check tour
        flag = false;
        for(int i = 0; i < ALL_TOURS.length; i++){
            if(tour.equals(ALL_TOURS[i])){
                flag = true;
                intTour = i;
            }
        }
        if(flag == false){
            valid = valid + "[Error: Invalid Tour]";
            return valid; //Critical Error, exit method
        }
        
        //Check duration
        flag = false;
        for(int i = 0; i < ALL_DURATIONS[intTour].length; i++){
            if(dur == ALL_DURATIONS[intTour][i]){
                flag = true;  
            }
        }
        if(flag == false){
            valid = valid + "[Error: Invalid Duration for "
                    + tour + "]";
            return valid; //Critical Error, exit method
        }
        
        //Check date
        if(year < now.get(Calendar.YEAR)){
            valid = valid + "[Error: Invalid Year]";
        }
        if(month < 1 || month > 12){
            valid = valid + "[Error: Invalid Month]";
        }
        if(month == 4 || month == 6 || month == 8 || month == 11){
            if(day < 1 || day > 30){
                valid = valid + "[Error: Invalid Day]";
            }
        }else if(month == 2){
            if(year % 4 == 0){
                if(day < 1 || day > 29){
                    valid = valid + "[Error: Invalid Day]";
                }
            }else{
                if(day < 1 || day > 28){
                    valid = valid + "[Error: Invalid Day]";
                }
            }
        }else{
            if(day < 1 || day > 31){
                valid = valid + "[Error: Invalid Day]";
            }
        }
        
        return valid;
        
    }
    
    /**
     * Calculate the rate to quote to customer
     * @param tour
     * @param day
     * @param month
     * @param year
     * @param dur
     * @return 
     */
    private Rates calcQuote(String tour, int day, int month, 
            int year, int dur){
        
        Rates quote = null;
        BookingDay startDate = new BookingDay(year, month, day); 
        
        switch(tour){
            case "Gardiner_Lake":
                quote = new Rates(HIKE.GARDINER);
                break;
            case "Hellroaring_Plateau":
                quote = new Rates(HIKE.HELLROARING);
                break;
            case "The_Beaten_Path":
                quote = new Rates(HIKE.BEATEN);
                break;
            default:
                //Critical Error
                return quote;  //equals null
        }
        
        quote.setBeginDate(startDate);
        quote.setDuration(dur);
        
        
        return quote;
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