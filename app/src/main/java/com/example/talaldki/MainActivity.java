package com.example.talaldki;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random random;
    int randomSzam, tippekSzama;
    ImageView elet1, elet2, elet3;
    EditText tipp;
    Button ellen;
    TextView eredmeny;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ellen.setOnClickListener(v -> {
            if (tipp.getText().toString().isEmpty()) {
                tipp.setError("Nem adtál meg számot!");
                return;
            }
            int tippSzam = Integer.parseInt(tipp.getText().toString());
            if (tippSzam < 1 || tippSzam > 10) {
                tipp.setError("1 és 10 közötti számot adj meg!");
                return;
            }
            tippekSzama++;
            if (tippSzam == randomSzam) {
                eredmeny.setText("Gratulálok, eltaláltad " + tippekSzama + " tippből!");
                ellen.setEnabled(false);
            } else if (tippSzam < randomSzam) {
                eredmeny.setText("A gondolt szám nagyobb mint " + tippSzam);
                kepcsere();
            } else if (tippSzam > randomSzam) {
                eredmeny.setText("A gondolt szám kisebb mint " + tippSzam);
                kepcsere();
            }


            tipp.setText("");
        });
    }

    void init() {
        random = new Random();
        randomSzam = random.nextInt(10) + 1;
        tippekSzama = 0;
        ellen = findViewById(R.id.Ellenorzes);
        tipp = findViewById(R.id.Tipp);
        elet1 = findViewById(R.id.elet1);
        elet2 = findViewById(R.id.elet2);
        elet3 = findViewById(R.id.elet3);
        eredmeny = findViewById(R.id.eredmeny);
    }

    void kepcsere() {
        if (tippekSzama == 1) {
            elet3.setImageResource(R.drawable.lost);
        } else if (tippekSzama == 2) {
            elet2.setImageResource(R.drawable.lost);
        } else {
            elet1.setImageResource(R.drawable.lost);
            eredmeny.setText("Sajnos nem találtad el " + tippekSzama + " tippből a gondolt számot! A szám amire gondoltam a(z) " + randomSzam + " volt");
            ellen.setEnabled(false);
        }
    }
}