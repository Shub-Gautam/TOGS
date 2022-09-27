package com.example.tog_s;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class Slider_Adapter extends PagerAdapter {

    Context context ;
    LayoutInflater inflater ;

    public int[] list_img = {
            R.drawable.img1,
            R.drawable.img3,
            R.drawable.img2,

    };

    public String[] list_title = {
            "Track Attendence",
            "Raise Funds",
            "Interact Easily"
    };

    public String[] list_des = {
            "Just scan and mark attendance",
            "Make online Payments",
            "Get notifications on the go"
    };

    public int[] list_bg_color = {
            Color.rgb(255,255,255),
            Color.rgb(255,255,255),
            Color.rgb(255,255,255),
    };

    public Slider_Adapter(Context context){
        this.context = context ;
    }


    @Override
    public int getCount() {
        return list_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view ==(LinearLayout)object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slider,container,false);
        LinearLayout linearLayout = view.findViewById(R.id.slider_layout);
        ImageView imageView = view.findViewById(R.id.slide_img);
        TextView heading_text = view.findViewById(R.id.slide_heading);
        TextView des_text = view.findViewById(R.id.slide_des);
        linearLayout.setBackgroundColor(list_bg_color[position]);
        imageView.setImageResource(list_img[position]);
        heading_text.setText(list_title[position]);
        des_text.setText(list_des[position]);
        container.addView(view);
        return view ;
    }
}
