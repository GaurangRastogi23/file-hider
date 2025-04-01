package Service;

import Model.User;
import dao.UserDAO;

import java.sql.SQLException;

public class userService {
    public static Integer saveUser(User user){
        try{
            if(UserDAO.isExists(user.getEmail())){
                return 0;
            } else{
                 UserDAO.saveUser(user);
                 return 1;
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return  null;
    }
}
