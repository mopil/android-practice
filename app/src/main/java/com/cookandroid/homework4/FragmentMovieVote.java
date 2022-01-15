package com.cookandroid.homework4;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentMovieVote extends Fragment {
    GridView gv;
    MyDBHelper myDBHelper;
    SQLiteDatabase sqLiteDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie_grid, container, false);
        myDBHelper = new MyDBHelper(v.getContext());
        sqLiteDatabase = myDBHelper.getWritableDatabase();


        String movieTitles[] = myDBHelper.getAllTitle(sqLiteDatabase);

        gv = (GridView) v.findViewById(R.id.gridView);
        MyGridViewAdapter myGridViewAdapter = new MyGridViewAdapter(v.getContext(), myDBHelper, sqLiteDatabase , movieTitles);
        gv.setAdapter(myGridViewAdapter);

        return v;
    }

    @Override
    public void onDestroyView() {
        sqLiteDatabase.close();
        super.onDestroyView();
    }
}
