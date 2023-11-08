package ru.liga.orderservice.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Date;

@Configuration
@Aspect
public class OrderServiceLoggingAspect {

    private final Logger logger = LogManager.getLogger(OrderServiceLoggingAspect.class);

    @Pointcut("execution(* ru.liga.orderservice.service.OrderService.getOrderById(..))")
    public void getOrderById() {
    }

    @Pointcut("execution(* ru.liga.orderservice.service.OrderService.getAllOrders(..))")
    public void getAllOrders() {
    }

    @Pointcut("execution(* ru.liga.orderservice.service.OrderService.createOrder(..))")
    public void createOrder() {
    }

    @Pointcut("execution(* ru.liga.orderservice.service.OrderService.paymentOrder(..))")
    public void paymentOrder() {
    }

    @Pointcut("execution(* ru.liga.orderservice.service.OrderService.acceptOrder(..))")
    public void acceptOrder() {
    }

    @Pointcut("execution(* ru.liga.orderservice.service.OrderService.completeOrder(..))")
    public void completeOrder() {
    }

    @Pointcut("execution(* ru.liga.orderservice.service.OrderService.cancelOrder(..))")
    public void cancelOrder() {
    }

    @Before("getOrderById() || getAllOrders() || createOrder() || paymentOrder() || acceptOrder() || completeOrder() || cancelOrder()")
    public void logBeforeCallingMethod(JoinPoint jointPoint) {
        logger.info("[" + new Date() + "] Метод "
                + jointPoint.getSignature().getName()
                + " вызывается. В него передаются данные: "
                + Arrays.toString(jointPoint.getArgs()));
    }

    @AfterReturning(pointcut = "getOrderById() || getAllOrders() || createOrder() || paymentOrder() || acceptOrder() || completeOrder() || cancelOrder()", returning = "result")
    public void logAfterSuccessfulEnding(JoinPoint joinPoint, Object result) {
        logger.info("[" + new Date() + "] Метод "
                + joinPoint.getSignature().getName()
                + " завершился успешно и вернул " + result.toString());
    }

    @AfterThrowing(pointcut = "getOrderById() || getAllOrders() || createOrder() || paymentOrder() || acceptOrder() || completeOrder() || cancelOrder()", throwing = "exception")
    public void logAfterExceptionEnding(JoinPoint joinPoint, Throwable exception) {
        logger.info("[" + new Date()
                + "] Метод " + joinPoint.getSignature().getName() + " завершился неудачно, вызвав ошибку "
                + exception.getClass().getName()
                + " с сообщением " + exception.getMessage()
                + " и StackTrace " + Arrays.toString(exception.getStackTrace())
        );
    }
}
