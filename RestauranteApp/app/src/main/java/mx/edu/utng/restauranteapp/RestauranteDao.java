package mx.edu.utng.restauranteapp;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Dao;

import java.util.List;

@Dao
public interface RestauranteDao {
    @Insert
    void addRestaurante(Restaurante restaurante);

    @Update
    void updateRestaurante(Restaurante restaurante);

    @Query("DELETE FROM restaurante")
    void deleteAll();

    @Query("DELETE FROM restaurante WHERE nombre=:nombreRestaurante")
    void deleteById(String nombreRestaurante);

    @Query("SELECT * FROM restaurante ORDER BY nombre ASC")
    LiveData<List<Restaurante>> getAlphabetizedRestaurante();

    @Query("SELECT * FROM restaurante")
    List<Restaurante> getRestaurantes();

    @Query("SELECT * FROM restaurante WHERE nombre LIKE :nom")
    Restaurante getRestaurante(String nom);

    @Delete
    void deleteRestaurante(Restaurante restaurante);

}
