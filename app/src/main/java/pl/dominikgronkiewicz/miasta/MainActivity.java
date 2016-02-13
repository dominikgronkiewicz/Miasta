package pl.dominikgronkiewicz.miasta;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> listOfCities;
    TextView textViewActionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(pl.dominikgronkiewicz.miasta.R.layout.activity_main);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listOfCities = new ArrayList<>();
        MainActivity.listOfCities.add("Rytro");
        MainActivity.listOfCities.add("Biecz");
        MainActivity.listOfCities.add("Nowy Sącz");
        MainActivity.listOfCities.add("Barcice");
        MainActivity.listOfCities.add("Gosprzydowa");
        MainActivity.listOfCities.add("GosprzydowaXYZQWE");

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setCustomView(R.layout.actionbar_view);
        textViewActionbar = (TextView) actionBar.getCustomView().findViewById(R.id.textActionbar);
        textViewActionbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getFragmentManager().getBackStackEntryCount()>0) {
                    getFragmentManager().popBackStack();
                }
            }
        });
        actionBar.setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM
                | android.support.v7.app.ActionBar.DISPLAY_SHOW_HOME);


        getFragmentManager().beginTransaction().add(R.id.fragmentContainer, new FragmentListOfCities()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem itemBack = menu.findItem(R.id.back_button_bar);
        itemBack.setVisible(false);
        if(getFragmentManager().getBackStackEntryCount()==0) {
            textViewActionbar.setText(R.string.app_name);
        } else {
            textViewActionbar.setText(R.string.actionbar_back);;
        }
        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        getFragmentManager().popBackStack();
        return false;
    }
    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
/*        switch (item.getItemId()) {
            case R.id.textActionbar:
                if(getFragmentManager().getBackStackEntryCount()>0) {
                    getFragmentManager().popBackStack();
                }
                return true;
            default:*/
                return super.onOptionsItemSelected(item);
//        }
    }




    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setMessage("Zamknąć aplikację?");
        alertDialog.setPositiveButton("Zamknij", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                System.exit(0);
            }
        });
        alertDialog.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialogStart = alertDialog.create();
        alertDialogStart.show();
    }








}
