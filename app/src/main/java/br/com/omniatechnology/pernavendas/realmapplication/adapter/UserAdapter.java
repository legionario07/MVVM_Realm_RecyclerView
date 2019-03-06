package br.com.omniatechnology.pernavendas.realmapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.omniatechnology.pernavendas.realmapplication.R;
import br.com.omniatechnology.pernavendas.realmapplication.databinding.ItemUserBinding;
import br.com.omniatechnology.pernavendas.realmapplication.model.User;
import br.com.omniatechnology.pernavendas.realmapplication.view.UserActivity;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;

    public UserAdapter(List<User> users) {
        this.userList = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ItemUserBinding userBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_user,
                viewGroup,
                false

        );

        return new UserViewHolder(userBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {

        User user = userList.get(i);
        userViewHolder.userBinding.setUser(user);

        if (i % 2 == 0) {
            userViewHolder.userBinding.ll.setBackgroundColor(Color.LTGRAY);
        }

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public User getItem(int position) {
        return userList.get(position);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private ItemUserBinding userBinding;

        public UserViewHolder(@NonNull ItemUserBinding itemView) {
            super(itemView.getRoot());
            this.userBinding = itemView;
            userBinding.ll.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(), 1, 1, "Update").setIcon(R.drawable.ic_edit_black_24dp);
            menu.add(this.getAdapterPosition(), 2, 2, "Delete").setIcon(R.drawable.ic_delete_black_24dp);

        }
    }

}
