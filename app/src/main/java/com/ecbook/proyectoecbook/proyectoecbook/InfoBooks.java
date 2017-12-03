package com.ecbook.proyectoecbook.proyectoecbook;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alberto on 15/11/2017.
 */
public class InfoBooks implements Parcelable{
    private String keyLibro;
    private String intercVenta;
    private String nombreLibro;
    private String autorLibro;
    private String telefono;
    private String genero;
    private int precio;
    private String envio;
    private String lugarQuedada;
    private String horaQuedada;
    private String email;



    public InfoBooks() {
    }

    public InfoBooks(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public InfoBooks(String keyLibro, String intercVenta, String nombreLibro, String autorLibro, String telefono, int precio, String genero, String envio, String horaQuedada, String lugarQuedada, String email) {
        this.keyLibro = keyLibro;
        this.intercVenta = intercVenta;
        this.nombreLibro = nombreLibro;
        this.autorLibro = autorLibro;
        this.telefono = telefono;
        this.precio = precio;
        this.genero = genero;
        this.envio = envio;
        this.horaQuedada = horaQuedada;
        this.lugarQuedada = lugarQuedada;
        this.email = email;
    }

    protected InfoBooks(Parcel in) {
        keyLibro = in.readString();
        intercVenta = in.readString();
        nombreLibro = in.readString();
        autorLibro = in.readString();
        telefono = in.readString();
        precio = in.readInt();
        genero = in.readString();
        envio = in.readString();
        horaQuedada = in.readString();
        lugarQuedada = in.readString();
        email = in.readString();
    }

    public static final Creator<InfoBooks> CREATOR = new Creator<InfoBooks>() {
        @Override
        public InfoBooks createFromParcel(Parcel in) {
            return new InfoBooks(in);
        }

        @Override
        public InfoBooks[] newArray(int size) {
            return new InfoBooks[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(keyLibro);
        parcel.writeString(intercVenta);
        parcel.writeString(nombreLibro);
        parcel.writeString(autorLibro);
        parcel.writeString(telefono);
        parcel.writeInt(precio);
        parcel.writeString(genero);
        parcel.writeString(envio);
        parcel.writeString(horaQuedada);
        parcel.writeString(lugarQuedada);
        parcel.writeString(email);
    }

    public String getIntercVenta() {
        return intercVenta;
    }

    public void setIntercVenta(String intercVenta) {
        this.intercVenta = intercVenta;
    }

    public String getEnvio() {
        return envio;
    }

    public void setEnvio(String envio) {
        this.envio = envio;
    }

    public String getHoraQuedada() {
        return horaQuedada;
    }

    public void setHoraQuedada(String horaQuedada) {
        this.horaQuedada = horaQuedada;
    }

    public String getLugarQuedada() {
        return lugarQuedada;
    }

    public void setLugarQuedada(String lugarQuedada) {
        this.lugarQuedada = lugarQuedada;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAutorLibro() {
        return autorLibro;
    }

    public void setAutorLibro(String autorLibro) {
        this.autorLibro = autorLibro;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getKeyLibro() {
        return keyLibro;
    }

    public void setKeyLibro(String keyLibro) {
        this.keyLibro = keyLibro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
