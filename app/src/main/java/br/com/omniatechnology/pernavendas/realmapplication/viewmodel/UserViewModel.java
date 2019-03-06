package br.com.omniatechnology.pernavendas.realmapplication.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;
import android.util.Log;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;

import br.com.omniatechnology.pernavendas.realmapplication.contracts.IUserContract;
import br.com.omniatechnology.pernavendas.realmapplication.dao.UserDao;
import br.com.omniatechnology.pernavendas.realmapplication.model.User;
import br.com.omniatechnology.pernavendas.realmapplication.view.MainActivity;

public class UserViewModel extends BaseObservable implements IUserContract {

    private User user;
    private UserDao userDao;
    private Context context;

    public UserViewModel(Context context){
        this.user = new User();
        this.userDao = new UserDao(context);
        this.context = context;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void afterTextChangedNome(Editable e) {
        user.setNome(e.toString());
    }

    @Override
    public void afterTextChangedEmail(Editable e) {
        user.setEmail(e.toString());
    }

    @Override
    public void save() {

        String validModel = user.validModel();

        if(!validModel.isEmpty()) {
            setToastError(validModel);
            return;
        }

        user = userDao.save(user);

        if(user!=null)
            setToastSuccess("SAVE SUCCESSUFUL");
        else
            setToastError("NOT SAVE");
    }

    @Override
    public void delete() {
        boolean wasSave = userDao.delete(user);
        if(wasSave)
            setToastSuccess("DELETE SUCCESSUFUL");
        else
            setToastError("NOT DELETE");
    }


    public void setToastSuccess(String toastSuccess) {
        Toast.makeText(context, toastSuccess, Toast.LENGTH_LONG).show();
        context.startActivity(new Intent(context, MainActivity.class));
    }


    public void setToastError(String toastError) {
        Toast.makeText(context, toastError, Toast.LENGTH_LONG).show();
    }
}
