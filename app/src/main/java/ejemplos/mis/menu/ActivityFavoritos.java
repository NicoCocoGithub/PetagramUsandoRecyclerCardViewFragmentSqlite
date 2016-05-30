package ejemplos.mis.menu;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import ejemplos.mis.menu.adapter.MascotaAdaptadorItem;
import ejemplos.mis.menu.poyo.Mascota;

public class ActivityFavoritos extends AppCompatActivity {

    private RecyclerView recicladorFav;
    private RecyclerView.LayoutManager layoutManagerFav;
    private RecyclerView.Adapter recyclerAdaptadorFav;
    ArrayList<Mascota> datosFav = new ArrayList<Mascota>();

    private Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Favoritos");

       datosFav.removeAll(datosFav);

        View v = getLayoutInflater().inflate(R.layout.activity_favoritos, null);

         ListarFavoritos(v);


        recicladorFav = (RecyclerView)findViewById(R.id.RecyclerView_RecicladorFavoritos);
        layoutManagerFav = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recicladorFav.setLayoutManager(layoutManagerFav);

        recyclerAdaptadorFav = new MascotaAdaptadorItem(datosFav);
        recicladorFav.setAdapter(recyclerAdaptadorFav);

    }


    public void ListarFavoritos(View v){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(v.getContext(),
                "administracionFavoritos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery("select * from favoritos ", null);
        int cantidad = fila.getCount();
      //  Toast.makeText(v.getContext(), "cantidad: "+cantidad, Toast.LENGTH_SHORT).show();

        fila.moveToFirst();


        for(int i=1;i<=cantidad;i++){
            int id_ = fila.getInt(0);
            int codigoUnicoBD = Integer.parseInt(fila.getString(1));
            String nombreBD = fila.getString(2);
            int imagenBD = fila.getInt(3);
            int colorBD = fila.getInt(4);

      //      Toast.makeText(v.getContext(), "id_: "+Integer.toString(id_) + "nombre: " +nombreBD + "codigoUnico: "+ codigoUnicoBD+" imagen: " + imagenBD + " color:" + colorBD, Toast.LENGTH_SHORT).show();

            datosFav.add(new Mascota(codigoUnicoBD,nombreBD,imagenBD,colorBD));
            fila.moveToNext();

        }

        bd.close();
    }



}
