package services;

import models.User;

public abstract class AuthenticationService {
    private static User loggedInUser;

    public static void setLoggedUser(User user){
        loggedInUser = user;
    }
    public static User getLoggedInUser(){
        return loggedInUser;
    }
    public static void logout(){
        loggedInUser = null;
    }
}
