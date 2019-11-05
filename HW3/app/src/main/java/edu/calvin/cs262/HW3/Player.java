package edu.calvin.cs262.HW3;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "player_table")
public class Player {

    @NonNull
    @ColumnInfo(name = "playerName")
    private String playerName;

    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    public Player(@NonNull String playerName, @NonNull String email, @NonNull String id) {
        this.playerName = playerName;
        this.email = email;
        this.id = id;
    }

    public String getPlayerName(){return this.playerName;}
    public String getEmail(){return this.email;}
    public String getId(){return this.id;}

}

