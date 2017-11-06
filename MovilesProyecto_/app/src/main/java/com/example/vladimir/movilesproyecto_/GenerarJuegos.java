package com.example.vladimir.movilesproyecto_;

import android.provider.ContactsContract;
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
import java.util.Calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;
public class GenerarJuegos extends AppCompatActivity {


    CheckBox america, pumas, toluca, chivas, tigres, pachuca, cruz_azul, atlas;
    CheckBox empate1,empate2,empate3,empate4;
    Button apostar;
    EditText cantidadApostar;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference Root_reference=firebaseDatabase.getReference();
    String user = FirebaseAuth.getInstance().getCurrentUser().getUid();

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
        cantidadApostar=(EditText)findViewById(R.id.cantidad_apostar);
        /*
        RANDOM
        RANDOM_JUEGO_1
            1:GANO PUMAS
            2:EMPATE
            3:GANO TOLUCA

        RANDOM_JUEGO_2
            1:GANO CHIVAS
            2:EMPATE
            3:GANO AMERICA

        RANDOM_JUEGO_3
            1:GANO TIGRES
            2:EMPATE
            3:GANO PACHUCA

        RANDOM_JUEGO_4
            1:GANO CRUZ AZUL
            2:EMPATE
            3:GANO ATLAS

        DECISION_USUARIO

        IF(PUMAS.ISCHECKED() && RANDOM_JUEGO_1==1)
            FIREBASE GANAR 1000 PUNTOS
        IF ELSE{PUMAS.ISCHECKED() && RANDOM_JUEGO_1!==1 || RANDOM_JUEGO_1==2
             FIREBASE PERDER cantidad_apostada

         */
        apostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());

                try {
                    String cantidad_apostada = cantidadApostar.getText().toString();

                    int cantidad_apostada_int = Integer.parseInt(cantidad_apostada);
                    Log.d("Cantidad apostada", String.valueOf(cantidad_apostada_int));
                }catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Por favor ingrese los datos de manera correcta",Toast.LENGTH_SHORT).show();
                }
                Random r=new Random();
                int resultado_random_juego1=r.nextInt((3-1)+1)+1;
                int resultado_random_juego2=r.nextInt((3-1)+1)+1;
                int resultado_random_juego3=r.nextInt((3-1)+1)+1;
                int resultado_random_juego4=r.nextInt((3-1)+1)+1;
                Log.d("EL RESULTADO RANDOM ES",String.valueOf(resultado_random_juego1));

                //RANDOMM JUEGO PUMAS TOLUCA
                if(resultado_random_juego1==1)
                {
                    Log.d("Gano","Pumas");
                }
                else if(resultado_random_juego1==2)
                {
                    Log.d("Gano","Empate");
                }
                else if(resultado_random_juego1==3)
                {
                    Log.d("Gano","Toluca");
                }

                //RANDOM JUEGO CHIVAS AMERICA

                if(resultado_random_juego2==1)
                {
                    Log.d("Gano","Chivas");
                }
                else if(resultado_random_juego2==2)
                {
                    Log.d("Gano","Empate");
                }
                else if(resultado_random_juego2==3)
                {
                    Log.d("Gano","America");
                }


                //RANDOM JUEGO TIGRES PACHUCA

                if(resultado_random_juego3==1)
                {
                    Log.d("Gano","Tigres");
                }
                else if(resultado_random_juego3==2)
                {
                    Log.d("Gano","Empate");
                }
                else if(resultado_random_juego3==3)
                {
                    Log.d("Gano","Pachuca");
                }


                //RANDOM JUEGO CRUZ AZUL ATLAS
                if(resultado_random_juego4==1)
                {
                    Log.d("Gano","Cruz azul");
                }
                else if(resultado_random_juego4==2)
                {
                    Log.d("Gano","Empate");
                }
                else if(resultado_random_juego4==3)
                {
                    Log.d("Gano","Atlas");
                }


                int day = calendar.get(Calendar.DATE);

                //JUEGO PUMAS TOLUCA
                   if(pumas.isChecked() && !toluca.isChecked() && !empate1.isChecked())
                   {
                       Log.d("la apuesta es por","pumas toluca");
                        DatabaseReference apuesta=Root_reference.child("Usuario "+user).child("Apuestas primer juego").child("Juego");
                        apuesta.setValue("Pumas vs Toluca");
                        DatabaseReference apuesta_choice=Root_reference.child("Usuario "+user).child("Apuestas primer juego").child("Apostó por");
                        apuesta_choice.setValue("Pumas");
                        DatabaseReference date_apuesta=Root_reference.child("Usuario "+user).child("Apuestas primer juego").child("Fecha");
                       date_apuesta.setValue(formattedDate);

                       Log.d("la apuesta es por","pumas gana");
                   }
                   else if(toluca.isChecked() && !pumas.isChecked() && !empate1.isChecked())
                   {
                       Log.d("la apuesta es por","pumas toluca");
                       DatabaseReference apuesta=Root_reference.child("Usuario "+user).child("Apuestas primer juego").child("Juego");
                       apuesta.setValue("Pumas vs Toluca");
                       DatabaseReference apuesta_choice=Root_reference.child("Usuario "+user).child("Apuestas primer juego").child("Apostó por");
                       apuesta_choice.setValue("Toluca");
                       DatabaseReference date_apuesta=Root_reference.child("Usuario "+user).child("Apuestas primer juego").child("Fecha");
                       date_apuesta.setValue(formattedDate);

                       Log.d("La apuesta es por","toluca gana");
                   }
                   else if((toluca.isChecked()&&pumas.isChecked())||(toluca.isChecked()&&empate1.isChecked())||(pumas.isChecked()&&empate1.isChecked()))
                   {
                       Log.d("por favor","solo escoja a un equipo");
                       Toast.makeText(getApplicationContext(),"Por favor, solo escoja a un equipo",Toast.LENGTH_SHORT).show();
                   }
                   else if(empate1.isChecked()&&!pumas.isChecked()&&!toluca.isChecked())
                   {
                       Log.d("La apuesta es por","pumas toluca");
                       DatabaseReference apuesta=Root_reference.child("Usuario "+user).child("Apuestas primer juego").child("Juego");
                       apuesta.setValue("Pumas vs Toluca");
                       DatabaseReference apuesta_choice=Root_reference.child("Usuario "+user).child("Apuestas primer juego").child("Apostó por");
                       apuesta_choice.setValue("Empate");
                       DatabaseReference date_apuesta=Root_reference.child("Usuario "+user).child("Apuestas primer juego").child("Fecha");
                       date_apuesta.setValue(formattedDate);
                       Log.d("La apuesta es por","empate");
                   }
                //JUEGO AMERICA CHIVAS
                if(america.isChecked() && !chivas.isChecked() && !empate2.isChecked())
                {
                    Log.d("la apuesta es por","america chivas");
                    DatabaseReference apuesta=Root_reference.child("Usuario "+user).child("Apuestas segundo juego").child("Juego");
                    apuesta.setValue("America vs Chivas");
                    DatabaseReference apuesta_choice=Root_reference.child("Usuario "+user).child("Apuestas segundo juego").child("Apostó por");
                    apuesta_choice.setValue("America");
                    DatabaseReference date_apuesta=Root_reference.child("Usuario "+user).child("Apuestas segundo juego").child("Fecha");
                    date_apuesta.setValue(formattedDate);

                    Log.d("la apuesta es por","america gana");
                }
                else if(chivas.isChecked() && !america.isChecked() && !empate2.isChecked())
                {
                    Log.d("la apuesta es por","america chivas");
                    DatabaseReference apuesta=Root_reference.child("Usuario "+user).child("Apuestas segundo juego").child("Juego");
                    apuesta.setValue("America vs Chivas");
                    DatabaseReference apuesta_choice=Root_reference.child("Usuario "+user).child("Apuestas segundo juego").child("Apostó por");
                    apuesta_choice.setValue("Chivas");
                    DatabaseReference date_apuesta=Root_reference.child("Usuario "+user).child("Apuestas segundo juego").child("Fecha");
                    date_apuesta.setValue(formattedDate);


                    Log.d("La apuesta es por","chivas gana");
                }
                else if((chivas.isChecked()&&america.isChecked())||(chivas.isChecked()&&empate2.isChecked())||(america.isChecked()&&empate2.isChecked()))
                {
                    Log.d("por favor","solo escoja a un equipo");
                    Toast.makeText(getApplicationContext(),"Por favor, solo escoja a un equipo",Toast.LENGTH_SHORT).show();

                }
                else if(empate2.isChecked()&&!america.isChecked()&&!chivas.isChecked())
                {
                    Log.d("La apuesta es por","america chivas");
                    Log.d("La apuesta es por","empate");
                    DatabaseReference apuesta=Root_reference.child("Usuario "+user).child("Apuestas segundo juego").child("Juego");
                    apuesta.setValue("America vs Chivas");
                    DatabaseReference apuesta_choice=Root_reference.child("Usuario "+user).child("Apuestas segundo juego").child("Apostó por");
                    apuesta_choice.setValue("Empate");
                    DatabaseReference date_apuesta=Root_reference.child("Usuario "+user).child("Apuestas segundo juego").child("Fecha");
                    date_apuesta.setValue(formattedDate);

                }
                //JUEGO TIGRES PACHUCA
                if(tigres.isChecked() && !pachuca.isChecked() && !empate3.isChecked())
                {
                    Log.d("la apuesta es por","tigres pachuca");

                    Log.d("la apuesta es por","tigres gana");

                    DatabaseReference apuesta=Root_reference.child("Usuario "+user).child("Apuestas tercer juego").child("Juego");
                    apuesta.setValue("Tigres vs Pachuca");
                    DatabaseReference apuesta_choice=Root_reference.child("Usuario "+user).child("Apuestas tercer juego").child("Apostó por");
                    apuesta_choice.setValue("Tigres");
                    DatabaseReference date_apuesta=Root_reference.child("Usuario "+user).child("Apuestas tercer juego").child("Fecha");
                    date_apuesta.setValue(formattedDate);

                }
                else if(pachuca.isChecked() && !tigres.isChecked() && !empate3.isChecked())
                {
                    Log.d("la apuesta es por","tigres pachuca");

                    Log.d("La apuesta es por","pachuca gana");

                    DatabaseReference apuesta=Root_reference.child("Usuario "+user).child("Apuestas tercer juego").child("Juego");
                    apuesta.setValue("Tigres vs Pachuca");
                    DatabaseReference apuesta_choice=Root_reference.child("Usuario "+user).child("Apuestas tercer juego").child("Apostó por");
                    apuesta_choice.setValue("Pachuca");
                    DatabaseReference date_apuesta=Root_reference.child("Usuario "+user).child("Apuestas tercer juego").child("Fecha");
                    date_apuesta.setValue(formattedDate);
                }
                else if((pachuca.isChecked()&&tigres.isChecked())||(pachuca.isChecked()&&empate3.isChecked())||(tigres.isChecked()&&empate3.isChecked()))
                {
                    Log.d("por favor","solo escoja a un equipo");
                    Toast.makeText(getApplicationContext(),"Por favor, solo escoja a un equipo",Toast.LENGTH_SHORT).show();

                }
                else if(empate3.isChecked()&&!tigres.isChecked()&&!pachuca.isChecked())
                {
                    Log.d("La apuesta es por","tigres pachuca");
                    Log.d("La apuesta es por","empate");

                    DatabaseReference apuesta=Root_reference.child("Usuario "+user).child("Apuestas tercer juego").child("Juego");
                    apuesta.setValue("Tigres vs Pachuca");
                    DatabaseReference apuesta_choice=Root_reference.child("Usuario "+user).child("Apuestas tercer juego").child("Apostó por");
                    apuesta_choice.setValue("Empate");
                    DatabaseReference date_apuesta=Root_reference.child("Usuario "+user).child("Apuestas tercer juego").child("Fecha");
                    date_apuesta.setValue(formattedDate);
                }

                //JUEGO CRUZ AZUL ATLAS
                if(cruz_azul.isChecked() && !atlas.isChecked() && !empate4.isChecked())
                {
                    Log.d("la apuesta es por","cruz azul vs atlas");

                    Log.d("la apuesta es por","cruz azul gana");

                    DatabaseReference apuesta=Root_reference.child("Usuario "+user).child("Apuestas cuarto juego").child("Juego");
                    apuesta.setValue("Cruz azul vs Atlas");
                    DatabaseReference apuesta_choice=Root_reference.child("Usuario "+user).child("Apuestas cuarto juego").child("Apostó por");
                    apuesta_choice.setValue("Cruz azul");
                    DatabaseReference date_apuesta=Root_reference.child("Usuario "+user).child("Apuestas cuarto juego").child("Fecha");
                    date_apuesta.setValue(formattedDate);
                }
                else if(atlas.isChecked() && !cruz_azul.isChecked() && !empate4.isChecked())
                {
                    Log.d("la apuesta es por","cruz azul vs atlas");

                    Log.d("La apuesta es por","atlas gana");

                    DatabaseReference apuesta=Root_reference.child("Usuario "+user).child("Apuestas cuarto juego").child("Juego");
                    apuesta.setValue("Cruz azul vs Atlas");
                    DatabaseReference apuesta_choice=Root_reference.child("Usuario "+user).child("Apuestas cuarto juego").child("Apostó por");
                    apuesta_choice.setValue("Atlas");
                    DatabaseReference date_apuesta=Root_reference.child("Usuario "+user).child("Apuestas cuarto juego").child("Fecha");
                    date_apuesta.setValue(formattedDate);
                }
                else if((atlas.isChecked()&&cruz_azul.isChecked())||(atlas.isChecked()&&empate4.isChecked())||(cruz_azul.isChecked()&&empate4.isChecked()))
                {
                    Log.d("por favor","solo escoja a un equipo");
                    Toast.makeText(getApplicationContext(),"Por favor, solo escoja a un equipo",Toast.LENGTH_SHORT).show();

                }
                else if(empate4.isChecked()&&!cruz_azul.isChecked()&&!atlas.isChecked())
                {
                    Log.d("La apuesta es por","cruz azul vs atlas");
                    Log.d("La apuesta es por","empate");

                    DatabaseReference apuesta=Root_reference.child("Usuario "+user).child("Apuestas cuarto juego").child("Juego");
                    apuesta.setValue("Cruz azul vs Atlas");
                    DatabaseReference apuesta_choice=Root_reference.child("Usuario "+user).child("Apuestas cuarto juego").child("Apostó por");
                    apuesta_choice.setValue("Empate");
                    DatabaseReference date_apuesta=Root_reference.child("Usuario "+user).child("Apuestas cuarto juego").child("Fecha");
                    date_apuesta.setValue(formattedDate);
                }

            }
        });



    }
}
