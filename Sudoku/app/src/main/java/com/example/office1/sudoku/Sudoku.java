package com.example.office1.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class Sudoku extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        View aboutbtn = (Button) findViewById(R.id.about_btn);
        aboutbtn.setOnClickListener(this);

        View newgamebtn = (Button) findViewById(R.id.newgame_btn);
        newgamebtn.setOnClickListener(this);

        View continuebtn = (Button) findViewById(R.id.continue_btn);
        continuebtn.setOnClickListener(this);

        View exitbtn = (Button) findViewById(R.id.exit_btn);
        exitbtn.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.about_btn:
                Intent i = new Intent(this, About.class);
                startActivity(i);
                break;
            case R.id.newgame_btn:
                openNewGameDialog();
                break;
            case R.id.exit_btn:
                finish(); //เป็นการปิด activity แล้วส่งคืนการควยคุมไปยัง activity ถัดไป
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.setting:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                return true;
        }
        return false;
    }

    private static final String TAG="Sudoku";

    private void openNewGameDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.newgame);
        dialog.setItems(R.array.level_game, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                startGame(i);
            }
        });
        dialog.show();
    }

    private void startGame(int i){
        Log.d(TAG , "you choose " + i);
        Intent intent = new Intent(this, PuzzleActivity.class);
        intent.putExtra(PuzzleActivity.KEY_DIFFICULTY, i);
        startActivity(intent);
    }
}
