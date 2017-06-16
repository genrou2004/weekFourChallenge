

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Display
 */
@WebServlet("/Display")
public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    
        String nextURL = "/WEB-INF/jsp/displayProfile.jsp";
        String resumeID = request.getParameter("ResumeID");
       
      
        
        String query = "SELECT ResumeID,FirstName,LastName,EmailAddress, SchoolName, LevelOfEducation,WorkExperiance, LevelOfExperty, SkillSetName, LevelOfSkillSet FROM Resume ResumeID = '"+ resumeID + "'";
        try{
            
            Statement   stm = connection.createStatement();
            ResultSet  rs = stm.executeQuery(query);
        
            while(rs.next()){
                
                String CustomerDetail = rs.getString(1) + "\n" 
                                        +rs.getString(2) + " " +rs.getString(3) + "\n "
                                        + rs.getString(4) + "\n "
                                        + rs.getString(5) + " \n"
                                        + rs.getString(6) + " \n"
                                        + rs.getString(7) + " \n"
                                        + rs.getString(8) + "\n"
                                        + rs.getString(9) + "\n"
                                        + rs.getString(10);
                request.setAttribute("message", CustomerDetail);
            }
            response.sendRedirect("/WEB-INF/jsp/displayProfile.jsp");
            getServletContext().getRequestDispatcher(nextURL).forward(request,response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		doGet(request, response);
	}

}
