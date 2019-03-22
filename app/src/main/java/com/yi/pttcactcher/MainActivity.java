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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member(1, R.drawable.ic_launcher_background, "白沙屯海灘1"));
        memberList.add(new Member(2, R.drawable.ic_launcher_background, "白沙屯海灘2"));
        memberList.add(new Member(3, R.drawable.ic_launcher_foreground, "白沙屯海灘3"));
        memberList.add(new Member(4, R.drawable.ic_launcher_background, "白沙屯海灘4"));
        memberList.add(new Member(5, R.drawable.ic_launcher_background, "白沙屯海灘5"));
        memberList.add(new Member(6, R.drawable.ic_launcher_background, "白沙屯3"));
        memberList.add(new Member(7, R.drawable.ic_launcher_background, "後龍1"));
        memberList.add(new Member(8, R.drawable.ic_launcher_background, "後龍2"));
        memberList.add(new Member(9, R.drawable.ic_launcher_background, "後龍3"));
        memberList.add(new Member(10, R.drawable.ic_launcher_background, "龍港4"));
        memberList.add(new Member(11, R.drawable.ic_launcher_background, "龍港4.1"));
        memberList.add(new Member(12, R.drawable.ic_launcher_background, "龍港4.2"));

        Log.d("lifeCycle","OnCreate");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MemberAdapter(this, memberList));
    }

    private class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {
        private Context context;
        private List<Member> memberList;

        MemberAdapter(Context context, List<Member> memberList) {
            this.context = context;
            this.memberList = memberList;
        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.cardview, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MemberAdapter.ViewHolder holder, int position) {
            final Member member = memberList.get(position);
            holder.imageId.setImageResource(member.getImage());
            holder.textId.setText(String.valueOf(member.getId()));
            holder.textName.setText(member.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView imageView = new ImageView(context);
                    imageView.setImageResource(member.getImage());
                    Toast toast = new Toast(context);
                    toast.setView(imageView);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

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

