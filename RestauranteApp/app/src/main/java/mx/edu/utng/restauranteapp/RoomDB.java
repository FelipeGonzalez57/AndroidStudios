package mx.edu.utng.restauranteapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Anota la clase para que sea una base de datos de Room @Database
// y usa los parámetros de anotación para declarar las entidades que
// pertenecen a la base de datos y establecer el número de versión.

@Database(entities = {Restaurante.class}, version = 1, exportSchema = false)

public abstract class RoomDB extends RoomDatabase {
    public abstract RestauranteDao restauranteDao();

    private static volatile RoomDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static RoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, "restaurante_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
