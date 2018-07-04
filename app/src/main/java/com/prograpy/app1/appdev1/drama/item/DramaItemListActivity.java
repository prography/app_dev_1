package com.prograpy.app1.appdev1.drama.item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.prograpy.app1.appdev1.R;
import com.prograpy.app1.appdev1.drama.item.adapter.DramaBestItemListAdapter;
import com.prograpy.app1.appdev1.drama.item.adapter.DramaItemListAdapter;
import com.prograpy.app1.appdev1.drama.list.DramaMainActivity;
import com.prograpy.app1.appdev1.network.ApiValue;
import com.prograpy.app1.appdev1.network.response.CategoryResult;
import com.prograpy.app1.appdev1.network.response.DramaListResult;
import com.prograpy.app1.appdev1.popup.NetworkProgressDialog;
import com.prograpy.app1.appdev1.popup.info.CustomPopup;
import com.prograpy.app1.appdev1.task.DramaListAsyncTask;
import com.prograpy.app1.appdev1.task.DramaProductAsyncTask;
import com.prograpy.app1.appdev1.view.TopbarView;


public class DramaItemListActivity extends AppCompatActivity{

    private NetworkProgressDialog networkProgressDialog;

    private TopbarView topbarView;

    private RecyclerView dramaBestItemListView;
    private DramaBestItemListAdapter bestItemListAdapter;

    private RecyclerView dramaItemListView;
    private DramaItemListAdapter dramaItemListAdapter;

    private Spinner oneDepthSpinner;
    private Spinner twoDepthSpinner;


    private View.OnClickListener itemPopupListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CustomPopup infoPopup = new CustomPopup(DramaItemListActivity.this);
            infoPopup.show();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);

        setContentView(R.layout.activity_item_list);


        topbarView = (TopbarView) findViewById(R.id.title);
        topbarView.setType(TopbarView.TOPBAR_TYPE.BACK_TITLE);
        topbarView.setTopBarTitle(getIntent().getStringExtra("title"));
        topbarView.setTopMenuBackClick(new TopbarView.ItemClick() {
            @Override
            public void onItemClick() {
                finish();
            }
        });

        dramaBestItemListView = (RecyclerView) findViewById(R.id.best_item_list);
        dramaItemListView = (RecyclerView) findViewById(R.id.item_list);
        oneDepthSpinner = (Spinner) findViewById(R.id.one_depth_spinner);
        twoDepthSpinner = (Spinner) findViewById(R.id.two_depth_spinner);

        dramaItemListView.setNestedScrollingEnabled(false);

        bestItemListAdapter = new DramaBestItemListAdapter();
        bestItemListAdapter.setOnItemClickListener(itemPopupListener);

        dramaItemListAdapter = new DramaItemListAdapter(getApplicationContext(), itemPopupListener);
        dramaItemListAdapter.setOnItemClickListener(itemPopupListener);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        dramaBestItemListView.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        dramaItemListView.setLayoutManager(gridLayoutManager);

        dramaBestItemListView.setAdapter(bestItemListAdapter);
        dramaItemListView.setAdapter(dramaItemListAdapter);

//        networkProgressDialog.show();

        DramaProductAsyncTask dramaProductAsyncTask = new DramaProductAsyncTask(new DramaProductAsyncTask.CategoryResultHandler() {
            @Override
            public void onSuccessAppAsyncTask(CategoryResult result) {

                networkProgressDialog.dismiss();

                if(result != null){
                    Log.d("TAG", result.success + "\n" + result.categoryList);

                    if(result.success){

                        if(result.categoryList != null && result.categoryList.size() > 0){
                            dramaItemListAdapter.setDramaProductData(result.categoryList);
                            dramaItemListAdapter.notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(DramaItemListActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
                        }
                    }else{

                        Toast.makeText(DramaItemListActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
                    }

                }else{

                    Toast.makeText(DramaItemListActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailAppAsysncask() {

//                networkProgressDialog.dismiss();

                Toast.makeText(DramaItemListActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelAppAsyncTask() {

                networkProgressDialog.dismiss();

                Toast.makeText(DramaItemListActivity.this, getResources().getString(R.string.failed_server_connect), Toast.LENGTH_SHORT).show();

            }
        });
        dramaProductAsyncTask.execute(ApiValue.API_DRAMA_PRODUCT, getIntent().getStringExtra("type"));


    }


    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }
}