package com.example.conversormonedas;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAmount;
    private Spinner spinnerFromCurrency, spinnerToCurrency;
    private Button buttonConvert;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAmount = findViewById(R.id.editText_amount);
        spinnerFromCurrency = findViewById(R.id.spinner_from_currency);
        spinnerToCurrency = findViewById(R.id.spinner_to_currency);
        buttonConvert = findViewById(R.id.button_convert);
        textViewResult = findViewById(R.id.textView_result);

        // Configuración del Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromCurrency.setAdapter(adapter);
        spinnerToCurrency.setAdapter(adapter);

        // Listener para el botón de conversión
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        String amountString = editTextAmount.getText().toString();
        if (amountString.isEmpty()) {
            textViewResult.setText("Por favor, ingresa una cantidad.");
            return;
        }

        double amount = Double.parseDouble(amountString);
        String fromCurrency = spinnerFromCurrency.getSelectedItem().toString();
        String toCurrency = spinnerToCurrency.getSelectedItem().toString();
        double result = 0;

        // Conversión según las tasas de cambio
        if (fromCurrency.equals("PEN")) {
            if (toCurrency.equals("USD")) {
                result = amount * 0.27; // Tasa de cambio de PEN a USD
            } else if (toCurrency.equals("EUR")) {
                result = amount * 0.24; // Tasa de cambio de PEN a EUR
            }
        } else if (fromCurrency.equals("USD")) {
            if (toCurrency.equals("PEN")) {
                result = amount * 3.75; // Tasa de cambio de USD a PEN
            } else if (toCurrency.equals("EUR")) {
                result = amount * 0.89; // Tasa de cambio de USD a EUR
            }
        } else if (fromCurrency.equals("EUR")) {
            if (toCurrency.equals("PEN")) {
                result = amount * 4.19; // Tasa de cambio de EUR a PEN
            } else if (toCurrency.equals("USD")) {
                result = amount * (1 / 0.93); // Conversión de EUR a USD
            }
        }

        textViewResult.setText(String.format("%.2f %s", result, toCurrency));
    }
}
