package com.buylist.solomakha.buylistapp.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.buylist.solomakha.buylistapp.R;
import com.buylist.solomakha.buylistapp.bind.BasketViewModel;
import com.buylist.solomakha.buylistapp.databinding.BasketDialogBinding;
import com.buylist.solomakha.buylistapp.db.model.Basket;
import com.buylist.solomakha.buylistapp.mvp.presentors.BasketPresenter;

public class BasketDialogFragment extends DialogFragment implements DialogInterface.OnClickListener
{
    private BasketPresenter mPresenter;
    private Basket mBasket;
    private boolean editAction;

    private BasketDialogBinding binding;

    public BasketDialogFragment()
    {
        mBasket = new Basket("");
    }

    public void setPresenter(BasketPresenter presenter)
    {
        this.mPresenter = presenter;
    }

    public void setBasket(Basket basket)
    {
        mBasket = basket;
        editAction = true;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        initBinding();
        return initDialog(binding.getRoot());
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        if (editAction)
        {
            mPresenter.editBasket(binding.getBasket().getBasket(), true);
        }
        else
        {
            mPresenter.createBasket(binding.getBasket().getName(), true);
        }
        dismiss();
    }

    private void initBinding()
    {
        View view = getActivity().getLayoutInflater().inflate(R.layout.basket_dialog, null);
        binding = DataBindingUtil.bind(view);
        BasketViewModel basketViewModel = new BasketViewModel(mBasket);
        binding.setBasket(basketViewModel);
    }

    private AlertDialog initDialog(View view)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        if (editAction)
        {
            builder.setTitle(R.string.edit_basket);
        }
        else
        {
            builder.setTitle(R.string.create_basket);
        }
        builder.setPositiveButton(android.R.string.ok, this);
        builder.setNegativeButton(android.R.string.cancel, null);
        return builder.create();
    }
}
