package servlet.assets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import admin.Model.Model;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Model model = new Model();
		JSONObject response_data = new JSONObject();
//		get app_id
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		if( request.getSession().getAttribute("current-admin") != null ) {
			try {
				boolean isDeleted = model.DeleteUser(user_id);
				System.out.print(isDeleted);
				if( isDeleted ) {
					response.getWriter().print(new JSONObject().put("status", 200));
				}else {
				response.getWriter().print(new JSONObject().put("status", "404"));
				}
			} catch (ClassNotFoundException | JSONException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			response_data.put("status", 401);
			response_data.put("message", "Unauthorized - Login First");
			response.getWriter().print(response_data);
		}
	}

}
