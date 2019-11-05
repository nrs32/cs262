package edu.calvin.cs262.HW3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder> {

    private final LayoutInflater mInflater;
    private List<Player> mPlayer; // Cached copy of players

    PlayerListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new PlayerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        if (mPlayer != null) {
            Player current = mPlayer.get(position);
            holder.playerNameView.setText(current.getPlayerName());
            String detailText = "ID: " + current.getId() + "      Email: " + current.getEmail();
            holder.playerDetailsView.setText(detailText);
        } else {
            // Covers the case of data not being ready yet.
            holder.playerNameView.setText("No Player");
            holder.playerDetailsView.setText("");
        }
    }

    void setPlayers(List<Player> player){
        mPlayer = player;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mPlayer has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mPlayer != null)
            return mPlayer.size();
        else return 0;
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder {
        private final TextView playerNameView;
        private final TextView playerDetailsView;

        private PlayerViewHolder(View itemView) {
            super(itemView);
            playerNameView = itemView.findViewById(R.id.nameView);
            playerDetailsView = itemView.findViewById(R.id.detailsView);

        }
    }

    public Player getPlayerAtPosition (int position) {
        return mPlayer.get(position);
    }
}
