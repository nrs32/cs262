package edu.calvin.cs262.HW3;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Players should have a name, email, and PrimaryKey ID
 */
@Entity(tableName = "player_table")
public class Player {

    @NonNull
    @ColumnInfo(name = "playerName")
    private final String playerName;

    @NonNull
    @ColumnInfo(name = "email")
    private final String email;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final Integer id;

    public Player(@NonNull String playerName, @NonNull String email, @NonNull Integer id) {
        this.playerName = playerName;
        this.email = email;
        this.id = id;
    }

    // Getters for name, email, id
    @NonNull public String getPlayerName(){return this.playerName;}
    @NonNull public String getEmail(){return this.email;}
    @NonNull public Integer getId(){return this.id;}

}

