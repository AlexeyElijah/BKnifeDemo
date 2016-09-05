package inject.zwc.com.injectdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zwc.inject.BKnife;
import com.zwc.inject.annotation.BindView;
import com.zwc.inject.annotation.OnClick;

import inject.zwc.com.injectdemo.R;

/**
 * Created by alexey on 16-9-5.
 */

public class FirstFragment extends Fragment {
    @BindView(R.id.tv_first_frag)
    TextView mTvFirstFrag;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_first, null);
        BKnife.inject(this,view);
        mTvFirstFrag.setText("第一个frag绑定成功");
        return view;
    }
    @OnClick(R.id.bt_first_frag)
    public void onFirtFragBthClick(){
        Toast.makeText(getContext(),"第一个frag的按钮被点击",Toast.LENGTH_SHORT).show();
    }

}
