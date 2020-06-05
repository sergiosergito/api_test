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
public class DemoApplication 
        {
	static final String TABLAPLANES= "PLANES";
	
	private boolean tableExists(String name) {
		Connection con = null;
	    boolean answer = false;
		try {
	      Class.forName("org.sqlite.JDBC");
	      //con = DriverManager.getConnection("jdbc:sqlite:test.db");
	      //con = DriverManager.getConnection("jdbc:sqlite:C:Users/sergi/eclipse-workspace/7_project_Tarifario/ArquisoftCDRMaven/test.db");
	      con = DriverManager.getConnection("jdbc:sqlite:c:\\\\Users\\\\sergi\\\\eclipse-workspace\\\\7_project_Tarifario\\\\ArquisoftCDRMaven\\\\test.db");
	      
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
	
        ////////////////////////////////////////////////
	public void connect() {
		Connection con = null;
	      try {
	         Class.forName("org.sqlite.JDBC");
	         //c = DriverManager.getConnection("jdbc:sqlite:C:Users/sergi/eclipse-workspace/7_project_Tarifario/ArquisoftCDRMaven/test.db");
	         //c = DriverManager.getConnection("jdbc:sqlite:'C:Users/sergi/eclipse-workspace/7_project_Tarifario/ArquisoftCDRMaven/test.db'");
	         con = DriverManager.getConnection("jdbc:sqlite:c:\\\\Users\\\\sergi\\\\eclipse-workspace\\\\7_project_Tarifario\\\\ArquisoftCDRMaven\\\\test.db");
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	}
	
	
	
    @RequestMapping("/")
    @ResponseBody
    String home()       
        {
    	connect();
    	/*
    	File file = new File("c:\\Users\\sergi\\eclipse-workspace\\7_project_Tarifario\\ArquisoftCDRMaven\\test.db");
    	if (!file.isDirectory())
    	   file = file.getParentFile();
    	if (file.exists()){
    	    System.out.println("DB encontrada");
    	}
    	*/
        return "Hello World from Sergio!";
        }
        ////////////////////////////////////////////////


        ////////////////////////////////////////////////
        // /v1/fruits/add
        @RequestMapping(value = "/v1/fruits/add", method=RequestMethod.POST)
    @ResponseBody
    String FruitsAdd(@RequestParam("fruitname") String fruitname, @RequestParam("quantity") Integer quantity)
        {
        // Start the response to return to the API user.
        String response = "Called /v1/fruits/add.\n";
        response += "You want to add " + Integer.toString(quantity) + " " + fruitname + "'s.";

        // TODO: ADD CODE TO UPDATE THE DATABASE HERE.
        
        return response;
        }
        ////////////////////////////////////////////////


        ////////////////////////////////////////////////
        // /v1/fruits/subtract    
        @RequestMapping(value = "/v1/fruits/subtract", method=RequestMethod.POST)
    @ResponseBody
    String FruitsSubtract()     
        {
        // TODO: ADD CODE TO ACCEPT PARAMETERS HERE.
        // TODO: ADD CODE TO UPDATE THE DATABASE HERE.

        return "Called fruits/subtract";
        }
        ////////////////////////////////////////////////


        ////////////////////////////////////////////////
        // /v1/fruits/check
    @RequestMapping(value = "/v1/fruits/check", method=RequestMethod.GET)
    @ResponseBody
    String FruitsCheck()        
        {
        // TODO: ADD CODE TO ACCEPT PARAMETERS HERE.
        // TODO: ADD CODE TO QUERY THE DATABASE HERE.
    	String response = "Called /v1/fruits/check.";
    	if(tableExists(TABLAPLANES))
    		response = "yesss";
    	else
    		response = "not the correct db";
        
        return response;
        }
        ////////////////////////////////////////////////


        ////////////////////////////////////////////////
        // MAIN
    public static void main(String[] args) 
        {
        SpringApplication.run(DemoApplication.class, args);
        }
        ////////////////////////////////////////////////
        }