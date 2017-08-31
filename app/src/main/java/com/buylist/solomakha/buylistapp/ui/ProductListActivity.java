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
        presenter.openAddProductDialog();
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

    @Override
    public void showAddProductDialog(List<Unit> units, List<Category> categories)
    {
        ProductDialogFragment dialogFragment = ProductDialogFragment.getInstance(presenter, units, categories);
        dialogFragment.show(getFragmentManager(), "");
    }
}
