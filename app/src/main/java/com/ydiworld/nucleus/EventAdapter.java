package com.ydiworld.nucleus;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sammy on 11/11/17.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    public List<Content> eventList;

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View eventView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_event_list, parent, false);
        return new EventViewHolder(eventView);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        holder.eventTime.setText(eventList.get(position).getTime());
        holder.theEvent.setText(eventList.get(position).getEvent());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder{
        TextView eventTime;
        TextView theEvent;

        EventViewHolder(View itemView) {
            super(itemView);

            eventTime = itemView.findViewById(R.id.time);
            theEvent = itemView.findViewById(R.id.the_event);
        }
    }
}
