package com.buylist.solomakha.buylistapp.ui.adapter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import com.buylist.solomakha.buylistapp.mvp.presentors.BasketPresenter;
import com.buylist.solomakha.buylistapp.mvp.presentors.impl.BasketPresenterImpl;
import com.buylist.solomakha.buylistapp.ui.BasketListActivity;

/**
 * Created by asolomakha on 6/12/2017.
 */

public class FragmentWorker extends Fragment
{
    private BasketPresenter mPresenter = new BasketPresenterImpl();

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mPresenter.setView((BasketListActivity) context);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mPresenter.setView(null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public BasketPresenter getPresenter()
    {
        return mPresenter;
    }
}
