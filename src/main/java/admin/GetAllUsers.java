package admin;

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

import Model.AppModel;
import admin.Model.Model;

/**
 * Servlet implementation class GetAllUsers
 */
@WebServlet("/GetAllUsers")
public class GetAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONObject response_json = new JSONObject();
		if( request.getSession().getAttribute("current-admin") != null ) {
			Model model = new Model();
			try {
				response_json.put("users", model.GetAllUsers());
				out.print(response_json);
			} catch (JSONException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
		response_json.put("status", 401);
		out.print(response_json);
		}
	}

}
