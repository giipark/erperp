package com.erp.web;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import erp.common.util.encoder.PasswordEncoding;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@Log4j
public class PasswordTest {

	@Autowired
	private PasswordEncoding passwordEncoding;
	
	@Test
	public void test2() {
		// 1234 라 할지라도 매번 다른 값으로 랜덤하게 encoding 된다.
		String encode=passwordEncoding.encode("1234");
		// encode 를 DB에 저장 하면 된다.
		
		//값을 비교 하는 것은 matches() 를 사용해서
		//로그인 유저와 DB에 저장된 비번을 비교 해서 true 값 이면 비번이 일치 한다는 것이다.
		
		passwordEncoding.matches("1234", encode);
		log.info("passwordEncoding.encode(1234)" +encode);
		log.info("1234 비교: " + passwordEncoding.matches("1234", encode));
	}
	
	
}