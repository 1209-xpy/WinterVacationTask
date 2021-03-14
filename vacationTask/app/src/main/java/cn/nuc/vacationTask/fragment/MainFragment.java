package cn.nuc.vacationTask.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.nuc.vacationTask.R;
import cn.nuc.vacationTask.activity.OkHttpActivity;
import cn.nuc.vacationTask.activity.ProgressBarActivity;
import cn.nuc.vacationTask.adapter.StaggerdGridAdapter;
import cn.nuc.vacationTask.adapter.ViewPageAdapter;


public class MainFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<View> views;
    private List<String> titles;
    private RecyclerView mRvPu;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View viewOne;
    private View viewTwo;

    private Button btnOkHttp;
    private Button btnProgressBar;

    List<Map<String, Object>> staggeredData = new ArrayList<>();

    String[] title = {
            "课程设计", "Java", "C", "Android",
            "Python", "Mysql", "观影记录", "假期心得", "Web"
    };

    String[] content = {
            "自拟题目\n健身管理系统\n快递管理系统\n",
            "interface\nIO流\nStringBuilder\nFile\n",
            "数据结构\n算法\n",
            "TabLayout\nToolBar\nFragment\nDrawerLayout\nSQLite\n",
            "无\n",
            "数据库\nPowerDesigner\n",
            "唐人街探案3\n你好李焕英\n柯南\n",
            "无\n",
            "html\nCSS\nJavaScript\nBootScript\n"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        viewOne = LayoutInflater.from(view.getContext()).inflate(R.layout.activity_view_1, null);
        viewTwo = LayoutInflater.from(view.getContext()).inflate(R.layout.activity_view_2, null);
        views = new ArrayList<>();
        views.add(viewOne);
        views.add(viewTwo);
        titles = new ArrayList<>();
        titles.add("笔记");
        titles.add("待办");

        btnOkHttp = viewTwo.findViewById(R.id.btn_request);
        btnOkHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OkHttpActivity.class);
                startActivity(intent);
            }
        });

        btnProgressBar = viewTwo.findViewById(R.id.btn_progressbar);
        btnProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProgressBarActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.one_view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);


        /*views = new ArrayList<>();
        views.add(viewOne);
        views.add(viewTwo);
        titles = new ArrayList<>();
        titles.add("笔记");
        titles.add("待办");*/

        ViewPageAdapter adapter = new ViewPageAdapter(views, titles);

        for (String title : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);

        addStaggeredData();

        mRvPu = viewOne.findViewById(R.id.rv_main);
        mRvPu.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRvPu.setAdapter(new StaggerdGridAdapter(getContext(), staggeredData));


        swipeRefreshLayout = viewOne.findViewById(R.id.swipe_refresh);
        initSwipeRefresh();

    }


    /**
     * 实现下拉刷新
     */
    private void initSwipeRefresh(){
        //为下拉刷新，设置一组颜色
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(106 ,90, 205), Color.rgb(221,160,221));
        //设置触发刷新的距离
        swipeRefreshLayout.setDistanceToTriggerSync(200);
        //设置滑动的距离
        swipeRefreshLayout.setSlingshotDistance(400);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1500);

            }
        });
    }

    /**
     * 实现瀑布流数据的添加
     */
    private void addStaggeredData() {
        Map<String, Object> map = null;
        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            int n = random.nextInt(title.length);
            map = new HashMap<>();
            map.put("title", title[n]);
            map.put("content", content[n]);
            staggeredData.add(map);
        }
    }


}
