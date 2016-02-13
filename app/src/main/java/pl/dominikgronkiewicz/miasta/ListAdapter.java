package pl.dominikgronkiewicz.miasta;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dominik on 05.02.16.
 */
public class ListAdapter extends ArrayAdapter {
    private final Activity context;
    ArrayList<String> elements = new ArrayList<>();

    public ListAdapter(Activity context, int resourceID, ArrayList<String> listWithElements){
        super(context,resourceID,listWithElements);
        // TODO Auto-generated constructor stub
        this.context = context;
        elements.addAll(listWithElements);
        Log.d("=====adapter kostruktor", " start");
    }

    public View getView(int position,View view,ViewGroup parent){
                LayoutInflater inflater=context.getLayoutInflater();
                View rowView=inflater.inflate(R.layout.list_item, null, true);

                TextView cityName=(TextView)rowView.findViewById(R.id.cityName);
                cityName.setText(elements.get(position));

                if(position%2!=0) {
                    int color = context.getResources().getColor(R.color.grayblue);
                    rowView.setBackgroundColor(color);
                }
                return rowView;
        }

}
