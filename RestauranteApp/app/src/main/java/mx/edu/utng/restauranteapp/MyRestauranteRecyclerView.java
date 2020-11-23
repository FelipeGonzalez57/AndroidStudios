package mx.edu.utng.restauranteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

//import mx.edu.utng.recyclerviewgds0241.dummy.DummyContent.DummyItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Restaurante}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyRestauranteRecyclerView extends RecyclerView.Adapter<MyRestauranteRecyclerView.ViewHolder> {

    private final List<Restaurante> mValues;
    private Context contexto;

    public MyRestauranteRecyclerView(Context ctx, List<Restaurante> items) {
        contexto = ctx;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_restaurante, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //Este método dibuja cada elemento de la lista
        try {
            holder.mItem = mValues.get(position);  //Rescatamos los datos de cada elemento
            holder.tvNombre.setText(holder.mItem.getNombre());
            holder.tvDireccion.setText(holder.mItem.getDireccion());
            holder.rbRestaurante.setRating(holder.mItem.getValoracion()); //Detalle
            //Para manejo de la imagen
            holder.tvDireccion.setText(holder.mItem.getUrlPhoto());

            Glide.with(contexto)
                    .load(holder.mItem.getUrlPhoto())
                    .centerCrop()
                    .placeholder(R.drawable.restaurante)
                    .error(R.drawable.restaurante)
                    .into(holder.ivPhoto);
        }catch (Exception e){

            //Toast.makeText(RestauranteFragment, "Error"+e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView; //Hace referencia al constraint layout
        public final TextView tvNombre;
        public final TextView tvDireccion;
        public final ImageView ivPhoto;
        public final RatingBar rbRestaurante;
        public Restaurante mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvNombre = (TextView) view.findViewById(R.id.tvNombre);
            tvDireccion = (TextView) view.findViewById(R.id.tvDireccion);
            ivPhoto = (ImageView)view.findViewById(R.id.ivPhoto);
            rbRestaurante = (RatingBar)view.findViewById(R.id.rbValoracion);

        }
        @Override
        public String toString() {
            return super.toString() + " '" + tvNombre.getText() + "'";
        }
    }
}