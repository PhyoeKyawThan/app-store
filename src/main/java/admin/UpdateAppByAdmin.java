package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import admin.Model.Model;

/**
 * Servlet implementation class UpdateAppByAdmin
 */
@WebServlet("/UpdateAppByAdmin")
public class UpdateAppByAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAppByAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		JSONObject update_data = new JSONObject(request.getParameter("update-data"));
		JSONObject response_data = new JSONObject();
		if( session.getAttribute("current-admin") != null ) {
			Model model = new Model();
			try {
				PrintWriter out = response.getWriter();
				boolean isUpdated = model.UpdateApp(update_data);
				if( isUpdated ) {
					response_data.put("status", 200);
					out.print(response_data);
				}else {
					response_data.put("status", 400);
					out.print(response_data);
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
