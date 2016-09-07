package inject.zwc.com.injectdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zwc.inject.BKnife;
import com.zwc.inject.annotation.BindView;

import inject.zwc.com.injectdemo.R;

/**
 * Created by alexey on 16-9-5.
 */

public class FirstFragment extends Fragment {

    @BindView(R.id.tv_first_frag)
    TextView tvFirstFrag;
    @BindView(R.id.bt_first_frag)
    Button btFirstFrag;
    @BindView(R.id.rl_first_frag_view_container)
    RelativeLayout rlFirstFragViewContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_first, null);
        BKnife.inject(this, view);
        tvFirstFrag.setText("first frag inject successs");
        return view;
    }

}
