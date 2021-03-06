package iut.ticket.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Ticket.class, Product.class}, version = 3, exportSchema = false)
public abstract class AppDB extends RoomDatabase {

    private static AppDB db;

    public static AppDB createDB(Context c){
        if(db == null){
            db = Room.databaseBuilder(c, AppDB.class, "db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return db;
    }

    public static TicketDao getTicketDao(){
        return db.ticketDao();
    }

    public abstract TicketDao ticketDao();
}
