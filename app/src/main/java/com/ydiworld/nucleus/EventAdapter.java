package com.ydiworld.nucleus;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sammy on 11/11/17.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View eventView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_event_list, parent, false);
        return new EventViewHolder(eventView);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
//        holder.eventTime.setText("4:00pm");
//        holder.theEvent.setText("Arrival &amp; Red Carpet");
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class EventViewHolder extends RecyclerView.ViewHolder{
        TextView eventTime;
        TextView theEvent;

        EventViewHolder(View itemView) {
            super(itemView);

            eventTime = (TextView) itemView.findViewById(R.id.time);
            theEvent = (TextView) itemView.findViewById(R.id.the_event);
        }
    }
}
