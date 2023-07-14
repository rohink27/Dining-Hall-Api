package com.rohin.uiuc.dining;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.text.*;

@Path("times")
public class TimeFrameResource {
	
	TimeFrameRepository reppo = new TimeFrameRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// the /times path will return the list of all the times
	public List<TimeFrame> get()
	{
		return reppo.getTimes();
	}
	
	@POST
	@Path("add/{hall}/{time}")
	@Produces(MediaType.APPLICATION_JSON)
	// this request will add a timeframe to the database and set recent write to 1
	public TimeFrame add(@PathParam("hall") String hall, @PathParam("time") int time)
	{
		TimeFrame t = new TimeFrame();
		Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("E yyyy.MM.dd 'at' HH:mm:ss a zzz");

	    String unformated = ft.format(dNow);
	    String formatted = "";
	    for(int i=0; i< unformated.length(); i++)
	    {
	    	if(Character.isDigit(unformated.charAt(i))){
	    		formatted+= unformated.charAt(i);
	    	}
	    }
	    int hour = Integer.parseInt(formatted.substring(8,10));
	    if(hour >= 7 && hour <=10)
	    {
	    	t.setMeal_time("Breakfast");
	    }
	    else if(hour >= 11 && hour <= 14)
	    {
	    	t.setMeal_time("Lunch");
	    }
	    else if(hour>= 16 && hour<=20)
	    {
	    	t.setMeal_time("Dinner");
	    }
	    else
	    	t.setMeal_time("All Time");
	    t.setId(Long.parseLong(formatted.substring(2)));
	    t.setHall(hall);
	    t.setTime(time);
	    reppo.addTime(t);
		return t;
		
	}
	@GET
	@Path("recent")
	@Produces(MediaType.APPLICATION_JSON)
	//will return the most recent data written in the database
	public TimeFrame recent()
	{
		return reppo.recent();
	}
	
	@GET
	@Path("recent/{hall}")
	@Produces(MediaType.APPLICATION_JSON)
	//will return the most recent data for a particular restaurant
	public TimeFrame recent(@PathParam("hall") String hall)
	{
		return reppo.recent(hall);
	}
	
	

@GET
@Path("next")
@Produces(MediaType.APPLICATION_JSON)
// will return the next row of data from the database
public TimeFrame next()
{
	return reppo.next();
}

@GET
@Path("after/{hall}/{id}")
@Produces(MediaType.APPLICATION_JSON)
// will return the first time frame for the hall which is = or > than the provided time
public TimeFrame getTimes(@PathParam("hall") String hall, @PathParam("id") long id)
{
	return reppo.getTimes(hall, id);
}

@GET
@Path("range/{hall}/{start}/{end}")
@Produces(MediaType.APPLICATION_JSON)
//will return the list of all data from one time point to another
public List<TimeFrame> getRange(@PathParam("hall") String hall,@PathParam("start") long start,@PathParam("end") long end)
{
	return reppo.set(start, end, hall);
}

@GET
@Path("dininghall/{hall}")
@Produces(MediaType.APPLICATION_JSON)
//will return the list of all data for one dining hall
public List<TimeFrame> getDH(@PathParam("hall") String hall)
{
	return reppo.dining(hall);
}


}

