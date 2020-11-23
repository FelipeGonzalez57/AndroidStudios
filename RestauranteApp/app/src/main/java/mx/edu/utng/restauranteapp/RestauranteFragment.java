package mx.edu.utng.restauranteapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


//import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class RestauranteFragment extends Fragment {

    //Declaraciones:
    RecyclerView recyclerView;
    public List<Restaurante> restauranteList;
    private MyRestauranteRecyclerView adapterRestaurantes;
    //Objeto de la calse
    private RestauranteLab mRestaurante;

    public MyRestauranteRecyclerView getAdapterRestaurantes() {
        return adapterRestaurantes;
    }

    public void setAdapterRestaurantes(MyRestauranteRecyclerView adapterRestaurantes) {
        this.adapterRestaurantes = adapterRestaurantes;
    }

    public RestauranteLab getmRestaurante() {
        return mRestaurante;
    }

    public void setmRestaurante(RestauranteLab mRestaurante) {
        this.mRestaurante = mRestaurante;
    }

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RestauranteFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RestauranteFragment newInstance(int columnCount) {
        RestauranteFragment fragment = new RestauranteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_restaurante, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            mRestaurante = RestauranteLab.get(getContext());

            try {
                Restaurante r = new Restaurante();
                r.setNombre("Siete Mares");
                r.setUrlPhoto("https://10619-2.s.cdn12.com/rests/small/w550/h367/317_502926022.jpg");
                r.setValoracion(3.5f);
                r.setDireccion("Valle Negro");


                mRestaurante.addRestaurante(r);

            }catch (Exception e){
                Toast.makeText(getContext(), "Hay un error con los datos por favor intenta nuevamente, Error"+e.getMessage(),Toast.LENGTH_LONG).show();
            }

            List<Restaurante> restaurantes = mRestaurante.getRestaurantes();
            adapterRestaurantes = new MyRestauranteRecyclerView(getActivity(), restaurantes);
            recyclerView.setAdapter(adapterRestaurantes);

        }
        return view;
    }




}