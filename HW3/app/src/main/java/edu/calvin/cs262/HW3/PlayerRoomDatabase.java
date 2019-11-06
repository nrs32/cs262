package edu.calvin.cs262.HW3;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Manage source data with Player, Game, and PlayerGameJoin classes
 */
@Database(entities = { Player.class, Game.class, PlayerGameJoin.class }, version = 1, exportSchema = false)
public abstract class PlayerRoomDatabase extends RoomDatabase {

    public abstract PlayerDao playerDao();
    public abstract GameDao gameDao();
    public abstract PlayerGameJoinDao playerGameJoinDao();
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

        private final PlayerDao playerDao;
        private final GameDao gameDao;
        private final PlayerGameJoinDao playerGameJoinDao;
        PopulateDbAsync(PlayerRoomDatabase db) {
            playerDao = db.playerDao();
            gameDao = db.gameDao();
            playerGameJoinDao = db.playerGameJoinDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            // If we have no players, then create the initial list of players
            if (playerDao.getAnyPlayer().length < 1) {
                playerDao.insert(new Player("red",    "green@gmail.com", 50));
                playerDao.insert(new Player("orange", "blue@gmail.com",  51));
                playerDao.insert(new Player("yellow", "purple@gmail.com",52));
            }

            // If we have no games, then create the initial list of games
            if (gameDao.getAnyGame().length < 1) {
                gameDao.insert(new Game("2006-06-29 18:41:00", 1));
                gameDao.insert(new Game("2019-06-29 8:45:00",  2));
                gameDao.insert(new Game("2019-06-29 13:00:01", 3));
           }

            // If we have no playerGameJoins, then create the initial list of playerGameJoins
           if (playerGameJoinDao.getAnyPlayerGameJoin().length < 1) {
                playerGameJoinDao.insert(new PlayerGameJoin(1, 50));
                playerGameJoinDao.insert(new PlayerGameJoin(1, 51));
                playerGameJoinDao.insert(new PlayerGameJoin(2, 52));
                playerGameJoinDao.insert(new PlayerGameJoin(3, 52));
           }
            
           return null;
        }
    }
}