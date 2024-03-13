package servlet.assets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/UploadFile")
@MultipartConfig
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Retrieves text description from the form field

	    // Retrieves the file part from the form field
	    Part filePart = request.getPart("file");

	    // Retrieves the filename from the file part
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

	    // Retrieves the input stream of the file
	    InputStream fileContent = filePart.getInputStream();

	    // Do your job here (e.g., save the file)
	    saveFile( request.getServletContext().getRealPath("/"),fileContent, fileName);

	    // Close the input stream
	    fileContent.close();
	    
	    JSONObject response_json = new JSONObject();
	    response_json.put("status", 200);
	    // Send a response
	    response.getWriter().print(response_json);
	}

	private void saveFile(String dir, InputStream fileContent, String fileName) throws IOException {
	    // Specify the directory where you want to save the uploaded file
	    String uploadDir = dir + "/icons"; 

	    // Create the file object
	    File file = new File(uploadDir + File.separator + fileName);
	    System.out.println(file.getAbsolutePath());
	    // Write the content of the input stream to the file
	    try (FileOutputStream out = new FileOutputStream(file)) {
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = fileContent.read(buffer)) > 0) {
	            out.write(buffer, 0, length);
	        }
	    }

}
}
