package dev.weisser.samples;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.util.StringUtil;
import org.testcontainers.shaded.org.apache.commons.lang.StringUtils;

import dev.weisser.samples.utils.DBUtils;
import com.google.gson.Gson;


@Path("/")
public class SampleServlet {
	
	private DBUtils databaseUils;
	
	public SampleServlet() {
		databaseUils = new DBUtils();
	}

	// http://localhost:3001/api/v1/service/insert
	@Path("insert")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(
			@QueryParam("name") String name,
			@QueryParam("text") String text,
			@QueryParam("description") String description){

		if(StringUtil.isNotBlank(name) && StringUtil.isNotBlank(text) && StringUtil.isNotBlank(description)) {
			SampleDBObj someObj = new SampleDBObj(name, text, description);
			try {
				databaseUils.insert(someObj);
				return Response.ok("Obj successfully created").build();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(404, "An error occurred while calling the service, please try again. \r\n" + e.getMessage()).build();
			}
		} else {
			return Response.status(404, "Object could not be created, one of the required parameters was empty").build();
		}
	}

	
	// http://localhost:3001/api/v1/service/getByName
	@Path("getByName")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getByName(@QueryParam("name") String name) {
		if(StringUtils.isNotBlank(name)) {
		try {
			return new Gson().toJson(databaseUils.getByName(name));
		} catch (SQLException e) {
			e.printStackTrace();
			return "An error occurred while calling the service, please try again. \r\n" + e.getMessage();
		}
		} else {
			return new Gson().toJson("Object could not be created, one of the required parameters was empty");
		}
	}
	
	// http://localhost:3001/api/v1/service/getAll
	@Path("getAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String get() {
		try {
			return new Gson().toJson(databaseUils.getAll());
		} catch (SQLException e) {
			e.printStackTrace();
			return "An error occurred while calling the service, please try again. \r\n" + e.getMessage();
		}
	}
	
	@Path("export")
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response export() {
		try {
			StringBuilder sb = new StringBuilder();
			for(Field field : SampleDBObj.class.getDeclaredFields()) {
				sb.append(field.getName()).append(",");
			}
			// load everything from database
			List<SampleDBObj> objects = databaseUils.getAll();
			//header
			sb.deleteCharAt(sb.length()-1);
			for (SampleDBObj obj : objects) {
				sb.append("\r\n");
				sb.append(obj.toCSVLine());
			}
			return Response.ok(sb.toString()).header("Content-Disposition", "attachment; filename=" + new Date().toString() + "-export-objs.csv").build();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
}
