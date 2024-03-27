package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Model.UserModel;

/**
 * Servlet implementation class UpdateUserDetail
 */
@WebServlet("/UpdateUserDetail")
public class UpdateUserDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject update_data = new JSONObject(request.getParameter("update-user-data"));
		JSONObject response_json = new JSONObject();
		PrintWriter out = response.getWriter();
		if( request.getSession().getAttribute("current-user") == null ) {
			response_json.put("status", 401);
		}else {
			UserModel model = new UserModel();
			try {
				boolean isUploaded = model.UpdateUser(update_data);
				if( isUploaded ) {
					JSONObject current_user = model.GetUserById(update_data.getInt("user_id"));
					request.getSession().setAttribute("current-user", current_user);
					response_json.put("status", 200);
					out.print(response_json);
				}else {
					response_json.put("status", 500);
					out.print(response_json);
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
