package inject.zwc.com.injectdemo.fragment;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

/**
 * Created by alexey on 16-9-5.
 */

public class FragmentFactory {
    public static SparseArray<Fragment> mFragArray = new SparseArray<>();

    public static Fragment getFragment(int index) {
        Fragment fragment = mFragArray.get(index);
        if (fragment == null) {
            switch (index) {
                case 0:
                    fragment = new FirstFragment();
                    break;
                case 1:
                    fragment = new SecondFragment();
                    break;
                case 2:
                    fragment = new ThirdFragment();
                    break;
            }
            mFragArray.put(index, fragment);
        }

        return fragment;
    }
}
