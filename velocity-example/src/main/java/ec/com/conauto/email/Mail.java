package ec.com.conauto.email;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class Mail {
		
	 private VelocityEngine velocityEngine;

	    public Mail()
	    {
	        try
	        {
	            // Load the velocity properties from the class path
	            Properties properties = new Properties();
	            properties.load( getClass().getClassLoader().getResourceAsStream( "velocity.properties" ) );

	            // Create and initialize the template engine
	            velocityEngine = new VelocityEngine( properties );
	        }
	        catch( Exception e )
	        {
	            e.printStackTrace();
	        }
	    }

	    public String execute()
	    {
	        try
	        {
		
		        ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
		        Map<String,String> map = new HashMap<String,String>();
	
		        map.put("name", "Cow");
		        map.put("price", "$100.00");
		        list.add( map );
		 
		        map = new HashMap<String,String>();
		        map.put("name", "Eagle");
		        map.put("price", "$59.99");
		        list.add( map );
	
		        map = new HashMap<String,String>();
		        map.put("name", "Shark");
		        map.put("price", "$3.99");
		        list.add( map );
	
		
		        VelocityContext context = new VelocityContext();
		        context.put("petList", list);
	
		        Template t = velocityEngine.getTemplate( "templates/tmpl.vm" );
	
		        StringWriter writer = new StringWriter();
	
		        t.merge( context, writer );
	
	            // Return the result
	            return writer.toString();
	        }
	        catch( Exception e )
	        {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static void main( String[] args )
	    {
	        Mail hello = new Mail();
	        System.out.println(  hello.execute() );
	    }
	
}
