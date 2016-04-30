package mohammadsharif.com.hang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.Profile;

public class UserProfile extends AppCompatActivity {
    private String name;
    private Profile user;
    TextView nameView;

    public UserProfile() {



    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

    }

    public void setProfile(Profile user){
        this.user = user;
        writeName(user.getName());
    }

    public void writeName(String name){
        nameView = (TextView)findViewById(R.id.name_view);
        nameView.setText(name);
    }
}
