package mohammadsharif.com.hang;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView info;
    //Facebook Login button
    private LoginButton loginButton;
    //Will manage the callback from the facebook authentication
    private CallbackManager callbackManager;
    private boolean isUserProfileStarted = false;
    private AccessTokenTracker accessTokenTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.hide();
        //Initializes the Facebook SDK allowing for use of the Facebook login within the app
        AppEventsLogger.activateApp(this);
        //Creates the callback manager
        callbackManager = CallbackManager.Factory.create();
        info = (TextView)findViewById(R.id.infotext_login);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_education_history");

        //Creates an access token providing secure access
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken == null){
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

                @Override
                public void onSuccess(LoginResult loginResult) {
                    info.setText("User ID: "
                            + loginResult.getAccessToken().getUserId()
                            + "\n" +
                            "Auth Token: "
                            + loginResult.getAccessToken().getToken()
                    );

                    Intent userProfile = new Intent(MainActivity.this, UserProfile.class);
                    if(!isUserProfileStarted) {
                        startActivity(userProfile);
                        isUserProfileStarted = true;
                    }

                }

                @Override
                public void onCancel() {
                    info.setText("Login attempt canceled.");
                }

                @Override
                public void onError(FacebookException e) {
                    info.setText("Login attempt failed.");
                }
            });
        } else {
            Intent userProfile = new Intent(MainActivity.this, UserProfile.class);
            if(!isUserProfileStarted) {
                startActivity(userProfile);
                isUserProfileStarted = true;
            }
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



}
