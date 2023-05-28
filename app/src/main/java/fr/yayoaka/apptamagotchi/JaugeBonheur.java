package fr.yayoaka.apptamagotchi;

public class JaugeBonheur implements Jauge{


    @Override
    public int decrement(int currBonheur) {
        currBonheur = currBonheur -2;
        if (currBonheur < 0) {
            currBonheur = 0;
        }
        return currBonheur;
    }

    public int increment(int currBonheur, String jaugeAppendJeu) {
        int iJaugeAppend = 0;
        switch (jaugeAppendJeu) {
            case "pianotiles":
                iJaugeAppend = 5;
                break;
        }
        currBonheur = currBonheur + iJaugeAppend;
        if (currBonheur > 50) {
            currBonheur = 50;
        }
        return currBonheur;
    }
}
