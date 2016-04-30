package mohammadsharif.com.hang;

import com.facebook.Profile;

public class UserInformation {
    private String name;


    public UserInformation(Profile user){
        name = user.getName();
    }

    public String getName(){
        return name;
    }
}
