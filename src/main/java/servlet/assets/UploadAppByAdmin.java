package servlet.assets;

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

import admin.Model.Model;

/**
 * Servlet implementation class UploadAppByAdmin
 */
@WebServlet("/UploadAppByAdmin")
public class UploadAppByAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadAppByAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject new_app = new JSONObject(request.getParameter("new-app"));
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Model upload = new Model();
		JSONObject response_data = new JSONObject();
		if( session.getAttribute("current-admin") == null ) {
			response_data.put("status", 401);
			response_data.put("message", "Unauthorized - Login First");
			out.print(response_data);
		}else {
			try {
				if( upload.insertNewApp(new_app) ) {
					response_data.put("status", 200);
					System.out.println(response_data);
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
