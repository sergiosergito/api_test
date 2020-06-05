package demo;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
public class DemoApplication {
	
	static final String TABLALINEA= "LINEA";
	static final String DATABASELOCATION= "jdbc:sqlite:c:\\\\Users\\\\sergi\\\\eclipse-workspace\\\\7_project_Tarifario\\\\ArquisoftCDRMaven\\\\test.db";
	
	public boolean tableExists(String name) {
		Connection con = null;
	    boolean answer = false;
		try {
	      Class.forName("org.sqlite.JDBC");
	      con = DriverManager.getConnection(DATABASELOCATION);
	      
	      DatabaseMetaData meta = con.getMetaData();
	      ResultSet tables = meta.getTables(null, null, name, null);
		  		if (tables.next())
		  		  answer =  true;
	      con.close();
	    } catch (Exception e) {
	      System.err.println("Exception: "+e.getMessage());
	    }
		return answer;
	}
	
	public boolean connect() {
		Connection con = null;
		boolean response = false;
	      try {
	         Class.forName("org.sqlite.JDBC");
	         con = DriverManager.getConnection(DATABASELOCATION);
	         response =  true;
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      return response;
	}
	
    @RequestMapping("/")
    @ResponseBody
    String home(){
    	if(connect())
    		return "Hello World from Home!";
    	else return "connection failed";
        }
    
    @RequestMapping(value = "/v1/linea/check/all", method=RequestMethod.GET)
    @ResponseBody
    String LineasCheck(){
    	String response = "Called /v1/lineas/check.";
    	if(tableExists(TABLALINEA))
    		response = "yesss";
    	else
    		response = "not the correct db";
        return response;
        }
        
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}