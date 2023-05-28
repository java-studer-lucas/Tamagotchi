package fr.yayoaka.apptamagotchi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.json.JSONStringer;

public class MainActivity extends AppCompatActivity {

    private Button second_game;
    private Button first_game;
    private Button inv;
    private Button shop;
    private Tamagotchi currTamagotchi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try {
            Tamagotchi.readJsonFromFile(getApplicationContext());
            // astucepour forcer l'ecrasement du fichier JSON en reinit
            // throw new Exception("force reinit");
        }
        catch (Exception e) {

            // si fichier introuvable
            Tamagotchi.refreshGame(getApplicationContext(), 1);

        }

        if (Tamagotchi.currFaim <= 0) {
            // gérer la mort du tamagotchi
            ImageView imageView = findViewById(R.id.sung_tamagotchi);
            imageView.setImageResource(R.drawable.sung_img_game_over);
            first_game = (Button) findViewById(R.id.first_game);
            first_game.setVisibility(View.INVISIBLE);
            inv = (Button) findViewById(R.id.inv);
            inv.setVisibility(View.INVISIBLE);
            shop = (Button) findViewById(R.id.shop);
            shop.setVisibility(View.INVISIBLE);
            second_game = (Button) findViewById(R.id.second_game);
            second_game.setVisibility(View.INVISIBLE);
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

        if (Inventaire.currIgris == 2 & Inventaire.currBeru == 1) {
            ImageView imageView = findViewById(R.id.sung_tamagotchi);
            imageView.setImageResource(R.drawable.sung_img_igris);
        }
        if (Inventaire.currBeru == 2 & Inventaire.currIgris == 1) {
            ImageView imageView = findViewById(R.id.sung_tamagotchi);
            imageView.setImageResource(R.drawable.sung_img_beru);
        }
        if (Inventaire.currIgris == 1 & Inventaire.currBeru == 1) {
            ImageView imageView = findViewById(R.id.sung_tamagotchi);
            imageView.setImageResource(R.drawable.sung_img);
        }
        if (Inventaire.currBeru == 2 & Inventaire.currIgris == 2) {
            ImageView imageView = findViewById(R.id.sung_tamagotchi);
            imageView.setImageResource(R.drawable.sung_img_all);
        }



        Button second_game = findViewById(R.id.second_game);

        second_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cookieGame = new Intent(getApplicationContext(), cookieActivity.class);
                startActivity(cookieGame);
                finish();
            }
        });

        Button first_game = findViewById(R.id.first_game);

        first_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent first_game = new Intent(getApplicationContext(), MonsterGame.class);
                startActivity(first_game);
                finish();
            }
        });

        Button inv = findViewById(R.id.inv);
        inv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inventaire = new Intent(getApplicationContext(), InventaireActivity.class);
                startActivity(inventaire);
                finish();
            }
        });



        Button shop = findViewById(R.id.shop);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shopActivity = new Intent(getApplicationContext(), Shop.class);
                startActivity(shopActivity);
                finish();
            }
        });

    }
}