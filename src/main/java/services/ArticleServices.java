package services;

public interface ArticleServices {
    public void addArticle(String articleTitle, String articleBrief,
                           String articleText);
    public void viewArticle();
    public void removeArticle();
    public void editArticle();

}
