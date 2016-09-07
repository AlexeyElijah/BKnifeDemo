package inject.zwc.com.injectdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zwc.inject.BKnife;
import com.zwc.inject.annotation.BindView;
import com.zwc.inject.annotation.OnClick;


public class TestActivity extends Activity {


    @BindView(R.id.tv_container)
    TextView tvContainer;
    @BindView(R.id.bt_click)
    Button btClick;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        BKnife.inject(this);
    }

    @OnClick(R.id.tv_container)
    public void onClick() {
    }
}
