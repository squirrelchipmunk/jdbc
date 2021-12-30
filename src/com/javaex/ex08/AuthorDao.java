package com.javaex.ex08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs= null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	private void getConnection(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);			
		}catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		}catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
	}
	
	private void close() {
		try {    
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	
	public void authorInsert(AuthorVo vo) {
		
		try {
			getConnection();

			String query ="";
			query += " insert into author ";
			query += " values(seq_author_id.nextval, ?, ? ) " ;

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getAuthorName());    
			pstmt.setString(2, vo.getAuthorDesc());  
			
			int count = pstmt.executeUpdate();  
			System.out.println(count + " 건이 저장되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();
		
	}

	public void authorUpdate(AuthorVo vo) {

		try {
			getConnection();

			String query ="";
			query += " update author ";
		    query += " set author_name = ?, ";
		    query += " 	   author_desc = ? " ;
		    query += " where author_id = ? " ;

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getAuthorName());   //첫번째 물음표의 데이터
		    pstmt.setString(2, vo.getAuthorDesc()); //두번째 물음표의 데이터
		    pstmt.setInt(3, vo.getAuthorId());    
			
			int count = pstmt.executeUpdate();  
			System.out.println(count + " 건이 수정되었습니다.");

		}catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();
		
	}
	

	public void authorDelete(int authorId) {
	
		try {
			getConnection();

			String query ="";
			query += " delete from author ";
		    query += " where author_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);      
			
			int count = pstmt.executeUpdate();  
			System.out.println(count + " 건이 삭제되었습니다.");
			
		}catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();
		
	}

	public List<AuthorVo> authorSelect() {
		List<AuthorVo> authorList = new ArrayList<>();
		
		try {
			getConnection();

			String query ="";
			query += " select author_id id, "; // as 사용 가능
			query += " 	 	  author_name, ";
			query += " 		  author_desc ";
			query += " from author ";

			pstmt = conn.prepareStatement(query);   
			
			rs = pstmt.executeQuery();  
			
			while(rs.next()) {
				AuthorVo vo = new AuthorVo(rs.getInt(1), rs.getString(2), rs.getString(3));
				authorList.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();
		
		return authorList;
	}
	
}
