package com.cookandroid.homework4;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MyGridViewAdapter extends BaseAdapter {
    Integer[] posterID = { R.drawable.mov01, R.drawable.mov02,
            R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
            R.drawable.mov06, R.drawable.mov07, R.drawable.mov08,
            R.drawable.mov09, R.drawable.mov10, R.drawable.mov01,
            R.drawable.mov02, R.drawable.mov03, R.drawable.mov04,
            R.drawable.mov05, R.drawable.mov06, R.drawable.mov07,
            R.drawable.mov08, R.drawable.mov09, R.drawable.mov10 };
    String[] movieTitle;

    MyDBHelper myDBHelper;
    SQLiteDatabase sqLiteDatabase;

    Context context;
    public MyGridViewAdapter(Context c, MyDBHelper helper , SQLiteDatabase db, String[] pt) { context = c; myDBHelper = helper; sqLiteDatabase = db; movieTitle =pt;}

    @Override
    public int getCount() {
        return posterID.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    // 그리드 뷰 각 격자 칸 하나하나 실행될 메소드 구현
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new GridView.LayoutParams(200,300));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setPadding(5,5,5,5);
        imageView.setImageResource(posterID[position]);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dialogView = (View) View.inflate(context, R.layout.dialog, null);
                ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                ivPoster.setImageResource(posterID[position]);
                TextView title = (TextView) dialogView.findViewById(R.id.ivTitle);
                title.setText(movieTitle[position]);

                TextView likeText = (TextView) dialogView.findViewById(R.id.likeText);
                likeText.setText("Likes : "+myDBHelper.getAllLikes(sqLiteDatabase)[position]);
                ImageView likeBtn = (ImageView) dialogView.findViewById(R.id.likeIcon);

                likeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDBHelper.updateMovieLikes(sqLiteDatabase, movieTitle[position]);
                        likeText.setText("Likes : "+myDBHelper.getAllLikes(sqLiteDatabase)[position]);
                    }
                });

                AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                dlg.setView(dialogView);
                dlg.setNegativeButton("Close",null);
                dlg.show();
            }

        });
        return imageView;
    }
}
