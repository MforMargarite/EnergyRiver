package com.whucs.energyriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.whucs.energyriver.Adapter.BillAdapter;
import com.whucs.energyriver.Bean.Bill;
import com.whucs.energyriver.Presenter.BillPresenter;
import com.whucs.energyriver.View.BillView;
import com.whucs.energyriver.Widget.ScrollListView;
import com.whucs.energyriver.Widget.StateSwitchActivity;

import java.util.List;

/*账单列表 */
public class BillActivity extends StateSwitchActivity implements View.OnClickListener,BillView,AdapterView.OnItemClickListener{
    ImageView back;
    ScrollListView billList;
    BillAdapter adapter;
    BillPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /*    setContentView(R.layout.bill);
        initWidget();
    */
        View view = LayoutInflater.from(this).inflate(R.layout.bill,null);
        initWidget(view);
    }

    private void initWidget(View view){
        iniAdapter(view);
        back = (ImageView) view.findViewById(R.id.back);
        billList = (ScrollListView) view.findViewById(R.id.bill);
        back.setOnClickListener(this);

        presenter = new BillPresenter(this);
        //presenter.getBillByUser(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                BillActivity.this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void getBillSuccess(List<Bill> bills) {
        adapter = new BillAdapter(this,bills);
        billList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        billList.setOnItemClickListener(this);
        showViewByTag("content");
    }

    @Override
    public void execError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        showViewByTag("error");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,BillInfoActivity.class);
        intent.putExtra("id",adapter.getItemId(i));
        startActivity(intent);
    }
}
