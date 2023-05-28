package fr.yayoaka.apptamagotchi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telecom.TelecomManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MonsterGame extends AppCompatActivity {

    ImageView iv_11, iv_12, iv_13,
            iv_21, iv_22, iv_23,
            iv_31, iv_32, iv_33,
            iv_41, iv_42, iv_43,
            iv_51, iv_52, iv_53;
    Button b_play, b_home;
    TextView tv_time, tv_score, tv_best;
    Random r;
    int rockLocationRow1, rockLocationRow2, rockLocationRow3, rockLocationRow4, rockLocationRow5;
    int frameImage, pawInFrameImage, tapImage, emptyImage;
    int currScore = 0;
    int bestScore;
    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_game);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        bestScore = preferences.getInt("highscore", 0);

        iv_11 = (ImageView) findViewById(R.id.iv_11);
        iv_12 = (ImageView) findViewById(R.id.iv_12);
        iv_13 = (ImageView) findViewById(R.id.iv_13);

        iv_21 = (ImageView) findViewById(R.id.iv_21);
        iv_22 = (ImageView) findViewById(R.id.iv_22);
        iv_23 = (ImageView) findViewById(R.id.iv_23);

        iv_31 = (ImageView) findViewById(R.id.iv_31);
        iv_32 = (ImageView) findViewById(R.id.iv_32);
        iv_33 = (ImageView) findViewById(R.id.iv_33);

        iv_41 = (ImageView) findViewById(R.id.iv_41);
        iv_42 = (ImageView) findViewById(R.id.iv_42);
        iv_43 = (ImageView) findViewById(R.id.iv_43);

        iv_51 = (ImageView) findViewById(R.id.iv_51);
        iv_52 = (ImageView) findViewById(R.id.iv_52);
        iv_53 = (ImageView) findViewById(R.id.iv_53);

        b_play = (Button) findViewById(R.id.b_play);
        b_home = (Button) findViewById(R.id.b_home);

        tv_score = (TextView) findViewById(R.id.tv_score);
        tv_score.setText("Score : " + currScore);

        tv_best = (TextView) findViewById(R.id.tv_best);
        tv_best.setText("Best : " + bestScore);

        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_time.setText("Time : " + millisToTime(15000));

        r = new Random();
        loadImage();


        timer = new CountDownTimer(15000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_time.setText("Time : " + millisToTime(millisUntilFinished) + 1);
            }

            @Override
            public void onFinish() {
                tv_time.setText("Time : " + millisToTime(0));

                iv_31.setEnabled(false);
                iv_32.setEnabled(false);
                iv_33.setEnabled(false);
                b_play.setVisibility(View.VISIBLE);
                b_home.setVisibility(View.VISIBLE);

                iv_11.setImageResource(emptyImage);
                iv_12.setImageResource(emptyImage);
                iv_13.setImageResource(emptyImage);

                iv_21.setImageResource(emptyImage);
                iv_22.setImageResource(emptyImage);
                iv_23.setImageResource(emptyImage);

                iv_31.setImageResource(emptyImage);
                iv_32.setImageResource(emptyImage);
                iv_33.setImageResource(emptyImage);

                iv_41.setImageResource(emptyImage);
                iv_42.setImageResource(emptyImage);
                iv_43.setImageResource(emptyImage);

                iv_51.setImageResource(emptyImage);
                iv_52.setImageResource(emptyImage);
                iv_53.setImageResource(emptyImage);

                Toast.makeText(MonsterGame.this, "Game Over !", Toast.LENGTH_SHORT).show();

                if (currScore > bestScore) {
                    bestScore = currScore;
                    tv_best.setText("Best : " + bestScore);

                    SharedPreferences preferences1 = getSharedPreferences("PREFS", 0);
                    SharedPreferences.Editor editor = preferences1.edit();
                    editor.putInt("highscore", bestScore);
                    editor.apply();

                }

                Tamagotchi.currSung = Tamagotchi.currSung + currScore/2;
                if (Tamagotchi.currBonheur <= 48) {
                    Tamagotchi.currBonheur = Tamagotchi.currBonheur + 2;
                }
                Tamagotchi.saveJsonToFile(getApplicationContext());

            }
        };

        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rockLocationRow3 == 1) {
                    continueGame();
                } else {
                    endGame();
                }
            }
        });
        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rockLocationRow3 == 2) {
                    continueGame();
                } else {
                    endGame();
                }
            }
        });
        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rockLocationRow3 == 3) {
                    continueGame();
                } else {
                    endGame();
                }
            }
        });

        b_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGame();
            }
        });

        Button button_home = findViewById(R.id.b_home);

        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Home);
                finish();
            }
        });
    }

    private void continueGame() {
        rockLocationRow5 = rockLocationRow4;
        setRockLocation(rockLocationRow5, 5);

        rockLocationRow4 = rockLocationRow3;
        setRockLocation(rockLocationRow4, 4);

        rockLocationRow3 = rockLocationRow2;
        setRockLocation(rockLocationRow3, 3);

        rockLocationRow2 = rockLocationRow1;
        setRockLocation(rockLocationRow2, 2);

        rockLocationRow1 = r.nextInt(3) + 1;
        setRockLocation(rockLocationRow1, 1);

        currScore++;
        tv_score.setText("Score : " + currScore);
    }

    private void initGame() {
        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        b_play.setVisibility(View.INVISIBLE);
        b_home.setVisibility(View.INVISIBLE);

        currScore = 0;
        tv_score.setText("Score : " + currScore);

        timer.start();

        rockLocationRow4 = 2;
        iv_42.setImageResource(pawInFrameImage);

        rockLocationRow3 = 2;
        iv_32.setImageResource(tapImage);

        rockLocationRow2 = r.nextInt(3) + 1;
        setRockLocation(rockLocationRow2, 2);

        rockLocationRow1 = r.nextInt(3) + 1;
        setRockLocation(rockLocationRow1, 1);
    }

    private void endGame() {
        timer.cancel();

        iv_31.setEnabled(false);
        iv_32.setEnabled(false);
        iv_33.setEnabled(false);
        b_play.setVisibility(View.VISIBLE);
        b_home.setVisibility(View.VISIBLE);

        iv_11.setImageResource(emptyImage);
        iv_12.setImageResource(emptyImage);
        iv_13.setImageResource(emptyImage);

        iv_21.setImageResource(emptyImage);
        iv_22.setImageResource(emptyImage);
        iv_23.setImageResource(emptyImage);

        iv_31.setImageResource(emptyImage);
        iv_32.setImageResource(emptyImage);
        iv_33.setImageResource(emptyImage);

        iv_41.setImageResource(emptyImage);
        iv_42.setImageResource(emptyImage);
        iv_43.setImageResource(emptyImage);

        iv_51.setImageResource(emptyImage);
        iv_52.setImageResource(emptyImage);
        iv_53.setImageResource(emptyImage);

        Toast.makeText(MonsterGame.this, "Failed !", Toast.LENGTH_SHORT).show();

        Tamagotchi.currSung = Tamagotchi.currSung + currScore/2;
        Tamagotchi.saveJsonToFile(getApplicationContext());

    }

    private void setRockLocation(int place, int row) {
        if (row == 1) {
            iv_11.setImageResource(emptyImage);
            iv_12.setImageResource(emptyImage);
            iv_13.setImageResource(emptyImage);

            switch (place) {
                case 1:
                    iv_11.setImageResource(frameImage);
                    break;
                case 2:
                    iv_12.setImageResource(frameImage);
                    break;
                case 3:
                    iv_13.setImageResource(frameImage);
                    break;
            }
        }
        if (row == 2) {
            iv_21.setImageResource(emptyImage);
            iv_22.setImageResource(emptyImage);
            iv_23.setImageResource(emptyImage);

            switch (place) {
                case 1:
                    iv_21.setImageResource(frameImage);
                    break;
                case 2:
                    iv_22.setImageResource(frameImage);
                    break;
                case 3:
                    iv_23.setImageResource(frameImage);
                    break;
            }
        }
        if (row == 3) {
            iv_31.setImageResource(emptyImage);
            iv_32.setImageResource(emptyImage);
            iv_33.setImageResource(emptyImage);

            switch (place) {
                case 1:
                    iv_31.setImageResource(tapImage);
                    break;
                case 2:
                    iv_32.setImageResource(tapImage);
                    break;
                case 3:
                    iv_33.setImageResource(tapImage);
                    break;
            }
        }
        if (row == 4) {
            iv_41.setImageResource(emptyImage);
            iv_42.setImageResource(emptyImage);
            iv_43.setImageResource(emptyImage);

            switch (place) {
                case 1:
                    iv_41.setImageResource(pawInFrameImage);
                    break;
                case 2:
                    iv_42.setImageResource(pawInFrameImage);
                    break;
                case 3:
                    iv_43.setImageResource(pawInFrameImage);
                    break;
            }
        }
        if (row == 5) {
            iv_51.setImageResource(emptyImage);
            iv_52.setImageResource(emptyImage);
            iv_53.setImageResource(emptyImage);

            switch (place) {
                case 1:
                    iv_51.setImageResource(frameImage);
                    break;
                case 2:
                    iv_52.setImageResource(frameImage);
                    break;
                case 3:
                    iv_53.setImageResource(frameImage);
                    break;
            }
        }
    }

    private int millisToTime(long millis) {
        return (int) millis / 1000;
    }

    private void loadImage() {
        frameImage = R.drawable.pictos_frame;
        pawInFrameImage = R.drawable.pictos_paw_frame;
        tapImage = R.drawable.pictos_tap;
        emptyImage = R.drawable.pictos_empty;
    }

}
