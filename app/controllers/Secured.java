package controllers;

import play.mvc.Result;
import play.mvc.Security;
import play.mvc.Http.Context;

// For action composition, to chain actions based on whether the user 
// is securely authenticated or not.
public class Secured extends Security.Authenticator {
	
	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get("email");
	}
	
	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect(routes.Application.login());
	}

}
