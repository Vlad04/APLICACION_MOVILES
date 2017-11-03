package com.example.vladimir.movilesproyecto_;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.vladimir.movilesproyecto_.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
public class GenerarJuegos extends AppCompatActivity {


    CheckBox america, pumas, toluca, chivas, tigres, pachuca, cruz_azul, atlas;
    CheckBox empate1,empate2,empate3,empate4;
    Button apostar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_juegos);
        america=(CheckBox)findViewById(R.id.America_checkbox);
        pumas=(CheckBox)findViewById(R.id.Pumas_checkbox);
        toluca=(CheckBox)findViewById(R.id.Toluca_checkbox);
        chivas=(CheckBox)findViewById(R.id.Chivas_checkbox);
        tigres=(CheckBox)findViewById(R.id.Tigres_checkbox);
        pachuca=(CheckBox)findViewById(R.id.Pachuca_checkbox);
        cruz_azul=(CheckBox)findViewById(R.id.Cruzazul_checkbox);
        atlas=(CheckBox)findViewById(R.id.Atlas_checkbox);
        empate1=(CheckBox)findViewById(R.id.empate_checkbox1);
        empate2=(CheckBox)findViewById(R.id.empate_checkbox2);
        empate3=(CheckBox)findViewById(R.id.empate_checkbox3);
        empate4=(CheckBox)findViewById(R.id.empate_checkbox4);
        apostar=(Button)findViewById(R.id.Apostar_button);

        apostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //JUEGO PUMAS TOLUCA
                   if(pumas.isChecked() && !toluca.isChecked() && !empate1.isChecked())
                   {
                       Log.d("la apuesta es por","pumas toluca");

                       Log.d("la apuesta es por","pumas gana");
                   }
                   else if(toluca.isChecked() && !pumas.isChecked() && !empate1.isChecked())
                   {
                       Log.d("la apuesta es por","pumas toluca");

                       Log.d("La apuesta es por","toluca gana");
                   }
                   else if((toluca.isChecked()&&pumas.isChecked())||(toluca.isChecked()&&empate1.isChecked())||(pumas.isChecked()&&empate1.isChecked()))
                   {
                       Log.d("por favor","solo escoja a un equipo");
                   }
                   else if(empate1.isChecked()&&!pumas.isChecked()&&!toluca.isChecked())
                   {
                       Log.d("La apuesta es por","pumas toluca");
                       Log.d("La apuesta es por","empate");
                   }
                //JUEGO AMERICA CHIVAS
                if(america.isChecked() && !chivas.isChecked() && !empate2.isChecked())
                {
                    Log.d("la apuesta es por","america chivas");

                    Log.d("la apuesta es por","america gana");
                }
                else if(chivas.isChecked() && !america.isChecked() && !empate2.isChecked())
                {
                    Log.d("la apuesta es por","america chivas");

                    Log.d("La apuesta es por","chivas gana");
                }
                else if((chivas.isChecked()&&america.isChecked())||(chivas.isChecked()&&empate2.isChecked())||(america.isChecked()&&empate2.isChecked()))
                {
                    Log.d("por favor","solo escoja a un equipo");
                }
                else if(empate2.isChecked()&&!america.isChecked()&&!chivas.isChecked())
                {
                    Log.d("La apuesta es por","america chivas");
                    Log.d("La apuesta es por","empate");
                }
                //JUEGO TIGRES PACHUCA
                if(tigres.isChecked() && !pachuca.isChecked() && !empate3.isChecked())
                {
                    Log.d("la apuesta es por","tigres pachuca");

                    Log.d("la apuesta es por","tigres gana");
                }
                else if(pachuca.isChecked() && !tigres.isChecked() && !empate3.isChecked())
                {
                    Log.d("la apuesta es por","tigres pachuca");

                    Log.d("La apuesta es por","pachuca gana");
                }
                else if((pachuca.isChecked()&&tigres.isChecked())||(pachuca.isChecked()&&empate3.isChecked())||(tigres.isChecked()&&empate3.isChecked()))
                {
                    Log.d("por favor","solo escoja a un equipo");
                }
                else if(empate3.isChecked()&&!tigres.isChecked()&&!pachuca.isChecked())
                {
                    Log.d("La apuesta es por","tigres pachuca");
                    Log.d("La apuesta es por","empate");
                }

                //JUEGO CRUZ AZUL ATLAS
                if(cruz_azul.isChecked() && !atlas.isChecked() && !empate4.isChecked())
                {
                    Log.d("la apuesta es por","cruz azul vs atlas");

                    Log.d("la apuesta es por","cruz azul gana");
                }
                else if(atlas.isChecked() && !cruz_azul.isChecked() && !empate4.isChecked())
                {
                    Log.d("la apuesta es por","cruz azul vs atlas");

                    Log.d("La apuesta es por","atlas gana");
                }
                else if((atlas.isChecked()&&cruz_azul.isChecked())||(atlas.isChecked()&&empate4.isChecked())||(cruz_azul.isChecked()&&empate4.isChecked()))
                {
                    Log.d("por favor","solo escoja a un equipo");
                }
                else if(empate4.isChecked()&&!cruz_azul.isChecked()&&!atlas.isChecked())
                {
                    Log.d("La apuesta es por","cruz azul vs atlas");
                    Log.d("La apuesta es por","empate");
                }

            }
        });



    }
}
