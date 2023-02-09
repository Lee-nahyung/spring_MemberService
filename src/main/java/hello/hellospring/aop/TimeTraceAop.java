package hello.hellospring.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " +joinPoint.toString());
        try{
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish = start;
            System.out.println("END: " +joinPoint.toString() + " " +timeMs+ " ms");

        }
    }

}


/**
 * AOP가 필요한 경우
 * <p>
 * 1. 모든 메소드의 호출 시간이 측정하고 싶을 때 - 모든 메소드에 try-catch하여 측정하기에는 매우 비효율적
 * 2. 공통 관심사항에 대한 경우
 */