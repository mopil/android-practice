package com.cookandroid.homework4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentResetDB extends Fragment {
    MyDBHelper myDBHelper;
    SQLiteDatabase sqLiteDatabase;

    TableLayout resetTableLayout;
    Integer movieTitleId[] = {R.id.movieTitleText1,R.id.movieTitleText2,R.id.movieTitleText3,R.id.movieTitleText4,R.id.movieTitleText5,
            R.id.movieTitleText6,R.id.movieTitleText7,R.id.movieTitleText8,R.id.movieTitleText9,R.id.movieTitleText10,
            R.id.movieTitleText11,R.id.movieTitleText12,R.id.movieTitleText13,R.id.movieTitleText14,R.id.movieTitleText15,
            R.id.movieTitleText16,R.id.movieTitleText17,R.id.movieTitleText18,R.id.movieTitleText19,R.id.movieTitleText20};
    Integer movieLikesId[] = {R.id.movieLikesText1,R.id.movieLikesText2,R.id.movieLikesText3,R.id.movieLikesText4,R.id.movieLikesText5,
            R.id.movieLikesText6,R.id.movieLikesText7,R.id.movieLikesText8,R.id.movieLikesText9,R.id.movieLikesText10,
            R.id.movieLikesText11,R.id.movieLikesText12,R.id.movieLikesText13,R.id.movieLikesText14,R.id.movieLikesText15,
            R.id.movieLikesText16,R.id.movieLikesText17,R.id.movieLikesText18,R.id.movieLikesText19,R.id.movieLikesText20};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reset, container, false);
        myDBHelper = new MyDBHelper(v.getContext());
        sqLiteDatabase = myDBHelper.getWritableDatabase();

        String movieTitles[] = myDBHelper.getAllTitle(sqLiteDatabase);
        myDBHelper.init(sqLiteDatabase, movieTitles);


        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM movieTBL", null);
        resetTableLayout = (TableLayout) v.findViewById(R.id.resetTableLayout);
        TextView movieTitle[] = new TextView[movieTitleId.length];
        TextView movieLikes[] = new TextView[movieLikesId.length];
        int i = 0;
        while (cursor.moveToNext()) {
            String currentTitle = cursor.getString(0);
            String currentLikes = cursor.getString(1);
            movieTitle[i] = (TextView) v.findViewById(movieTitleId[i]);
            movieTitle[i].setText(currentTitle);
            movieLikes[i] = (TextView) v.findViewById(movieLikesId[i]);
            movieLikes[i].setText(currentLikes);
            i++;
        }
        Toast.makeText(getContext(), "투표 초기화 완료", Toast.LENGTH_SHORT).show();

        cursor.close();
        sqLiteDatabase.close();
        return v;
    }
}
