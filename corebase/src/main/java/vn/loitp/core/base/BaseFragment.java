package vn.loitp.core.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import loitp.core.R;
import retrofit2.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import vn.loitp.core.utilities.LDialogUtil;
import vn.loitp.core.utilities.LLog;

/**
 * Created by khanh on 7/31/16.
 */
public abstract class BaseFragment extends Fragment {
    protected Context context;
    protected CompositeSubscription compositeSubscription = new CompositeSubscription();
    protected final String BASE_TAG = BaseFragment.class.getSimpleName();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }

    @SuppressWarnings("unchecked")
    protected void subscribe(Observable observable, Subscriber subscriber) {
        //TODO maybe in some cases we don't need to check internet connection
        /*if (!NetworkUtils.hasConnection(context)) {
            subscriber.onError(new NoConnectionException());
            return;
        }*/

        Subscription subscription = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        compositeSubscription.add(subscription);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    protected void handleException(Throwable throwable) {
        if (throwable == null || throwable.getMessage() == null) {
            return;
        }
        LLog.e(BASE_TAG, BASE_TAG + " handleException " + throwable.toString());
        LLog.e(BASE_TAG, BASE_TAG + " handleException " + throwable.getMessage());
        if (throwable instanceof HttpException) {
            LLog.e(BASE_TAG, BASE_TAG + " handleException code: " + ((HttpException) throwable).code());
            LDialogUtil.showError(getActivity(), ((HttpException) throwable).code(), getString(R.string.err), new LDialogUtil.CallbackShowOne() {
                @Override
                public void onClick() {
                    //do nothing
                }
            });
        } else {
            showDialogError(throwable.getMessage());
        }
    }

    protected void handleException(final String msgErr) {
        showDialogError(msgErr);
    }

    protected void showDialogOne(final String msg) {
        showDialogOne(msg, false);
    }

    protected void showDialogOne(String msg, final boolean isExit) {
        LDialogUtil.showOne(getActivity(), getString(R.string.app_name), msg, getString(R.string.confirm), new LDialogUtil.CallbackShowOne() {
            @Override
            public void onClick() {
                if (isExit) {
                    getActivity().onBackPressed();
                }
            }
        });
    }

    protected void showDialogError(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LDialogUtil.showOne(getActivity(), getString(R.string.app_name), msg, getString(R.string.confirm), new LDialogUtil.CallbackShowOne() {
                    @Override
                    public void onClick() {
                        //do nothing
                    }
                });
            }
        });
    }

    protected void showDialogError(final String msg, final LDialogUtil.CallbackShowOne callbackShowOne) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LDialogUtil.showOne(getActivity(), getString(R.string.app_name), msg, getString(R.string.confirm), callbackShowOne);
            }
        });
    }

    public void onFragmentResume() {
    }

    public void onFragmentPause() {
    }
}
