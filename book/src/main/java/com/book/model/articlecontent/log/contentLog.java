package com.book.model.articlecontent.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class contentLog {
	@AfterThrowing(pointcut="logPointcut.allPointcut()", throwing="exceptObj")
	 public void exceptionLog(JoinPoint jp, Exception exceptObj) {
		 String method = jp.getSignature().getName();
		 System.out.println("[Content Log] ===> " + method + "() �޼ҵ� ���� �� ���� �߻�!");
		 
		 if(exceptObj instanceof Exception) {
			 System.out.println("[Content Log Exception] ===> ���ܹ߻� : " + exceptObj);
		 }
	 }
	
	@Around("logPointcut.allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		String method = pjp.getSignature().getName();
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object obj = pjp.proceed();
		
		stopWatch.stop();
		
		System.out.println("[Content Log] ===> " + method + "() �޼ҵ� ���� �ð� : " + stopWatch.getTotalTimeMillis() + "(ms)��");
		return obj;
	}
}
