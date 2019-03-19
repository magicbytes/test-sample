package com.magicbytes.githubtestsample.ui.posts

import com.magicbytes.githubtestsample.network.Post
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class PostsPresenterTest {

    private lateinit var presenter: PostsPresenter
    private lateinit var view: PostsView
    private lateinit var model: PostsModel

    @Before
    fun setUp() {
        view = mock(PostsView::class.java)
        model = mock(PostsModel::class.java)
        presenter = PostsPresenter(view, model)
    }

    @Test
    fun showPosts_ViewInitialisation() {
        presenter.showPosts()

        verify(view).isLoadingVisible = true
        // TODO this is actually not belonging to this method, should be in apart method
        verify(model, times(1)).loadPosts()
    }

    @Test
    fun showPosts_NetworkError() {
        `when`(model.loadPosts()).then { presenter.onErrorLoadingPosts() }
        presenter.showPosts()

        verify(view).isLoadingVisible = false
        verify(view, times(1)).showNoData()
    }

    @Test
    fun showPosts_PostsAvailable() {
        val post = Post(1, 1, "Hello", "Body")
        `when`(model.loadPosts()).then { presenter.onPostsLoaded(listOf(post)) }
        presenter.showPosts()

        verify(view).isLoadingVisible = false
        verify(view).showPosts(listOf(post))
    }
}