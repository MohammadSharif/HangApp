package mohammadsharif.com.hang;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import mohammadsharif.com.hang.DownloadImageTask;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

public class UserProfile extends AppCompatActivity {
    private String name = Profile.getCurrentProfile().getName();
    private String school;
    private Profile user = Profile.getCurrentProfile();
    private TextView nameView;
    private TextView schoolView;
    private ImageView profilePhoto;
    private Button startHanging;
    private String asString;
    public UserProfile() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e("JUNK", response.toString());
                        asString = response.getRawResponse();
                        int ab = asString.lastIndexOf("name");
                        int end = 0;
                        for(int i = 0; i < asString.length() - ab; i++){
                            if(asString.substring(ab + i, ab + i + 1).equals(",")){
                                end = i;
                                break;
                            }
                        }
                        String toEdit = asString.substring(ab, ab + end);
                        school = toEdit.substring(7, toEdit.lastIndexOf('"'));
                        schoolView = (TextView)findViewById(R.id.school_view);
                        schoolView.setText(school);

//                        Bitmap bitmap = getFacebookProfilePicture(user.getId());
//                        profilePhoto = (ImageView)findViewById(R.id.imageView2);
//                        profilePhoto.setImageBitmap(bitmap);
                        try {
                            URL imageURL = new URL("https://graph.facebook.com/" + user.getId().toString() + "/picture?type=large");
                            new DownloadImageTask((ImageView) findViewById(R.id.imageView2))
                                    .execute(imageURL.toString());
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

//                        JSONArray jArray = response.getJSONArray();
//                        for(int i = 0; i < jArray.length(); i++) {
//                            try{
//                                JSONObject json = jArray.getJSONObject(i);
////                                int objId = jArray.getInt(i);
////                                json.put(Integer.toString(objId), odao.getObjectName(objId));
////                                jArray.put(json);
//                                String name = json.getString("name");
//                                Log.e("JUNK", name);
//                            }
//                            catch ( Exception e){
//                                e.printStackTrace();
//                            }
//                        }

//                        results = jArray.toString();

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
        startHanging = (Button)findViewById(R.id.hangbutton);
        startHanging.setText("Ready to hang?");
        startHanging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(UserProfile.this, MapsActivity.class);
                startActivity(start);
            }
        });




        ActionBar actionBar = this.getSupportActionBar();
        actionBar.hide();

    }








}
