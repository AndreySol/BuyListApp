package com.buylist.solomakha.buylistapp.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.buylist.solomakha.buylistapp.R;
import com.buylist.solomakha.buylistapp.mvp.presentors.ProductPresenter;
import com.buylist.solomakha.buylistapp.storage.db.model.Category;
import com.buylist.solomakha.buylistapp.storage.db.model.Product;
import com.buylist.solomakha.buylistapp.storage.db.model.Unit;

import java.util.ArrayList;
import java.util.List;

public class ProductDialogFragment extends DialogFragment
{
    private ProductPresenter presenter;
    private List<Unit> units;
    private List<Category> categories;

    public static ProductDialogFragment getInstance(ProductPresenter presenter, List<Unit> units, List<Category> categories)
    {
        ProductDialogFragment dialog = new ProductDialogFragment();
        dialog.setValues(presenter, units, categories);
        return dialog;
    }

    private void setValues(ProductPresenter presenter, List<Unit> units, List<Category> categories)
    {
        this.presenter = presenter;
        this.units = units;
        this.categories = categories;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.add_product_dialog, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add product");
        builder.setView(dialogView);

        List<String> unitNameList = new ArrayList<>();
        for(Unit unit : units)
        {
            unitNameList.add(unit.getName());
        }

        ArrayAdapter<String> unityAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, unitNameList);
        unityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner quantitySpinner = dialogView.findViewById(R.id.product_quantity_spinner);
        quantitySpinner.setAdapter(unityAdapter);


        List<String> categoryNameList = new ArrayList<>();
        for(Category category : categories)
        {
            categoryNameList.add((category.getName()));
        }

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categoryNameList);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner categorySpinner = dialogView.findViewById(R.id.product_category_spinner);
        categorySpinner.setAdapter(categoryAdapter);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                EditText productNameEditText = dialogView.findViewById(R.id.product_name_edittext);
                EditText productQuantityEditText = dialogView.findViewById(R.id.product_quantity_edittext);
                Unit unit = (Unit) quantitySpinner.getSelectedItem();
                Category category = (Category) categorySpinner.getSelectedItem();

                Product product = new Product();
                product.setName(productNameEditText.getText().toString());
                product.setQuantity(Float.valueOf(productQuantityEditText.getText().toString()));
                product.setUnitId(unit.getId());
                product.setCategoryId(category.getId());

                presenter.createProductAndAssignToBasket(product, 1L);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        return builder.create();
    }
}
