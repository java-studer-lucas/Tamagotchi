package fr.yayoaka.apptamagotchi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Shop extends AppCompatActivity {


    Button shop_b_32, shop_b_22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);


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
                textJaugeBonheur.setText("Bonheur : " + Tamagotchi.currBonheur + "/50");
                textSungMoney.setText("Sung : " + Tamagotchi.currSung);
                Intent Home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Home);
                finish();
            }
        });



        Button breadShopBuy = findViewById(R.id.shop_b_21);

        breadShopBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tamagotchi.currSung >= 50) {
                    Inventaire.currBread++;
                    Tamagotchi.currSung = Tamagotchi.currSung - 50;
                    Inventaire.saveJsonToFile(getApplicationContext());
                    Tamagotchi.saveJsonToFile(getApplicationContext());
                    textSungMoney.setText("Sung : " + Tamagotchi.currSung);
                    Toast.makeText(getApplicationContext(),"nb pain:" + Inventaire.currBread, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button igrisShopBuy = findViewById(R.id.shop_b_22);

        igrisShopBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tamagotchi.currSung >= 200) {
                    Inventaire.currIgris++;
                    Tamagotchi.currSung = Tamagotchi.currSung - 200;
                    Inventaire.saveJsonToFile(getApplicationContext());
                    Tamagotchi.saveJsonToFile(getApplicationContext());
                    textSungMoney.setText("Sung : " + Tamagotchi.currSung);
                    shop_b_22 = (Button) findViewById(R.id.shop_b_22);
                    shop_b_22.setVisibility(View.INVISIBLE);
                }
            }
        });

        Button meatShopBuy = findViewById(R.id.shop_b_31);

        meatShopBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tamagotchi.currSung >= 100) {
                    Inventaire.currMeat++;
                    Tamagotchi.currSung = Tamagotchi.currSung - 100;
                    Inventaire.saveJsonToFile(getApplicationContext());
                    Tamagotchi.saveJsonToFile(getApplicationContext());
                    textSungMoney.setText("Sung : " + Tamagotchi.currSung);
                }
            }
        });

        Button beruShopBuy = findViewById(R.id.shop_b_32);

        beruShopBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tamagotchi.currSung >= 200) {
                    Inventaire.currBeru++;
                    Tamagotchi.currSung = Tamagotchi.currSung - 200;
                    Inventaire.saveJsonToFile(getApplicationContext());
                    Tamagotchi.saveJsonToFile(getApplicationContext());
                    textSungMoney.setText("Sung : " + Tamagotchi.currSung);
                    shop_b_32 = (Button) findViewById(R.id.shop_b_32);
                    shop_b_32.setVisibility(View.INVISIBLE);
                }
            }
        });

        Button iceShopBuy = findViewById(R.id.shop_b_41);

        iceShopBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tamagotchi.currSung >= 150) {
                    Inventaire.currIce++;
                    Tamagotchi.currSung = Tamagotchi.currSung - 150;
                    Inventaire.saveJsonToFile(getApplicationContext());
                    Tamagotchi.saveJsonToFile(getApplicationContext());
                    textSungMoney.setText("Sung : " + Tamagotchi.currSung);
                }
            }
        });


        Button shop_return_home = findViewById(R.id.return_home);

        shop_return_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Home);
                finish();
            }
        });

    }
}
