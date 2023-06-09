package com.gigbiz.adapters;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gigbiz.R;
import com.gigbiz.helper.BaseViewHolder;
import com.gigbiz.models.YoutubeVideo;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class YoutubeRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_NORMAL = 1;
    private List<YoutubeVideo> mYoutubeVideos;
    DisplayMetrics displayMetrics = new DisplayMetrics();

    public YoutubeRecyclerAdapter(List<YoutubeVideo> youtubeVideos) {
        mYoutubeVideos = youtubeVideos;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.training_row_lay, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        if (mYoutubeVideos != null && mYoutubeVideos.size() > 0) {
            return mYoutubeVideos.size();
        } else {
            return 1;
        }
    }

    public void setItems(List<YoutubeVideo> youtubeVideos) {
        mYoutubeVideos = youtubeVideos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {
        TextView textWaveTitle;
        ImageView playButton;
        ImageView imageViewItems;
        YouTubePlayerView youTubePlayerView;

        public ViewHolder(View itemView) {
            super(itemView);
            textWaveTitle = itemView.findViewById(R.id.textViewTitle);
            playButton = itemView.findViewById(R.id.btnPlay);
            imageViewItems = itemView.findViewById(R.id.imageViewItem);
            youTubePlayerView = itemView.findViewById(R.id.youtube_view);

        }

        protected void clear() {
        }

        public void onBind(int position) {
            super.onBind(position);
            if (mYoutubeVideos.size() != 0) {
                try {
                    final YoutubeVideo mYoutubeVideo = mYoutubeVideos.get(position);
                    ((Activity) itemView.getContext()).getWindowManager()
                            .getDefaultDisplay()
                            .getMetrics(displayMetrics);
                    int width = displayMetrics.widthPixels;
                    if (mYoutubeVideo.getTitle() != null) {
                        textWaveTitle.setText(mYoutubeVideo.getTitle());
                    }
                    if (mYoutubeVideo.getImageUrl() != null) {
                        Glide.with(itemView.getContext())
                                .load(mYoutubeVideo.getImageUrl()).
                                apply(new RequestOptions().override(width - 36, 200))
                                .into(imageViewItems);
                    }
                    imageViewItems.setVisibility(View.VISIBLE);
                    playButton.setVisibility(View.VISIBLE);
                    youTubePlayerView.setVisibility(View.GONE);
                    playButton.setOnClickListener(view -> {
                        imageViewItems.setVisibility(View.GONE);
                        youTubePlayerView.setVisibility(View.VISIBLE);
                        playButton.setVisibility(View.GONE);
                        //                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        //                    @Override
                        //                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        //
                        //                        youTubePlayer.loadVideo(mYoutubeVideo.getVideoId(), 0);
                        //
                        //                    }
                        //                });

                        youTubePlayerView.getYouTubePlayerWhenReady(new YouTubePlayerCallback() {
                            @Override
                            public void onYouTubePlayer(@NonNull YouTubePlayer youTubePlayer) {
                                youTubePlayer.loadVideo(mYoutubeVideo.getVideoId(), 0);

                            }
                        });
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}