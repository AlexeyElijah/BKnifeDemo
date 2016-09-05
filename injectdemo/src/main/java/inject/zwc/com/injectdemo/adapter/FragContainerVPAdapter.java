package inject.zwc.com.injectdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import inject.zwc.com.injectdemo.fragment.FragmentFactory;

/**
 * Created by alexey on 16-9-5.
 */

public class FragContainerVPAdapter extends FragmentPagerAdapter {

    public FragContainerVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getFragment(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
