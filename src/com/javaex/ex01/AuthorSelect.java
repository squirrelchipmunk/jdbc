package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AuthorSelect {

	public static void main(String[] args) {

		List<AuthorVo> authorList = new ArrayList<>();
		
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
			query += " select author_id id, "; // as 사용 가능
			query += " 	 	  author_name, ";
			query += " 		  author_desc ";
			query += " from author ";
			
			//문자열 쿼리문 만들기
			pstmt = conn.prepareStatement(query); 
			
			//바인딩 -- ? 없음
			
			//실행
			rs = pstmt.executeQuery(); //select
			
			System.out.println("author_id\tauthor_name\tauthor_desc");
			// 4.결과처리
			while(rs.next()) {
//				int author_id = rs.getInt("id");
//				String author_name = rs.getString("author_name");
//				String author_desc = rs.getString("author_desc");
				int author_id = rs.getInt(1);
				String author_name = rs.getString(2);
				String author_desc = rs.getString(3);
//				System.out.println(author_id+"\t\t"+author_name+"\t\t"+author_desc);
				
				AuthorVo vo = new AuthorVo(author_id, author_name, author_desc);
				authorList.add(vo);
			}
			
			//출력
			for(AuthorVo vo : authorList) {
				System.out.println(vo.getAuthor_id()+", "+vo.getAuthor_name() + ", "+vo.getAuthor_desc());
			}
			// 첫번째 작가 이름 출력
			System.out.println(authorList.get(0).getAuthor_name());
			
			
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
