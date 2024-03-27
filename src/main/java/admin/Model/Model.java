package admin.Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;

import Model.AppModel;

public class Model extends AppModel {
	
//	get json data from the admin and insert into app table 
// parems: app_data<JSONObject>
	public boolean insertNewApp(JSONObject new_app) throws ClassNotFoundException, SQLException {
		Connection connect = connect();
		PreparedStatement query = connect.prepareStatement("insert into app("
				+ "app_name,"
				+ "icon,"
				+ "category,"
				+ "download_link,"
				+ "description,"
				+ "features,"
				+ "system_require,"
				+ "release_date"
				+ ") "
				+ "values('"
				+ new_app.getString("app_name") 
				+ "','"
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
//	update app 
	public boolean UpdateApp(JSONObject update_data) throws ClassNotFoundException, SQLException {
		Connection connect = connect();
		String data_string = "app_name='"
				+ update_data.getString("app_name")
				+ "',download_link='"
				+ update_data.getString("download_link")
				+ "',features='"
				+ update_data.getString("features")
				+ "',system_require='"
				+ update_data.getString("system_require")
				+ "',release_date='"
				+ update_data.getString("release_date")
				+ "'";
		PreparedStatement query = connect.prepareStatement("update app set " + data_string + " where app_id="+update_data.getInt("app_id"));
		if( query.executeUpdate() > 0 ) {
			query.close();
			connect.close();
			return true;
		}
		query.close();
		connect.close();
		return false;
	}
	// update user
	
	public boolean UpdateUser(JSONObject update_data) throws ClassNotFoundException, SQLException {
		Connection connect = connect();
		String data_string = "user_name='"
				+ update_data.getString("user_name")
				+ "',email='"
				+ update_data.getString("email")
				+ "',password='"
				+ update_data.getString("password")
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
//	delete app by id
	
	public boolean DeleteApp(int app_id) throws SQLException, ClassNotFoundException {
		Connection connect = connect();
		PreparedStatement query = connect.prepareStatement("delete from app where app_id="+ app_id);
		if( query.executeUpdate() > 0) {
			query.close();
			connect.close();
			return true;
		}
		query.close();
		connect.close();
		return false;
	}
	
//	delete user by id
	public boolean DeleteUser(int user_id) throws SQLException, ClassNotFoundException {
		Connection connect = connect();
		PreparedStatement query = connect.prepareStatement("delete from user where user_id="+ user_id);
		if( query.executeUpdate() > 0) {
			query.close();
			connect.close();
			return true;
		}
		query.close();
		connect.close();
		return false;
	}
//	reterive contects datas 
	public ArrayList<JSONObject> GetContects() throws SQLException, ClassNotFoundException{
		ArrayList<JSONObject> contects = new ArrayList<JSONObject>();
		Connection connect = connect();
		PreparedStatement query = connect.prepareStatement("select * from contects ORDER BY user_id DESC");
		ResultSet data = query.executeQuery();
		while( data.next() ) {
			JSONObject contect = new JSONObject();
			contect.put("contect_id", data.getInt("contect_id"));
			contect.put("user_id", data.getInt("user_id"));
			contect.put("user_name", data.getString("user_name"));
			contect.put("email", data.getString("email"));
			contect.put("message", data.getString("message"));
			
			contects.add(contect);
		}
		
		return contects;
	}
//	delete contect 
	public boolean DeleteContect(int contect_id) throws SQLException, ClassNotFoundException {
		Connection connect = connect();
		PreparedStatement query = connect.prepareStatement("delete from contects where contect_id="+ contect_id);
		if( query.executeUpdate() > 0) {
			query.close();
			connect.close();
			return true;
		}
		query.close();
		connect.close();
		return false;
	}
//	reterive users
	public ArrayList<JSONObject> GetAllUsers() throws SQLException, ClassNotFoundException{
		ArrayList<JSONObject> users = new ArrayList<JSONObject>();
		Connection connect = connect();
		PreparedStatement query = connect.prepareStatement("select * from user ORDER BY user_id DESC");
		ResultSet data = query.executeQuery();
		while( data.next() ) {
			JSONObject user = new JSONObject();
			user.put("user_id", data.getInt("user_id"));
			user.put("user_name", data.getString("user_name"));
			user.put("profile", data.getString("profile"));
			user.put("email", data.getString("email"));
			user.put("password", data.getString("password"));
			user.put("date_register", data.getString("date_register"));
			
			users.add(user);
		}
		
		return users;
	}
}
