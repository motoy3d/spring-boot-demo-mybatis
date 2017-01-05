package com.example.actor.web.common;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WebLoggingInterceptor extends HandlerInterceptorAdapter {
	final static Logger logger = LoggerFactory.getLogger(WebLoggingInterceptor.class);
	private StopWatch sw = new StopWatch();
	private String random;

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		// Handlerメソッドが呼び出される前に行う処理を実装する
		random = UUID.randomUUID().toString();
		logger.info("Start {} - {} #############################", req.getServletPath(), random);
//		StopWatch sw = new StopWatch();
		sw.reset();
		sw.start();
		req.setAttribute("sw", sw);

		// Handlerメソッドを呼び出す場合はtrueを返却する
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
			ModelAndView modelAndView) throws Exception {
		// Handlerメソッドが正常終了した後に行う処理を実装する(例外が発生した場合は、このメソッドは呼び出されない)
		// (実装は省略)
	}

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex)
			throws Exception {
		// Handlerメソッドの呼び出しが完了した後に行う処理を実装する(例外が発生しても、このメソッドは呼び出される)
//		StopWatch sw = (StopWatch) req.getAttribute("sw");
		logger.debug("afterCompletion>>>>>>>>>>");

		try {
			sw.stop();
		} catch(IllegalStateException iex) {
			sw.reset();
			sw.stop();
		}
		if (ex != null) {
			logger.error("★★★★★★★★★★★★エラー発生");
		}
		logger.info("End {} {} - {} ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
				, (sw.getTime()) / 1000.0 + "秒", req.getServletPath(), random);
	}

}
