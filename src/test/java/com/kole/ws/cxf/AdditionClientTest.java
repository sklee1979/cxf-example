package com.kole.ws.cxf;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/testContext.xml" })
public class AdditionClientTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private Addition additionService;
	
	private List<Thread> threads = null;
	
	private int counter;
	
	@Before
	public void setupPool() {
		counter = 0;
		
		threads = new ArrayList<Thread>();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(new Pool()));
		}
	}
	
	
	@Test
	public void testAddition(){
		Integer answer = additionService.add(new Integer(1), new Integer(4));
		Assert.assertTrue(answer.toString().equals("5"));
	}
	
	@Test
	public void testServicePool() {
		for (Thread thread:threads) {
			thread.start();
		}
		
		for (Thread thread:threads) {
			try {
				thread.join();
				Assert.assertTrue("counter: " + counter, counter == 3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	class Pool implements Runnable {


		public void run() {
			try {
				Addition addition = (Addition) applicationContext.getBean("sampleClientService");
				counter++;
				Integer result = addition.add(new Integer(2), new Integer(5));				
				Assert.assertTrue(result.toString().equals("7"));	
			} catch (NoSuchElementException e) {
				counter--;
			}
		}
		
	}

}
