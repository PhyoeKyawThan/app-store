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
 * Servlet implementation class DeleteContect
 */
@WebServlet("/DeleteContect")
public class DeleteContect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteContect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Model model = new Model();
//		get app_id
		int contect_id = Integer.parseInt(request.getParameter("contect_id"));
		if( request.getSession().getAttribute("current-admin") != null ) {
			try {
				boolean isDeleted = model.DeleteContect(contect_id);
				if( isDeleted ) {
					response.sendRedirect("./admin/user_contects?message=Successfully Deleted");
				}else {
					response.sendRedirect("./admin/user_contects?message=Fail To Delete");
				}
			} catch (ClassNotFoundException | JSONException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			response.sendRedirect("./admin");
		}
	}

}
