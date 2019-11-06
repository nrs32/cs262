// // package edu.calvin.cs262.HW3;

// // import android.content.Context;
// // import android.os.AsyncTask;

// // import androidx.annotation.NonNull;
// // import androidx.room.Dao;
// // import androidx.room.Database;
// // import androidx.room.Room;
// // import androidx.room.RoomDatabase;
// // import androidx.sqlite.db.SupportSQLiteDatabase;

// // @Database(entities = {Game.class}, version = 1, exportSchema = false)
// // public abstract class GameRoomDatabase extends RoomDatabase {

// //     public abstract GameDao gameDao();
// //     private static GameRoomDatabase INSTANCE;

//     // static GameRoomDatabase getDatabase(final Context context) {
//     //     if (INSTANCE == null) {
//     //         synchronized (GameRoomDatabase.class) {
//     //             if (INSTANCE == null) {
//     //                 INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//     //                         GameRoomDatabase.class, "game_database")
//     //                         // Wipes and rebuilds instead of migrating
//     //                         // if no Migration object.
//     //                         // Migration is not part of this practical.
//     //                         .fallbackToDestructiveMigration()
//     //                         .addCallback(sRoomDatabaseCallback)
//     //                         .build();
//     //             }
//     //         }
//     //     }
//     //     return INSTANCE;
//     // }

//     // private static RoomDatabase.Callback sRoomDatabaseCallback =
//     //         new RoomDatabase.Callback(){

//     //             @Override
//     //             public void onOpen (@NonNull SupportSQLiteDatabase db){
//     //                 super.onOpen(db);
//     //                 new PopulateDbAsync(INSTANCE).execute();
//     //             }
//     //         };

//     /**
//      * Populate the database in the background.
//      */
//     private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

//         private final GameDao mDao;

//         PopulateDbAsync(GameRoomDatabase db) {
//             mDao = db.gameDao();
//         }

//         @Override
//         protected Void doInBackground(final Void... params) {

//             // If we have no games, then create the initial list of games
// //            if (mDao.getAnyGame().length < 1) {
// //                mDao.insert(new Game("2006-06-29 18:41:00", 1));
// //                mDao.insert(new Game("2019-06-29 8:45:00",  2));
// //                mDao.insert(new Game("2019-06-29 13:00:01", 3));
// //            }
//             return null;
//         }
//     }
// }