package com.example.faigy.hala;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Home on 2/3/2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyNewsHolder> {

    private List<News> newsList;

    public class MyNewsHolder extends RecyclerView.ViewHolder {
        public TextView title, publication, date;
        ImageView emailImageView;

        public MyNewsHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.titleTextView);
            publication = (TextView) view.findViewById(R.id.publicationTextView);
            date = (TextView) view.findViewById(R.id.dateTextView);
            emailImageView = (ImageView)view.findViewById(R.id.emailImageView);


        }

    }


    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public MyNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_in_the_news_item, parent, false);

        final MyNewsHolder holder = new MyNewsHolder(itemView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.openUrlInBrowser("http://m.jpost.com/In-Jerusalem/A-revolution-in-the-haredi-" +
                        "community-438940#article=6030QzIzMUJBMUZDNDcxNDFDQzNDRkVDMEE2M0I0NkU3MEQ=");
            }
        });

        return new MyNewsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyNewsHolder holder, int position) {
        News news = newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.publication.setText(news.getPublication());
        holder.date.setText(news.getDate().toString());

        holder.emailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Context context = Util.getContext();
//                CharSequence text = "item clicked";
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
                Util.composeEmail(null,"Hala Article","http://m.jpost.com/In-Jerusalem/A-revolution-in-the-haredi-" +
                        "community-438940#article=6030QzIzMUJBMUZDNDcxNDFDQzNDRkVDMEE2M0I0NkU3MEQ=");
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
