package com.shashankchamoli.dangal;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int p1roundwin=0;
    int p2roundwin=0;
    int p1score=0;
    int p2score=0;
    int round=1;
    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            p1roundwin=savedInstanceState.getInt("p1roundwin");
            p2roundwin=savedInstanceState.getInt("p2roundwin");
            p1score=savedInstanceState.getInt("p1score");
            p2score=savedInstanceState.getInt("p2score");
        }
    }
    public void close(){
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.close:
                finish();
                break;

        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt("p1roundwin",p1roundwin);
        outState.putInt("p2roundwin",p2roundwin);
        outState.putInt("p1score",p1score);
        outState.putInt("p2score",p2score);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public void sam(View v){
        switch(v.getId()){
            case R.id.p1plus1:
                p1score+=1;
                updatescreen();
                break;
            case R.id.p1plus2:
                p1score+=2;
                updatescreen();
                break;
            case R.id.p1plus3:
                p1score+=3;
                updatescreen();
                break;
            case R.id.p1plus5:
                p1score+=5;
                updatescreen();
                break;
            case R.id.p2plus1:
                p2score+=1;
                updatescreen();
                break;
            case R.id.p2plus2:
                p2score+=2;
                updatescreen();
                break;
            case R.id.p2plus3:
                p2score+=3;
                updatescreen();
                break;
            case R.id.p2plus5:
                p2score+=5;
                updatescreen();
                break;
            case R.id.restart:
                p1score=0;
                p2score=0;
                p1roundwin=0;
                p2roundwin=0;
                round=1;
                flag=false;
                updatescreen();
                break;
            case R.id.nextround: {
                if (round <= 3 && flag==false) {


                    if (p1score > p2score) {
                        p1roundwin += 2;
                    } else if (p1score < p2score) {
                        p2roundwin += 2;
                    } else {
                        p1roundwin += 1;
                        p2roundwin += 1;
                    }
                    p1score = 0;
                    p2score = 0;

                    if (round < 3) {

                        round++;
                    } else {
                        flag=true;
                        if (p1roundwin > p2roundwin)
                            Toast.makeText(this, "Player 1 has won the game\npress x to close app\npress restart to start again", Toast.LENGTH_LONG).show();
                        else if (p1roundwin < p2roundwin)
                            Toast.makeText(this, "Player 2 has won the game\npress x to close app\npress restart to start again", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(this, "Its a Draw between player 1 and 2\npress x to close app\npress restart to start again", Toast.LENGTH_LONG).show();
                    }
                    updatescreen();
                }
            }
                break;
        }
    }

    private void updatescreen() {
            Button b = (Button) findViewById(R.id.nextround);
            if (round == 3) {

                b.setText("Finish");
            } else {
                b.setText("Next Round");
            }
            TextView rnd = (TextView) findViewById(R.id.round);
            rnd.setText("Round : " + round);
            TextView p1scr = (TextView) findViewById(R.id.player1score);
            p1scr.setText(p1score + "");
            TextView p2scr = (TextView) findViewById(R.id.player2score);
            p2scr.setText(p2score + "");
            TextView p1rnd = (TextView) findViewById(R.id.p1roundwin);
            p1rnd.setText("Player 1: " + p1roundwin);
            TextView p2rnd = (TextView) findViewById(R.id.p2roundwin);
            p2rnd.setText("Player 2: " + p2roundwin);

    }
}
