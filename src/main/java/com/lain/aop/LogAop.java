package com.lain.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.lain.dao.LogMapper;
import com.lain.entity.Log;
import com.lain.entity.User;

@Component
@Aspect
public class LogAop {
	@Autowired
	private LogMapper logMapper;
	@Pointcut("@annotation(com.lain.aop.LogAspect)")
	public void pointCut(){
	}

	@Before("pointCut()")
	public void begin(JoinPoint joinPoint){
		System.out.println("保存日志...");
	}

	@After("pointCut()")
	public void close(JoinPoint joinPoint){
		try {
			Class.forName(joinPoint.getTarget().getClass().getName()).getMethods();
			String targetName = joinPoint.getTarget().getClass().getName();  
			String methodName = joinPoint.getSignature().getName();  
			Object[] arguments = joinPoint.getArgs();  
			Class targetClass = null;
			targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			String operationType = "";
			String operationName = "";
			for (Method method : methods) {  
				if (method.getName().equals(methodName)) {  
					Class[] clazzs = method.getParameterTypes();  
					if (clazzs.length == arguments.length) {  
						operationType = method.getAnnotation(LogAspect.class).operationType();
						operationName = method.getAnnotation(LogAspect.class).operationName();
						break;  
					}
				}  
			} 
			System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);  
			System.out.println("方法描述:" + operationName);  
//			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			Log log = new Log();
//			log.setuId(user.getuId());
//			log.setLogType(operationType);
//			log.setContent(operationName);
//			logMapper.insertLog(log);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
	}
	@AfterReturning("pointCut()")
	public void after() {
		System.out.println("保存成功...");
	}
}
