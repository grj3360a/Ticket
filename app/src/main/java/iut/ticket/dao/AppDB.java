package iut.ticket.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Ticket.class}, version = 1)
public abstract class AppDB extends RoomDatabase {

    private static AppDB db;

    public static AppDB getDB(Context c){
        if(db == null){
            db = Room.databaseBuilder(c, AppDB.class, "database-name").build();
        }

        return db;
    }

    public abstract TicketDao ticketDao();
}
