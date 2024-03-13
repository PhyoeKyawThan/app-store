package admin.Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
//	delete app by id
	
	public boolean Delete(int app_id) throws SQLException, ClassNotFoundException {
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
}
