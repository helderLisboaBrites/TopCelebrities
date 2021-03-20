package com.example.topcelebritieslistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

class CelebrityAdapter extends ArrayAdapter<Celebrity> {


    public CelebrityAdapter(@NonNull Context context, int resource, @NonNull List<Celebrity> celebrity) {
        super(context, resource, celebrity);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if(convertView == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_celebrities, null,false);
        else
            view = convertView;

        ImageView imageView = (ImageView) view.findViewById(R.id.ivCelebImage);
        TextView fname = (TextView) view.findViewById(R.id.fname);
        TextView lname = (TextView) view.findViewById(R.id.lname);

        Celebrity celebrity = getItem(position);
        fname.setText(celebrity.getName());
        lname.setText(celebrity.getLastName());
        imageView.setImageResource(celebrity.getImageRes());

        return view;
    }

}
