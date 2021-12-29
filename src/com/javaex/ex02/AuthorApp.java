package com.javaex.ex02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		List<AuthorVo> authorList;
		
		AuthorDao authorDao = new AuthorDao();
		//작가등록
		authorDao.authorInsert("이문열", "경북 영양");
		//작가등록
		authorDao.authorInsert("박경리", "경상남도 통영");
		//작가등록
		authorDao.authorInsert("유시민", "17대 국회의원");
		
		authorList = authorDao.authorSelect();
		for(AuthorVo vo : authorList) {
			System.out.println(vo.getAuthorId() +", "+ vo.getAuthorName()+", "+vo.getAuthorDesc());
		}
		
		
		// 1번 작가 수정
		authorDao.authorUpdate(1, "김문열", "경북 영양");
		authorList = authorDao.authorSelect();
		for(AuthorVo vo : authorList) {
			System.out.println(vo.getAuthorId() +", "+ vo.getAuthorName()+", "+vo.getAuthorDesc());
		}
		
		
//		// 작가 검색
//		authorList = authorDao.authorSelect();
//		for(AuthorVo vo : authorList) {
//			System.out.println(vo.getAuthorId() +", "+ vo.getAuthorName()+", "+vo.getAuthorDesc());
//		}
		
		// 1번 작가 삭제
		authorDao.authorDelete(1);
		authorList = authorDao.authorSelect();
		for(AuthorVo vo : authorList) {
			System.out.println(vo.getAuthorId() +", "+ vo.getAuthorName()+", "+vo.getAuthorDesc());
		}
		
	}

}
