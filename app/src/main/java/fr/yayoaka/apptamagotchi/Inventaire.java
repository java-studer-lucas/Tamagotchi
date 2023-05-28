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


public class Inventaire {

    public static int currBread;
    public static int currMeat;
    public static int currIce;
    public static int currIgris;
    public static int currBeru;
    public static String fileName = "tamagotchi-data.json";




    public static String convertToJson() {
        JSONObject saveJson = new JSONObject();
        try {
            saveJson.put("pain", currBread);
            saveJson.put("viande", currMeat);
            saveJson.put("glace", currIce);
            saveJson.put("igris", currIgris);
            saveJson.put("beru", currBeru);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return saveJson.toString();
    }

    public static void saveJsonToFile(Context context) {
        try {
            String json = convertToJson();
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(json.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(context,"data inv saved !", Toast.LENGTH_LONG).show();
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
            parseJsonToInventaire(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void parseJsonToInventaire(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            currBread = jsonObject.getInt("pain");
            currMeat = jsonObject.getInt("viande");
            currIce = jsonObject.getInt("glace");
            currIgris = jsonObject.getInt("igris");
            currBeru = jsonObject.getInt("beru");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}