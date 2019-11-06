package edu.calvin.cs262.HW3;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Single source of truth for app data that UI can interact with
 */
public class PlayerRepository {
    // Player declarations
    private PlayerDao playerDao;
    private LiveData<List<Player>> allPlayers;

    // PlayerGameJoin declarations
    private PlayerGameJoinDao playerGameJoinDao;
    private LiveData<List<PlayerGameJoin>> allPlayerGameJoins;
    private LiveData<List<Player>> allPlayersForGame;
    private LiveData<List<Game>> allGamesForPlayer;

    // Game declarations
    private GameDao gameDao;
    private LiveData<List<Game>> allGames;


    // Repo of tables
    PlayerRepository(Application application) {
        PlayerRoomDatabase db = PlayerRoomDatabase.getDatabase(application);
        playerDao = db.playerDao();
        allPlayers = playerDao.getAllPlayers();

        playerGameJoinDao = db.playerGameJoinDao();
        allPlayerGameJoins = playerGameJoinDao.getAllPlayerGameJoins();

        gameDao = db.gameDao();
        allGames = gameDao.getAllGames();
    }

    ////////////////// GET ALL ... - LiveData List methods //////////////////
    LiveData<List<Player>> getAllPlayers() {
        return allPlayers;
    }

    LiveData<List<Game>> getAllGames() {
        return allGames;
    }

    LiveData<List<PlayerGameJoin>> getAllPlayerGameJoins() {
        return allPlayerGameJoins;
    }

    LiveData<List<Player>> getPlayersForGame(final int playerId) {
        allPlayersForGame = playerGameJoinDao.getPlayersForGame(playerId);
        return allPlayersForGame;
    }

    LiveData<List<Game>> getGamesForPlayer(final int  gameId) {
        allGamesForPlayer = playerGameJoinDao.getGamesForPlayer(gameId);
        return allGamesForPlayer;
    }

    ////////////////// INSERT PLAYER //////////////////
    public void insert (Player player) {
        new insertPlayerAsyncTask(playerDao).execute(player);
    }

    /**
     * Use AsyncTask to insert a single player and maintain performance
     */
    private static class insertPlayerAsyncTask extends AsyncTask<Player, Void, Void> {

        private PlayerDao mAsyncTaskDao;

        insertPlayerAsyncTask(PlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Player... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    ////////////////// INSERT GAME //////////////////
    public void insert (Game game) {
        new insertGameAsyncTask(gameDao).execute(game);
    }

    /**
     * Use AsyncTask to insert a single game and maintain performance
     */
    private static class insertGameAsyncTask extends AsyncTask<Game, Void, Void> {

        private GameDao mAsyncTaskDao;

        insertGameAsyncTask(GameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Game... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    ////////////////// INSERT PLAYER GAME JOIN //////////////////
    public void insert (PlayerGameJoin playerGameJoin) {
        new insertPlayerGameJoinAsyncTask(playerGameJoinDao).execute(playerGameJoin);
    }

    /**
     * Use AsyncTask to insert a single playerGameJoin and maintain performance
     */
    private static class insertPlayerGameJoinAsyncTask extends AsyncTask<PlayerGameJoin, Void, Void> {

        private PlayerGameJoinDao mAsyncTaskDao;

        insertPlayerGameJoinAsyncTask(PlayerGameJoinDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PlayerGameJoin... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    ////////////////// DELETE PLAYER //////////////////
    public void deletePlayer(Player player)  {
        new deletePlayerAsyncTask(playerDao).execute(player);
    }

    /**
     * Use AsyncTask to delete a single player and maintain performance
     */
    private static class deletePlayerAsyncTask extends AsyncTask<Player, Void, Void> {
        private PlayerDao mAsyncTaskDao;

        deletePlayerAsyncTask(PlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Player... params) {
            mAsyncTaskDao.deletePlayer(params[0]);
            return null;
        }
    }

    ////////////////// DELETE PLAYER GAME JOIN //////////////////
    public void deletePlayerGameJoin(PlayerGameJoin playerGameJoin)  {
        new deletePlayerGameJoinAsyncTask(playerGameJoinDao).execute(playerGameJoin);
    }

    /**
     * Use AsyncTask to delete a single playerGameJoin and maintain performance
     */
    private static class deletePlayerGameJoinAsyncTask extends AsyncTask<PlayerGameJoin, Void, Void> {
        private PlayerGameJoinDao mAsyncTaskDao;

        deletePlayerGameJoinAsyncTask(PlayerGameJoinDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PlayerGameJoin... params) {
            mAsyncTaskDao.deletePlayerGameJoin(params[0]);
            return null;
        }
    }

    ////////////////// DELETE GAME //////////////////
    public void deleteGame(Game game)  {
        new deleteGameAsyncTask(gameDao).execute(game);
    }

    /**
     * Use AsyncTask to delete a single game and maintain performance
     */
    private static class deleteGameAsyncTask extends AsyncTask<Game, Void, Void> {
        private GameDao mAsyncTaskDao;

        deleteGameAsyncTask(GameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Game... params) {
            mAsyncTaskDao.deleteGame(params[0]);
            return null;
        }
    }

    ////////////////// DELETE ALL //////////////////
    public void deleteAll()  {
        new deleteAllPlayerGameJoinsAsyncTask(playerGameJoinDao).execute();
        new deleteAllPlayersAsyncTask(playerDao).execute();
        new deleteAllGamesAsyncTask(gameDao).execute();
    }

    /**
     * Use AsyncTask to delete all playerGameJoins and maintain performance
     */
    private static class deleteAllPlayerGameJoinsAsyncTask extends AsyncTask<Void, Void, Void> {
        private PlayerGameJoinDao mAsyncTaskDao;

        deleteAllPlayerGameJoinsAsyncTask(PlayerGameJoinDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /**
     * Use AsyncTask to delete all players and maintain performance
     */
    private static class deleteAllPlayersAsyncTask extends AsyncTask<Void, Void, Void> {
        private PlayerDao mAsyncTaskDao;

        deleteAllPlayersAsyncTask(PlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /**
     * Use AsyncTask to delete all games and maintain performance
     */
    private static class deleteAllGamesAsyncTask extends AsyncTask<Void, Void, Void> {
        private GameDao mAsyncTaskDao;

        deleteAllGamesAsyncTask(GameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
