package br.com.omniatechnology.pernavendas.realmapplication.contracts;

import android.text.Editable;

public interface IUserContract {

    void afterTextChangedNome(Editable e);
    void afterTextChangedEmail(Editable e);
    void save();
    void delete();
    void setToastSuccess(String msg);
    void setToastError(String msg);

}
