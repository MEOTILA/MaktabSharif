package services.impl;

import database.Database;
import models.Article;
import models.Author;
import models.User;
import services.ArticleServices;
import services.AuthenticationService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.Period;


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

        article.setArticleCreateDate(LocalDate.now());

        article.setArticleIsPublished(false);
        article.setArticleLastUpdateDate(LocalDate.now());
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
                articleToEdit.setArticleLastUpdateDate(LocalDate.now());

                System.out.println("Article updated successfully.");
                return;
            }
        }
    }

    public List<Article> viewArticlesFilteredByCreateDate(int filterBy) {
        List<Article> filteredArticles = new ArrayList<>();
        List<Article> allArticles = Database.pendingArticlesList;

        LocalDate currentDate = LocalDate.now();

        for (Article article : allArticles) {
            LocalDate articleDate = article.getArticleCreateDate();

            switch (filterBy) {
                case 1: // Last year
                    if (articleDate != null && articleDate.isAfter(currentDate.minusYears(1))) {
                        filteredArticles.add(article);
                    }
                    break;
                case 2: // Last 6 months
                    Period sixMonthsAgo = Period.ofMonths(6);
                    LocalDate sixMonthsBefore = currentDate.minus(sixMonthsAgo);

                    if (articleDate != null && articleDate.isAfter(sixMonthsBefore)) {
                        filteredArticles.add(article);
                    }
                    break;
                case 3: // Last month
                    Period oneMonthAgo = Period.ofMonths(1);
                    LocalDate oneMonthBefore = currentDate.minus(oneMonthAgo);

                    if (articleDate != null && articleDate.isAfter(oneMonthBefore)) {
                        filteredArticles.add(article);
                    }
                    break;
                case 4: // Last week
                    Period oneWeekAgo = Period.ofDays(7);
                    LocalDate oneWeekBefore = currentDate.minus(oneWeekAgo);

                    if (articleDate != null && articleDate.isAfter(oneWeekBefore)) {
                        filteredArticles.add(article);
                    }
                    break;
                case 5: // Last day
                    if (articleDate != null && articleDate.isEqual(currentDate)) {
                        filteredArticles.add(article);
                    }
                    break;
                default:
                    continue;
            }
        }

        return filteredArticles;
    }


    public List<Article> viewArticlesFilteredByUpdateDate(int filterBy) {
        List<Article> filteredArticles = new ArrayList<>();
        List<Article> allArticles = Database.pendingArticlesList;

        LocalDate currentDate = LocalDate.now();

        for (Article article : allArticles) {
            LocalDate articleDate = article.getArticleLastUpdateDate();

            switch (filterBy) {
                case 1: // Last year
                    if (articleDate != null && articleDate.isAfter(currentDate.minusYears(1))) {
                        filteredArticles.add(article);
                    }
                    break;
                case 2: // Last 6 months
                    Period sixMonthsAgo = Period.ofMonths(6);
                    LocalDate sixMonthsBefore = currentDate.minus(sixMonthsAgo);

                    if (articleDate != null && articleDate.isAfter(sixMonthsBefore)) {
                        filteredArticles.add(article);
                    }
                    break;
                case 3: // Last month
                    Period oneMonthAgo = Period.ofMonths(1);
                    LocalDate oneMonthBefore = currentDate.minus(oneMonthAgo);

                    if (articleDate != null && articleDate.isAfter(oneMonthBefore)) {
                        filteredArticles.add(article);
                    }
                    break;
                case 4: // Last week
                    Period oneWeekAgo = Period.ofDays(7);
                    LocalDate oneWeekBefore = currentDate.minus(oneWeekAgo);

                    if (articleDate != null && articleDate.isAfter(oneWeekBefore)) {
                        filteredArticles.add(article);
                    }
                    break;
                case 5: // Last day
                    if (articleDate != null && articleDate.isEqual(currentDate)) {
                        filteredArticles.add(article);
                    }
                    break;
                default:
                    continue;
            }
        }

        return filteredArticles;
    }

    public List<Article> viewArticlesFilteredByPublishDate(int filterBy) {
        List<Article> filteredArticles = new ArrayList<>();
        List<Article> allArticles = Database.pendingArticlesList;

        LocalDate currentDate = LocalDate.now();

        for (Article article : allArticles) {
                LocalDate articleDate = article.getArticlePublishDate();

                switch (filterBy) {
                    case 1: // Last year
                        if (articleDate != null && articleDate.isAfter(currentDate.minusYears(1))) {
                            filteredArticles.add(article);
                        }
                        break;
                    case 2: // Last 6 months
                        Period sixMonthsAgo = Period.ofMonths(6);
                        LocalDate sixMonthsBefore = currentDate.minus(sixMonthsAgo);

                        if (articleDate != null && articleDate.isAfter(sixMonthsBefore)) {
                            filteredArticles.add(article);
                        }
                        break;
                    case 3: // Last month
                        Period oneMonthAgo = Period.ofMonths(1);
                        LocalDate oneMonthBefore = currentDate.minus(oneMonthAgo);

                        if (articleDate != null && articleDate.isAfter(oneMonthBefore)) {
                            filteredArticles.add(article);
                        }
                        break;
                    case 4: // Last week
                        Period oneWeekAgo = Period.ofDays(7);
                        LocalDate oneWeekBefore = currentDate.minus(oneWeekAgo);

                        if (articleDate != null && articleDate.isAfter(oneWeekBefore)) {
                            filteredArticles.add(article);
                        }
                        break;
                    case 5: // Last day
                        if (articleDate != null && articleDate.isEqual(currentDate)) {
                            filteredArticles.add(article);
                        }
                        break;
                    default:
                        continue;
                }
            }
        return filteredArticles;
    }
}
