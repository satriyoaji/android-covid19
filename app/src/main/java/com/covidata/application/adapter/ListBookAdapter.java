package com.antriansehat.application.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antriansehat.application.databinding.ItemBookBinding;
import com.antriansehat.application.model.Book;

import java.util.List;

public class ListBookAdapter extends RecyclerView.Adapter<ListBookAdapter.ViewHolder> {
    private List<Book> books;
    private LayoutInflater layoutInflater;

    public ListBookAdapter(List<Book> books, LayoutInflater layoutInflater) {
        this.books = books;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBookBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setBook(books.get(position));
    }

    @Override
    public int getItemCount() {
        if(books != null){
            return books.size();
        }
        else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemBookBinding binding;

        public ViewHolder(ItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
