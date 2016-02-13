package pl.dominikgronkiewicz.miasta;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by dominik on 05.02.16.
 */
public class FragmentCityInfo extends Fragment {
    ArrayList<String> cityInfo;
    TextView textViewCityInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info, container, false);
        getActivity().invalidateOptionsMenu();
/*        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }*/



        cityInfo = getArguments().getStringArrayList("cityInfo");
        textViewCityInfo = (TextView) v.findViewById(R.id.textViewCityInfo);
        String stringToShow = "";
        String[] infoDesc = {"Miejscowość: ","Gmina: ","Powiat: ","Województwo: "};

        for (int i = 0; i < cityInfo.size(); i++) {
            if(i<infoDesc.length && cityInfo.size()==5) {
                stringToShow += infoDesc[i];
            }
            stringToShow +=  cityInfo.get(i) + "\n";
        }
        textViewCityInfo.setText(stringToShow);
        return v;
    }



}
