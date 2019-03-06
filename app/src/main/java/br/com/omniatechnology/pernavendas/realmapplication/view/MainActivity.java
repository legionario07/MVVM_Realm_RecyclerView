package br.com.omniatechnology.pernavendas.realmapplication.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.omniatechnology.pernavendas.realmapplication.R;
import br.com.omniatechnology.pernavendas.realmapplication.adapter.UserAdapter;
import br.com.omniatechnology.pernavendas.realmapplication.dao.UserDao;
import br.com.omniatechnology.pernavendas.realmapplication.databinding.ActivityMainBinding;
import br.com.omniatechnology.pernavendas.realmapplication.model.User;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.recyclerView.setHasFixedSize(true);
        registerForContextMenu(mainBinding.recyclerView);


        mainBinding.fabNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(i);
            }
        });

        UserDao userDao = new UserDao(this);

        List<User> users = userDao.findAll();

        userAdapter = new UserAdapter(users);
        mainBinding.recyclerView.setAdapter(userAdapter);
        mainBinding.executePendingBindings();

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {

            case 1:

                Intent intent = new Intent(this,UserActivity.class);
                intent.putExtra("USER",((UserAdapter) mainBinding.recyclerView.getAdapter()).getItem(item.getGroupId()));
                startActivity(intent);

                break;

            case 2:

                Intent i = new Intent(this,UserActivity.class);
                User user = ((UserAdapter) mainBinding.recyclerView.getAdapter() ).getItem(item.getGroupId());
                i.putExtra("ACTION", "DELETE");
                i.putExtra("USER", user);
                startActivity(i);

                break;
        }
        return super.onContextItemSelected(item);
    }



}
