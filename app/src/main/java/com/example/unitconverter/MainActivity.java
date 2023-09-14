package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextValue;
    private Spinner spinnerUnits;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextValue = findViewById(R.id.editTextValue);
        spinnerUnits = findViewById(R.id.spinnerUnits);
        textViewResult = findViewById(R.id.textViewResult);

        // Populate the spinner with unit options (e.g., centimeters, meters, grams, kilograms)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.unit_options,  // Define your string array resource for unit options
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnits.setAdapter(adapter);

        Button buttonConvert = findViewById(R.id.buttonConvert);
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Convert" button click event here
                performConversion();
            }
        });
    }

    private void performConversion() {
        // Retrieve user input from the EditText
        String input = editTextValue.getText().toString().trim();

        if (input.isEmpty()) {
            // Handle the case where the user didn't enter a value
            textViewResult.setText("Please enter a value.");
            return;
        }

        try {
            double inputValue = Double.parseDouble(input);
            String selectedUnit = spinnerUnits.getSelectedItem().toString();

            // Implement the conversion logic based on selectedUnit and inputValue
            // Update textViewResult with the converted result
            // Handle invalid input or unit conversion errors

            // Example conversion:
            if (selectedUnit.equals("Centimeters to Meters")) {
                double result = inputValue / 100.0;
                textViewResult.setText(String.format("%.2f meters", result));
            } else if (selectedUnit.equals("Grams to Kilograms")) {
                double result = inputValue / 1000.0;
                textViewResult.setText(String.format("%.2f kilograms", result));
            }else if (selectedUnit.equals("Meters to Centimeters")) {
                 double result = inputValue * 100.0;
                textViewResult.setText(String.format("%.2f centimeters", result));
            } else if (selectedUnit.equals("Kilograms to Grams")) {
                 double result = inputValue * 1000.0;
                textViewResult.setText(String.format("%.2f grams", result));
            }
        } catch (NumberFormatException e) {
            // Handle the case where the user enters an invalid number
            textViewResult.setText("Invalid input. Please enter a valid number.");
        }
    }
}
