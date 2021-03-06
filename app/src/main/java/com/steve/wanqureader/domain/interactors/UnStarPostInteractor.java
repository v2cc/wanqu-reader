package com.steve.wanqureader.domain.interactors;

import com.steve.wanqureader.domain.interactors.base.Interactor;

/**
 * Created by steve on 4/11/16.
 */
public interface UnStarPostInteractor extends Interactor {
    interface Callback {
        void onPostUnstarred(int position);
    }
}
