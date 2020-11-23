package mx.edu.utng.restauranteapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Restaurante.class}, version = 1, exportSchema = false)
public abstract class DB extends RoomDatabase {
    public abstract RestauranteDao getRestauranteDao();

}
