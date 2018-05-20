package redfest.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import redfest.myapplication.R;
import redfest.myapplication.network.models.EPGChannel;

import static redfest.myapplication.utils.AppConstants.EPG_TIMELINE_HRS;
import static redfest.myapplication.utils.AppConstants.MINUTES_IN_HOUR;

/**
 * HELPER CLASS TO GENERATE EPG VIEW
 * Created by usman on 3/22/18.
 */

public class EPGViewUtils {


    public static void addChannels(Context context, ArrayList<EPGChannel> channels, LinearLayout channelsLayout){
        for(int i=0;i<channels.size();i++) {
            LinearLayout linearLayout = new LinearLayout(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int)context.getResources().getDimension(R.dimen.row_height));
            lp.setMargins(4,5,5,4);
            lp.gravity = Gravity.CENTER;
            linearLayout.setLayoutParams(lp);

            LinearLayout.LayoutParams textLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            TextView channelText = new TextView(context);
            channelText.setLayoutParams(textLp);
            channelText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            channelText.setGravity(Gravity.CENTER);
            channelText.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            channelText.setText(channels.get(i).getName());
            linearLayout.addView(channelText,0);
            channelsLayout.addView(linearLayout,i+1);

        }

    }

    public static void addTimeLineToEPG(LinearLayout timelineLayout,Context context){

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int)context.getResources().getDimension(R.dimen.hour_width), (int)context.getResources().getDimension(R.dimen.row_height));
        lp.gravity = Gravity.LEFT;
        lp.setMargins(5,10,5,0);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,Gravity.LEFT | Gravity.CENTER_VERTICAL);
        params.leftMargin = 3;
        for(int j=0;j<1;j++) {
            LinearLayout programRow = (LinearLayout)((Activity)context).getLayoutInflater().inflate(R.layout.horizontal_item, null);
            for (int i = 0; i < EPG_TIMELINE_HRS; i++) {

                FrameLayout frameLayout = new FrameLayout(context);
                frameLayout.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                frameLayout.setLayoutParams(lp);

                TextView hour = new TextView(context);
                hour.setLayoutParams(params);
                hour.setGravity(Gravity.CENTER_VERTICAL| Gravity.LEFT);
                String hourStr = context.getString(R.string.hour_format,Integer.toString(i));
                hour.setText(hourStr);
                hour.setBackgroundColor(context.getResources().getColor(android.R.color.black));
                hour.setTextColor(context.getResources().getColor(android.R.color.white));

                frameLayout.addView(hour);
                programRow.addView(frameLayout);

            }
            timelineLayout.addView(programRow);
        }

    }

    public static void addProgramsToEPG(LinearLayout programsLayout,Context context,ArrayList<EPGChannel> channels){


        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        for(int j=0;j<channels.size();j++) {
            LinearLayout programRow = (LinearLayout)((Activity)context).getLayoutInflater().inflate(R.layout.horizontal_item, null);
            for (int i = 0; i < channels.get(j).getPrograms().size(); i++) {

                FrameLayout frameLayout = new FrameLayout(context);
                frameLayout.setBackgroundColor(context.getResources().getColor(android.R.color.white));



                float factor = getReducingFactor((float)channels.get(j).getPrograms().get(i).getDuration());
                int width = (int)(context.getResources().getDimension(R.dimen.hour_width) *factor);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, (int)context.getResources().getDimension(R.dimen.row_height));
                lp.gravity = Gravity.LEFT;
                lp.setMargins(5,10,5,0);


                frameLayout.setLayoutParams(lp);

                TextView hour = new TextView(context);
                hour.setLayoutParams(params);
                hour.setGravity(Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL);
                hour.setText(channels.get(j).getPrograms().get(i).getTitle());

                frameLayout.addView(hour);
                programRow.addView(frameLayout);

            }
            programsLayout.addView(programRow);
        }
    }

    private static float getReducingFactor(float duration){
        return duration/MINUTES_IN_HOUR;
    }

    public static void addDayToEPG(LinearLayout channelsLayout, Context context, String day){
        LinearLayout linearLayout = new LinearLayout(context);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int)context.getResources().getDimension(R.dimen.row_height));
        lp.setMargins(5,10,5,5);
        lp.gravity = Gravity.CENTER;
        linearLayout.setLayoutParams(lp);

        LinearLayout.LayoutParams textLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        TextView channelText = new TextView(context);
        channelText.setLayoutParams(textLp);
        channelText.setText(day);
        channelText.setGravity(Gravity.CENTER);
        channelText.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        linearLayout.addView(channelText,0);
        channelsLayout.addView(linearLayout,0);

    }
}
