package iut.ticket.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TicketDao {
    @Query("SELECT * FROM ticket")
    List<Ticket> getAll();

    @Query("SELECT * FROM ticket WHERE id IN (:ids)")
    List<Ticket> loadAllByIds(int[] ids);

    @Insert
    void insertAll(Ticket... users);

    @Delete
    void delete(Ticket user);
}

