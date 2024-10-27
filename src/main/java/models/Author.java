package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Author extends User{

    private int authorID;
    private String nationalID;
    private Date birthday;
    private List<Article> authorArticlesList = new ArrayList<>();



    public Author(int authorID, String username,String nationalID, Date birthday, String password) {
        super(username, password);
        this.authorID = authorID;
        this.nationalID = nationalID;
        this.birthday = birthday;
    }


    public Author(){
    }


    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String  getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Article> getAuthorArticlesList() {
        return authorArticlesList;
    }

    public void setAuthorArticlesList(List<Article> authorArticlesList) {
        this.authorArticlesList = authorArticlesList;
    }
}
