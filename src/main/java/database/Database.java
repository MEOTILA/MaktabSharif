package database;

import models.Article;
import models.Author;
import models.Moderator;
import models.User;

import java.util.ArrayList;

public abstract class Database {

    public static final ArrayList<User> userList = new ArrayList<>();
    public static final ArrayList<Author> authorsList = new ArrayList<>();
    public static final ArrayList<Moderator> moderatorsList = new ArrayList<>();
    public static final ArrayList<Article> pendingArticlesList = new ArrayList<>();
}
