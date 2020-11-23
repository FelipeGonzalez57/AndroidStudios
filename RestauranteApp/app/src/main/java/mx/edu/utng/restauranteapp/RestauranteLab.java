package mx.edu.utng.restauranteapp;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class RestauranteLab {
    @SuppressLint("StaticFieldLeak")
    private static RestauranteLab sRestauranteLab;

    private RestauranteDao mRestauranteDao;

    private RestauranteLab(Context context) {
        Context appContext = context.getApplicationContext();
        DB database = Room.databaseBuilder(appContext, DB.class, "restaurante_db")
                .allowMainThreadQueries().build();
        mRestauranteDao = database.getRestauranteDao();
    }

    public static RestauranteLab get(Context context) {
        if (sRestauranteLab == null) {
            sRestauranteLab = new RestauranteLab(context);
        }
        return sRestauranteLab;
    }

    public List<Restaurante> getRestaurantes() {
        return mRestauranteDao.getRestaurantes();
    }

    public Restaurante getRestaurante(String id) {
        return mRestauranteDao.getRestaurante(id);
    }

    public void addRestaurante(Restaurante restaurante) {
        mRestauranteDao.addRestaurante(restaurante);
    }

    public void updateRes(Restaurante restaurante) {
        mRestauranteDao.updateRestaurante(restaurante);
    }

    public void deleteRes(Restaurante restaurante) {
        mRestauranteDao.deleteRestaurante(restaurante);
    }
}
