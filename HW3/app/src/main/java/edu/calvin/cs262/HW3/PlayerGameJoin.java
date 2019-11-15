package edu.calvin.cs262.HW3;
import androidx.room.Entity;
import androidx.room.ForeignKey;

/**
 * PlayerGameJoin joins Player table/class with Game table/class
 */
@Entity(tableName = "player_game_join",
    // establish two primary keys which must be a unique combo
    primaryKeys = { "playerId", "gameId" },

    // derive foreign keys from Game and Player primary keys
    foreignKeys = {
        @ForeignKey(entity = Game.class,
            parentColumns = "id",
            childColumns = "gameId"),
        @ForeignKey(entity = Player.class,
            parentColumns = "id",
            childColumns = "playerId")
    })
public class PlayerGameJoin {
    final int gameId;
    final int playerId;

     public PlayerGameJoin(final int gameId, final int playerId) {
         this.gameId = gameId;
         this.playerId = playerId;
     }
 }