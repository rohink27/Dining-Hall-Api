package com.rohin.uiuc.dining;


import java.util.ArrayList;
import java.util.List;



import java.sql.*;


public class TimeFrameRepository {
	
	Connection con = null;

	TimeFrameRepository(){
		String url = "jdbc:mysql://localhost:3306/UIUC_Dining";
		String username = "root";
		String password = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("There was an error");
		}
	}
	
	public List<TimeFrame> getTimes()
	{
		List<TimeFrame> times = new ArrayList<TimeFrame>();
		String sql = "select * from times";
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next())
		{
			TimeFrame t = new TimeFrame();
			t.setId(rs.getLong(1));
			t.setHall(rs.getString(2));
			t.setMeal_time(rs.getString(3));
			t.setTime(rs.getInt(4));
			t.setRecent_read(rs.getInt(5));
			t.setRecent_write(rs.getInt(6));
			times.add(t);
		}
		}catch (Exception e)
		{
			System.out.println(e);
			
		}
		return times;
	}
	public TimeFrame getTimes(String hall, long id)
	{
		String sql = "select * from times where hall=" + "\"" + hall + "\"" + " and id>="  +id;
		TimeFrame t = new TimeFrame();
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next())
		{
			
			t.setId(rs.getLong(1));
			t.setHall(rs.getString(2));
			t.setMeal_time(rs.getString(3));
			t.setTime(rs.getInt(4));
			t.setRecent_read(rs.getInt(5));
			t.setRecent_write(rs.getInt(6));
			
		}
		}catch (Exception e)
		{
			System.out.println(e);
			
		}
		String sql2 = "update times set recent_read = " + 0 + " where recent_read=" + 1;
		try {
		PreparedStatement stp = con.prepareStatement(sql2);
		stp.executeUpdate(sql2);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		String sql3 = "update times set recent_read = " +1 + " where hall=" + "\"" + hall + "\"" + "and id=" + "\"" +id + "\"";
		try {
		PreparedStatement stp = con.prepareStatement(sql3);
		stp.executeUpdate(sql3);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		return t;
	}
	public void addTime(TimeFrame t)
	{
		String sql2 = "update times set recent_write= " + 0 + " where recent_write=" + 1;
		try {
		PreparedStatement stp = con.prepareStatement(sql2);
		stp.executeUpdate(sql2);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		String sql = "insert into times values(?,?,?,?,?,?)";
		try {
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, t.getId());
		st.setString(2, t.getHall());
		st.setString(3,  t.getMeal_time());
		st.setInt(4, t.getTime());
		st.setInt(5,  0);
		st.setInt(6,  1);
		st.executeUpdate();
		
		}catch (Exception e)
		{
			System.out.println(e);
			
		}
		System.out.println("Exiting");
	}
	
	public TimeFrame recent()
	{
		String sql = "select * from times where recent_write=" + 1;
		TimeFrame t = new TimeFrame();
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next())
		{
			
			t.setId(rs.getLong(1));
			t.setHall(rs.getString(2));
			t.setMeal_time(rs.getString(3));
			t.setTime(rs.getInt(4));
			t.setRecent_read(rs.getInt(5));
			t.setRecent_write(rs.getInt(6));
			
		}
		else
			return null;
		}catch (Exception e)
		{
			System.out.println(e);
			
		}
		String sql2 = "update times set recent_read= " + 0 + " where recent_read=" + 1;
		try {
		PreparedStatement stp = con.prepareStatement(sql2);
		stp.executeUpdate(sql2);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		String sql3 = "update times set recent_read= " +1 + " where recent_write=" + 1;
		try {
		PreparedStatement stp = con.prepareStatement(sql3);
		stp.executeUpdate(sql3);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		String sql4 = "update times set recent_write= " + 0 + " where recent_write=" + 1;
		try {
		PreparedStatement stp = con.prepareStatement(sql4);
		stp.executeUpdate(sql4);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		return t;
	
	}
	public TimeFrame next()
	{
		String sql = "select * from times where recent_read=" + 1;
		TimeFrame t = new TimeFrame();
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next())
		{
			
			t.setId(rs.getLong(1));
			t.setHall(rs.getString(2));
			t.setMeal_time(rs.getString(3));
			t.setTime(rs.getInt(4));
			t.setRecent_read(rs.getInt(5));
			t.setRecent_write(rs.getInt(6));
			
		}}
		catch (Exception e)
		{
			System.out.println(e);
			
		}
		String sql2 = "update times set recent_read = " + 0 + " where recent_read=" + 1;
		try {
		PreparedStatement stp = con.prepareStatement(sql2);
		stp.executeUpdate(sql2);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		sql = "select * from times where id>" + t.getId();

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
		if(rs.next())
		{
			
			t.setId(rs.getLong(1));
			t.setHall(rs.getString(2));
			t.setMeal_time(rs.getString(3));
			t.setTime(rs.getInt(4));
			t.setRecent_read(rs.getInt(5));
			t.setRecent_write(rs.getInt(6));
			
		}
	}catch (Exception e)
		{
		System.out.println(e);
		
	}
		String sql4 = "update times set recent_read = " + 1 + " where id=" + t.getId();
		try {
		PreparedStatement stp = con.prepareStatement(sql4);
		stp.executeUpdate(sql4);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		return t;
	
	}
	public List<TimeFrame> set(long start, long end, String hall)
	{
		List<TimeFrame> times = new ArrayList<TimeFrame>();
		if(start>end)
		{
			long temp = start;
			start = end;
			end = temp;
		}
		System.out.println(hall+ start + " " + end);
		String sql = "select * from times where hall=" + "\"" + hall + "\"" + " and id>=" + start;
		System.out.println("Set function called");
		
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next())
		{
			System.out.println("In loop");
			TimeFrame t = new TimeFrame();
			t.setId(rs.getLong(1));
			System.out.println(t.getId());
			t.setHall(rs.getString(2));
			t.setMeal_time(rs.getString(3));
			t.setTime(rs.getInt(4));
			t.setRecent_read(rs.getInt(5));
			t.setRecent_write(rs.getInt(6));
			if(t.getId()<end) {
			times.add(t);
			System.out.println("Added to list");
			}
			else
				break;
		}
		}catch (Exception e)
		{
			System.out.println(e);
			
		}
		return times;
	}
	public TimeFrame recent(String hall)
	{
		List<TimeFrame> times = new ArrayList<TimeFrame>();
		
		String sql = "select * from times where hall=" + "\"" + hall + "\"";
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next())
		{
			TimeFrame t = new TimeFrame();
			t.setId(rs.getLong(1));
			t.setHall(rs.getString(2));
			t.setMeal_time(rs.getString(3));
			t.setTime(rs.getInt(4));
			t.setRecent_read(rs.getInt(5));
			t.setRecent_write(rs.getInt(6));
			times.add(t);
		}}
		catch(Exception e)
		{
			System.out.println(e);
		}
		TimeFrame ti = times.get(times.size() - 1);
		String sql2 = "update times set recent_read= " + 0 + " where recent_read=" + 1;
		try {
		PreparedStatement stp = con.prepareStatement(sql2);
		stp.executeUpdate(sql2);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		String sql4 = "update times set recent_read = " + 1 + " where id=" + ti.getId();
		try {
		PreparedStatement stp = con.prepareStatement(sql4);
		stp.executeUpdate(sql4);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return ti;
		
		
	}
	public List<TimeFrame> dining(String hall)
	{
		List<TimeFrame> times = new ArrayList<TimeFrame>();
		String sql = "select * from times where hall=" + "\"" + hall + "\"" ;
		System.out.println("Set function called");
		
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				System.out.println("In loop");
				TimeFrame t = new TimeFrame();
				t.setId(rs.getLong(1));
				System.out.println(t.getId());
				t.setHall(rs.getString(2));
				t.setMeal_time(rs.getString(3));
				t.setTime(rs.getInt(4));
				t.setRecent_read(rs.getInt(5));
				t.setRecent_write(rs.getInt(6));
				
				times.add(t);
			
			}
		}catch (Exception e)
		{
			System.out.println(e);
			
		}
		return times;

	}
}
	

