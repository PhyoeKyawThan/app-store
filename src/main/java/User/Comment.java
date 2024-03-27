package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import Model.UserModel;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/Comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject comment = new JSONObject(request.getParameter("comment"));
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		UserModel model = new UserModel();
		JSONObject response_data = new JSONObject();
		if( session.getAttribute("current-user") == null ) {
			response_data.put("status", 401);
			response_data.put("message", "Unauthorized - Login First");
			out.print(response_data);
		}else {
			Object user_data =  request.getSession().getAttribute("current-user");
			JSONObject current_user = (JSONObject) user_data;
			comment.put("user_id", current_user.getInt("user_id"));
			try {
				if( model.InsertNewComment(comment) ) {
					response_data.put("status", 200);
					out.print(response_data);
				}else {
					response_data.put("status", 500);
					out.print(response_data);
				}
			} catch (ClassNotFoundException | JSONException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
