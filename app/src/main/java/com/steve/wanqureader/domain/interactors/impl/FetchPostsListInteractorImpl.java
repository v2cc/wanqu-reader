package com.steve.wanqureader.domain.interactors.impl;

import com.steve.wanqureader.domain.executor.Executor;
import com.steve.wanqureader.domain.executor.MainThread;
import com.steve.wanqureader.domain.interactors.FetchPostsListInteractor;
import com.steve.wanqureader.domain.interactors.base.AbstractInteractor;
import com.steve.wanqureader.domain.repository.PostRepository;
import com.steve.wanqureader.network.model.Post;

import java.util.ArrayList;

/**
 * Created by steve on 3/28/16.
 * <p/>
 * This interactor handles getting all costs from the database in a sorted manner. Costs should be sorted by date with
 * the most recent one coming first and the oldest one coming last.
 */
public class FetchPostsListInteractorImpl extends AbstractInteractor implements FetchPostsListInteractor {
    private Callback mCallback;
    private PostRepository mPostRepository;

    public FetchPostsListInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                        PostRepository postRepository, Callback callback) {
        super(threadExecutor, mainThread);

        if (postRepository == null || callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }

        mPostRepository = postRepository;
        mCallback = callback;
    }

    @Override
    public void run() {
        // retrieve the posts from the disk or network
        final ArrayList<Post> posts = mPostRepository.fetchPostsList();

        // show posts on the main thread
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onPostsRetrieved(posts);
            }
        });
    }
}
