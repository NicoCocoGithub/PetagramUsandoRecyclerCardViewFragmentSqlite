package ejemplos.mis.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import ejemplos.mis.menu.adapter.PageAdapter;
import ejemplos.mis.menu.fragment.PerfilFragment;
import ejemplos.mis.menu.fragment.RecyclerViewFragment;

public class MainActivity extends AppCompatActivity {



    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();

        if (toolbar != null){
            setSupportActionBar(toolbar);
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Mencionar que accion quieres realizar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
               // startActivity(new Intent(MainActivity.this , ActivityFavoritos.class));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_contacto) {
           // Toast.makeText(this,"Este es el item: "+item.getTitle(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this , ContactoActivity.class));
            return true;


        }
        if (id == R.id.action_acercaDe) {
            // Toast.makeText(this,"Este es el item: "+item.getTitle(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this , AcercaDeActivity.class));
            return true;
        }
        if (id == R.id.action_favoritos) {
           // Toast.makeText(this,"Este es el item: "+item.getTitle(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this , ActivityFavoritos.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private  ArrayList<Fragment> agregarFragments(){

        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_cucha_perro);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_perro_perfil);

    }
}
