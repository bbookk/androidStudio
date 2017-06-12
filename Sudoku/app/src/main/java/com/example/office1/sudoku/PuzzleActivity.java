package com.example.office1.sudoku;

import android.app.Activity;
import android.os.Bundle;

public class PuzzleActivity extends Activity {
    protected static final String KEY_DIFFICULTY = "level_game";
    protected static final int EASY = 0;
    protected static final int MEDIUM = 1;
    protected static final int HARD = 2;

    private int puzzle[] = new int[9*9];

    private PuzzleView puzzleView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int diff =getIntent().getIntExtra(KEY_DIFFICULTY,EASY);

        puzzle = getPuzzle(diff);
        calculatedUsedTiles();

        puzzleView = new PuzzleView(this);
        setContentView(puzzleView);
        puzzleView.requestFocus();


    }
}
