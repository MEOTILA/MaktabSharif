package services;

public interface UserServices {
    void userLogin(String username, String password);
    void changePassword(String newPassword);
}
