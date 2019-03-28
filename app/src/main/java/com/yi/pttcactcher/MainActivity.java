package com.yi.pttcactcher;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GetImageUrl.OnTaskCompleted{

    List<Member_titleTitleurlImgurl> articleList = new ArrayList<>();
    List<Member_titleTitleurlImg> list_titleimg = new ArrayList<>();
    private GetImageUrl.OnTaskCompleted onTaskCompletedListener =new GetImageUrl.OnTaskCompleted() {
        @Override
        public void onTaskCompleted(List<Member_titleTitleurlImgurl> articleList) {
            for (Member_titleTitleurlImgurl member:articleList){
                Log.d("Main_articleList",member.getTitle());
//                Log.d("Main_articleImageUrl",member.getImgUrl());
            }
            recyclerView.setAdapter(new MemberAdapter(articleList));
        }
    };

//    private GetImage.OnTaskCompleted onGetImageCompletedListener = new GetImage.OnTaskCompleted() {
//        @Override
//        public void onTaskCompleted(List<Member_titleTitleurlImg> imgList) {
//            recyclerView.setAdapter(new MemberAdapter(imgList));
//        }
//    };
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Member_titleTitleurlImgurl> memberList = new ArrayList<>();
        memberList.add(new Member_titleTitleurlImgurl("第一項", "", "https://s3cdn.yourator.co/banners/banners/000/001/025/home/86c659e86d9b46f2c80d62e40f229d6b3fe989aa.png?1552965835"));
        memberList.add(new Member_titleTitleurlImgurl("第二項", "", "https://scontent.ftpe8-3.fna.fbcdn.net/v/t1.0-9/54524290_2325249344154887_6619064362898292736_n.jpg?_nc_cat=107&_nc_ht=scontent.ftpe8-3.fna&oh=5cde5d2f931626a30f64fa170f05c538&oe=5D0328E4"));
        memberList.add(new Member_titleTitleurlImgurl("第三項", "", "https://upload.wikimedia.org/wikipedia/commons/6/61/NYCS-bull-trans-3.svg"));
//        memberList.add(new Member(4, R.drawable.ic_launcher_background, "白沙屯海灘4"));
//        memberList.add(new Member(5, R.drawable.ic_launcher_background, "白沙屯海灘5"));
//        memberList.add(new Member(6, R.drawable.ic_launcher_background, "白沙屯3"));
//        memberList.add(new Member(7, R.drawable.ic_launcher_background, "後龍1"));
//        memberList.add(new Member(8, R.drawable.ic_launcher_background, "後龍2"));
//        memberList.add(new Member(9, R.drawable.ic_launcher_background, "後龍3"));
//        memberList.add(new Member(10, R.drawable.ic_launcher_background, "龍港4"));
//        memberList.add(new Member(11, R.drawable.ic_launcher_background, "龍港4.1"));
//        memberList.add(new Member(12, R.drawable.ic_launcher_background, "龍港4.2"));

        Log.d("lifeCycle","OnCreate");


//        new GetImageUrl(onTaskCompletedListener).execute();
        new GetImageUrl(onTaskCompletedListener).execute();
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new MemberAdapter(memberList));

        Log.d("articleLength",String.valueOf(articleList.size()));
        for (Member_titleTitleurlImgurl member:articleList){
            Log.d("articleList",member.getTitle());

        }
//        recyclerView.setAdapter(new MemberAdapter(this, memberList));

//        new DownloadImageTask((ImageView) findViewById(R.id.imageView2)).execute("https://s3cdn.yourator.co/banners/banners/000/001/025/home/86c659e86d9b46f2c80d62e40f229d6b3fe989aa.png?1552965835");
    }

    public void setArticleList(List<Member_titleTitleurlImgurl> articleList){
        this.articleList = articleList;
    }

    @Override
    public void onTaskCompleted(List<Member_titleTitleurlImgurl> articleList) {

    }


    private class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {
        private Context context;
        private List<Member_titleTitleurlImgurl> memberList;

        MemberAdapter(List<Member_titleTitleurlImgurl> memberList) {
            this.memberList = memberList;
        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
            // TODO: 2019/3/26 context interface 
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MemberAdapter.ViewHolder holder, int position) {
            final Member_titleTitleurlImgurl member = memberList.get(position);
            Log.d("lifeCycle"," onBindViewHolder");
            if (member.getImgUrl() != ""){
                Picasso.get().load(member.getImgUrl()).resize(600,400).centerInside().into(holder.imageId);
            }

//            new DownloadImageTask(holder.imageId).execute(member.getImgUrl());
//            holder.imageId.setImageResource(member.getImage());
//            holder.imageId.setImageBitmap(member.getImg());
            holder.textId.setText("");
            holder.textName.setText(member.getTitle());
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ImageView imageView = new ImageView(context);
//                    imageView.setImageResource(member.getImage());
//                    Toast toast = new Toast(context);
//                    toast.setView(imageView);
//                    toast.setDuration(Toast.LENGTH_SHORT);
//                    toast.show();
//                }
//            });

        }

        @Override
        public int getItemCount() {
            return memberList.size();
        }



        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageId;
            TextView textId, textName;

            ViewHolder(View itemView) {
                super(itemView);
                imageId = (ImageView) itemView.findViewById(R.id.imageView);
                textId = (TextView) itemView.findViewById(R.id.textId);
                textName = (TextView) itemView.findViewById(R.id.textName);

            }
        }

    }

}

