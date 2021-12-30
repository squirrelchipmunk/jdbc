package com.javaex.ex08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookDao {

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
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	private void close() {
		try {               
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
	
	public void BookInsert(BookVo vo) {

		try {
			getConnection();

			String query ="";
			query += " insert into book ";
			query += " values(seq_book_id.nextval, ?, ?, ?, ?) " ;

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getTitle());    
			pstmt.setString(2, vo.getPubs());
			pstmt.setString(3, vo.getPubDate());
			pstmt.setInt   (4, vo.getAuthorId());
			
			int count = pstmt.executeUpdate();  
			System.out.println(count + " 건이 저장되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();
	}

	public void bookUpdate(BookVo vo) {

		try {
			getConnection();

			String query ="";
			query += " update book ";
		    query += " set title = ?, ";
		    query += " 	   pubs = ?, " ;
		    query += " 	   pub_date = ?, " ;
		    query += " 	   author_id = ? " ;
		    query += " where book_id = ? " ;

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getTitle()); 
		    pstmt.setString(2, vo.getPubs()); 
		    pstmt.setString(3, vo.getPubDate()); 
		    pstmt.setInt(4, vo.getAuthorId()); 
		    pstmt.setInt(5, vo.getBookId()); 
			
			int count = pstmt.executeUpdate();  
			System.out.println(count + " 건이 수정되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
	
		close();
	}
	

	public void bookDelete(int bookId) {

		try {
			getConnection();

			String query ="";
			query += " delete from book ";
		    query += " where book_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);      
			
			int count = pstmt.executeUpdate();  
			System.out.println(count + " 건이 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();
	}

	public List<BookVo> bookSelect() {
		List<BookVo> bookList = new ArrayList<>();
		
		try {
			getConnection();

			String query ="";
			query += " select book_id, "; // as 사용 가능
			query += " 	 	  title, ";
			query += " 		  pubs, ";
			query += " 		  to_char(pub_date,'yyyy-mm-dd'), ";
			query += " 		  author.author_id, ";
			query += " 		  author_name, ";
			query += " 		  author_desc ";
			query += " from book, author ";
			query += " where book.author_id = author.author_id ";

			pstmt = conn.prepareStatement(query);   
			
			rs = pstmt.executeQuery();  
			
			while(rs.next()) {
				BookVo vo = new BookVo(
						rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7));
				bookList.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();
			
		return bookList;
	}
	
	public List<BookVo> bookSearch(String key) {
		List<BookVo> bookList = new ArrayList<>();
		
		try {
			getConnection();

			String query ="";
			query += " select book_id, "; // as 사용 가능
			query += " 	 	  title, ";
			query += " 		  pubs, ";
			query += " 		  to_char(pub_date,'yyyy-mm-dd'), ";
			query += " 		  author.author_id, ";
			query += " 		  author_name, ";
			query += " 		  author_desc ";
			query += " from book, author ";
			query += " where book.author_id = author.author_id and ";
			query += " 		 (title like ? or ";
			query += " 		  pubs like ? or ";
			query += " 		  author_name like ?) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+key+"%");
			pstmt.setString(2, "%"+key+"%");
			pstmt.setString(3, "%"+key+"%");
			
			rs = pstmt.executeQuery();  
			
			while(rs.next()) {
				BookVo vo = new BookVo(
						rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7));
				bookList.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();
			
		return bookList;
	}
	
}
