package com.ecbook.proyectoecbook.proyectoecbook;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ecbook.proyectoecbook.R;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.app.Activity.RESULT_OK;


public class PublicarFragment extends Fragment{

    FirebaseDatabase firebasedb = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    String[] tipoVentIntercam = new String[2];
    EditText NombLibro, autorLibro, telefono, generoLibro, precio, envio, editTextEmail;
    TextView horaPuesta, enterCurrentLocation;
    Button seleccionarHora, seleccionarLugar, subirProducto;
    Spinner spinner;
    int posicion=0;
    public final static String HORA = "timePicker";
    private final int REQUEST_CODE_PLACEPICKER=1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_publicar, container, false);
        NombLibro = (EditText) rootView.findViewById(R.id.editTextNombreLibro);
        autorLibro = (EditText) rootView.findViewById(R.id.editTextAutorLibro);
        telefono = (EditText) rootView.findViewById(R.id.editTextTelefono);
        generoLibro = (EditText) rootView.findViewById(R.id.editTextGeneroLibro);
        precio = (EditText) rootView.findViewById(R.id.editTextPrecio);
        envio = (EditText) rootView.findViewById(R.id.editTextEnvio);
        editTextEmail = (EditText) rootView.findViewById(R.id.editTextEmail);
        horaPuesta = (TextView) rootView.findViewById(R.id.textViewHora);
        seleccionarHora = (Button)rootView.findViewById(R.id.buttonHora);
        enterCurrentLocation = (TextView) rootView.findViewById(R.id.show_selected_location);

        subirProducto = (Button) rootView.findViewById(R.id.buttonSubirProducto);
        spinner = (Spinner)rootView.findViewById(R.id.spinnerInterVenta);

        seleccionarHora = (Button)rootView.findViewById(R.id.buttonHora);
        seleccionarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeReloj horaIda = new TimeReloj();
                horaIda.setTv1(horaPuesta);
                horaIda.show(getFragmentManager(), HORA);

            }
        });
        seleccionarLugar = (Button) rootView.findViewById(R.id.buttonSelecionarLugar);
        seleccionarLugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intentL = new Intent(getActivity(),SeleccionarLugar.class);
                startActivity(intentL);*/
                startPlacePickerActivity();

            }
        });


        subirProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NombLibro.getText().toString().equals("") || autorLibro.getText().toString().equals("") || telefono.getText().toString().equals("") || generoLibro.getText().toString().equals("") ||
                        precio.getText().toString().equals("") || editTextEmail.getText().toString().equals("")) {
                    Toast.makeText(getContext(),"Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
                } else if(envio.getText().toString().equals("")) {
                    Toast.makeText(getContext(),"Complete el campo envio poniendo SI o NO", Toast.LENGTH_SHORT).show();
                }else {
                    InfoBooks libros = new InfoBooks();
                    databaseReference = FirebaseDatabase.getInstance().getReference();
                    String Key = databaseReference.child("post").push().getKey();

                    libros.setKeyLibro(Key);
                    libros.setIntercVenta(spinner.getSelectedItem().toString());
                    libros.setNombreLibro(NombLibro.getText().toString());
                    libros.setAutorLibro(autorLibro.getText().toString());
                    libros.setTelefono(telefono.getText().toString());
                    libros.setGenero(generoLibro.getText().toString());
                    libros.setPrecio(Integer.parseInt(precio.getText().toString()));
                    libros.setEnvio(envio.getText().toString());
                    libros.setEmail(editTextEmail.getText().toString());
                    libros.setLugarQuedada(enterCurrentLocation.getText().toString());

                    //hora
                    libros.setHoraQuedada(horaPuesta.getText().toString());



                    //el porcentaje ese es para sustituir el primer string or el segundo
                    DatabaseReference miRef = firebasedb.getReference(String.format("nuevo_libro: %s->%s", libros.getIntercVenta(), libros.getNombreLibro()));
                    posicion = spinner.getSelectedItemPosition();


                    databaseReference.child("Libros").child(Key).setValue(libros);
                    //miRef.setValue(torneo);

                    Toast.makeText(getContext(),"Articulo subido!", Toast.LENGTH_SHORT).show();
                    //limpiamos los campos para que vuelva a introducir datos
                    NombLibro.setText("");
                    autorLibro.setText("");
                    telefono.setText("");
                    generoLibro.setText("");
                    precio.setText("");
                    envio.setText("");
                    horaPuesta.setText("");
                    editTextEmail.setText("");
                    enterCurrentLocation.setText("");

                }

            }
        });

        tipoVentIntercam[0]="VENTA";
        tipoVentIntercam[1]="INTERCAMBIO";

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, tipoVentIntercam);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        return rootView;
    }

    private void startPlacePickerActivity() {
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
        // this would only work if you have your Google Places API working

        try {
            Intent intent = intentBuilder.build(getActivity());
            startActivityForResult(intent, REQUEST_CODE_PLACEPICKER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PLACEPICKER && resultCode == RESULT_OK) {
            displaySelectedPlaceFromPlacePicker(data);
        }
    }

    private void displaySelectedPlaceFromPlacePicker(Intent data) {
        Place placeSelected = PlacePicker.getPlace(data, getActivity());

        String name = placeSelected.getName().toString();
        String address = placeSelected.getAddress().toString();

        enterCurrentLocation.setText(address);
    }
}
