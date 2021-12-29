package com.javaex.ex04;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		List<AuthorVo> authorList;
		
		AuthorDao authorDao = new AuthorDao();
		
		//작가 추가
		AuthorVo vo01 = new AuthorVo("김문열","경북 영양");
		authorDao.authorInsert(vo01);
		
		AuthorVo vo02 = new AuthorVo("박경리","경상남도 통영");
		authorDao.authorInsert(vo02);
		
		AuthorVo vo03 = new AuthorVo("유시민","17대 국회의원");
		authorDao.authorInsert(vo03);
		
		System.out.println("----------------------------------");
		authorList = authorDao.authorSelect();
		for(AuthorVo vo : authorList) {
			System.out.println(vo.getAuthorId() +", "+ vo.getAuthorName()+", "+vo.getAuthorDesc());
		}
		
		//작가 수정
		System.out.println("----------------------------------");
		AuthorVo vo04 = new AuthorVo(1,"이문열","삼국지 작가");
		authorDao.authorUpdate(vo04);
		authorList = authorDao.authorSelect();
		for(AuthorVo vo : authorList) {
			System.out.println(vo.getAuthorId() +", "+ vo.getAuthorName()+", "+vo.getAuthorDesc());
		}
		// 작가 삭제
		System.out.println("----------------------------------");
		authorDao.authorDelete(2);
		authorList = authorDao.authorSelect();
		for(AuthorVo vo : authorList) {
			System.out.println(vo.getAuthorId() +", "+ vo.getAuthorName()+", "+vo.getAuthorDesc());
		}
	}

}
