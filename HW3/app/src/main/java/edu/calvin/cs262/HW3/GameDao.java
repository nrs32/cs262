package edu.calvin.cs262.HW3;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * GameDao (data access object) allows easy interaction with db Game table
 */
@Dao
public interface GameDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Game game);

    @Query("DELETE FROM game_table")
    void deleteAll();

    @Delete
    void deleteGame(Game game);

    @Query("SELECT * from game_table")
    LiveData<List<Game>> getAllGames();

    @Query("SELECT * from game_table LIMIT 1")
    Game[] getAnyGame();
}