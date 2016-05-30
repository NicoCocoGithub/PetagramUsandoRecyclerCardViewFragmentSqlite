package ejemplos.mis.menu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ejemplos.mis.menu.R;
import ejemplos.mis.menu.adapter.MascotaAdaptador;
import ejemplos.mis.menu.poyo.Mascota;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {

    public final ArrayList<Mascota> ListaMascotas = new ArrayList<Mascota>();
    private RecyclerView reciclador;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdaptador;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);


        reciclador = (RecyclerView)v.findViewById(R.id.RecyclerView_reciclador);
        layoutManager = new LinearLayoutManager(getContext());
        reciclador.setLayoutManager(layoutManager);


        inicializarDatosMascotas();
        inicializaAdaptador();

        return v;





    }


    void inicializarDatosMascotas(){
        ListaMascotas.add(new Mascota(1,"Toro",R.drawable.perro1,0xFF00FF00));
        ListaMascotas.add(new Mascota(2,"Darth Vader",R.drawable.perro2,0xFF10D94C));
        ListaMascotas.add(new Mascota(3,"Chewyr",R.drawable.perro12,0xFF45694C));
        ListaMascotas.add(new Mascota(4,"Akita",R.drawable.perro3,0xFF426989));
        ListaMascotas.add(new Mascota(5,"Goliat",R.drawable.perro4,0xFF7a355b));
        ListaMascotas.add(new Mascota(6,"Goku",R.drawable.perro5,0xFFd1c1fc));
        ListaMascotas.add(new Mascota(7,"Yeika",R.drawable.perro6,0xFFa8285c));
        ListaMascotas.add(new Mascota(8,"Akita",R.drawable.perro7,0xFF962489));
        ListaMascotas.add(new Mascota(9,"Florcita",R.drawable.perro8,0xFF37a55b));
        ListaMascotas.add(new Mascota(10,"Colita",R.drawable.perro9,0xFFd1f2cc));
        ListaMascotas.add(new Mascota(11,"Mancha",R.drawable.perro10,0xFF81285c));



    }

    void inicializaAdaptador(){
        recyclerAdaptador = new MascotaAdaptador(ListaMascotas);
        reciclador.setAdapter(recyclerAdaptador);
    }


    /*public Mascota  BuscarListaMascota(int codigoBuscado, View v){

        codigoBuscado = 1;


        Mascota mascota_ = new Mascota(0," nuevo",R.drawable.perro1,0xFF00FF00);

        Toast.makeText(v.getContext(), "tamañoDentro"+ListaMascotas.size(), Toast.LENGTH_SHORT).show();

        for (int i=1;i< ListaMascotas.size(); i++) {
            mascota_.setCodigoUnico( ListaMascotas.get(i).getCodigoUnico());
            mascota_.setNombre( ListaMascotas.get(i).getNombre());
            mascota_.setColor( ListaMascotas.get(i).getColor());
            mascota_.setImagen(ListaMascotas.get(i).getImagen());


            if(mascota_.getCodigoUnico() == codigoBuscado)
            {
                break;
            }else{
                mascota_.setNombre("No esta");
            }
        }
        return mascota_;
    }
    */


}
