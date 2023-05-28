package fr.yayoaka.apptamagotchi;

public class JaugeFaim implements Jauge {

    int maxJauge = 50;
    int minJauge = 0;


    @Override
    public int decrement(int currFaim) {
        currFaim = currFaim -1;
        if (currFaim < 0) {
            currFaim = 0;
        }
        return currFaim;
    }

    public int increment(int currFaim, String jaugeAppendFaim) {
        int iJaugeAppend = 0;
        switch (jaugeAppendFaim){
            case "pain": iJaugeAppend = 5; break;
            case "steak": iJaugeAppend = 4; break;
            case "burger": iJaugeAppend = 8; break;
            case "dessert": iJaugeAppend = 10; break;
        }
        currFaim = currFaim + iJaugeAppend;
        if (currFaim > 50) {
            currFaim = 50;
        }
        return currFaim;
    }

}
