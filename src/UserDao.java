
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserDao
 */
@WebServlet("/insertPersonalDetails")
public class UserDao extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Connection connection;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDao() {
        super();
        connection = ConnectionManager.getConnection();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addProfile.jsp");
       requestDispatcher.forward(request, response);*/

       
       String resumeid = request.getParameter("resumeid");
       String firstName = request.getParameter("firstName");
       String lastName =(request.getParameter("lastname"));
       String email = request.getParameter("email");
       String schoolName = request.getParameter("schoolname");
       String educationLevel =request.getParameter("educationlevel");
       String experience = request.getParameter("experience");
       String expertLevel =  request.getParameter("experty");
       String skillSetName =  request.getParameter("skillsetname");
       String skillLevel =  request.getParameter("skillsetlevel");
       PrintWriter out = response.getWriter();
       if(firstName == null || lastName == null ){
           
           
           out.print("please enter the correct information");
       }else{
           out.print("Recored inserted to the database successfully!");
       }
       

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into Resume(ResumeID,FirstName,LastName,EmailAddress, SchoolName, LevelOfEducation,WorkExperiance, LevelOfExperty, SkillSetName, LevelOfSkillSet) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, resumeid );
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3,lastName);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, schoolName);
            preparedStatement.setString(6, educationLevel);
            preparedStatement.setString(7, experience);
            preparedStatement.setString(8, expertLevel);
            preparedStatement.setString(9, skillSetName);
            preparedStatement.setString(10, skillLevel);
            preparedStatement.executeUpdate();
            // conn.commit();
           /* String msg = "Record has been inserted";
            HttpSession session = null;
            session.setAttribute("msg", msg);
            response.sendRedirect("profile.jsp");*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
       // response.sendRedirect("/WEB-INF/jsp/addEducation.jsp");
        doGet(request, response);
     
    }
   
}
