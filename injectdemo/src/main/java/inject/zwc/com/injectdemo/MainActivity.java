package inject.zwc.com.injectdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zwc.inject.BKnife;
import com.zwc.inject.annotation.BindView;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_container)
    TextView mTvContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BKnife.inject(this);
        mTvContainer.setText("bind succsess");
    }
}
