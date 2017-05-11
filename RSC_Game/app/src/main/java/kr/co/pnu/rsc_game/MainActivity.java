package kr.co.pnu.rsc_game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int[] imgRes = {R.drawable.scissors, R.drawable.rock, R.drawable.paper};

    int you = 0;
    int com = 0;

    int win = 0;
    int lose = 0;

    ImageView imgViewYou;
    ImageView imgViewCom;

    TextView txtYou;
    TextView txtCom;

    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.fab).setOnClickListener(onButtonClick);
        for (int i = 0 ; i < 3; i++) {
            findViewById(R.id.imageButton1 + i).setOnClickListener(onButtonClick);
        }

        imgViewYou = (ImageView) findViewById(R.id.imageView1);
        imgViewCom = (ImageView) findViewById(R.id.imageView2);

        txtYou = (TextView)findViewById(R.id.textYou);
        txtCom = (TextView)findViewById(R.id.textCom);
        txtResult = (TextView)findViewById(R.id.textResult);

        initGame();
    }

    private void initGame() {
        win = 0;
        lose = 0;

        txtYou.setText("당신 : 0");
        txtCom.setText("단말기 : 0");
        txtResult.setText("");

        imgViewCom.setImageResource(R.drawable.question_mark);
        imgViewYou.setImageResource(R.drawable.question_mark);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "다시시작");
        menu.add(0, 2, 1, "종료");
        menu.add(0, 3, 2, "About");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch( item.getItemId()) {
            case 1:
                initGame();
                break;
            case 2:
                finishAffinity();
                break;
            case 3:
                View v = findViewById(R.id.imageButton1);
                Snackbar.make(v, "가위바위보 ver 1.0", Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        })
                        .show();
        }
        return true;
    }

    Button.OnClickListener onButtonClick = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.fab) {
                return;
            } else {
                String tag = v.getTag().toString();
                you = Integer.parseInt(tag);
                SetGameResult();
            }
        }
    };



    private void SetGameResult() {
        com = new Random().nextInt(3);
        int k = you - com;

        String str = "";
        if (k == 0) {
            str = "비겼습니다.";
        } else if ( k == 1 || k == -2) {
            str = "당신이 이겼습니다.";
            win ++;
        } else {
            str = "당신이 졌습니다.";
            lose++;
        }

        SetImage();

        txtYou.setText("당신 : " + win);
        txtCom.setText("단말기 : " + lose);
        txtResult.setText(str);
    }

    private void SetImage() {
        imgViewYou.setImageResource(imgRes[you]);
        imgViewCom.setImageResource(imgRes[com]);

        Bitmap orgImg = BitmapFactory.decodeResource( getResources(), imgRes[com]);

        Matrix matrix = new Matrix();
        matrix.setScale(-1,1);
        Bitmap revImg = Bitmap.createBitmap(orgImg, 0, 0, orgImg.getWidth(),
                orgImg.getHeight(), matrix, false);
        imgViewCom.setImageBitmap(revImg);
    }

}
