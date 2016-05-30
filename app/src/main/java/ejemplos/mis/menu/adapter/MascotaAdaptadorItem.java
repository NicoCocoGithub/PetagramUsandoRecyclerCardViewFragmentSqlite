package ejemplos.mis.menu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ejemplos.mis.menu.R;
import ejemplos.mis.menu.poyo.Mascota;

/**
 * Created by nicopro on 16/5/16.
 */
public class MascotaAdaptadorItem extends RecyclerView.Adapter<MascotaAdaptadorItem.mascotaViewHolder>{


    static int MAXIMOFAVORITOS = 5;
    public MascotaAdaptadorItem(ArrayList<Mascota> item) {
        this.item = item;
    }
    private ArrayList <Mascota> item;

    View v;

    @Override
    public mascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        //tengo que inflar el layout que quiero mostrar
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota_favoritos,parent,false);
        mascotaViewHolder producto = new mascotaViewHolder(v);
        return producto;
    }

    @Override
    public void onBindViewHolder(mascotaViewHolder mascotaViewHolder, int i) {
        //trae la informacion uno a uno muestro cada item

        String codigoOcultoCasteo;
        mascotaViewHolder.nombre.setText(item.get(i).getNombre());
        mascotaViewHolder.imagen.setImageResource(item.get(i).getImagen());
        mascotaViewHolder.imagen.setBackgroundColor(item.get(i).getColor());
        codigoOcultoCasteo = String.valueOf(item.get(i).getCodigoUnico());
        mascotaViewHolder.codigoOculto.setText(codigoOcultoCasteo);


    }

    @Override
    public int getItemCount() {
        return item.size();
        //cuanto va a ser el tamaño de los items
    }

    public class mascotaViewHolder extends RecyclerView.ViewHolder{

        TextView nombre;
        ImageView imagen;
        TextView codigoOculto;

        private CheckBox botonLikeDislike;



        //   ImageView imagenHuesoAmarillo;


        public mascotaViewHolder(final View itemView) {
            super(itemView);

            nombre = (TextView)itemView.findViewById(R.id.TextView_nombre);
            imagen = (ImageView)itemView.findViewById(R.id.ImageView_mascota);
            codigoOculto = (TextView)itemView.findViewById(R.id.TextView_codigoUnicoOculto);


        }


    }
}