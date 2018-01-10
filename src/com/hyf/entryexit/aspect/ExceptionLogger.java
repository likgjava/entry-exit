package com.hyf.entryexit.aspect;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.hyf.entryexit.entity.Clerk;
import com.hyf.entryexit.entity.SuperUser;


/**
 *	记录异常日志
 */
@Component
@Aspect
public class ExceptionLogger {
	
	@Resource
	private HttpServletRequest request;

	@Around("within(com.hyf.entryexit.controller..*)")
	public Object log(ProceedingJoinPoint point) throws Exception {
		Object obj = null;
		try {
			obj = point.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			// 记录错误日志
			Logger logger = Logger.getLogger(this.getClass());
			
			//业务员
			Clerk clerk = (Clerk) request.getSession().getAttribute("clerk");
			if(clerk != null) {
				String className = point.getTarget().getClass().getName();
				String method = point.getSignature().getName();
				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				
				StringBuffer sb = new StringBuffer();
				sb.append("用户[").append(clerk.getClerk_username()).append("], ");
				sb.append("IP[").append(request.getRemoteHost()).append("], ");
				sb.append("在[").append(date).append("]时间, 执行[");
				sb.append(className).append(".").append(method);
				sb.append("]方法时，发生如下异常：");
				logger.error(sb.toString()+"\t");
			}
			//管理员
			SuperUser superUser = (SuperUser) request.getSession().getAttribute("superUser");
			if(superUser != null) {
				String className = point.getTarget().getClass().getName();
				String method = point.getSignature().getName();
				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				
				StringBuffer sb = new StringBuffer();
				sb.append("用户[").append(superUser.getSuper_name()).append("], ");
				sb.append("IP[").append(request.getRemoteHost()).append("], ");
				sb.append("在[").append(date).append("]时间, 执行[");
				sb.append(className).append(".").append(method);
				sb.append("]方法时，发生如下异常：");
				logger.error(sb.toString()+"\t");
			}
			
			//其它异常
			logger.error("\t"+"其它异常：");
			StackTraceElement[] elems = e.getStackTrace();
			for(StackTraceElement elem : elems) {
				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				StringBuffer sb = new StringBuffer();
				sb.append("IP[").append(request.getRemoteHost()).append("], ");
				sb.append("在["+date+"]时间,");
				sb.append("[类:" + elem.getClassName() + "]调用"+elem.getMethodName()+"方法时，");
				sb.append("在第" + elem.getLineNumber()+ "行代码处发生异常!");
				sb.append("异常类型:"+e.getClass().getName());
				logger.error("\t" + sb.toString());
//				logger.error("\t" + elem.toString());
			}
			logger.error("\t");
			// 抛出异常
			throw new Exception(e);
		}
		return obj;
	}

}
