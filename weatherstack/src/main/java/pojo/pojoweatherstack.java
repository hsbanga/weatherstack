/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date 14/03/2022 
 * @modify By:  date:
 * 
 * @desc 
 */

package pojo;

import java.util.ArrayList;

public class pojoweatherstack {
	
	public class Request{
	    public String type;
	    public String query;
	    public String language;
	    public String unit;
	}

	public class Location{
	    public String name;
	    public String country;
	    public String region;
	    public String lat;
	    public String lon;
	    public String timezone_id;
	    public String localtime;
	    public int localtime_epoch;
	    public String utc_offset;
	}

	public class Current{
	    public String observation_time;
	    public int temperature;
	    public int weather_code;
	    public ArrayList<String> weather_icons;
	    public ArrayList<String> weather_descriptions;
	    public int wind_speed;
	    public int wind_degree;
	    public String wind_dir;
	    public int pressure;
	    public int precip;
	    public int humidity;
	    public int cloudcover;
	    public int feelslike;
	    public int uv_index;
	    public int visibility;
	    public String is_day;
	}

	public class Root{
	    public Request request;
	    public Location location;
	    public Current current;
	}


}
