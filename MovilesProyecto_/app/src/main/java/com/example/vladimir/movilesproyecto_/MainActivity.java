package com.example.vladimir.movilesproyecto_;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentWithButton.OnFragmentInteractionListener{

    private Button sign_out;
    private Button ver_perfil;
    private Button ver_juegos, recargar_Saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign_out=(Button)findViewById(R.id.Sign_out_button);
        ver_perfil=(Button)findViewById(R.id.ver_perfil);
        ver_juegos=(Button)findViewById(R.id.juegos_proximos);
        recargar_Saldo=(Button)findViewById(R.id.recargar_saldo);
        Intent name_intent=this.getIntent();
        final String name=name_intent.getExtras().getString("name");

        ver_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,VerPerfil.class);
                Log.d("actual name",String.valueOf(name));
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        ver_juegos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Calendario.class);
                //Log.d("actual name",String.valueOf(name));
                //intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });
    }


    public void Create_fragment(View v)
    {
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        FragmentWithButton newFragment=FragmentWithButton.newInstance("Hello","Vlad");
        transaction.add(R.id.fragment_space,newFragment, "button");
        Toast.makeText(getApplicationContext(), "Por favor seleccione un tipo de recarga", Toast.LENGTH_SHORT).show();
        transaction.commit();
    }
/*
    public void remove_fragment(View v)
    {
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        FragmentWithButton oldFragment=(FragmentWithButton)manager.findFragmentByTag("button");
        if(oldFragment!=null)
        {
            transaction.remove(oldFragment);
            transaction.commit();
        }
    }
*/




    @Override
    public void toastText(String text, String text2) {

    }
}
