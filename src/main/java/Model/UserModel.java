package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class UserModel extends AppModel{
	public boolean RegisterNewUser(JSONObject new_user) throws ClassNotFoundException, SQLException {
		Connection connect = connect();
		PreparedStatement checkExist = connect.prepareStatement("select count(*) from user where email='" + new_user.getString("email") +  "'");
		ResultSet result = checkExist.executeQuery();
		if( result.next() ) {
			int count = result.getInt(1);
			if( count > 0 ) {
				return false;
			}else {
				PreparedStatement query = connect.prepareStatement("insert into user("
						+ "user_name,"
						+ "profile,"
						+ "email,"
						+ "password,"
						+ "date_register"
						+ ") "
						+ "values('"
						+ new_user.getString("user_name") 
						+ "','"
						+ new_user.getString("profile") 
						+ "','"
						+ new_user.getString("email") 
						+ "','"
						+ new_user.getString("password") 
						+ "','"
						+ new_user.getString("date_register")
						+ "')");
				if( query.executeUpdate() > 0 ) {
					query.close();
					connect.close();
					return true;
				}
				query.close();
				connect.close();
				return false;
			}
		}
		return false;
		
	}
	
//	update user 
	public boolean UpdateUser(JSONObject update_data) throws ClassNotFoundException, SQLException {
		Connection connect = connect();
		String data_string = "user_name='"
				+ update_data.getString("user_name")
				+ "',profile='"
				+ update_data.getString("profile")
				+ "'";
		PreparedStatement query = connect.prepareStatement("update user set " + data_string + " where user_id="+update_data.getInt("user_id"));
		if( query.executeUpdate() > 0 ) {
			query.close();
			connect.close();
			return true;
		}
		query.close();
		connect.close();
		return false;
	}
	
	//comment
	public boolean InsertNewComment(JSONObject comment) throws ClassNotFoundException, SQLException {
		Connection connect = connect();
		PreparedStatement query = connect.prepareStatement("insert into comment("
				+ "user_id,"
				+ "app_id,"
				+ "text) values('"
				+ comment.getInt("user_id") 
				+ "',"
				+ comment.getInt("app_id")
				+ ",'"
				+ comment.getString("text")  
				+ "')");
		if( query.executeUpdate() > 0 ) {
			query.close();
			connect.close();
			return true;
		}
		query.close();
		connect.close();
		return false;
	}
	// contect upload to contact table
	public boolean UploadContect(JSONObject contect) throws JSONException, SQLException, ClassNotFoundException {
		Connection connect = connect();
		PreparedStatement query = connect.prepareStatement("insert into contects("
				+ "email,"
				+ "message,"
				+ " user_name) "
				+ "values('"
				+ contect.getString("email")
				+ "','"
				+ contect.getString("message") 
				+ "','"
				+ contect.getString("user_name")
				+ "')");
		System.out.println(contect.getInt("user_id"));
		if( contect.getInt("user_id") > 0 ) {
			query = connect.prepareStatement("insert into contects("
					+ "user_id,"
					+ "email,"
					+ "message,"
					+ " user_name) "
					+ "values("
					+ contect.getInt("user_id")
					+ ",'"
					+ contect.getString("email")
					+ "','"
					+ contect.getString("message") 
					+ "','"
					+ contect.getString("user_name")
					+ "')");
		}
		if( query.executeUpdate() > 0 ) {
			query.close();
			connect.close();
			return true;
		}
		query.close();
		connect.close();
		return false;
	}
//	 upload app by user
	public boolean UploadApp(JSONObject new_app) throws ClassNotFoundException, SQLException {
		Connection connect = connect();
		PreparedStatement query = connect.prepareStatement("insert into app("
				+ "app_name,"
				+ "developer_id,"
				+ "icon,"
				+ "category,"
				+ "download_link,"
				+ "description,"
				+ "features,"
				+ "system_require,"
				+ "release_date"
				+ ",repo) "
				+ "values('"
				+ new_app.getString("app_name") 
				+ "',"
				+ new_app.getInt("developer_id")
				+ ",'"
				+ new_app.getString("icon") 
				+ "','"
				+ new_app.getString("category") 
				+ "','"
				+ new_app.getString("download_link") 
				+ "','"
				+ new_app.getString("description") 
				+ "','"
				+ new_app.getString("features") 
				+ "','"
				+ new_app.getString("system_require") 
				+ "','"
				+ new_app.getString("release_date") 
				+ "','"
				+ new_app.getString("repo") 
				+ "')");
		if( query.executeUpdate() > 0 ) {
			query.close();
			connect.close();
			return true;
		}
		query.close();
		connect.close();
		return false;
	}
	
//	check user is correct or not
	public JSONObject isUser(String email, String password) throws SQLException, ClassNotFoundException {
		Connection connect = connect();
		PreparedStatement checkExist = connect.prepareStatement("select * from user where email=? and password=?");
		checkExist.setString(1, email);
		checkExist.setString(2, password);
		JSONObject user = new JSONObject();
		ResultSet result = checkExist.executeQuery();
		if( result.next() ) {
			user.put("user_id", result.getInt("user_id"));
			user.put("user_name", result.getString("user_name"));
			user.put("profile", result.getString("profile"));
			user.put("email", result.getString("email"));
		}
		checkExist.close();
		connect.close();
		return user;
	}
	
	public ArrayList<JSONObject> GetUserUploadedApps(int developer_id) throws SQLException, ClassNotFoundException{
		ArrayList<JSONObject> apps = new ArrayList<JSONObject>();
		Connection connect = connect();
		PreparedStatement query = connect.prepareStatement("select * from app where developer_id=? ORDER BY app_id DESC");
		query.setInt(1, developer_id);
		ResultSet data = query.executeQuery();
		while( data.next() ) {
			JSONObject app = new JSONObject();
			app.put("app_id", data.getInt("app_id"));
			app.put("app_name", data.getString("app_name"));
			app.put("icon", data.getString("icon"));
			app.put("category", data.getString("category"));
			app.put("developer_id", data.getInt("developer_id"));
			app.put("download_link", data.getString("download_link"));
			app.put("description", data.getString("description"));
			app.put("features", data.getString("features"));
			app.put("system_requirie", data.getString("system_require"));
			app.put("release_date", data.getString("release_date"));
			
			apps.add(app);
		}
		query.close();
		connect.close();
		return apps;
	}
	
	public JSONObject GetUserById(int user_id) throws SQLException, ClassNotFoundException {
		Connection connect = connect();
		PreparedStatement query = connect.prepareStatement("select * from user where user_id=?");
		query.setInt(1, user_id);
		ResultSet data = query.executeQuery();
		JSONObject user = new JSONObject();
		while( data.next() ) {
			user.put("user_id", data.getInt("user_id"));
			user.put("user_name", data.getString("user_name"));
			user.put("profile", data.getString("profile"));
			user.put("email", data.getString("email"));
		}
		query.close();
		connect.close();
		return user;
	}
	
	public ArrayList<JSONObject> GetAllComment(int app_id) throws ClassNotFoundException, SQLException{
		ArrayList<JSONObject> comments = new ArrayList<JSONObject>();
		Connection connect = connect();
		PreparedStatement query = connect.prepareStatement(" select comment.comment_id, user.user_id, user.user_name, user.profile, comment.text from app, user, comment where app.app_id=? and comment.user_id=user.user_id and comment.app_id=app.app_id ORDER BY comment_id DESC");
		query.setInt(1, app_id);
		ResultSet data = query.executeQuery();
		while( data.next() ) {
			JSONObject comment = new JSONObject();
			comment.put("comment_id", data.getInt("comment_id"));
			comment.put("user_id", data.getInt("user_id"));
			comment.put("user_name", data.getString("user_name"));
			comment.put("profile", data.getString("profile"));
			comment.put("text", data.getString("text"));
			comments.add(comment);
		}
		query.close();
		connect.close();
		return comments;
	}
}
