package User;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import Model.UserModel;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LocalDate currentDate = LocalDate.now();
		String user_name = request.getParameter("user_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String profile = request.getParameter("profile");
		if( user_name.isEmpty() || password.isEmpty() || email.isEmpty() ) {
			response.sendRedirect("./signup.jsp?fail=Username or Email or Password Must Not Be Empty!");
		}else {
			UserModel model = new UserModel();
			JSONObject new_user = new JSONObject();
			new_user.put("user_name", user_name);
			new_user.put("email", email);
			new_user.put("profile", profile);
			new_user.put("password", password);
			new_user.put("date_register", currentDate.toString());
			try {
				boolean isRegister = model.RegisterNewUser(new_user);
				if( isRegister ) {
					response.sendRedirect("./login.jsp?success=Your Account is Successfully Created! Login here");
				}else {
					response.sendRedirect("./signup.jsp?fail=Email Aleady Exists");
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
