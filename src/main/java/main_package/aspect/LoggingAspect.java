package main_package.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
  public int cnt = 0;

  // Метод, который будет вызван перед выполнением любого метода в сервисе
  @Before("execution(* main_package.controller.*.*(..))")
  public void logBefore(JoinPoint joinPoint) {
    log.info("Перед вызовом метода: " + joinPoint.getSignature().getName());
  }

  // Самый мощный совет, контролирует выполнение метода
  @Around("execution(* main_package.controller.*.*(..))")
  public void measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    cnt++;
    Object result = joinPoint.proceed(); // Вызов целевого метода
    cnt++;
    long duration = System.currentTimeMillis() - startTime;
    log.info("Метод " + joinPoint.getSignature().getName()
            + " выполнен за " + duration + " мс");
  }

  public int getCnt() {
    return cnt;
  }
}