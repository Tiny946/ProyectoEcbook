package com.ecbook.proyectoecbook.proyectoecbook.Explorar;


import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.ecbook.proyectoecbook.R;
import com.ecbook.proyectoecbook.proyectoecbook.InfoBooks;
import com.ecbook.proyectoecbook.proyectoecbook.Inicio_Sesion.LoginActivity;
import com.ecbook.proyectoecbook.proyectoecbook.MainActivity;
import com.ecbook.proyectoecbook.proyectoecbook.MainActivityTabs;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static javax.xml.datatype.DatatypeConstants.DURATION;


public class ExplorarFragment extends Fragment implements RecyclerViewOnClickListener{
    RecyclerView rv;
    //Toolbar toolbarCard;

    List<InfoBooks> libros;
    AdapterExplorar adapter;
    Context context;

    /*private ViewGroup linearLayoutDetails;
    private ImageView imageViewExpand;
    private static final int DURATION = 250;*/

    public static String telefono = "telefono";
    public static String email = "email";

    public final static String FIREBASE_REFERENCES = "Libros";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(FIREBASE_REFERENCES);

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_explorar, container, false);
        refresh(rootView);


        //setContentView(R.layout.fragment_explorar);
        //toolbarCard = (Toolbar) rootView.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbarCard);

        //linearLayoutDetails = (ViewGroup) rootView.findViewById(R.id.linearLayoutDetails);
        //imageViewExpand = (ImageView) rootView.findViewById(R.id.imageViewExpand);

        /*Toolbar toolbarCard = (Toolbar) rootView.findViewById(R.id.toolbarCard);
        toolbarCard.setTitle("Hola");
        toolbarCard.setSubtitle("Ejemplo");
        toolbarCard.inflateMenu(R.menu.card_menu);*/

        /*rv = (RecyclerView)rootView.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(context));*/

        libros = new ArrayList<>();

        final AdapterExplorar adapter = new AdapterExplorar(libros);

        adapter.setRecyclerViewOnClickListener(this);

        myRef.child(FIREBASE_REFERENCES);
        rv.setAdapter(adapter);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                InfoBooks libro = dataSnapshot.getValue(InfoBooks.class);
                libros.add(libro);
                refresh(rootView);
                /*rv = (RecyclerView)rootView.findViewById(R.id.rv);
                rv.setHasFixedSize(true);
                rv.setLayoutManager(new LinearLayoutManager(context));*/
                rv.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                InfoBooks libro = dataSnapshot.getValue(InfoBooks.class);
                libros.add(libro);
                refresh(rootView);
                /*rv = (RecyclerView)rootView.findViewById(R.id.rv);
                rv.setHasFixedSize(true);
                rv.setLayoutManager(new LinearLayoutManager(context));*/
                rv.setAdapter(adapter);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //PARA EL MENU TOOLBAR
        /*toolbarCard.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_option1:
                        Toast.makeText(getContext(),"funciona1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_option2:
                        Toast.makeText(getContext(),"funciona2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_option3:
                        Toast.makeText(getContext(),"funciona3", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });*/

        return rootView;
    }


    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(getContext(),"funciona", Toast.LENGTH_SHORT).show();
        hacerIntent(position);
    }

    private void refresh(View rootView) {
        rv = (RecyclerView)rootView.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(context));
    }

    public void setSupportActionBar(Toolbar supportActionBar) {
        Toolbar supportActionBar1 = supportActionBar;
    }

    public void hacerIntent (int position){
        String telfA = String.format(libros.get(position).getTelefono(), telefono);
        String resA = String.format(libros.get(position).getEmail(), email);

        Intent i = new Intent(getActivity(), Pop.class);
        i.putExtra(telefono, telfA);
        i.putExtra(email, resA);
        startActivity(i);
    }

    //PARA EL EXPANDIR
    /*public void toggleDetails(View view) {
        if (linearLayoutDetails.getVisibility() == View.GONE) {
            ExpandAndCollapseViewUtil.expand(linearLayoutDetails, DURATION);
            imageViewExpand.setImageResource(R.mipmap.more);
            rotate(-180.0f);
        } else {
            ExpandAndCollapseViewUtil.collapse(linearLayoutDetails, DURATION);
            imageViewExpand.setImageResource(R.mipmap.less);
            rotate(180.0f);
        }
    }

    private void rotate(float angle) {
        Animation animation = new RotateAnimation(0.0f, angle, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(DURATION);
        imageViewExpand.startAnimation(animation);
    }*/
}
