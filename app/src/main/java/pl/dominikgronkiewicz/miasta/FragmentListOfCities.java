package pl.dominikgronkiewicz.miasta;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by dominik on 05.02.16.
 */
public class FragmentListOfCities extends Fragment{
    ListView listView;

    EditText editText;
    ImageButton addButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        getActivity().invalidateOptionsMenu();
        listView = (ListView) v.findViewById(R.id.listViewFragment);

        setListView();
        editText = (EditText) v.findViewById(R.id.editText);
        addButton = (ImageButton) v.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.listOfCities.add(editText.getText().toString());
                editText.setText("");
                showList();
            }
        });

        return v;
    }
    void showList() {
        ListAdapter adapter = new ListAdapter(getActivity(), R.layout.list_item, MainActivity.listOfCities);
        listView.setAdapter(adapter);
    }
    void setListView() {
        showList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                item = item.replaceAll("\\s", "");
                new RetrieveFeedTask().execute(item);
            }
        });
        Log.d("=====", "adapter utworzony");
    }


    void changeFragment(ArrayList<String> info) {
        if(info.size()==0) {
            Toast.makeText(getActivity(), "Nie znaleziono miasta!", Toast.LENGTH_SHORT).show();
            return;
        }
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        FragmentCityInfo fragmentCityInfo = new FragmentCityInfo();
        bundle.putStringArrayList("cityInfo", info);
        fragmentCityInfo.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragmentContainer, fragmentCityInfo);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, String > {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            dialog= new ProgressDialog(getActivity());
            dialog.setMessage("Pobieranie info miasta...");
            dialog.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... cityName) {
            return GSONClass.downloadFile(cityName[0]);    //
        }
        @Override
        protected void onPostExecute(String elem) {
            dialog.dismiss();
            if(elem.equals("")) {
                Toast.makeText(getActivity(),"Brak połącznienia z internetem",Toast.LENGTH_SHORT).show();
                return;
            }
            changeFragment(GSONClass.parseJSON(elem));
        }
    }


}
