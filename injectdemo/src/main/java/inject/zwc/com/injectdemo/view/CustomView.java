package inject.zwc.com.injectdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zwc.inject.BKnife;
import com.zwc.inject.annotation.BindView;

import inject.zwc.com.injectdemo.R;

/**
 * Created by alexey on 16-9-7.
 */

public class CustomView extends LinearLayout {


    @BindView(R.id.tv_custom_view)
    TextView tvCustomView;

    public CustomView(Context context) {
        super(context);
        initView(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, null);
        initView(context);
    }

    private void initView(Context context) {
        View view = LinearLayout.inflate(context, R.layout.custom_view, null);
        addView(view);
        BKnife.inject(this, view);


    }

}
