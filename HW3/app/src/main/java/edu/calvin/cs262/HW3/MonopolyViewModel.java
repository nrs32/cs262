package edu.calvin.cs262.HW3;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Way to safely interact with data from MainActivity
 * Holds data for UI
 */
public class MonopolyViewModel extends AndroidViewModel {

    // Repo
    private MonopolyRepository mRepository;

    // Players, Games, and PlayerGameJoins
    private LiveData<List<Player>> mAllPlayers;
    private LiveData<List<PlayerGameJoin>> mAllPlayerGameJoins;
    private LiveData<List<Game>> mAllGames;

    // ViewModel for tables
    public MonopolyViewModel(Application application) {
        super(application);
        mRepository = new MonopolyRepository(application);
        mAllPlayers = mRepository.getAllPlayers();
        mAllGames = mRepository.getAllGames();
        mAllPlayerGameJoins = mRepository.getAllPlayerGameJoins();
    }

    // Get all from a table
    LiveData<List<Player>> getAllPlayers() { return mAllPlayers; }
    LiveData<List<Game>> getAllGames() { return mAllGames; }
    LiveData<List<PlayerGameJoin>> getAllPlayerGameJoins() { return mAllPlayerGameJoins; }
    LiveData<List<Player>> getPlayersForGame(final int gameId) {
        return mRepository.getPlayersForGame(gameId);
    }

    LiveData<List<Game>> getGamesForPlayer(final int  playerId) {
        return mRepository.getGamesForPlayer(playerId);
    }

    // Insert methods
    public void insert(Player player) { mRepository.insert(player); }
    public void insert(Game game) { mRepository.insert(game); }
    public void insert(PlayerGameJoin playerGameJoin) { mRepository.insert(playerGameJoin); }

    // Delete methods
    public void deletePlayer(Player player) {mRepository.deletePlayer(player);}
    public void deleteGame(Game game) {mRepository.deleteGame(game);}
    public void deletePlayerGameJoin(PlayerGameJoin playerGameJoin) {mRepository.deletePlayerGameJoin(playerGameJoin);}
    
    // Delete all from repo
    public void deleteAll() {mRepository.deleteAll();}
}
