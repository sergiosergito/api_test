package demo;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
public class DemoApplication 
        {
        ////////////////////////////////////////////////
	public void connect() {
		Connection c = null;
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:test.db");
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