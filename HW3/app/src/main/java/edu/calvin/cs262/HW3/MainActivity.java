package edu.calvin.cs262.HW3;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

/**
 * Create UI for interacting with Player table
 * and test Game and PlayerGameJoin tables
 */
public class MainActivity extends AppCompatActivity {

    private MonopolyViewModel mMonopolyViewModel;
    public static final int NEW_PLAYER_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Add new player, open new player screen
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewPlayerActivity.class);
                startActivityForResult(intent, NEW_PLAYER_ACTIVITY_REQUEST_CODE);
            }
        });

        // set up recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PlayerListAdapter adapter = new PlayerListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMonopolyViewModel = ViewModelProviders.of(this).get(MonopolyViewModel.class);

        mMonopolyViewModel.getAllPlayers().observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(@Nullable final List<Player> player) {
                // Update the cached copy of the player in the adapter.
                adapter.setPlayers(player);
            }
        });

        // recycler view to delete that item
        ItemTouchHelper helper = new ItemTouchHelper(
            new ItemTouchHelper.SimpleCallback(0,
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView,
                                      RecyclerView.ViewHolder viewHolder,
                                      RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                     int direction) {
                    int position = viewHolder.getAdapterPosition();
                    Player myPlayer = adapter.getPlayerAtPosition(position);
                    Integer playerId = myPlayer.getId();
                    if (playerId == 50 || playerId == 51 || playerId == 51) {
                        Toast.makeText(MainActivity.this, "You may not delete the presets :(", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Deleting " +
                                myPlayer.getPlayerName(), Toast.LENGTH_LONG).show();

                        // Delete the player
                        mMonopolyViewModel.deletePlayer(myPlayer);
                    }
                }
            });

        helper.attachToRecyclerView(recyclerView);

        ///////////////////////////////////////////////////////////////////////
        ///////////////// Test Game and PlayerGameJoin tables /////////////////
        ///////////////////////////////////////////////////////////////////////
        mMonopolyViewModel.getPlayersForGame(1).observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(@Nullable final List<Player> playerFromGame1) {
                // Print expected players from game 1
                Log.d("||||||||||||||||", " \n"
                    + "\nPRINT ALL PLAYERS FROM GAME 1"
                    + "\nExpected output is player Red      green@gmail.com  50"
                    + "\nand                player Orange   blue@gmail.com   51");

                // Print each Player received by query
                for(int i = 0; i < playerFromGame1.size(); i++) {
                    Player currentPlayer = playerFromGame1.get(i);
                    Log.d("||||||||||||||||", " \n"
                        + "\nName: " + currentPlayer.getPlayerName()
                        + "\nEmail: " + currentPlayer.getEmail()
                        + "\nId: "    + currentPlayer.getId());
                }
        }});

        mMonopolyViewModel.getGamesForPlayer(52).observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable final List<Game> gamesFromPlayer52) {
                // Print expected games from player 52
                Log.d("||||||||||||||||", " \n"
                    + "\nPRINT ALL GAMES FROM PLAYER 52"
                    + "\nExpected output is game 2    2019-06-29 8:45:00"
                    + "\nand                game 3    2019-06-29 13:00:01");

                // Print each Game received by query
                for(int i = 0; i < gamesFromPlayer52.size(); i++) {
                    Game currentGame =  gamesFromPlayer52.get(i);
                    Log.d("||||||||||||||||", " \n"
                        + "\nGame Time: " + currentGame.getTime()
                        + "\nId: "         + currentGame.getId());
                }
            }});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.clear_data) {
            // Add a toast just for confirmation
            Toast.makeText(this, "Clearing the data...",
                    Toast.LENGTH_SHORT).show();

            // Delete the existing data
            mMonopolyViewModel.deleteAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Add new player to db
        if (requestCode == NEW_PLAYER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Player player = new Player(data.getStringExtra("PLAYER_NAME"), data.getStringExtra("EMAIL"), data.getIntExtra("ID", 0));
            mMonopolyViewModel.insert(player);

        // Empty string, don't save
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
