package ejemplos.mis.menu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ejemplos.mis.menu.R;
import ejemplos.mis.menu.adapter.MascotaAdaptadorItem;
import ejemplos.mis.menu.poyo.Mascota;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    ArrayList<Mascota> datosPerfil = new ArrayList<Mascota>();
    private RecyclerView recicladorPerfil;
    private RecyclerView.LayoutManager layoutManagerPerfil;
    private RecyclerView.Adapter recyclerAdaptadorPerfil;

    TextView nombreMascota;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        recicladorPerfil = (RecyclerView)v.findViewById(R.id.RecyclerView_gridPerfil);
        layoutManagerPerfil = new LinearLayoutManager(getContext());
        recicladorPerfil.setLayoutManager(layoutManagerPerfil);

        nombreMascota = (TextView) v.findViewById(R.id.TextView_nombrePerfil);
        nombreMascota.setText("Chewyr");
        nombreMascota.setTextSize(20);

        inicializarDatosPerfilMascota();
        inicializaAdaptadorGrilla();

        return v;
    }


    void inicializarDatosPerfilMascota(){
        datosPerfil.add(new Mascota(1," ",R.drawable.chewyr01,0xFFFFFFFF));
        datosPerfil.add(new Mascota(2," ",R.drawable.chewyr02,0xFFFFFFFF));
        datosPerfil.add(new Mascota(3," ",R.drawable.chewyr03,0xFFFFFFFF));
        datosPerfil.add(new Mascota(4," ",R.drawable.chewyr04,0xFFFFFFFF));
        datosPerfil.add(new Mascota(5," ",R.drawable.chewyr05,0xFFFFFFFF));
        datosPerfil.add(new Mascota(6," ",R.drawable.chewyr06,0xFFFFFFFF));
        datosPerfil.add(new Mascota(7," ",R.drawable.chewyr07,0xFFFFFFFF));
        datosPerfil.add(new Mascota(8," ",R.drawable.chewyr08,0xFFFFFFFF));
        datosPerfil.add(new Mascota(9," ",R.drawable.chewyr09,0xFFFFFFFF));
        datosPerfil.add(new Mascota(10," ",R.drawable.chewyr10,0xFFFFFFFF));
        datosPerfil.add(new Mascota(11," ",R.drawable.chewyr12,0xFFFFFFFF));
        datosPerfil.add(new Mascota(12," ",R.drawable.chewyr13,0xFFFFFFFF));
        datosPerfil.add(new Mascota(13," ",R.drawable.chewyr14,0xFFFFFFFF));
        datosPerfil.add(new Mascota(14," ",R.drawable.chewyr15,0xFFFFFFFF));
        datosPerfil.add(new Mascota(15," ",R.drawable.chewyr05,0xFFFFFFFF));



    }

    void inicializaAdaptadorGrilla(){
        int Cantidad_de_Columnas = 3;
        recyclerAdaptadorPerfil = new MascotaAdaptadorItem(datosPerfil);
        recicladorPerfil.setLayoutManager(new GridLayoutManager(getContext(), Cantidad_de_Columnas ));
        recicladorPerfil.setAdapter(recyclerAdaptadorPerfil);
    }

}
