package edu.calvin.cs262.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner operationDropdown;
    private final String[]  dropdownOptions = {"+ (add)", "- (subtract)", "* (multiply)", "/ (divide)"};
    private EditText value1;
    private EditText value2;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set dropdown items for spinner to dropdownOptions array
        operationDropdown = findViewById(R.id.operator_dropdown);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dropdownOptions);
        operationDropdown.setAdapter(adapter);

        // Assign components to corresponding ids
        Button calculate = findViewById(R.id.calc_button);
        value1 = findViewById(R.id.value_1);
        value2 = findViewById(R.id.value_2);
        result = findViewById(R.id.result);

        // Update result on calculate
        calculate.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        // determine operation taking place from spinner
                        // Access values to perform operation
                        // Set text to display result
                        String operation = operationDropdown.getSelectedItem().toString();
                        Integer operationResult;
                        switch(operation) {
                            case "+ (add)":
                                operationResult = Integer.parseInt(value1.getText().toString()) + Integer.parseInt(value2.getText().toString());
                                result.setText(String.valueOf(operationResult));
                                break;
                            case "- (subtract)":
                                operationResult = Integer.parseInt(value1.getText().toString()) - Integer.parseInt(value2.getText().toString());
                                result.setText(String.valueOf(operationResult));
                                break;
                            case "* (multiply)":
                                operationResult = Integer.parseInt(value1.getText().toString()) * Integer.parseInt(value2.getText().toString());
                                result.setText(String.valueOf(operationResult));
                                break;
                            case "/ (divide)":
                                Float operationResultDiv = Float.parseFloat(value1.getText().toString()) / Float.parseFloat(value2.getText().toString());
                                result.setText(String.valueOf(operationResultDiv));
                                break;
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