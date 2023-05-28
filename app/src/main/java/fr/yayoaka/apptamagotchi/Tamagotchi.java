package fr.yayoaka.apptamagotchi;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Tamagotchi{

    public static int currFaim;
    public static int currBonheur;
    public static String lastTimeFaimDec;
    public static String lastTimeBonheurDec;
    public static int currSung;
    public static int  clicksCookie;
    public static String fileName = "tamagotchi-data.json";



    public static String convertToJson(int diffHeuresF, int diffHeuresB) {
        JSONObject saveJson = new JSONObject();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            saveJson.put("faim", currFaim - diffHeuresF);
            if (currFaim <= 0) {
                // gérer la mort du tamagotchi
            }

            LocalDateTime dateTimeF = LocalDateTime.parse(lastTimeFaimDec, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            // Ajouter des heures à la date
            LocalDateTime newDateTimeF = dateTimeF.plusHours(diffHeuresF);
            lastTimeFaimDec = newDateTimeF.format(formatter);
            saveJson.put("last_faim", lastTimeFaimDec);


            saveJson.put("bonheur", currBonheur - (diffHeuresB*2));

            LocalDateTime dateTimeB = LocalDateTime.parse(lastTimeBonheurDec, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            // Ajouter des heures à la date
            LocalDateTime newDateTimeB = dateTimeB.plusHours(diffHeuresB);
            lastTimeBonheurDec = newDateTimeB.format(formatter);
            saveJson.put("last_bonheur", lastTimeBonheurDec);

            saveJson.put("sung", currSung);
            saveJson.put("clicks_cookie", clicksCookie);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return saveJson.toString();
    }
    public static void saveJsonToFile(Context context) {
        try {
            int intHeuresF = checkDiffDate();
            int intHeuresB = checkDiffDate();
            Toast.makeText(context,"Nb heures F : " + intHeuresF, Toast.LENGTH_SHORT).show();


            String json = convertToJson(intHeuresF, intHeuresB);
            Toast.makeText(context,"Last Faim 5 : " + lastTimeFaimDec, Toast.LENGTH_SHORT).show();
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(json.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(context,"data saved !", Toast.LENGTH_LONG).show();
    }

    public static void readJsonFromFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            parseJsonToTamagotchi(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void parseJsonToTamagotchi(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            currFaim = jsonObject.getInt("faim");
            lastTimeFaimDec = jsonObject.getString("last_faim");
            currBonheur = jsonObject.getInt("bonheur");
            lastTimeBonheurDec = jsonObject.getString("last_bonheur");
            currSung = jsonObject.getInt("sung");
            clicksCookie = jsonObject.getInt("clicks_cookie");
        } catch (JSONException e) {
            e.printStackTrace();
        }}



    public static int checkDiffDate() {
        ZoneId parisZone = ZoneId.of("Europe/Paris");
        ZoneOffset parisOffset = parisZone.getRules().getOffset(java.time.Instant.now());



        Date dateNow = new Date();
        //Date dateLast = new Date(lastTimeFaimDec);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateLast = LocalDateTime.parse(lastTimeFaimDec, formatter);
        long milliLast = dateLast.toInstant(parisOffset).toEpochMilli();
        long diffInMillis = dateNow.getTime() - milliLast;

        float nbHeures = diffInMillis / (1000 * 60 * 60); // Conversion en heures

        int intHeures = (int) Math.floor(nbHeures);

        return intHeures;
    }

    /**
     * Reinit les données des objets et de leur JSON
     * @param context
     * @param mode  1=Tamagotchi, 2=Inventaire, 3= les 2
     */
    public static void refreshGame(Context context, int mode) {
        ZoneId parisZone = ZoneId.of("Europe/Paris");
        ZoneOffset parisOffset = parisZone.getRules().getOffset(java.time.Instant.now());
        LocalDateTime initDate = LocalDateTime.now(parisOffset);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = initDate.format(formatter);

        if(mode==1 || mode==3) {
            Tamagotchi.currFaim = 50;
            Tamagotchi.currBonheur = 50;
            Tamagotchi.lastTimeFaimDec = formattedDate; // date complete a obtenir
            Tamagotchi.lastTimeBonheurDec = formattedDate;
            Tamagotchi.currSung = 1000;
            Tamagotchi.clicksCookie = 0;
            Tamagotchi.saveJsonToFile(context);
        }

        if(mode==2 || mode==3) {
            Inventaire.currBread = 0;
            Inventaire.currMeat = 0;
            Inventaire.currIce = 0;
            Inventaire.currIgris = 0;
            Inventaire.currBeru = 0;
            Inventaire.saveJsonToFile(context);
        }

    }
}