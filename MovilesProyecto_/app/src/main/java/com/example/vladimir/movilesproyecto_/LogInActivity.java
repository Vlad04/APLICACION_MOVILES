package com.example.vladimir.movilesproyecto_;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.Activity;
import android.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LogInActivity extends Activity {


    private EditText inputEmail, inputPassword, name; //Son los textos en donde ingresas el email y password
    private FirebaseAuth auth;                  //Este es un objeto de tipo FIrebase
    private ProgressBar progressBar;            //El circulo girando que aparece en la parte inferior de la pantalla.
    private Button btnSignup, btnLogin, btnReset;   //Botones para iniciar las actividades SignUp, ResetPassword y
    //a la actividad principal una vez ya haber ingresado.
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference Root_reference=firebaseDatabase.getReference();
    public int points=500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();  //Aqui  inicia la instancia de firebase con el objeto

        // set the view now
        setContentView(R.layout.activity_log_in);

        //Localiza los inputs que ingresa datos, y los botones que llevan a otras actividades.
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        name=(EditText)findViewById(R.id.name);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        auth = FirebaseAuth.getInstance();
        final String result = auth.getCurrentUser().getUid();



        //Al dar click en el boton de registrar lleva a la clase signup
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, SignupActivity.class));
            }
        });

        //Al dar click en el boton de reset password, lleva a la clase ResetPasswordActivity
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, ResetPasswordActivity.class));
            }
        });


        //Se refiere al proceso COMPLETO de ingresar a la aplicacion por medio del Log In usando email y password
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Lee el email del usuario, convirtiendo el input en un String
                final String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                //Si no existe informacion de email, se le pide al usuario ingresar su email
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Ingrese email", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Si no existe informacion de password, se le pide al usuario ingresar su password
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Ingrese contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                //El progressBar sirve para mostrar el circulo girando en la parte inferior
                //de la pantalla, mientras se ingresa a la aplicacion.
                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                //Esta parte es completamente para autentificar si el correo Y el password coinciden con
                //la informacion en la base de datos, ademas de ciertos reglamentos.
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                //Asi como dice arriba, esta parte es para revisar si NO FUE EXITOSO el LogIn
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    //En el caso de que el password sea menor de 6 caracteres (lo cual no esta permitido)
                                    //enviara un mensaje de error tratandose sobre lo mismo.
                                    if (password.length() < 6) {
                                        Toast.makeText(LogInActivity.this, "La contrase;a debe tener mas de 5 caracteres", Toast.LENGTH_LONG).show();
                                    } else {
                                        //Si no es por el tamaño de password, entonces es un error de autentificacion
                                        //lo cual arroja otro mensaje de alerta
                                        Toast.makeText(LogInActivity.this, "Auth failed", Toast.LENGTH_LONG).show();

                                    }
                                } else {


                                    //En el caso de que la autentificacion fue EXITOSA, envia al usuario a la clase llamada
                                    //SecondActivity
                                    //name_Ref.setValue(name.getText().toString().trim());      //check for trim method
                                    //email_Ref.setValue(inputEmail);
                                    //password_Ref.setValue(inputPassword);
                                    //Points_Ref.setValue("500");
                                    final String routine_name = "Usuario " + result;

                                    final DatabaseReference email_Ref = Root_reference.child(routine_name).child("Perfil").child("Email ");
                                    final DatabaseReference name_Ref = Root_reference.child(routine_name).child("Perfil").child("Nombre ");
                                    final DatabaseReference password_Ref = Root_reference.child(routine_name).child("Perfil").child("Password ");
                                    final DatabaseReference Points_Ref = Root_reference.child(routine_name).child("Perfil").child("Puntos ");
                                    DatabaseReference check_if_exist=Root_reference.child(routine_name);
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference reference;
                                    check_if_exist.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if(!dataSnapshot.exists())
                                            {
                                                name_Ref.setValue(name.getText().toString().trim());
                                                email_Ref.setValue(inputEmail.getText().toString().trim());
                                                password_Ref.setValue(inputPassword.getText().toString().trim());
                                                Points_Ref.setValue(points);
                                            }

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                                    intent.putExtra("name",routine_name);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}


