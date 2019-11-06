// // package edu.calvin.cs262.HW3;
// // import android.app.Application;
// // import android.os.AsyncTask;
// // import java.util.List;

// // import androidx.lifecycle.LiveData;

// // public class GameRepository {
// //     private GameDao gameDao;
// //     private LiveData<List<Game>> allGames;

// //     GameRepository(Application application) {
// //         GameRoomDatabase db = GameRoomDatabase.getDatabase(application);
// //         gameDao = db.gameDao();
// //         allGames = gameDao.getAllGames();
// //     }

//     LiveData<List<Game>> getAllGames() {
//         return allGames;
//     }

//     ////////////////// INSERT GAME //////////////////
//     public void insert (Game game) {
//         new edu.calvin.cs262.HW3.GameRepository.insertAsyncTask(gameDao).execute(game);
//     }

//     /**
//      * Use AsyncTask to insert a single game and maintain performance
//      */
//     private static class insertAsyncTask extends AsyncTask<Game, Void, Void> {

//         private GameDao mAsyncTaskDao;

//         insertAsyncTask(GameDao dao) {
//             mAsyncTaskDao = dao;
//         }

//         @Override
//         protected Void doInBackground(final Game... params) {
//             mAsyncTaskDao.insert(params[0]);
//             return null;
//         }
//     }

//     ////////////////// DELETE ALL GAMES //////////////////
//     public void deleteAll()  {
//         new edu.calvin.cs262.HW3.GameRepository.deleteAllGamesAsyncTask(gameDao).execute();
//     }

//     /**
//      * Use AsyncTask to delete all games and maintain performance
//      */
//     private static class deleteAllGamesAsyncTask extends AsyncTask<Void, Void, Void> {
//         private GameDao mAsyncTaskDao;

//         deleteAllGamesAsyncTask(GameDao dao) {
//             mAsyncTaskDao = dao;
//         }

//         @Override
//         protected Void doInBackground(Void... voids) {
//             mAsyncTaskDao.deleteAll();
//             return null;
//         }
//     }

//     ////////////////// DELETE GAME //////////////////
//     public void deleteGame(Game game)  {
//         new edu.calvin.cs262.HW3.GameRepository.deleteGameAsyncTask(gameDao).execute(game);
//     }

//     /**
//      * Use AsyncTask to delete a single game and maintain performance
//      */
//     private static class deleteGameAsyncTask extends AsyncTask<Game, Void, Void> {
//         private GameDao mAsyncTaskDao;

//         deleteGameAsyncTask(GameDao dao) {
//             mAsyncTaskDao = dao;
//         }

//         @Override
//         protected Void doInBackground(final Game... params) {
//             mAsyncTaskDao.deleteGame(params[0]);
//             return null;
//         }
//     }

// }
