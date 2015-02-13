import play.*;
import play.libs.*;

import java.util.*;

import com.avaje.ebean.*;

import models.*;

public class Global extends GlobalSettings {
    
    @Override
    public void onStart(Application app) {
     
        Logger.info("Application has started.");
        InitialData.insert(app);
    }
    
    static class InitialData {      
        
        public static void insert(Application app) {
            
            if(Ebean.find(User.class).findRowCount() == 0) {            
            	Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");
                
            	// Insert users first
                Logger.info("Loading members from initial-data.yml");
                Ebean.save(all.get("users"));                
            }            
            Logger.info("Loaded "+Ebean.find(User.class).findRowCount()+" users");
        }        
    }
    
    public void onStop(Application app) {
    	Logger.info("Application is shutting down...");
    }

}
