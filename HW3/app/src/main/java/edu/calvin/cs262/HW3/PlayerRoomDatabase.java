package edu.calvin.cs262.HW3;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Player.class}, version = 1, exportSchema = false)
public abstract class PlayerRoomDatabase extends RoomDatabase {

    public abstract PlayerDao playerDao();
    private static PlayerRoomDatabase INSTANCE;

    static PlayerRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlayerRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PlayerRoomDatabase.class, "player_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PlayerDao mDao;
        // Default values
        String[] playerNames = {"Name1", "Name2", "Name3"};
        String[] playerEmails = {"name@gmail.com", "email@calvin.edu", "example@student.nvps.net"};
        String[] playerIds = {"1", "2", "3"};

        PopulateDbAsync(PlayerRoomDatabase db) {
            mDao = db.playerDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            // If we have no players, then create the initial list of players
            if (mDao.getAnyPlayer().length < 1) {
                for (int i = 0; i <= playerNames.length - 1; i++) {
                    Player player = new Player(playerNames[i], playerEmails[i], playerIds[i]);
                    mDao.insert(player);
                }
            }
            return null;
        }
    }
}