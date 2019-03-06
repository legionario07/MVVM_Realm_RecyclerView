package br.com.omniatechnology.pernavendas.realmapplication.dao;

import io.realm.Realm;
import io.realm.RealmObject;

public class RealmUtil{

    public static Integer nextId(Realm realm, Class realmObject){

        int key;
        try {
            key = realm.where(realmObject).max("id").intValue() + 1;
        } catch(ArrayIndexOutOfBoundsException ex) {
            key = 1;
        }catch (Exception e){
            key = 1;
        }

        return key;

    }

}
