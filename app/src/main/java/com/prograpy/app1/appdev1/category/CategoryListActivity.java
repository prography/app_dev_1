package com.prograpy.app1.appdev1.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.prograpy.app1.appdev1.R;
import com.prograpy.app1.appdev1.category.item.NewIntentCategorySample;
import com.prograpy.app1.appdev1.view.TopbarView;

/**
 * Created by SeungJun on 2018-04-05.
 */

public class CategoryListActivity extends AppCompatActivity{

    private RecyclerView categoryRecyclerView;
    private CategoryRecyclerAdapter categoryRecyclerAdapter;

    private TopbarView topbarView;
    private ImageView categoryItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category_main);


        topbarView = (TopbarView) findViewById(R.id.title);
        topbarView.setType(TopbarView.TOPBAR_TYPE.BACK_TITLE);
        topbarView.setTopBarTitle(getIntent().getStringExtra("title")); //전달받은 데이터 받는 것
        topbarView.setTopMenuBackClick(new TopbarView.ItemClick() {
            @Override
            public void onItemClick() {
                finish();
            }
        });


        categoryRecyclerView = (RecyclerView) findViewById(R.id.category_list);

        categoryRecyclerAdapter = new CategoryRecyclerAdapter();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        categoryRecyclerView.setLayoutManager(gridLayoutManager);
        categoryRecyclerView.setAdapter(categoryRecyclerAdapter);


    }



}
