package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBBackend {
	
	private String[] header;
	private String[][] rows;
	private String query;
	private String errorMsg;
	private int rowcount;
	private ResultSet rs;
	private Connection conn;
	
	public DBBackend(Connection conn, String query){
		this.conn = conn;
		this.query = query;
	}
	
	public String getErrorMsg(){
		return errorMsg;
	}
	
	public int getRowCount(){
		if(errorMsg==null)
			return rowcount;
		else return 0;
	}
	
	public ResultSet getResultSet(){
		return rs;
	}
	
	public String[] getHeader(){
		return header;
	}
	
	public void setQuery(String query){
		this.query = query;
	}
	
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	
	public String[][] getRows(){
		int columncount;
		
		if(query!=null){
			if(conn!=null){
				try{
					PreparedStatement pstatement = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = pstatement.executeQuery();
					rs.last();
					rowcount = rs.getRow();
					rs.beforeFirst();
					
					ResultSetMetaData metadata = rs.getMetaData();
					columncount = metadata.getColumnCount();
					header = new String[columncount];
					for(int i=0;i<columncount;i++){
//						System.out.println(metadata.getColumnName(i));
//						System.out.println(i);
						header[i] = metadata.getColumnName(i+1);
					}
				}catch(SQLException sqle){
					errorMsg = sqle.getMessage();
				}
			}
		}
		
		if(query!=null&&header!=null&&rowcount!=0&&errorMsg==null){
			rows = new String[rowcount][header.length];
			try{
				for(int i=0; i<rowcount && rs.next(); i++){
				for(int j=0; j<header.length; j++){
					rows[i][j] = rs.getString(j+1);
				}
			}
			}catch(SQLException sqle){
				errorMsg = sqle.getMessage();
			}
		}
		return rows;
	}
	
	public String[][] getRows(String[] header){
		this.header = header;
		
		if(query!=null){
			if(conn!=null){
				try{
					PreparedStatement pstatement = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = pstatement.executeQuery();
					rs.last();
					rowcount = rs.getRow();
					rs.beforeFirst();
					
				}catch(SQLException sqle){
					errorMsg = sqle.getMessage();
				}
			}
		}
		
		if(query!=null&&header!=null&&rowcount!=0&&errorMsg==null){
			rows = new String[rowcount][header.length];
			try{
				for(int i=0; i<rowcount && rs.next(); i++){
				for(int j=0; j<header.length; j++){
					rows[i][j] = rs.getString(j+1);
				}
			}
			}catch(SQLException sqle){
				errorMsg = sqle.getMessage();
			}
		}
		return rows;
	}

}
