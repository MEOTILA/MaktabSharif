package models;

import java.time.LocalDate;
import java.util.Date;

public class Article {
    private int authorID;
    private int articleID;
    private String articleTitle;
    private String articleBrief;
    private String articleText;
    private LocalDate articleCreateDate;
    private boolean articleIsPublished;
    private LocalDate articleLastUpdateDate;
    private LocalDate articlePublishDate;
    private String articleStatus;

    public Article(int articleID, String articleTitle, String articleBrief,
                   String articleText, LocalDate articleCreateDate,
                   boolean articleIsPublished, LocalDate articleLastUpdateDate,
                   LocalDate articlePublishDate, String articleStatus) {

        this.articleID = articleID;
        this.articleTitle = articleTitle;
        this.articleBrief = articleBrief;
        this.articleText = articleText;
        this.articleCreateDate = articleCreateDate;
        this.articleIsPublished = articleIsPublished;
        this.articleLastUpdateDate = articleLastUpdateDate;
        this.articlePublishDate = articlePublishDate;
        this.articleStatus = articleStatus;
    }

    public Article() {
    }


    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleBrief() {
        return articleBrief;
    }

    public void setArticleBrief(String articleBrief) {
        this.articleBrief = articleBrief;
    }

    public LocalDate getArticleCreateDate() {
        return articleCreateDate;
    }

    public void setArticleCreateDate(LocalDate articleCreateDate) {
        this.articleCreateDate = articleCreateDate;
    }

    public boolean isArticleIsPublished() {
        return articleIsPublished;
    }

    public void setArticleIsPublished(boolean articleIsPublished) {
        this.articleIsPublished = articleIsPublished;
    }

    public LocalDate getArticleLastUpdateDate() {
        return articleLastUpdateDate;
    }

    public void setArticleLastUpdateDate(LocalDate articleLastUpdateDate) {
        this.articleLastUpdateDate = articleLastUpdateDate;
    }

    public LocalDate getArticlePublishDate() {
        return articlePublishDate;
    }

    public void setArticlePublishDate(LocalDate articlePublishDate) {
        this.articlePublishDate = articlePublishDate;
    }

    public String getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleID=" + articleID +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleBrief='" + articleBrief + '\'' +
                ", articleText='" + articleText + '\'' +
                ", articleCreateDate=" + articleCreateDate +
                ", articleIsPublished=" + articleIsPublished +
                ", articleLastUpdateDate=" + articleLastUpdateDate +
                ", articlePublishDate=" + articlePublishDate +
                ", articleStatus='" + articleStatus + '\'' +
                '}';
    }
}
