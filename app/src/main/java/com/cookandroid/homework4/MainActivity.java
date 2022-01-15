package com.cookandroid.homework4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    NavigationView navigationView;
    SQLiteDatabase sqLiteDatabase;
    MyDBHelper myDBHelper;

    String[] posterTitle = {"제목1","제목2","제목3","제목4","제목5","제목6","제목7","제목8","제목9","제목10","제목11","제목12","제목13","제목14","제목15","제목16",
        "제목17","제목18","제목19","제목20"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("영화투표앱");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);


        myDBHelper = new MyDBHelper(MainActivity.this);
        sqLiteDatabase = myDBHelper.getWritableDatabase();
        if (myDBHelper.checkEmpty(sqLiteDatabase)) {
            myDBHelper.init(sqLiteDatabase, posterTitle);
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_movie_vote:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new FragmentMovieVote()).commit();
                        break;
                    case R.id.nav_movie_result:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new FragmentMovieResult()).commit();
                        break;
                    case R.id.nav_vote_reset:
                        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                        dlg.setTitle("경고");
                        dlg.setMessage("투표 DB를 초기화 합니다.\n 한번 하면 되돌릴 수 없습니다.\n 그래도 하시겠습니까?");
                        dlg.setIcon(R.drawable.ic_baseline_warning_24);
                        dlg.setPositiveButton("예", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new FragmentResetDB()).commit();
                                    }
                                });
                        dlg.setNegativeButton("아니오",null);
                        dlg.show();
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        sqLiteDatabase.close();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: {
                drawer.openDrawer(GravityCompat.START);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}