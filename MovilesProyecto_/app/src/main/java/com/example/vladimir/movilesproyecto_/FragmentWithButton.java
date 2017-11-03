package com.example.vladimir.movilesproyecto_;

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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentWithButton.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentWithButton#newInstance} factory method to
 * create an instance of this fragment.
 */


public class FragmentWithButton extends Fragment implements ValueEventListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<String> categorias;
    //CategoriaAdapter categoriasAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Button principiante, profesional, clase_mundial;
    int puntos_recarga;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference Root_reference = firebaseDatabase.getReference();
    //String user_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
    String puntos_actuales;
    Integer puntos_actuales_int;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    String user = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_with_button, container, false);
        principiante=(Button)view.findViewById(R.id.Beginner);
        profesional=(Button)view.findViewById(R.id.Medium);
        clase_mundial=(Button)view.findViewById(R.id.Expert);
        final int[] current_points = new int[1];
        reference = database.getReference("Usuario "+user).child("Perfil");
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //rutina.setText(dataSnapshot.getKey());
                int i = 0;
                Log.d("number of childs",""+dataSnapshot.getChildrenCount());
                puntos_actuales= String.valueOf(dataSnapshot.child("Puntos ").getValue());
                puntos_actuales_int=Integer.parseInt(puntos_actuales);
               Log.d("PUNTOS ACTUALES",puntos_actuales);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        principiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("actual email", String.valueOf(email_Ref));
                //String user_name=user.getDisplayName();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference puntos_reference = Root_reference.child("Usuario "+user).child("Perfil").child("Puntos ");
                puntos_recarga=100+puntos_actuales_int;
                puntos_reference.setValue(puntos_recarga);

                //actual_points.setValue(actual_points_integer+puntos_recarga);
                Log.d("actual name", String.valueOf(user));
                Log.d("actual money", String.valueOf(puntos_reference));
                Log.d("actual charge money",String.valueOf(puntos_recarga));

            }
        });
        profesional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference puntos_reference = Root_reference.child("Usuario "+user).child("Perfil").child("Puntos ");
                puntos_recarga=500+puntos_actuales_int;
                puntos_reference.setValue(puntos_recarga);
            }
        });
        clase_mundial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference puntos_reference = Root_reference.child("Usuario "+user).child("Perfil").child("Puntos ");
                puntos_recarga=1000+puntos_actuales_int;
                puntos_reference.setValue(puntos_recarga);            }
        });

        //DatabaseReference email_Ref = Root_reference.child(routine_name).child("Email ");


        return view;
    }



    public FragmentWithButton() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentWithButton.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentWithButton newInstance(String param1, String param2) {
        FragmentWithButton fragment = new FragmentWithButton();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }




    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void toastText(String text, String text2);
    }

}
