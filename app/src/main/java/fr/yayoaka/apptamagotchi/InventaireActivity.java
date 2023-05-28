package fr.yayoaka.apptamagotchi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class InventaireActivity extends AppCompatActivity {

    Button inventaire_b_21, inventaire_b_22, inventaire_b_hide_21, inventaire_b_hide_22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventaire);


        try {
            Inventaire.readJsonFromFile(getApplicationContext());
            // astucepour forcer l'ecrasement du fichier JSON en reinit
            // throw new Exception("force reinit");
        }
        catch (Exception e) {
            // si fichier introuvable
            Tamagotchi.refreshGame(getApplicationContext(), 2);
        }


        // initialisé l'interface Nav Bar
        TextView textJaugeFaim =  findViewById(R.id.faim);
        textJaugeFaim.setText("Faim : " + Tamagotchi.currFaim + "/50");
        TextView textJaugeBonheur =  findViewById(R.id.bonheur);
        textJaugeBonheur.setText("Bonheur : " + Tamagotchi.currBonheur + "/50");
        TextView textSungMoney =  findViewById(R.id.sung);
        textSungMoney.setText("Sung : " + Tamagotchi.currSung);
        Button reloadBtn = findViewById(R.id.quest_button);
        reloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tamagotchi.refreshGame(getApplicationContext(), 3);
                Intent Home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Home);
                finish();
            }
        });

        //Affichage des valeurs dans l'inventaire depuis le json
        TextView textBread =  findViewById(R.id.inventaire_tv_11);
        textBread.setText(Inventaire.currBread + " Pain (+4)");
        TextView textMeat =  findViewById(R.id.inventaire_tv_12);
        textMeat.setText(Inventaire.currMeat + " Viande (+7)");
        TextView textIce =  findViewById(R.id.inventaire_tv_13);
        textIce.setText(Inventaire.currIce + " Glace (+10)");


        Button breadInvUse = findViewById(R.id.inventaire_b_11);

        breadInvUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tamagotchi.currFaim <= 46 & Inventaire.currBread >= 1) {
                    Tamagotchi.currFaim = Tamagotchi.currFaim + 4;
                    Inventaire.currBread = Inventaire.currBread - 1;
                    Inventaire.saveJsonToFile(getApplicationContext());
                    Tamagotchi.saveJsonToFile(getApplicationContext());
                    textBread.setText(Inventaire.currBread + " Pain (+4)");
                    textJaugeFaim.setText("Faim : " + Tamagotchi.currFaim + "/50");
                }
            }
        });

        Button meatInvUse = findViewById(R.id.inventaire_b_12);

        meatInvUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tamagotchi.currFaim <= 43 & Inventaire.currMeat >= 1) {
                    Tamagotchi.currFaim = Tamagotchi.currFaim + 7;
                    Inventaire.currMeat = Inventaire.currMeat - 1;
                    Inventaire.saveJsonToFile(getApplicationContext());
                    Tamagotchi.saveJsonToFile(getApplicationContext());
                    textMeat.setText(Inventaire.currMeat + " Meat (+7)");
                    textJaugeFaim.setText("Faim : " + Tamagotchi.currFaim + "/50");
                }
            }
        });

        Button iceInvUse = findViewById(R.id.inventaire_b_13);

        iceInvUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tamagotchi.currFaim <= 40 & Inventaire.currIce >= 1) {
                    Tamagotchi.currFaim = Tamagotchi.currFaim + 10;
                    Inventaire.currIce = Inventaire.currIce - 1;
                    Inventaire.saveJsonToFile(getApplicationContext());
                    Tamagotchi.saveJsonToFile(getApplicationContext());
                    textIce.setText(Inventaire.currIce + " Ice (+10)");
                    textJaugeFaim.setText("Faim : " + Tamagotchi.currFaim + "/50");
                }
            }
        });

        Button igrisInvAff = findViewById(R.id.inventaire_b_21);

        igrisInvAff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Inventaire.currIgris == 1) {
                    TextView textChangColor = findViewById(R.id.inventaire_tv_21);
                    textChangColor.setTextColor(Color.GREEN);
                    Inventaire.currIgris++;
                    Inventaire.saveJsonToFile(getApplicationContext());
                    inventaire_b_21 = (Button) findViewById(R.id.inventaire_b_21);
                    inventaire_b_21.setVisibility(View.INVISIBLE);
                    inventaire_b_hide_21 = (Button) findViewById(R.id.inventaire_b_hide_21);
                    inventaire_b_hide_21.setVisibility(View.VISIBLE);
                }
            }
        });

        Button igrisInvHide = findViewById(R.id.inventaire_b_hide_21);

        igrisInvHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Inventaire.currIgris == 2) {
                    TextView textChangColor = findViewById(R.id.inventaire_tv_21);
                    textChangColor.setTextColor(Color.RED);
                    Inventaire.currIgris--;
                    Inventaire.saveJsonToFile(getApplicationContext());
                    inventaire_b_hide_21 = (Button) findViewById(R.id.inventaire_b_hide_21);
                    inventaire_b_hide_21.setVisibility(View.INVISIBLE);
                    inventaire_b_21 = (Button) findViewById(R.id.inventaire_b_21);
                    inventaire_b_21.setVisibility(View.VISIBLE);
                }
            }
        });


        Button beruInvAff = findViewById(R.id.inventaire_b_22);

        beruInvAff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Inventaire.currBeru == 1) {
                    TextView textChangColor = findViewById(R.id.inventaire_tv_22);
                    textChangColor.setTextColor(Color.GREEN);
                    Inventaire.currBeru++;
                    Inventaire.saveJsonToFile(getApplicationContext());
                    inventaire_b_22 = (Button) findViewById(R.id.inventaire_b_22);
                    inventaire_b_22.setVisibility(View.INVISIBLE);
                    inventaire_b_hide_22 = (Button) findViewById(R.id.inventaire_b_hide_22);
                    inventaire_b_hide_22.setVisibility(View.VISIBLE);
                }
            }
        });

        Button beruInvHide = findViewById(R.id.inventaire_b_hide_22);

        beruInvHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Inventaire.currBeru == 2) {
                    TextView textChangColor = findViewById(R.id.inventaire_tv_22);
                    textChangColor.setTextColor(Color.RED);
                    Inventaire.currBeru--;
                    Inventaire.saveJsonToFile(getApplicationContext());
                    inventaire_b_hide_22 = (Button) findViewById(R.id.inventaire_b_hide_22);
                    inventaire_b_hide_22.setVisibility(View.INVISIBLE);
                    inventaire_b_22 = (Button) findViewById(R.id.inventaire_b_22);
                    inventaire_b_22.setVisibility(View.VISIBLE);
                }
            }
        });




        Button inv_return_home = findViewById(R.id.return_home);

        inv_return_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Home);
                finish();
            }
        });
    }
}


