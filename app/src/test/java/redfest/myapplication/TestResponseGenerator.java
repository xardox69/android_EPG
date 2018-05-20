package redfest.myapplication;

import java.util.ArrayList;

import redfest.myapplication.network.models.EPGChannel;
import redfest.myapplication.network.models.EPGProgram;
import redfest.myapplication.network.models.EPGResponse;

/**
 * Created by usman on 3/22/18.
 */

public class TestResponseGenerator {

    public static EPGResponse generateResponse(){
        EPGResponse response = new EPGResponse();
        ArrayList<EPGChannel> channels = new ArrayList<>();
        for(int i = 0;i< 18;i++){
            EPGChannel channel = new EPGChannel();
            channel.setId(Integer.toString(i));
            channel.setName("Channel " + i);
            channel.setImageUrl(getImageUrl(i));
            for(int j = 0; j<12 ;j++){
                EPGProgram program = new EPGProgram();
                program.setTitle("program " + j);
                program.setDuration(getRandomDuration(i,j));
                program.setStartTime(Integer.toString(i+1));
                channel.getPrograms().add(program);
            }
            response.getChannels().add(channel);

        }

        return response;

    }

    private static String getImageUrl(int i) {
        return "https://dummyimage.com/60x60/ffffff/000000&text=Channel " + i;
    }


    private static int getRandomDuration(int channelIndex, int programIndex){
        int i =0;
        boolean even = channelIndex%2==0?true:false;

        if(even){
            if(programIndex == 0){
                return 30;
            }else if(programIndex == 1){
                return 60;
            }else if(programIndex == 2){
                return 90;
            }else if(programIndex == 3){
                return 120;
            }else if(programIndex == 4){
                return 150;
            }else if(programIndex == 5){
                return 180;
            }else if(programIndex == 6){
                return 210;
            }else if(programIndex == 7){
                return 240;
            }else if(programIndex == 8){
                return 270;
            }else if(programIndex == 9){
                return 300;
            }else if(programIndex == 10){
                return 45;
            }else if(programIndex == 11){
                return 75;
            }


        }else{
            if(programIndex == 0){
                return 75;
            }else if(programIndex == 1){
                return 45;
            }else if(programIndex == 2){
                return 300;
            }else if(programIndex == 3){
                return 270;
            }else if(programIndex == 4){
                return 240;
            }else if(programIndex == 5){
                return 210;
            }else if(programIndex == 6){
                return 180;
            }else if(programIndex == 7){
                return 150;
            }else if(programIndex == 8){
                return 120;
            }else if(programIndex == 9){
                return 90;
            }else if(programIndex == 10){
                return 60;
            }else if(programIndex == 11){
                return 30;
            }
        }

        return i;

    }

}
