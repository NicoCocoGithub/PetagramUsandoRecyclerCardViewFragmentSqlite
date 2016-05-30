package ejemplos.mis.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactoActivity extends AppCompatActivity {



    private Button botonEnviarCorreo;
    private EditText nombre, correoElectronico, descripcionDelMensaje;

    String emailUsario;
    String nombreUsuario;
    String mensajeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ponte en contacto");


        botonEnviarCorreo = (Button) findViewById(R.id.Button_Siguiente);

        nombre = (EditText) findViewById(R.id.EditText_nombreCompleto);
        correoElectronico = (EditText) findViewById(R.id.EditText_correoElectronico);
        descripcionDelMensaje = (EditText) findViewById(R.id.EditText_descripcionMensaje);


        botonEnviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nombreUsuario = nombre.getText().toString();
                emailUsario = correoElectronico.getText().toString();
                mensajeUsuario = descripcionDelMensaje.getText().toString();

                Toast.makeText(getBaseContext(), " Tu : " + nombreUsuario + " Enviaste : " + mensajeUsuario + " a : " + emailUsario, Toast.LENGTH_LONG).show();

                Intent sendIntent = new Intent((Intent.ACTION_SEND));
                sendIntent.setType("plain/text");
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailUsario});
                sendIntent.putExtra(Intent.EXTRA_TEXT, mensajeUsuario);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Mensaje enviado por " + nombreUsuario);
                startActivity(Intent.createChooser(sendIntent, "Enviar mail atraves de "));
            }
        });


    }
}



