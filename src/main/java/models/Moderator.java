package models;

public class Moderator extends User{
    private int moderatorID;

    public Moderator(String username, String password, int moderatorID) {
        super(username, password);
        this.moderatorID = moderatorID;
    }
    public Moderator(){

    }

    public int getModeratorID() {
        return moderatorID;
    }


    public void setModeratorID(int moderatorID) {
        this.moderatorID = moderatorID;
    }
}


