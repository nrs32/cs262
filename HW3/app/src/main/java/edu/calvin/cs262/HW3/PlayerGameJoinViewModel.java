// package edu.calvin.cs262.HW3;

// import android.app.Application;

// import java.util.List;

// import androidx.lifecycle.AndroidViewModel;
// import androidx.lifecycle.LiveData;

// // Way to safely interact with PlayerGameJoin data from MainActivity
// public class PlayerGameJoinViewModel extends AndroidViewModel {

//     private PlayerGameJoinRepository mRepository;

//     private LiveData<List<PlayerGameJoin>> mAllPlayerGameJoins;

//     public PlayerGameJoinViewModel(Application application) {
//         super(application);
//         mRepository = new PlayerGameJoinRepository(application);
//         mAllPlayerGameJoins = mRepository.getAllPlayerGameJoins();
//     }

//     LiveData<List<PlayerGameJoin>> getAllPlayerGameJoins() { return mAllPlayerGameJoins; }

//     public void insert(PlayerGameJoin playerGameJoin) { mRepository.insert(playerGameJoin); }

//     public LiveData<List<Player>> getPlayersForGame(final int  gameId){ return mRepository.getPlayersForGame(gameId); }

//     public LiveData<List<Game>> getGamesForPlayer(final int  playerId){ return mRepository.getGamesForPlayer(playerId); }

//     public void deleteAll() {mRepository.deleteAll();}

//     public void deletePlayerGameJoin(PlayerGameJoin playerGameJoin) {mRepository.deletePlayerGameJoin(playerGameJoin);}
// }
