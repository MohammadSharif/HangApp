package mohammadsharif.com.hang;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;

import org.json.JSONObject;

public class UserProfile extends AppCompatActivity {
    private String name;
    private String school;
    private Profile user = Profile.getCurrentProfile();
    private TextView nameView;
    private String asString;
    public UserProfile() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e("JUNK", response.toString());
                        asString = response.getRawResponse();

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "education");
        request.setParameters(parameters);
        request.executeAsync();


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
