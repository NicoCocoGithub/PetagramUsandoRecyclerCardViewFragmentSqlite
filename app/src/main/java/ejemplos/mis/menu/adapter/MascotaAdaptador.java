package ejemplos.mis.menu.adapter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ejemplos.mis.menu.AdminSQLiteOpenHelper;
import ejemplos.mis.menu.R;
import ejemplos.mis.menu.poyo.Mascota;

/**
 * Created by nicopro on 16/5/16.
 */
public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.mascotaViewHolder>{


    static int MAXIMOFAVORITOS = 5;
    public MascotaAdaptador(ArrayList<Mascota> item) {
        this.item = item;
    }
    private ArrayList <Mascota> item;

    View v;

    @Override
    public mascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        //tengo que inflar el layout que quiero mostrar
         v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
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

        boolean valor = mascotaViewHolder.chequeoLikeDislikeEnBdD(v,codigoOcultoCasteo);
        /*Abrir la Base de Datos mediante el CodigoOculto y veo el CHECK*/
        mascotaViewHolder.botonLikeDislike.setChecked(valor);

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

          //  imagenHuesoAmarillo = (ImageView)itemView.findViewById(R.id.ImageView_huesoAmarrillo);

             botonLikeDislike = (CheckBox) itemView.findViewById(R.id.CheckBox_likeDislike);


            botonLikeDislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    int posicionSeleccionado = Integer.parseInt(codigoOculto.getText().toString())-1; // le resto 1 porque empieza desde el 0
                    String codigoUnicoSeleccionado = codigoOculto.getText().toString();
                    String nombreSeleccionado = item.get(posicionSeleccionado).getNombre();
                    int imagenSelecionado =  item.get(posicionSeleccionado).getImagen(); //Lo convierto para verlo por el Toast
                    int colorBackgroundSeleccionado = item.get(posicionSeleccionado).getColor();


                    if (((CheckBox) v).isChecked()) {
                        Toast.makeText(v.getContext(), "Checkado :)", Toast.LENGTH_LONG).show();
                        dislikeToLike(v, codigoUnicoSeleccionado, nombreSeleccionado, imagenSelecionado,colorBackgroundSeleccionado);

                    }else{
                        Toast.makeText(v.getContext(), "DesCheckado :)", Toast.LENGTH_LONG).show();
                        likeToDislike(v,codigoUnicoSeleccionado);
                    }
                }
            });




        }

        public void dislikeToLike(View v, String codigoUnico, String nombre_, int imagenDrawable, int colorBackground){


            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(v.getContext(), "administracionFavoritos", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor fila = bd.rawQuery("select * from favoritos ", null);
            int cantidadFavoritos = fila.getCount();

            if (cantidadFavoritos == MAXIMOFAVORITOS)
            {
                // Buscamos la Mascota Favorita más "vieja" o de las primeras que seleccione --> codigo de menor valor que se insertó en la BdD
                //codigo = codigoUnico , el primero es id dentro de la BdD y CodigoUnico el valor que le asigne al ArrayList
                Cursor filaAEliminar = bd.rawQuery("SELECT MIN(codigo) from favoritos", null);
                filaAEliminar.moveToFirst();

                int cant = bd.delete("favoritos", "codigo=" + filaAEliminar.getInt(0), null);

                if (cant == 1)
                    Toast.makeText(v.getContext(), "Se borró la Mascota de Favoritos con dicho código",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(v.getContext(), "No existe la Mascota de Favoritos  con dicho código",
                            Toast.LENGTH_SHORT).show();

            }


                //Agregamos una Mascota a Favoritos
            ContentValues registro = new ContentValues();


            String codigo_ = null;//autoincremental
            String raitsTotal = "3";// Esto es el Ranking de Todos los usuarios por ahora lo dejo así calculo que tendría que salir de un recuento global por ejemplo de un API

            registro.put("codigo", codigo_);
            registro.put("codigounico", codigoUnico);
            registro.put("nombre", nombre_);
            registro.put("imagen", imagenDrawable);
            registro.put("color", colorBackground);
            registro.put("raits", raitsTotal);
            bd.insert("favoritos", null, registro);
            bd.close();
            Toast.makeText(v.getContext(), "Se cargaron los datos de la Mascota", Toast.LENGTH_SHORT).show();

        }

        /*
        public void AgregarAFavoritos(View v, String nombre_, String codigoUnico)
        {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(v.getContext(),
                    "administracionFavoritos", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();


            String codigo_ = null;//autoincremental
          //  String codigoUnico= "0";
            String rutaImagen_ = "ruta imagen";
            String color_ = "rojo";
            String raits_ = "1";

            ContentValues registro = new ContentValues();


            registro.put("codigo", codigo_);
            registro.put("codigounico", codigoUnico);
            registro.put("nombre", nombre_);
            registro.put("imagen", rutaImagen_);
            registro.put("color", color_);
            registro.put("raits", raits_);
            bd.insert("favoritos", null, registro);
            bd.close();

            Toast.makeText(v.getContext(), "Se cargaron los datos del artículo", Toast.LENGTH_SHORT).show();
        }


        public void contar(View v) {


          //  int id_minimo = 0;

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(v.getContext(),
                    "administracionFavoritos", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            Cursor fila = bd.rawQuery("select * from favoritos ", null);
            int cantidad = fila.getCount();
            Toast.makeText(v.getContext(), "cantidad: "+cantidad, Toast.LENGTH_SHORT).show();

            bd.close();

        }

        public void Listar(View v){

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(v.getContext(),
                    "administracionFavoritos", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            Cursor fila = bd.rawQuery("select * from favoritos ", null);
            int cantidad = fila.getCount();
            Toast.makeText(v.getContext(), "cantidad: "+cantidad, Toast.LENGTH_SHORT).show();

            fila.moveToFirst();


            for(int i=1;i<=cantidad;i++){
                int id_ = fila.getInt(0);
                String codigoUnicoBD = fila.getString(1);
                String nombreBD = fila.getString(2);
                String imagenBD = fila.getString(3);
                String colorBD = fila.getString(4);

                Toast.makeText(v.getContext(), "id_: "+Integer.toString(id_) + "nombre: " +nombreBD + "codigoUnico: "+ codigoUnicoBD+" imagen: " + imagenBD + " color:" + colorBD, Toast.LENGTH_SHORT).show();
                fila.moveToNext();
            }

            bd.close();
        }
*/

        public void likeToDislike(View v, String codigoUnico) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(v.getContext(),
                    "administracionFavoritos", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            int cant = bd.delete("favoritos", "codigounico=" + codigoUnico, null);
            bd.close();

            if (cant == 1)
                Toast.makeText(v.getContext(), "Se borró el mascota con dicho código",
                        Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(v.getContext(), "No existe un mascota con dicho código",
                        Toast.LENGTH_SHORT).show();
        }

        public boolean chequeoLikeDislikeEnBdD(View v, String codigoUnico){
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(v.getContext(),
                    "administracionFavoritos", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            Cursor fila = bd.rawQuery("select * from favoritos where codigounico=" + codigoUnico, null);

            if (fila.moveToFirst()) {
                bd.close();
                return true;
            } else {
                bd.close();
                return false;

            }


        }




    }
}
