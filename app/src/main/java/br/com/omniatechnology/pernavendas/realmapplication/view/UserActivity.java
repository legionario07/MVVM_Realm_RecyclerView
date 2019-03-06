package br.com.omniatechnology.pernavendas.realmapplication.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.omniatechnology.pernavendas.realmapplication.R;
import br.com.omniatechnology.pernavendas.realmapplication.databinding.UserActivityBinding;
import br.com.omniatechnology.pernavendas.realmapplication.model.User;
import br.com.omniatechnology.pernavendas.realmapplication.viewmodel.UserViewModel;

public class UserActivity extends AppCompatActivity {

    private UserActivityBinding userBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userBinding = DataBindingUtil.setContentView(this, R.layout.user_activity);
        userBinding.setUserViewModel(new UserViewModel(this));
        hasUser();
        userBinding.executePendingBindings();

    }

    private void hasUser(){
        if(getIntent()!=null && getIntent().getExtras()!=null && getIntent().hasExtra(getString(R.string.user))){
            userBinding.getUserViewModel().setUser((User) getIntent().getExtras().get(getString(R.string.user)));
            if(getIntent().hasExtra("ACTION") && getIntent().getExtras().get("ACTION").equals("DELETE")){
                userBinding.imbSave.setVisibility(View.GONE);
                userBinding.imgDelete.setVisibility(View.VISIBLE);
            }
        }
    }
}
