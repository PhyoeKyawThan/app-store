package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import Model.UserModel;

/**
 * Servlet implementation class ContectUs
 */
@WebServlet("/ContectUs")
public class ContectUs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContectUs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user_name = request.getParameter("user_name");
		String email = request.getParameter("email");
		String message = request.getParameter("message");
		JSONObject contect = new JSONObject();
		contect.put("user_name", user_name);
		contect.put("email", email);
		contect.put("message", message);
		UserModel model = new UserModel();
		if( request.getSession().getAttribute("current-user") == null ) {
			contect.put("user_id", 0);
		}else {
			Object user_data = request.getSession().getAttribute("current-user") ;
			JSONObject current_user = (JSONObject) user_data;
			contect.put("user_id", current_user.getInt("user_id"));
		}
		try {
			if( model.UploadContect(contect) ) {
				response.sendRedirect("./contact_us.jsp?message=Success");
			}else {
				response.sendRedirect("./contact_us.jsp?message=Fail");
			}
		} catch (ClassNotFoundException | JSONException | SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
