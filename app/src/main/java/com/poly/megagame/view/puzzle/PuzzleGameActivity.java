package com.poly.megagame.view.puzzle;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.poly.megagame.R;
import com.poly.megagame.callback.PuzzleOnItemCount;
import com.poly.megagame.model.puzzle.Puzzle;
import com.poly.megagame.customview.CustomGridLayoutManager;
import com.poly.megagame.adapter.PuzzleGameAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class PuzzleGameActivity extends AppCompatActivity implements PuzzleOnItemCount {

    private RecyclerView recyclerGames;
    private PuzzleGameAdapter puzzleGameAdapter;
    private ArrayList<Puzzle> arrayRowGames = new ArrayList<>();
    private int[] arrayGames  = {R.drawable.image,R.drawable.image1};

    private TextView txtSteps;
    private int countClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_game);

        initView();
        createGames();
        playGame();
        addEvents();
    }

    private void addEvents() {
        puzzleGameAdapter.setPuzzleOnItemCount(this);
    }

    private void createGames() {
        recyclerGames.setHasFixedSize(true);
        recyclerGames.setLayoutManager(new CustomGridLayoutManager(getApplicationContext(),3,RecyclerView.VERTICAL,false));
        puzzleGameAdapter = new PuzzleGameAdapter(arrayRowGames);
        recyclerGames.setAdapter(puzzleGameAdapter);
    }

    private void initView() {
        txtSteps        = findViewById(R.id.txtSteps);
        recyclerGames   = findViewById(R.id.recyclerGames);
    }

    private void playGame(){
        Glide.with(getApplicationContext())
                .asBitmap()
                .load(R.drawable.image1)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Bitmap> transition) {
                        final int width = resource.getWidth();
                        final int height = resource.getHeight();

                        final int pixelByCol = width / 3;
                        final int pixelByRow = height / 3;

                        int index = 0;
                        for (int i = 0; i < 3;i++) {
                            for (int j = 0; j < 3;j++) {
                                int startX = pixelByCol * j;
                                int startY = pixelByRow * i;

                                Bitmap mBitmap = Bitmap.createBitmap(resource, startX, startY, pixelByCol, pixelByRow);

                                arrayRowGames.add(new Puzzle(mBitmap, String.valueOf(index)));
                                index ++;
                            }
                        }
                        Collections.shuffle(arrayRowGames);
                        arrayRowGames.set(2, new Puzzle(null,arrayRowGames.get(2).getIndex()));
                        puzzleGameAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }

    @Override
    public void onClick() {
        countClick++;
        txtSteps.setText("Bước: "+ countClick);
    }
}
