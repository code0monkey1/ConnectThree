package com.example.chiranjeev.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int arr[] = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int winning[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean isYellow = false;
    boolean gameEnd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    boolean check() {

        for (int[] ar : winning) {
            if (arr[ar[0]] == arr[ar[1]] && arr[ar[1]] == arr[ar[2]] && arr[ar[0]] != -1) {
                return true;
            }

        }
        return false;
    }

    public boolean gameFull() {

        for (int i = 0; i < 9; i++) {
            if (arr[i] == -1) return false;
        }
        return true;
    }

    public void clicketyClick(View view) {
        Log.i("Game full status",""+gameFull());
        ImageView token = (ImageView) view;
        int index = Integer.parseInt(token.getTag().toString());
        if (arr[index] == -1 && !gameFull() && !gameEnd) {


            token.setTranslationY(-1000);
            token.setImageResource(isYellow ? R.drawable.red : R.drawable.yellow);
            token.animate().translationYBy(1000).rotationBy(1800).setDuration(300);
            if (!isYellow) {
                isYellow = true;
                arr[index] = 1;
                if (check()) {
                    Toast.makeText(this, "Player yellow won", Toast.LENGTH_SHORT).show();
                    gameEnd = true;

                }
            } else {
                isYellow = false;
                arr[index] = 2;
                if (check()) {
                    Toast.makeText(this, "Player red won", Toast.LENGTH_SHORT).show();
                    gameEnd = true;

                }
            }

            if (gameEnd || gameFull()) {

                Button button = (Button) findViewById(R.id.redraw);
                button.setVisibility(View.VISIBLE);
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setVisibility(View.VISIBLE);
                if (check()) textView.setText(isYellow ? "Player yellow won" : "Player red won");
                else textView.setText("Game draw");
            }
        }
    }

    public void playAgain(View view) {
        Log.i("Game full status",""+gameFull());
        //Reset Everything
        Button button = (Button) findViewById(R.id.redraw);
        button.setVisibility(View.INVISIBLE);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);

        gameEnd = false;
        isYellow = false;
        for (int i = 0; i < arr.length; i++) arr[i] = -1;

        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout)findViewById(R.id.Gamegrid);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }

    }

}
