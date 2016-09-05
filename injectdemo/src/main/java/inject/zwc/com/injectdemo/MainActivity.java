package inject.zwc.com.injectdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zwc.inject.BKnife;
import com.zwc.inject.annotation.BindView;
import com.zwc.inject.annotation.BindViews;

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
}
