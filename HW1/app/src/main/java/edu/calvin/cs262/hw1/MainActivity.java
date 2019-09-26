package edu.calvin.cs262.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner operationDropdown;
    String[] dropdownOptions = {"+ (add)", "- (subtract)", "* (multiply)", "/ (divide)"};
    Button calculate;
    EditText value1;
    EditText value2;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set dropdown items for spinner to dropdownOptions array
        operationDropdown = (Spinner) findViewById(R.id.operator_dropdown);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dropdownOptions);
        operationDropdown.setAdapter(adapter);

        // Assign components to corresponding ids
        calculate = (Button)findViewById(R.id.calc_button);
        value1   = (EditText)findViewById(R.id.value_1);
        value2   = (EditText)findViewById(R.id.value_2);
        result = (TextView) findViewById(R.id.result);

        // Update result on calculate
        calculate.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    // determine operation taking place from spinner
                    // Access values to perform operation
                    // Set text to display result
                   String operation = operationDropdown.getSelectedItem().toString();
                   if (operation == "+ (add)") {
                       Integer operationResult = Integer.parseInt(value1.getText().toString()) + Integer.parseInt(value2.getText().toString());
                       result.setText(String.valueOf(operationResult));

                    } else if (operation == "- (subtract)") {
                       Integer operationResult = Integer.parseInt(value1.getText().toString()) - Integer.parseInt(value2.getText().toString());
                       result.setText(String.valueOf(operationResult));

                   } else if (operation == "* (multiply)") {
                       Integer operationResult = Integer.parseInt(value1.getText().toString()) * Integer.parseInt(value2.getText().toString());
                       result.setText(String.valueOf(operationResult));

                   } else if (operation == "/ (divide)") {
                       Float operationResult = Float.parseFloat(value1.getText().toString()) / Float.parseFloat(value2.getText().toString());
                       result.setText(String.valueOf(operationResult));
                   }
                }
            });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // SharedPreferences
        System.out.println("|||||||||||||||INSIDE ON PAUSE|||||||||||||||");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("|||||||||||||||INSIDE ON RESUME|||||||||||||||");
    }
}