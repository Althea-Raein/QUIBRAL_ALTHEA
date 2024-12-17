package com.example.implicitintents_quibral_a;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mWebsiteEditText=findViewById(R.id.website_edittext);
        mLocationEditText=findViewById(R.id.location_edittext);
        mShareTextEditText=findViewById(R.id.share_edittext);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void openWebsite(View view) {
        //Get the URL text.
        String url=mWebsiteEditText.getText().toString();

        //Parse the URI and create the intent.
        Uri webpage=Uri.parse(url);
        Intent intent= new Intent(Intent.ACTION_VIEW, webpage);

        //Find an activity to hand the intent and start the activity.
        if (intent.resolveActivity(getPackageManager()) !=null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }

    }

    public void openLocation(View view) {
        //Get the string indicating a location. Input is not validated; it is
        //passed to the location handler intact.
        String loc= mLocationEditText.getText().toString();

        //Parse the location and create the intent.
        Uri addressUri= Uri.parse("geo:0,0?q=" +loc);
        Intent intent=new Intent(Intent.ACTION_VIEW, addressUri);

        //Find an activity to handle the intent, and start the activity.
        if(intent.resolveActivity(getPackageManager()) !=null) {
            startActivity(intent);
        }else {
            Log.d("ImplicitIntent", "Can't handle this intent!");
        }
    }

    public void shareText(View view) {
        String txt= mShareTextEditText.getText().toString();
        String mimeType= "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_this_text_with)
                .setText(txt)
                .startChooser();
    }
}



