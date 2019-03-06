package br.com.omniatechnology.pernavendas.realmapplication.dao;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.omniatechnology.pernavendas.realmapplication.model.User;
import io.realm.Realm;
import io.realm.RealmResults;

public class UserDao {

    private Realm realm;

    public UserDao(Context context){
        realm.init(context);
        realm = Realm.getDefaultInstance();

    }

    public User save(User user){

        try {
            realm.beginTransaction();

            if(user.getId()==null)
                user.setId(RealmUtil.nextId(realm, user.getClass()));

            realm.insertOrUpdate(user);

            realm.commitTransaction();

        }catch (Exception e){
            Log.i("TAG", "save: "+e.getMessage());
            return null;
        }
        return user;

    }

    public Boolean delete(User user){

        boolean wasDelete = true;

        try {
            realm.beginTransaction();
            User user1 = realm.where(User.class).equalTo("id", user.getId()).findFirst();
            user1.deleteFromRealm();
            realm.commitTransaction();

        }catch (Exception e){
            Log.i("TAG", "delete: "+e.getMessage());
            wasDelete = false;
        }
        return wasDelete;

    }



    public List<User> findAll(){

        List<User> usersList = null;
        try {

            RealmResults<User> users = realm.where(User.class).findAll();

            usersList = users.subList(0, users.size());

        }catch (Exception e){
            Log.i("TAG", e.getMessage());
            usersList = new ArrayList<>();
        }
        return usersList;

    }

    public boolean deleteAll(){

        boolean wasDelete = true;

        try {
            realm.beginTransaction();
            realm.deleteAll();
            realm.commitTransaction();
        }catch (Exception e){
            Log.i("TAG", "delete: "+e.getMessage());
            wasDelete = false;
        }
        return wasDelete;
    }


}
