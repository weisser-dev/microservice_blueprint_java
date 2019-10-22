package dev.weisser.samples;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class Service {

	public static void main(String[] args) {
		
		new Service().startup();
	}

	public boolean startup() {
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		context.setContextPath("/");
		
		Server server = new Server(3001);
		server.setHandler(context);
		
		registerServletsV1(context);
		
		try {
			server.start();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void registerServletsV1(ServletContextHandler context) {
		Map<String, Class<?>> mappings = new HashMap<>();
		// name where our servlet is available
		mappings.put("service", SampleServlet.class);
		// api version
		registerServlets(context, "v1", mappings);
	}

	private void registerServlets(ServletContextHandler context, String version,
		Map<String, Class<?>> mappings) {

		for (String key : mappings.keySet()) {
			Class<?> value = mappings.get(key);

			// /api/v1/service/getAll for example
			ServletHolder servlet = context.addServlet(ServletContainer.class, "/api/" + version + "/" + key + "/*");
			servlet.setInitParameter("jersey.config.server.provider.classnames", value.getCanonicalName());
		}
	}
	
}
