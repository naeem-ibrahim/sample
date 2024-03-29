package com.algorepublic.brandmaker.ui.category;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.algorepublic.brandmaker.R;
import com.algorepublic.brandmaker.databinding.FragmentCategoryBinding;
import com.algorepublic.brandmaker.model.BaseResponse;
import com.algorepublic.brandmaker.model.CategoryModel;
import com.algorepublic.brandmaker.model.StoresModel;
import com.algorepublic.brandmaker.utils.Helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By apple on 2019-08-02
 */
public class CategoryFragment extends Fragment {
    private FragmentCategoryBinding binding;
    private CategoryViewModel viewModel;

    private List<CategoryModel> categories = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private int storeID;

    public static CategoryFragment getInstance(int storeID) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putInt("StoreID", storeID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        viewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        storeID = getArguments().getInt("StoreID");

        setRecyclerView();

        viewModel.getResponseObservable().observe(this, baseResponse -> {
            binding.progressBar.setVisibility(View.GONE);
            if (baseResponse != null) {
                if (baseResponse.isSuccess() && baseResponse.getData() != null) {
                    if (baseResponse.getData().getCategories().size() > 0) {
                        categories.clear();
                        categories.addAll(baseResponse.getData().getCategories());
                    } else {
                        binding.tvEmpty.setVisibility(View.VISIBLE);
                    }
                    categoryAdapter.notifyDataSetChanged();
                } else {
                    Helper.snackBarWithAction(binding.getRoot(), getActivity(), baseResponse.getMessage());
                }
            }
        });

        viewModel.getCategoryApi(storeID);

        return binding.getRoot();
    }

    private void setRecyclerView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setNestedScrollingEnabled(false);
        categories = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(), categories);
        binding.recyclerView.setAdapter(categoryAdapter);
    }
}
