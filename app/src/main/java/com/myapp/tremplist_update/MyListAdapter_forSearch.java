package com.myapp.tremplist_update;

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

public class MyListAdapter_forSearch extends ArrayAdapter<String> {
    FireBaseDBActivity fb;
    private int layout;
    private int curr_position;
    List<Ride> rides;

    public MyListAdapter_forSearch(@NonNull Context context, int resource, @NonNull List<String> objects, List<Ride> rides) {
        super(context, resource, objects);
        layout=resource;
        this.rides=rides;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder_search mainViewHolder=null;
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(layout, null, false);
            ViewHolder_search viewHolder=new ViewHolder_search();
            viewHolder.user_img=(ImageView) convertView.findViewById(R.id.user_im);
            viewHolder.ride_details=(TextView) convertView.findViewById(R.id.ride_details);
            viewHolder.join_ride=(androidx.appcompat.widget.AppCompatButton) convertView.findViewById(R.id.join_ride);
            viewHolder.join_ride.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Ride curr_ride= new Ride(rides.get(position));
                    int free_sits = curr_ride.getFree_sits();
                    curr_ride.setFree_sits(free_sits-1);
                    fb = new FireBaseDBActivity();
                    fb.setContext(getContext());
                    fb.updateRideOnDB(curr_ride);
                }
            });
            convertView.setTag(viewHolder);
        }

        mainViewHolder=(ViewHolder_search) convertView.getTag();
        mainViewHolder.ride_details.setText(getItem(position));


        return convertView;
    }

    public void setRides_List(List<Ride> rides) {
        this.rides=rides;
    }
}

class ViewHolder_search{
    ImageView user_img;
    TextView ride_details;
    androidx.appcompat.widget.AppCompatButton join_ride;


}