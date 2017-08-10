package com.buylist.solomakha.buylistapp.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.buylist.solomakha.buylistapp.R;
import com.buylist.solomakha.buylistapp.mvp.presentors.ProductPresenter;
import com.buylist.solomakha.buylistapp.mvp.presentors.impl.ProductPresenterImpl;
import com.buylist.solomakha.buylistapp.mvp.views.ProductView;
import com.buylist.solomakha.buylistapp.storage.db.model.Category;
import com.buylist.solomakha.buylistapp.storage.db.model.Product;
import com.buylist.solomakha.buylistapp.storage.db.model.Unit;
import com.buylist.solomakha.buylistapp.storage.db.model.embeded.ProductEmbedded;
import com.buylist.solomakha.buylistapp.ui.adapter.ExpandableRecyclerListAdapter;
import com.buylist.solomakha.buylistapp.ui.helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity implements ProductView
{
    public static final String BASKET_ID_BUNDLE = "BasketId";
    private RecyclerView mRecyclerView;
    private ExpandableRecyclerListAdapter mExpandableRecyclerListAdapter;
    private long basketId;

    private ProductPresenter presenter;

    private ItemTouchHelper mTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        presenter = new ProductPresenterImpl(this);

        basketId = getIntent().getLongExtra(BASKET_ID_BUNDLE, -1);

        mRecyclerView = (RecyclerView) findViewById(R.id.product_list_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExpandableRecyclerListAdapter = new ExpandableRecyclerListAdapter();
        mRecyclerView.setAdapter(mExpandableRecyclerListAdapter);

        SimpleItemTouchHelperCallback helperCallback = new SimpleItemTouchHelperCallback(mExpandableRecyclerListAdapter);
        mTouchHelper = new ItemTouchHelper(helperCallback);
        mTouchHelper.attachToRecyclerView(mRecyclerView);

        Button addProductButton = (Button) findViewById(R.id.add_production_button);
        addProductButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showAddProductDialog();
            }
        });

        Button addProductFromListButton = (Button) findViewById(R.id.add_production_from_list_button);
        addProductFromListButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showAddProductDialog();
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        presenter.loadProductsByBasketId(basketId);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    private void showAddProductDialog()
    {
        /*LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_product_dialog, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this);
        builder.setTitle("Add productEmbedded");
        builder.setView(dialogView);

        List<Unit> unitList = presenter.getUnits();
        ArrayAdapter<Unit> unityAdapter = new ArrayAdapter<Unit>(this, android.R.layout.simple_spinner_item, unitList);
        unityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner quantitySpinner = (Spinner) dialogView.findViewById(R.id.product_quantity_spinner);
        quantitySpinner.setAdapter(unityAdapter);

        List<Category> categoryList = mStorage.getCategories();
        ArrayAdapter<Category> categoryAdapter = new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_item, categoryList);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner categorySpinner = (Spinner) dialogView.findViewById(R.id.product_category_spinner);
        categorySpinner.setAdapter(categoryAdapter);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                EditText productNameEditText = (EditText) dialogView.findViewById(R.id.product_name_edittext);
                EditText productQuantityEditText = (EditText) dialogView.findViewById(R.id.product_quantity_edittext);
                Unit unit = (Unit) quantitySpinner.getSelectedItem();
                Category category = (Category) categorySpinner.getSelectedItem();

                Product product = new Product();
                product.setName(productNameEditText.getText().toString());
                product.setQuantity(Float.valueOf(productQuantityEditText.getText().toString()));
                product.setUnitId(unit.getId());
                product.setCategoryId(category.getId());

                mStorage.createProduct(product);
                mStorage.assignProductToBasket(basketId, product.getId());

                refreshList();
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

        builder.show();*/
    }

    @Override
    public void showProductsByBasketId(List<ProductEmbedded> productList)
    {
        List<ExpandableRecyclerListAdapter.Item> items = new ArrayList<>();
        List<String> categories = new ArrayList<>();

        for (ProductEmbedded productEmbedded : productList)
        {
            if (!categories.contains(productEmbedded.categories.get(0).getName()))
            {
                categories.add(productEmbedded.categories.get(0).getName());
            }
        }

        for (String category : categories)
        {
            ExpandableRecyclerListAdapter.Item headerItem = new ExpandableRecyclerListAdapter.Item();
            headerItem.type = ExpandableRecyclerListAdapter.HEADER;
            headerItem.categoryTitle = category;
            items.add(headerItem);
            for (ProductEmbedded productEmbedded : productList)
            {
                if (category.equals(productEmbedded.categories.get(0).getName()))
                {
                    ExpandableRecyclerListAdapter.Item childItem = new ExpandableRecyclerListAdapter.Item();
                    childItem.type = ExpandableRecyclerListAdapter.CHILD;
                    childItem.productEmbedded = productEmbedded;
                    items.add(childItem);
                }
            }
        }
        mExpandableRecyclerListAdapter.refreshList(items);
    }
}
