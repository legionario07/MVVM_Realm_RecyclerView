package br.com.omniatechnology.pernavendas.realmapplication.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.transition.PatternPathMotion;
import android.util.Patterns;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject implements Parcelable {

    @PrimaryKey
    private Integer id;
    private String email;
    private String nome;

    public User(){

    }

    public User(String nome, String email){

        this();
        this.email = email;
        this.nome = nome;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + this.email + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.email);
        dest.writeString(this.nome);
    }

    protected User(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.email = in.readString();
        this.nome = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String validModel(){

        StringBuilder validStr = new StringBuilder();

        if(getNome()==null || getNome().isEmpty()){
            validStr.append("Name Invalid");
        }else if(getEmail()== null || getEmail().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()){
            validStr.append("Email invalid");
        }

        return validStr.toString();

    }
}
