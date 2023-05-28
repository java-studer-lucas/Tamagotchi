package fr.yayoaka.apptamagotchi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class cookieActivity extends AppCompatActivity {

    private TextView points;
    private ImageView cookie;
    private Button b_swap_faim, b_swap_sung, b_swap_bonheur;
    private cookieActivity currActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie);



        // initialisé l'interface Nav Bar
        ZoneId parisZone = ZoneId.of("Europe/Paris");
        ZoneOffset parisOffset = parisZone.getRules().getOffset(java.time.Instant.now());
        LocalDateTime initDate = LocalDateTime.now(parisOffset);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = initDate.format(formatter);
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
                textJaugeFaim.setText("Faim : " + Tamagotchi.currFaim + "/50");
                Intent Home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Home);
                finish();
            }
        });




        if (!(Tamagotchi.clicksCookie > 0)) {
            Tamagotchi.clicksCookie = 0;
        }

        this.currActivity = this;

        this.points = (TextView) findViewById(R.id.point_cookie);
        this.cookie = (ImageView) findViewById(R.id.cookie_clicker);


        points.setText("Points : " + Tamagotchi.clicksCookie);

        cookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tamagotchi.clicksCookie++;
                points.setText("Points : " + Tamagotchi.clicksCookie);
                Tamagotchi.saveJsonToFile(getApplicationContext());
            }
        });


        Button btnSwapSung = findViewById(R.id.b_swap_sung);

        btnSwapSung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tamagotchi.clicksCookie >= 10) {
                    Tamagotchi.clicksCookie = Tamagotchi.clicksCookie - 10;
                    points.setText("Points : " + Tamagotchi.clicksCookie);
                    Tamagotchi.currSung++;
                    textSungMoney.setText("Sung : " + Tamagotchi.currSung);
                    Tamagotchi.saveJsonToFile(getApplicationContext());
                }

            }
        });

        Button btnSwapFaim = findViewById(R.id.b_swap_faim);

        btnSwapFaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tamagotchi.clicksCookie >= 100 & Tamagotchi.currFaim < 48) {
                    Tamagotchi.clicksCookie = Tamagotchi.clicksCookie - 100;
                    points.setText("Points : " + Tamagotchi.clicksCookie);
                    Tamagotchi.currFaim = Tamagotchi.currFaim + 2;
                    textJaugeFaim.setText("Faim : " + Tamagotchi.currFaim + "/50");
                    Tamagotchi.saveJsonToFile(getApplicationContext());
                }

            }
        });

        Button btnSwapBonheur = findViewById(R.id.b_swap_bonheur);


        btnSwapBonheur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tamagotchi.clicksCookie >= 40 & Tamagotchi.currBonheur < 48) {
                    Tamagotchi.clicksCookie = Tamagotchi.clicksCookie - 40;
                    points.setText("Points : " + Tamagotchi.clicksCookie);
                    Tamagotchi.currBonheur = Tamagotchi.currBonheur + 2;
                    textJaugeBonheur.setText("Bonheur : " + Tamagotchi.currBonheur + "/50");
                    Tamagotchi.saveJsonToFile(getApplicationContext());
                }

            }
        });



        Button second_game_to_home = findViewById(R.id.second_game_return_home);

        second_game_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Home);
                finish();
            }
        });

    }

}