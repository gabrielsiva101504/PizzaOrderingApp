package com.example.pizzaapp;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioSize, radioCrust;
    CheckBox cbCheeseMush, cbTomOnion;
    Button btnProcess, btnNew;
    TextView tvOutput;
    ImageView pizzaImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0); // full grayscale
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        getWindow().getDecorView().getBackground().setColorFilter(filter);

        setContentView(R.layout.activity_main);

        radioSize = findViewById(R.id.radioSize);
        radioCrust = findViewById(R.id.radioCrust);
        cbCheeseMush = findViewById(R.id.cbCheeseMush);
        cbTomOnion = findViewById(R.id.cbTomOnion);
        btnProcess = findViewById(R.id.btnProcess);
        btnNew = findViewById(R.id.btnNew);
        tvOutput = findViewById(R.id.tvOutput);
        pizzaImage = findViewById(R.id.pizzaImage);

        pizzaImage.setImageResource(R.drawable.jack_logo);

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = 0;
                StringBuilder summary = new StringBuilder("🍕 Pizza Order Summary:\n");

                int selectedSizeId = radioSize.getCheckedRadioButtonId();
                if (selectedSizeId != -1) {
                    RadioButton selectedSize = findViewById(selectedSizeId);
                    summary.append("Size: ").append(selectedSize.getText()).append("\n");
                    if (selectedSizeId == R.id.rbSmall) total += 100;
                    if (selectedSizeId == R.id.rbMedium) total += 150;
                    if (selectedSizeId == R.id.rbLarge) total += 200;
                }

                int selectedCrustId = radioCrust.getCheckedRadioButtonId();
                if (selectedCrustId != -1) {
                    RadioButton selectedCrust = findViewById(selectedCrustId);
                    summary.append("Crust: ").append(selectedCrust.getText()).append("\n");
                }

                summary.append("Toppings:\n");
                if (cbCheeseMush.isChecked()) {
                    summary.append("• Cheese & Mushroom (+₱20)\n");
                    total += 20;
                }
                if (cbTomOnion.isChecked()) {
                    summary.append("• Tomato & Onion (+₱15)\n");
                    total += 15;
                }

                summary.append("\nTotal Price: ₱").append(total);
                tvOutput.setText(summary.toString());
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioSize.clearCheck();
                radioCrust.clearCheck();
                cbCheeseMush.setChecked(false);
                cbTomOnion.setChecked(false);
                tvOutput.setText("");
                pizzaImage.setImageResource(R.drawable.jack_logo);
            }
        });
    }
}
