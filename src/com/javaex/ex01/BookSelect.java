package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelect {
	public static void main(String[] args) {
		// 책 데이터 가져오기
		List<BookVo> bookList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문준비/ 바인딩/ 실행
			
			//문자열 만들기
			String query = "";
			query += " select book_id, "; // as 사용 가능
			query += "        title, ";
			query += "        pubs, ";
			query += "        to_char(pub_date,'yyyy-mm-dd'),";
			query += "        author_id ";
			query += " from book ";
			
			//문자열 쿼리문 만들기
			pstmt = conn.prepareStatement(query); 
			
			//바인딩 -- ? 없음
			
			//실행
			rs = pstmt.executeQuery(); //select
			
			System.out.println("book_id\t\tttitle\t\tpubs\tpub_date\tauthor_id");
			// 4.결과처리
			while(rs.next()) {
				int book_id = rs.getInt(1);
				String title = rs.getString(2);
				String pubs = rs.getString(3);
				String pub_date = rs.getString(4);
				int author_id = rs.getInt(5);
//				System.out.println(book_id+", "+title+", "+pubs+", "+pub_date+", "+author_id);
				BookVo vo = new BookVo(book_id, title, pubs, pub_date, author_id);
				bookList.add(vo);
			}
			
			for(BookVo vo : bookList) {
				System.out.println(vo.getBook_id()+", "+vo.getTitle()+", "+vo.getPubs()+", "+vo.getPub_date()+", "+vo.getAuthor_id());
			}
			// 첫번째 책 이름 출력
			System.out.println(bookList.get(0).getTitle());
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버로딩실패-"+ e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs!= null) {
					rs.close();
				}
				if (pstmt!= null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

	}

}
	
	
	
