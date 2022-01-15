package com.cookandroid.homework4;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentMovieResult extends Fragment {
    SQLiteDatabase sqLiteDatabase;
    MyDBHelper myDBHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragement_result_list,container,false);

        myDBHelper = new MyDBHelper(v.getContext());
        sqLiteDatabase = myDBHelper.getReadableDatabase();

        int voteResult[] = myDBHelper.getAllLikes(sqLiteDatabase);
        String movieTitles[] = myDBHelper.getAllTitle(sqLiteDatabase);


        Integer textId[] = {R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9, R.id.tv10, R.id.tv11, R.id.tv12,
                R.id.tv13, R.id.tv14, R.id.tv15, R.id.tv16, R.id.tv17, R.id.tv18, R.id.tv19, R.id.tv20};

        Integer rbarId[] = {R.id.rating1,R.id.rating2,R.id.rating3,R.id.rating4,R.id.rating5,R.id.rating6,R.id.rating7,R.id.rating8,R.id.rating9,R.id.rating10,
                R.id.rating11,R.id.rating12,R.id.rating13,R.id.rating14,R.id.rating15,R.id.rating16,R.id.rating17,R.id.rating18,R.id.rating19,R.id.rating20};

        RatingBar rbar[] = new RatingBar[voteResult.length];

        TextView title[] = new TextView[voteResult.length];

        for (int j = 0; j<voteResult.length ; j++){
            rbar[j] = (RatingBar) v.findViewById(rbarId[j]);
            rbar[j].setRating((float) voteResult[j]);
            title[j] = (TextView) v.findViewById(textId[j]);
            title[j].setText(movieTitles[j]);
            title[j].setTextSize(30);

        }

        sqLiteDatabase.close();
        return v;
    }
}
