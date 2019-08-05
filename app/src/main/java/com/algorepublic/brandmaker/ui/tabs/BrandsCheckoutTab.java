package com.algorepublic.brandmaker.ui.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.algorepublic.brandmaker.R;
import com.algorepublic.brandmaker.databinding.FragmentTabsBinding;
import com.algorepublic.brandmaker.ui.brand.BrandFragment;
import com.algorepublic.brandmaker.ui.campaign.CampaignFragment;
import com.algorepublic.brandmaker.ui.category.CategoryFragment;
import com.algorepublic.brandmaker.ui.checkout.CheckOutFragment;
import com.algorepublic.brandmaker.ui.dashboard.MainActivity;
import com.algorepublic.brandmaker.ui.home.ViewPagerAdapter;

/**
 * Created By apple on 2019-08-05
 */
public class BrandsCheckoutTab extends Fragment {
    private static BrandsCheckoutTab fragment;
    private FragmentTabsBinding b;


    public static BrandsCheckoutTab getInstance() {

        if (fragment == null) {
            fragment = new BrandsCheckoutTab();
        }
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_tabs, container, false);


        b.tvTab1.setText("Brands");
        b.tvTab2.setText("Checkout");

        b.tvTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.tvTab1.setTextColor(getContext().getColor(R.color.red));
                b.view.setBackgroundColor(getContext().getColor(R.color.red));
                b.tvTab2.setTextColor(getContext().getColor(R.color.text_color_g));
                b.view2.setBackgroundColor(getContext().getColor(R.color.text_color_g));

                b.viewPager.setCurrentItem(0);
            }
        });

        b.tvTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b.tvTab2.setTextColor(getContext().getColor(R.color.red));
                b.view2.setBackgroundColor(getContext().getColor(R.color.red));
                b.tvTab1.setTextColor(getContext().getColor(R.color.text_color_g));
                b.view.setBackgroundColor(getContext().getColor(R.color.text_color_g));

                b.viewPager.setCurrentItem(1);
            }
        });

        setupViewPager();

        return b.getRoot();
    }

    private void setupViewPager() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        ((MainActivity) getContext()).setToolBar("Hello", "Check In 05-08-2019 - 11:00 PM", true);
        adapter.addFragment(BrandFragment.getInstance(), "Brands");

        adapter.addFragment(CheckOutFragment.getInstance(), "Checkout");


        b.viewPager.setAdapter(adapter);

        b.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                if (position == 0) {
                    b.tvTab1.setTextColor(getContext().getColor(R.color.red));
                    b.view.setBackgroundColor(getContext().getColor(R.color.red));
                    b.tvTab2.setTextColor(getContext().getColor(R.color.text_color_g));
                    b.view2.setBackgroundColor(getContext().getColor(R.color.text_color_g));
                } else {
                    b.tvTab2.setTextColor(getContext().getColor(R.color.red));
                    b.view2.setBackgroundColor(getContext().getColor(R.color.red));
                    b.tvTab1.setTextColor(getContext().getColor(R.color.text_color_g));
                    b.view.setBackgroundColor(getContext().getColor(R.color.text_color_g));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
