// package edu.calvin.cs262.HW3;

// import android.content.Context;
// import android.os.AsyncTask;

// import androidx.annotation.NonNull;
// import androidx.room.Database;
// import androidx.room.Room;
// import androidx.room.RoomDatabase;
// import androidx.sqlite.db.SupportSQLiteDatabase;

// @Database(entities = {PlayerGameJoin.class, Player.class, Game.class}, version = 1, exportSchema = false)
// public abstract class PlayerGameJoinRoomDatabase extends RoomDatabase {

//     public abstract PlayerGameJoinDao playerGameJoinDao();
//     private static PlayerGameJoinRoomDatabase INSTANCE;

//     static PlayerGameJoinRoomDatabase getDatabase(final Context context) {
//         if (INSTANCE == null) {
//             synchronized (PlayerGameJoinRoomDatabase.class) {
//                 if (INSTANCE == null) {
//                     INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                             PlayerGameJoinRoomDatabase.class, "player_game_join_database")
//                             // Wipes and rebuilds instead of migrating
//                             // if no Migration object.
//                             // Migration is not part of this practical.
//                             .fallbackToDestructiveMigration()
//                             .addCallback(sRoomDatabaseCallback)
//                             .build();
//                 }
//             }
//         }
//         return INSTANCE;
//     }

//     private static RoomDatabase.Callback sRoomDatabaseCallback =
//             new RoomDatabase.Callback(){

//                 @Override
//                 public void onOpen (@NonNull SupportSQLiteDatabase db){
//                     super.onOpen(db);
//                     new PopulateDbAsync(INSTANCE).execute();
//                 }
//             };

//     /**
//      * Populate the database in the background.
//      */
//     private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

//         private final PlayerGameJoinDao mDao;

//         PopulateDbAsync(PlayerGameJoinRoomDatabase db) {
//             mDao = db.playerGameJoinDao();
//         }

//         @Override
//         protected Void doInBackground(final Void... params) {

//             // If we have no playerGameJoins, then create the initial list of playerGameJoins
// //            if (mDao.getAnyPlayerGameJoin().length < 1) {
// //                mDao.insert(new PlayerGameJoin(1, 50));
// //                mDao.insert(new PlayerGameJoin(1, 51));
// //                mDao.insert(new PlayerGameJoin(2, 52));
// //                mDao.insert(new PlayerGameJoin(3, 52));
// //            }
//             return null;
//         }
//     }
// }