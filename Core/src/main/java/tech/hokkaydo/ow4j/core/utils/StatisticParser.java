package tech.hokkaydo.ow4j.core.utils;

public class StatisticParser {
    public static double parseStatistic(String value) {
        value = value.replace("%", "");
        try{
            return Double.parseDouble(value);
        }catch(NumberFormatException e){
            String[] times = value.split(":");
            if(times.length == 2){
                return Integer.parseInt(times[0])*60 + Integer.parseInt(times[1]);
            }else if(times.length == 3){
                return Integer.parseInt(times[0])*60*60 + Integer.parseInt(times[1])*60 + Integer.parseInt(times[2]);
            }
        }
        return 0;
    }
}
