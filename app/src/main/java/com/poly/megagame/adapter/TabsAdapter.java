package com.poly.megagame.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.poly.megagame.fragment_layout.Fragment_CaNhan;
import com.poly.megagame.fragment_layout.Fragment_ChiaSe;
import com.poly.megagame.fragment_layout.Fragment_DangGia;
import com.poly.megagame.fragment_layout.Fragment_TrangChu;

public class TabsAdapter extends FragmentPagerAdapter {

    private String[] title = {"Trang chủ","Cá nhân","Chia sẽ","Đánh giá"};

    public TabsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_TrangChu();
            case 1:
                return new Fragment_CaNhan();
            case 2:
                return new Fragment_ChiaSe();
            case 3:
                return new Fragment_DangGia();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
