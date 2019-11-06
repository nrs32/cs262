// package edu.calvin.cs262.HW3;

// import android.app.Application;

// import java.util.List;

// import androidx.lifecycle.AndroidViewModel;
// import androidx.lifecycle.LiveData;

// // Way to safely interact with Game data from MainActivity
// public class GameViewModel extends AndroidViewModel {

//     private GameRepository mRepository;

//     private LiveData<List<Game>> mAllGames;

//     public GameViewModel(Application application) {
//         super(application);
//         mRepository = new GameRepository(application);
//         mAllGames = mRepository.getAllGames();
//     }

//     LiveData<List<Game>> getAllGames() { return mAllGames; }

//     public void insert(Game game) { mRepository.insert(game); }

//     public void deleteAll() {mRepository.deleteAll();}

//     public void deleteGame(Game game) {mRepository.deleteGame(game);}
// }
