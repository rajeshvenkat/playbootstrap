package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import play.Logger;

import play.db.ebean.Model;

// @Entity annotation marks this class as a managed Ebean entity
@Entity								
public class User extends Model {
	
	// Every JPA entity must provide an @Id property
	@Id
	public String email;
	public String name;
	public String password;
	
	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}
	
    public static User authenticate(String email, String password) {
        Logger.info("Trying to authenticate user");
        return find.where().eq("email", email).eq("password", password).findUnique();
    }
    
    public static List<User> all() {
    	return find.all();
    }
    
	// To programatically make queries
    public static Finder<String,User> find = new Finder<String,User> (String.class, User.class);

}
