package com.miskaa.assignment.borders.frontend.ui.fragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.miskaa.assignment.borders.R;
import com.miskaa.assignment.borders.backend.model.Root;
import com.miskaa.assignment.borders.databinding.FragmentCountryBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Root}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCountryRecyclerViewAdapter extends RecyclerView.Adapter<MyCountryRecyclerViewAdapter.ViewHolder> {

    private final List<Root> mValues;

    public MyCountryRecyclerViewAdapter(List<Root> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentCountryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Root root = mValues.get(position);
        holder.mItem = root;
        holder.mNameView.setText(root.getName());
        String text = String.valueOf(root.getPopulation());
        holder.mPopulationView.setText(text);
        Glide.with(holder.mPopulationView).load(root.getFlag()).placeholder(android.R.drawable.ic_menu_gallery).dontAnimate().into(holder.mFlag);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void update(@NonNull List<Root> data) {
        final RootDiffCallback diffCallback = new RootDiffCallback(this.mValues, data);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.mValues.clear();
        this.mValues.addAll(data);
        diffResult.dispatchUpdatesTo(this);
    }

    private static class RootDiffCallback extends DiffUtil.Callback {

        private final List<Root> mOldList;
        private final List<Root> mNewList;

        public RootDiffCallback(List<Root> oldEmployeeList, List<Root> newEmployeeList) {
            this.mOldList = oldEmployeeList;
            this.mNewList = newEmployeeList;
        }

        @Override
        public int getOldListSize() {
            return mOldList.size();
        }

        @Override
        public int getNewListSize() {
            return mNewList.size();
        }
        // TODO could be improved
        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldList.get(oldItemPosition).getName().length() == mNewList.get(
                    newItemPosition).getName().length();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final Root _old = mOldList.get(oldItemPosition);
            final Root _new = mNewList.get(newItemPosition);

            return _old.getName().equals(_new.getName());
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            // Implement method if you're going to use ItemAnimator
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mNameView;
        public final TextView mPopulationView;
        public final ImageView mFlag;
        public Root mItem;

        public ViewHolder(FragmentCountryBinding binding) {
            super(binding.getRoot());
            mNameView = binding.name;
            mPopulationView = binding.population;
            mFlag = binding.flag;
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mPopulationView.getText() + "'";
        }
    }
}