package edu.calvin.cs262.HW3;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Games should have a time and PrimaryKey ID
 */
@Entity(tableName = "game_table")
public class Game {

    @NonNull
    @ColumnInfo(name = "time")
    private final String time;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final Integer id;

    public Game(@NonNull String time, @NonNull Integer id) {
        this.time = time;
        this.id = id;
    }

    // Getters for time and id
    @NonNull public String getTime(){return this.time;}
    @NonNull public Integer getId(){return this.id;}

}