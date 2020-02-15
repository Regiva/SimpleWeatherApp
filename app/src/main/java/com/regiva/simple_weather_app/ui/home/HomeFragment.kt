package com.regiva.simple_weather_app.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import com.badoo.mvicore.modelWatcher
import com.regiva.simple_weather_app.R
import com.regiva.simple_weather_app.model.system.FlowRouter
import com.regiva.simple_weather_app.ui.base.BaseFragment
import com.regiva.simple_weather_app.ui.base.MviFragment
import com.regiva.simple_weather_app.util.ErrorHandler

class HomeFragment : MviFragment<HomeFragment.ViewModel, HomeFragment.UiEvents>() {

    override val layoutRes: Int
        get() = R.layout.fragment_home

    private val flowRouter: FlowRouter by scope()
//    private val feature: PostsFeature by scope()
    private val errorHandler: ErrorHandler by scope()
//    private val adapter: PostsAdapter by lazy {
//        PostsAdapter(
//            listOf(),
//            { onNext(UiEvents.OnLikeClicked(it.isLiked, it.source.id, it.post_id)) },
//            { flowRouter.navigateTo(Screens.DetailedPost(it)) }
//        )
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBindings()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initRecycler()
//        onNext(UiEvents.OnGetNewsFeed)
    }

    private fun setUpBindings() {
        /*object : AndroidBindings<HomeFragment>(this) {
            override fun setup(view: HomeFragment) {
                binder.bind(view to feature using { event ->
                    when (event) {
                        is UiEvents.OnGetNewsFeed -> PostsFeature.Wish.GetAllPosts
                        is UiEvents.OnLikeClicked -> PostsFeature.Wish.LikePost(event.isLiked, event.owner_id, event.item_id)
                    }
                })
                binder.bind(feature to view using { state ->
                    ViewModel(
                        state.isLoading,
                        state.posts
                    )
                })
                binder.bind(feature.news to Consumer { news ->
                    when (news) {
                        is PostsFeature.News.GetAllPostsFailure -> errorHandler.proceed(news.throwable) { view.showError(it) }
                        is PostsFeature.News.LikePostFailure -> errorHandler.proceed(news.throwable) { view.showError(it) }
                    }
                })
            }
        }.setup(this)*/
    }

    private fun initRecycler() {
//        rv_posts.layoutManager = LinearLayoutManager(activity)
//        rv_posts.adapter = adapter
//        rv_posts.setOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val totalItemCount = (recyclerView.layoutManager as LinearLayoutManager).itemCount
//                val lastVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
//                if (isLoadingMore != true && lastVisibleItem == totalItemCount - 1) {
//                    onNext(UiEvents.OnGetNewsFeed)
//                    isLoadingMore = true
//                }
//            }
//
//        })
    }

//    private fun showPosts(posts: List<PostModel>) {
//        if (posts.isNotEmpty()) {
//            adapter.updateList(posts)
//            rl_posts_not_empty?.setVisible()
//            rl_posts_empty?.setGone()
//        }
//        else {
//            rl_posts_empty?.setVisible()
//            rl_posts_not_empty?.setGone()
//        }
//    }

    override fun accept(vm: ViewModel) {
        modelWatcher<ViewModel> {
            watch(ViewModel::isLoading) { pb_loading?.setLoadingState(it) }
        }.invoke(vm)
    }

    sealed class UiEvents {
        object OnGetNewsFeed : UiEvents()
        data class OnLikeClicked(val isLiked: Boolean, val owner_id: Long, val item_id: Long) : UiEvents()
    }

    class ViewModel(
        val isLoading: Boolean,
        val posts: List<PostModel>?
    )
}

/*{
  "cod": "200",
  "message": 0.0032,
  "cnt": 36,
  "list": [
    {
      "dt": 1487246400,
      "main": {
        "temp": 286.67,
        "temp_min": 281.556,
        "temp_max": 286.67,
        "pressure": 972.73,
        "sea_level": 1046.46,
        "grnd_level": 972.73,
        "humidity": 75,
        "temp_kf": 5.11
      },
      "weather": [
        {
          "id": 800,
          "main": "Clear",
          "description": "clear sky",
          "icon": "01d"
        }
      ],
      "clouds": {
        "all": 0
      },
      "wind": {
        "speed": 1.81,
        "deg": 247.501
      },
      "sys": {
        "pod": "d"
      },
      "dt_txt": "2017-02-16 12:00:00"
    }
  ],
  "city": {
    "id": 6940463,
    "name": "Altstadt",
    "coord": {
      "lat": 48.137,
      "lon": 11.5752
    },
    "country": "none"
  }
}*/