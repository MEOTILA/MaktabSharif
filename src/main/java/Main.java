import database.Database;
import models.Article;
import services.AuthenticationService;
import services.impl.ArticleServicesImpl;
import services.impl.AuthorServicesImpl;
import services.impl.ModeratorServicesImpl;

import java.util.Date;
import java.util.Scanner;

public class Main {

    final static Scanner scanner = new Scanner(System.in);
    final static AuthorServicesImpl authorServices = new AuthorServicesImpl();
    final static ModeratorServicesImpl moderatorServices = new ModeratorServicesImpl();
    final static ArticleServicesImpl articleServices = new ArticleServicesImpl();



    public static void main(String[] args) {

        Article articleOne = new Article(0,"New Medicine",
                "New Medicine founded!","New medicine founded in europe",
                new Date(2024,1,1),true,
                new Date(2024,1,1), new Date (2024,1,1),
                "Approved");

        Article articleTwo = new Article(1,"New Instrument",
                "New Instrument founded!","New Instrument founded in europe",
                new Date(2024,4,1),true,
                new Date(2024,4,1), new Date (2024,4,1),
                "Approved");

        Article articleThree = new Article(2,"New Device","New Device founded!",
                "New Device founded in europe",
                new Date(2024,10,1),true,
                new Date(2024,10,1), new Date (2024,10,1),
                "Approved");

        Database.pendingArticlesList.add(articleOne);
        Database.pendingArticlesList.add(articleTwo);
        Database.pendingArticlesList.add(articleThree);

        System.out.println("Welcome to the World of Articles!");

        while (true) {

            System.out.println("1. Moderator 💻");
            System.out.println("2. Author 📝");
            System.out.println("Choose your action: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    moderatorMenu();
                    break;
                case 2:
                    authorMenu();
                    break;
            }
        }
    }

    private static void moderatorMenu() {
        System.out.println("Moderator Menu 💻");
        System.out.println("1. SignUp");
        System.out.println("2. Login");
        System.out.println("3. Main Menu");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.print("Please Enter Your Username: ");
                String username = scanner.next();
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();

                moderatorServices.moderatorSignup(username, password);
                System.out.println("Your account has been created!");
                break;

            case 2:
                System.out.print("Please Enter Your Username: ");
                username = scanner.next();
                System.out.print("Please Enter Your Password: ");
                password = scanner.next();

                moderatorServices.userLogin(username, password);

                if (AuthenticationService.getLoggedInUser() != null) {
                    System.out.println("Welcome Dear " +
                            AuthenticationService.getLoggedInUser().getUsername() + " 😍");

                    moderatorPanel();
                } else {
                    System.out.println("Username or Password is Incorrect!");
                }
                break;

            case 3:
                break;
        }

    }

    private static void moderatorPanel() {
        while (true) {
            System.out.println("Moderator Panel 💻");
            System.out.println("1. View Pending Articles");
            System.out.println("2. Approve Article");
            System.out.println("3. Change Password");
            System.out.println("4. Logout");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    moderatorServices.viewPendingArticles();
                    break;

                case 2:
                    System.out.print("Enter the article ID to approve: ");
                    int articleID = scanner.nextInt();
                    moderatorServices.approveArticle(articleID);
                    break;

                case 3:
                    System.out.println("Enter Your New Password: ");
                    String newPassword = scanner.next();
                    moderatorServices.changePassword(newPassword);
                    break;

                case 4:
                    AuthenticationService.logout();
                    System.out.println("Logged out successfully.");
                    return;
            }
        }
    }


    private static void authorMenu() {
        System.out.println("Author Menu 📝");
        System.out.println("1. SignUp");
        System.out.println("2. Login");
        System.out.println("3. View All Published Articles");
        System.out.println("4. Main Menu");
        int option = scanner.nextInt();

        switch (option) {

            case 1:
                System.out.print("Please Enter Your Username: ");
                String username = scanner.next();
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                System.out.print("Please Enter Your National ID: ");
                String nationalID = scanner.next();
                System.out.print("Please Enter Your Birth Year: ");
                int year = scanner.nextInt();
                System.out.print("Please Enter Your Birth Month: ");
                int month = scanner.nextInt();
                System.out.print("Please Enter Your Birth Day: ");
                int day = scanner.nextInt();
                Date birthday = new Date(year, month, day);

                authorServices.authorSignup(username, password, nationalID, birthday);
                System.out.println("Your account has been created!");
                break;
            case 2:
                System.out.print("Please Enter Your Username: ");
                username = scanner.next();
                System.out.print("Please Enter Your Password: ");
                password = scanner.next();

                authorServices.userLogin(username, password);

                if (AuthenticationService.getLoggedInUser() != null) {
                    System.out.println("Welcome Dear " +
                            AuthenticationService.getLoggedInUser().getUsername() + " 😍");

                    authorPanel();
                } else {
                    System.out.println("Username or Password is Incorrect!");
                }
                break;

            case 3:
                moderatorServices.viewAllPublishedArticles();
                System.out.println("Enter the Article ID to View the Full Text: ");
                int articleIDChoice = scanner.nextInt();
                moderatorServices.displayArticleDetails(articleIDChoice);
            case 4:
                break;
        }

    }

    private static void authorPanel() {
        while (true) {
            System.out.println("Author Panel 📝");
            System.out.println("1. Create an Article");
            System.out.println("2. View all Articles");
            System.out.println("3. Edit an Article");
            System.out.println("4. Remove an Article");
            System.out.println("5. Change Password");
            System.out.println("6. Logout");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter Your Article Title: ");
                    String articleTitle = scanner.next();
                    System.out.println("Enter Your Article Brief: ");
                    String articleBrief = scanner.next();
                    System.out.println("Enter Your Article Text: ");
                    String articleText = scanner.next();
                    articleServices.addArticle(articleTitle, articleBrief, articleText);
                    break;

                case 2:
                    articleServices.viewArticle();
                    break;

                case 3:
                    System.out.print("Enter the article ID to edit: ");
                    int articleIDToEdit = scanner.nextInt();
                    
                    System.out.print("Enter the new title: ");
                    String newTitle = scanner.next();

                    System.out.print("Enter the new brief: ");
                    String newBrief = scanner.next();

                    System.out.print("Enter the new text: ");
                    String newText = scanner.next();

                    articleServices.editArticle(articleIDToEdit, newTitle, newBrief, newText);
                    break;
                case 4:
                    System.out.print("Enter the article ID to remove: ");
                    int articleIDToRemove = scanner.nextInt();
                    articleServices.removeArticle(articleIDToRemove);
                    break;

                case 5:
                    System.out.println("Enter Your New Password: ");
                    String newPassword = scanner.next();
                    authorServices.changePassword(newPassword);
                    break;

                case 6:
                    AuthenticationService.logout();
                    System.out.println("Logged out successfully.");
                    return;
            }
        }
    }
}