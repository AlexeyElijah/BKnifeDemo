package inject.zwc.com.injectdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zwc.inject.BKnife;

import inject.zwc.com.injectdemo.R;

/**
 * Created by alexey on 16-9-5.
 */

public class ThirdFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_third, null);
        BKnife.inject(this,view);
        return view;
    }
}
