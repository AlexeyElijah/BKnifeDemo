package inject.zwc.com.injectdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.zwc.inject.BKnife;
import com.zwc.inject.annotation.BindView;
import com.zwc.inject.annotation.BindViews;
import com.zwc.inject.annotation.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_container)
    TextView mTvContainer;
    @BindViews({R.id.tv_binds_container1,R.id.tv_binds_container2,R.id.tv_binds_container3,R.id.tv_binds_container4})
    TextView[] mTvConArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BKnife.inject(this);
        startBinds();
    }

    private void startBinds() {
        mTvContainer.setText("bind succsess");
        for(int i=0;i<mTvConArray.length;i++){
            mTvConArray[i].setText("bins+"+i+"   ");
        }
    }
    @OnClick(R.id.bt_click1)
    public void onBt1Click(){
        Toast.makeText(this,"按钮1被点击了",Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.bt_click2)
    public void onBt2Click(){
        Toast.makeText(this,"按钮2被点击了",Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.bt_click3)
    public void onBt3Click(){
        Toast.makeText(this,"按钮3被点击了",Toast.LENGTH_SHORT).show();
    }
}
