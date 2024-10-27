package services.impl;

import database.Database;
import models.Author;
import models.Moderator;
import services.AuthenticationService;
import services.UserServices;

import java.util.Date;

public class AuthorServicesImpl implements UserServices {
    private static int authorIDIndex = 0;

    public void authorSignup(String username, String password, String nationalID, Date birthday) {
        Author author = new Author();
        author.setAuthorID(authorIDIndex++);
        author.setUsername(username);
        author.setNationalID(nationalID);
        author.setPassword(nationalID);

        Database.authorsList.add(author);
    }

    @Override
    public void userLogin(String username, String password) {
        for (Author checkingAuthor : Database.authorsList) {

            if (checkingAuthor.getUsername().equals(username)){

                if (checkingAuthor.getPassword().equals(password)) {

                    AuthenticationService.setLoggedUser(checkingAuthor);

                    return;
                }
            }
        }

    }

    @Override
    public void changePassword(String newPassword) {
        Author loggedAuthor = (Author) AuthenticationService.getLoggedInUser();
        if (loggedAuthor != null) {
            loggedAuthor.setPassword(newPassword);
            System.out.println("Password changed successfully.");
        }
    }
}