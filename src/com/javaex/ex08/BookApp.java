package com.javaex.ex08;

import java.util.List;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {
		List<BookVo> bookList;
		AuthorDao authorDao = new AuthorDao();
		BookDao bookDao = new BookDao();
		
		//작가 추가
		AuthorVo aVo01 = new AuthorVo("이문열","경북 영양");
		authorDao.authorInsert(aVo01);
		AuthorVo aVo02 = new AuthorVo("박경리","경상남도 통영");
		authorDao.authorInsert(aVo02);
		AuthorVo aVo03 = new AuthorVo("유시민","17대 국회의원");
		authorDao.authorInsert(aVo03);
		AuthorVo aVo04 = new AuthorVo("기안84","기안동에서 산 84년생");
		authorDao.authorInsert(aVo04);
		AuthorVo aVo05 = new AuthorVo("강풀","온라인 만화가 1세대");
		authorDao.authorInsert(aVo05);
		AuthorVo aVo06 = new AuthorVo("김영하","알쓸신잡");
		authorDao.authorInsert(aVo06);
		AuthorVo aVo07 = new AuthorVo("이고잉","개발자");
		authorDao.authorInsert(aVo07);
		
		//책 추가
		BookVo bVo01 = new BookVo("우리들의 일그러진 영웅","다림", "1998-02-22", 1);
		bookDao.BookInsert(bVo01);
		BookVo bVo02 = new BookVo("삼국지","민음사", "2002-03-01", 1);
		bookDao.BookInsert(bVo02);
		BookVo bVo03 = new BookVo("토지", "마로니에북스", "2012-08-15",2 );
		bookDao.BookInsert(bVo03);
		BookVo bVo04 = new BookVo("자바 프로그래밍 입문","위키북스", "2015-04-01", 7);
		bookDao.BookInsert(bVo04);
		BookVo bVo05 = new BookVo("패션왕","중앙북스(books)", "2012-02-22", 4);
		bookDao.BookInsert(bVo05);
		BookVo bVo06 = new BookVo("순정만화","재미주의", "2011-08-03",5 );
		bookDao.BookInsert(bVo06);
		BookVo bVo07 = new BookVo("오직두사람","문한동네", "2017-05-04", 6);
		bookDao.BookInsert(bVo07);
		BookVo bVo08 = new BookVo("26년","재미주의", "2012-02-04", 5);
		bookDao.BookInsert(bVo08);
		
		
		// 책 검색
		System.out.println("*********** 책 검색 **************");
		Scanner sc = new Scanner(System.in);
		System.out.print("키워드를 입력하세요 : ");
		String key = sc.nextLine();

		bookList = bookDao.bookSearch(key);
		
		for(BookVo vo : bookList) {
			System.out.println(vo.getBookId()+", "+vo.getTitle()+", "+vo.getPubs()+", "+vo.getPubDate()+", "+vo.getAuthorName());
		}
		sc.close();
		
	}

}
