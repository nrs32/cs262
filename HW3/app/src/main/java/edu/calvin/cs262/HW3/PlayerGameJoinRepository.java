// // package edu.calvin.cs262.HW3;

// // import android.app.Application;
// // import android.os.AsyncTask;
// // import android.util.Log;

// // import java.util.List;

// // import androidx.lifecycle.LiveData;

// // public class PlayerGameJoinRepository {
// //     private PlayerGameJoinDao playerGameJoinDao;
// //     private LiveData<List<PlayerGameJoin>> allPlayerGameJoins;
// //     private LiveData<List<Player>> allPlayersForGame;
// //     private LiveData<List<Game>> allGamesForPlayer;

// //     PlayerGameJoinRepository(Application application) {
// //         PlayerGameJoinRoomDatabase db = PlayerGameJoinRoomDatabase.getDatabase(application);
// //         playerGameJoinDao = db.playerGameJoinDao();
// //         allPlayerGameJoins = playerGameJoinDao.getAllPlayerGameJoins();
// //     }

//     // LiveData<List<PlayerGameJoin>> getAllPlayerGameJoins() {
//     //     return allPlayerGameJoins;
//     // }

//     // LiveData<List<Player>> getPlayersForGame(final int playerId) {
//     //     allPlayersForGame = playerGameJoinDao.getPlayersForGame(playerId);
//     //     return allPlayersForGame;
//     // }

//     // LiveData<List<Game>> getGamesForPlayer(final int  gameId) {
//     //     allGamesForPlayer = playerGameJoinDao.getGamesForPlayer(gameId);
//     //     return allGamesForPlayer;
//     // }

//                     //    ////////////////// GET PLAYERS FOR GAME //////////////////
//                     //    public void getPlayersForGame(Integer gameId)  {
//                     //        new edu.calvin.cs262.HW3.PlayerGameJoinRepository.getPlayersForGameAsyncTask(playerGameJoinDao).execute(gameId);
//                     //    }
//                     //
//                     //    /**
//                     //     * Use AsyncTask to delete all playerGameJoins and maintain performance
//                     //     */
//                     //    private static class getPlayersForGameAsyncTask extends AsyncTask<Integer, Void, Void> {
//                     //        private PlayerGameJoinDao mAsyncTaskDao;
//                     //
//                     //        getPlayersForGameAsyncTask(PlayerGameJoinDao dao) {
//                     //            mAsyncTaskDao = dao;
//                     //        }
//                     //
//                     //        @Override
//                     //        protected Void doInBackground(final Integer... params) {
//                     //            mAsyncTaskDao.getPlayersForGame(params[0]);
//                     //            return null;
//                     //        }
//                     //    }
//                     //
//                     //    ////////////////// GET GAMES FOR PLAYER //////////////////
//                     //    public void getGamesForPlayer(Integer playerId)  {
//                     //        new edu.calvin.cs262.HW3.PlayerGameJoinRepository.getGamesForPlayerAsyncTask(playerGameJoinDao).execute(playerId);
//                     //    }
//                     //
//                     //    /**
//                     //     * Use AsyncTask to delete all playerGameJoins and maintain performance
//                     //     */
//                     //    private static class getGamesForPlayerAsyncTask extends AsyncTask<Integer, Void, Void> {
//                     //        private PlayerGameJoinDao mAsyncTaskDao;
//                     //
//                     //        getGamesForPlayerAsyncTask(PlayerGameJoinDao dao) {
//                     //            mAsyncTaskDao = dao;
//                     //        }
//                     //
//                     //        @Override
//                     //        protected Void doInBackground(final Integer... params) {
//                     //            mAsyncTaskDao.getGamesForPlayer(params[0]);
//                     //            return null;
//                     //        }
//                     //    }


//     ////////////////// INSERT GAME //////////////////
//     public void insert (PlayerGameJoin playerGameJoin) {
//         new edu.calvin.cs262.HW3.PlayerGameJoinRepository.insertAsyncTask(playerGameJoinDao).execute(playerGameJoin);
//     }

//     /**
//      * Use AsyncTask to insert a single playerGameJoin and maintain performance
//      */
//     private static class insertAsyncTask extends AsyncTask<PlayerGameJoin, Void, Void> {

//         private PlayerGameJoinDao mAsyncTaskDao;

//         insertAsyncTask(PlayerGameJoinDao dao) {
//             mAsyncTaskDao = dao;
//         }

//         @Override
//         protected Void doInBackground(final PlayerGameJoin... params) {
//             mAsyncTaskDao.insert(params[0]);
//             return null;
//         }
//     }

//     ////////////////// DELETE ALL PLAYER GAME JOINS //////////////////
//     public void deleteAll()  {
//         new edu.calvin.cs262.HW3.PlayerGameJoinRepository.deleteAllPlayerGameJoinsAsyncTask(playerGameJoinDao).execute();
//     }

//     /**
//      * Use AsyncTask to delete all playerGameJoins and maintain performance
//      */
//     private static class deleteAllPlayerGameJoinsAsyncTask extends AsyncTask<Void, Void, Void> {
//         private PlayerGameJoinDao mAsyncTaskDao;

//         deleteAllPlayerGameJoinsAsyncTask(PlayerGameJoinDao dao) {
//             mAsyncTaskDao = dao;
//         }

//         @Override
//         protected Void doInBackground(Void... voids) {
//             mAsyncTaskDao.deleteAll();
//             return null;
//         }
//     }

//     ////////////////// DELETE GAME //////////////////
//     public void deletePlayerGameJoin(PlayerGameJoin playerGameJoin)  {
//         new edu.calvin.cs262.HW3.PlayerGameJoinRepository.deletePlayerGameJoinAsyncTask(playerGameJoinDao).execute(playerGameJoin);
//     }

//     /**
//      * Use AsyncTask to delete a single playerGameJoin and maintain performance
//      */
//     private static class deletePlayerGameJoinAsyncTask extends AsyncTask<PlayerGameJoin, Void, Void> {
//         private PlayerGameJoinDao mAsyncTaskDao;

//         deletePlayerGameJoinAsyncTask(PlayerGameJoinDao dao) {
//             mAsyncTaskDao = dao;
//         }

//         @Override
//         protected Void doInBackground(final PlayerGameJoin... params) {
//             mAsyncTaskDao.deletePlayerGameJoin(params[0]);
//             return null;
//         }
//     }

// }

