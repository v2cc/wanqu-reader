package com.steve.wanqureader.domain.interactors;

import com.steve.wanqureader.domain.interactors.base.Interactor;

/**
 * Created by steve on 3/28/16.
 */
public interface LikePostInteractor extends Interactor {
    interface Callback {
        void onPostLiked();
    }
}
