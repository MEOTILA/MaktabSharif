package services.impl;

import database.Database;
import models.Article;
import models.Author;
import models.Moderator;
import services.AuthenticationService;
import services.UserServices;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class ModeratorServicesImpl implements UserServices {
    private static final Scanner scanner = new Scanner(System.in);
    private static int moderatorIDIndex = 0;

    public void moderatorSignup(String username, String password) {
        Moderator moderator = new Moderator();
        moderator.setModeratorID(moderatorIDIndex++);
        moderator.setUsername(username);
        moderator.setPassword(password);

        Database.moderatorsList.add(moderator);
    }

    @Override
    public void userLogin(String username, String password) {
        for (Moderator checkingModerator : Database.moderatorsList) {

            if (checkingModerator.getUsername().equals(username)) {

                if (checkingModerator.getPassword().equals(password)) {

                    AuthenticationService.setLoggedUser(checkingModerator);

                    return;
                }
            }
        }
    }

    @Override
    public void changePassword(String newPassword) {
        Moderator loggedModerator = (Moderator) AuthenticationService.getLoggedInUser();
            loggedModerator.setPassword(newPassword);
            System.out.println("Password changed successfully.");
    }


    public void approveArticle(int articleID) {
        Article pendingArticle = null;
        for (Article article : Database.pendingArticlesList) {
            if (article.getArticleID() == articleID) {
                pendingArticle = article;
                break;
            }
        }
        if(pendingArticle == null){
            System.out.println("Article not Found!");
            return;
        }


        System.out.print("Do you want to approve this article? (y/n): ");
        String approveChoice = scanner.next();

        if (approveChoice.equals("y")) {

            pendingArticle.setArticleStatus("Approved");
            pendingArticle.setArticlePublishDate(LocalDate.now());
            pendingArticle.setArticleIsPublished(true);

            int authorID = pendingArticle.getAuthorID();
            Author author = null;
            for (Author checkingAuthor : Database.authorsList) {
                if (checkingAuthor.getAuthorID() == authorID) {
                    author = checkingAuthor;
                    break;
                }
            }
            author.getAuthorArticlesList().add(pendingArticle);

            System.out.println("Article with ID " + articleID + " is approved and added to author's list.");

        } else if (approveChoice.equals("n")) {
            pendingArticle.setArticleStatus("Denied");
            System.out.println("Article with ID " + articleID + " is denied.");
        }
    }


    public void viewPendingArticles() {
        System.out.println("Pending Articles:");

        for (Article pendingArticle : Database.pendingArticlesList) {
            System.out.println("Article ID: " + pendingArticle.getArticleID());
            System.out.println("Article Title: " + pendingArticle.getArticleTitle());
            System.out.println("Article Brief: " + pendingArticle.getArticleBrief());
            System.out.println("Article Status: " + pendingArticle.getArticleStatus());
            System.out.println("----------------------------------");
        }
    }

    public void viewAllPublishedArticles() {
        System.out.println("All Published Articles:");

        for (Article article : Database.pendingArticlesList) {
            if (article.getArticleStatus().equals("Approved")
                    && article.isArticleIsPublished()==true) {
                System.out.println("Article ID: " + article.getArticleID());
                System.out.println("Article Title: " + article.getArticleTitle());
                System.out.println("Article Brief: " + article.getArticleBrief());
                System.out.println("----------------------------------");
            }
        }
    }
    public void displayArticleDetails(int articleID) {
        for (Article article : Database.pendingArticlesList) {
            if (article.getArticleID() == articleID
                    && article.getArticleStatus().equals("Approved")
                    && article.isArticleIsPublished()) {
                System.out.println("Article Title: " + article.getArticleTitle());
                System.out.println("Article Brief: " + article.getArticleBrief());
                System.out.println("Article Text: " + article.getArticleText());
                return;
            }
        }
    }
}