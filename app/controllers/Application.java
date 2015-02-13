package controllers;

import java.util.List;

import models.User;
import play.*;
import play.mvc.*;
import play.data.*;
import play.db.ebean.Model;

import play.libs.Json;

import views.html.*;

public class Application extends Controller {
  
    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(index.render("your new application is ready!", User.find.byId(request().username())));
    }
    
    public static Result login() {
        return ok(login.render(Form.form(Login.class)));
    }
    
    public static Result authenticate () {
    	Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
    	if(loginForm.hasErrors()) {
    		return badRequest(login.render(loginForm));
    	} else {
    		session().clear();
    		session("email", loginForm.get().email);
    		return redirect(routes.Application.index());
    	}
    }
   
    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
            routes.Application.login()
        );
    }
    
    public static Result getUsers() {
    	List<User> users = new Model.Finder(String.class, User.class).all();
    	return ok(Json.toJson(users));
    }
   
    public static class Login {
    	public String email;
    	public String password;
    	
        public String validate() {
        	if(User.authenticate(email, password) == null) {
        		return "Invalid user or password";
        	}
        	return null;
        }

    }
  
}
