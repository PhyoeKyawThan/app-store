package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;

public class AppModel {
	private Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp", "domak", "domak");
		return connect;
	}
	
	
//	get all app data from db
	public ArrayList<JSONObject> GetAllApps() throws SQLException, ClassNotFoundException{
		ArrayList<JSONObject> apps = new ArrayList<JSONObject>();
		Connection connect = connect();
		PreparedStatement query = connect.prepareStatement("select * from app ORDER BY app_id DESC");
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
		
		return apps;
	}
	
//	get app by id
	public JSONObject GetAppById(int APP_ID) throws ClassNotFoundException, SQLException {
		JSONObject app = new JSONObject();
		Connection connect = connect();
		PreparedStatement query = connect.prepareStatement("select * from app where app_id=" + APP_ID);
		ResultSet data = query.executeQuery();
		while( data.next() ) {
			app.put("app_id", data.getInt("app_id"));
			app.put("app_name", data.getString("app_name"));
			app.put("icon", data.getString("icon"));
			app.put("category", data.getString("category"));
			app.put("developer_id", data.getInt("developer_id"));
			app.put("download_link", data.getString("download_link"));
			app.put("description", data.getString("description"));
			app.put("features", data.getString("features"));
			app.put("system_require", data.getString("system_require"));
			app.put("release_date", data.getString("release_date"));
		}
		return app;
	}
}
