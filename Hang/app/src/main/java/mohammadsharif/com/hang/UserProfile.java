package mohammadsharif.com.hang;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.TextView;

import com.facebook.Profile;

public class UserProfile extends AppCompatActivity {
    private String name;
    private String school;
    private Profile user = Profile.getCurrentProfile();
    private TextView nameView;

    public UserProfile() {
        name = user.getName();
//        school = user.getProperty("")

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile_page);
        nameView = (TextView)findViewById(R.id.name_view);
        nameView.setText(name);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.hide();

    }




}
