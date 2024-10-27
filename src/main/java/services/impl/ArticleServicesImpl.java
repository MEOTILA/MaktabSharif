package services.impl;

import database.Database;
import models.Article;
import models.Author;
import models.User;
import services.ArticleServices;
import services.AuthenticationService;

import java.util.Date;
import java.util.List;



public class ArticleServicesImpl {
    private static int articleIDIndex = 0;

    //@Override
    public void addArticle(String articleTitle, String articleBrief,
                           String articleText) {

        Article article = new Article();

        article.setArticleID(articleIDIndex++);
        article.setArticleTitle(articleTitle);
        article.setArticleBrief(articleBrief);
        article.setArticleText(articleText);

        article.setArticleCreateDate(new Date());

        article.setArticleIsPublished(false);
        article.setArticleLastUpdateDate(article.getArticleCreateDate());
        article.setArticlePublishDate(null);
        article.setArticleStatus("Pending");

        Database.pendingArticlesList.add(article);
        System.out.println("Article added to Pending Articles list successfully!");
    }


    public void viewArticle() {

        User loggedInUser = AuthenticationService.getLoggedInUser();
        Author author = (Author) loggedInUser;

        System.out.println("Your Published Articles:");
        for (Article article : author.getAuthorArticlesList()) {
            if (article.getArticleStatus().equals("Approved") &&
            article.isArticleIsPublished()) {
                System.out.println("Article ID: " + article.getArticleID());
                System.out.println("Article Title: " + article.getArticleTitle());
                System.out.println("Article Text: " + article.getArticleText());
                System.out.println("Article Brief: " + article.getArticleBrief());
                System.out.println("----------------------------------");
            }
        }
    }


    public void removeArticle(int articleID) {
        Author loggedAuthor = (Author) AuthenticationService.getLoggedInUser();
            List<Article> authorArticles = loggedAuthor.getAuthorArticlesList();
            for (int i = 0; i < authorArticles.size(); i++) {
                if (authorArticles.get(i).getArticleID() == articleID) {
                    authorArticles.remove(i);
                    System.out.println("Article removed successfully.");
                    return;
                }
            }
    }


    public void editArticle(int articleID, String newTitle, String newBrief, String newText) {
        Author loggedAuthor = (Author) AuthenticationService.getLoggedInUser();
            List<Article> authorArticles = loggedAuthor.getAuthorArticlesList();
            for (int i = 0; i < authorArticles.size(); i++) {
                if (authorArticles.get(i).getArticleID() == articleID) {
                    Article articleToEdit = authorArticles.get(i);

                    articleToEdit.setArticleTitle(newTitle);
                    articleToEdit.setArticleBrief(newBrief);
                    articleToEdit.setArticleText(newText);
                    articleToEdit.setArticleLastUpdateDate(new Date());

                    System.out.println("Article updated successfully.");
                    return;
                }
            }
        }
    }







