package com.ayushman999.dialog;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;

public class MySingleton {
    private static SwipeDismissDialog swipeDismissDialog;
    private static View dialog;
    private static Button dialog_listen;
    private static TextView dialog_title;
    private static ImageView dialog_image;
    private MySingleton()
    {

    }
    public static void getInstance(Context context)
    {
        if(swipeDismissDialog==null)
        {

            dialog= LayoutInflater.from(context).inflate(R.layout.dialog_box,null);
            dialog_listen=(Button) dialog.findViewById(R.id.dialog_listen);
            dialog_title=(TextView) dialog.findViewById(R.id.dialog_title);
            dialog_image=(ImageView) dialog.findViewById(R.id.dialog_image);
            swipeDismissDialog=new SwipeDismissDialog.Builder(context)
                    .setView(dialog)
                    .build()
                    .show();
        }
    }
    public static void showDialog(String title, String img_url, String song_url, Context context)
    {
        MySingleton.getInstance(context);
        if(title!=null) {
            dialog_title.setText(title);
        }
        Glide.with(context).load(img_url).into(dialog_image);
        dialog_listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri webpage=Uri.parse(song_url);
                    Intent transfer=new Intent(Intent.ACTION_VIEW,webpage);
                    context.startActivity(transfer);
                }
                catch (Exception e)
                {
                    Toast.makeText(context, "Something is wrong...", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
