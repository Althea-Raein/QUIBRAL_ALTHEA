package com.example.droidcafe;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrderActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //...Rest of onCreate code...
        //Create the spinner.
        Spinner spinner=findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        //Create ArrayAdapter using  the string array and default spinner layout.
        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choice appears.
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        //Apply the adapter to the spinner.
        if (spinner !=null) {
            spinner.setAdapter(adapter);
        }
        //...End of onCreate code...
    }

    public void onRadioButtonClicked(View view) {
        //Is the button now checked?
        boolean checked= ((RadioButton)view).isChecked();
        //Check which radio button ws clicked.
        switch (view.getId()) {
            case R.id.sameday:
                if (checked)
                    //Small day service
                    displayToast(getString(R.string.same_day_messenger_service));
                break;
            case R.id.nextday:
                if (checked)
                    //Next day delivery
                    displayToast(getString(R.string.next_day_ground_delivery));
                break;
            case R.id.pickup:
                if (checked)
                    //Pick up
                    displayToast(getString(R.string.pick_up));
                break;
            default:
                //Do nothing.
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int
            i, long l) {
        String  spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}