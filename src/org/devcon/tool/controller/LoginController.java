package org.devcon.tool.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LoginController extends Controller {
    
    private UserService userService = UserServiceFactory.getUserService();
    private User user = userService.getCurrentUser();
    
    @Override
    public Navigation run() throws Exception {
        System.out.println("asdfasdfasdf");
        if (user != null) {
            System.out.println("asdf");
            requestScope("user", user.getNickname());
        } else {
            System.out.println(userService.createLoginURL(request.getRequestURI()));
            return redirect(userService.createLoginURL(request.getRequestURI()));
        }
        return forward("login.jsp");
    }
}
