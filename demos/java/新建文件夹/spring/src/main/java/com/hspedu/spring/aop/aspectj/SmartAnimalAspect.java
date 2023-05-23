package com.hspedu.spring.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author 韩顺平
 * @version 1.0
 * 切面类 , 类似于我们以前自己写的MyProxyProvider,但是功能强大很多
 *
 */
@Order(value = 2)//表示该切面类执行的顺序, value的值越小, 优先级越高
@Aspect //表示是一个切面类[底层切面编程的支撑(动态代理+反射+动态绑定...)]
@Component //会注入SmartAnimalAspect到容器
public class SmartAnimalAspect {


    //定义一个切入点, 在后面使用时可以直接引用, 提高了复用性
    @Pointcut(value = "execution(public float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float, float)))")
    public void myPointCut() {
    }

    //希望将f1方法切入到SmartDog-getSum前执行-前置通知

    /**
     * 老师解读
     * 1. @Before 表示前置通知:即在我们的目标对象执行方法前执行
     * 2. value = "execution(public float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float, float)
     * 指定切入到哪个类的哪个方法  形式是: 访问修饰符 返回类型 全类名.方法名(形参列表)
     * 3. showBeginLog方法可以理解成就是一个切入方法, 这个方法名是可以程序员指定  比如:showBeginLog
     * 4. JoinPoint joinPoint 在底层执行时，由AspectJ切面框架， 会给该切入方法传入 joinPoint对象
     * , 通过该方法，程序员可以获取到 相关信息
     *
     * @param joinPoint
     */
    //@Before(value = "execution(public float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float, float))")
    //这里我们使用定义好的切入点
    @Before(value = "myPointCut()")
    public void showBeginLog(JoinPoint joinPoint) {
        //通过连接点对象joinPoint 可以获取方法签名
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showBeginLog()[使用的myPointCut()]-方法执行前-日志-方法名-" + signature.getName() + "-参数 "
                + Arrays.asList(joinPoint.getArgs()));
    }

    //返回通知：即把showSuccessEndLog方法切入到目标对象方法正常执行完毕后的地方
    //老韩解读
    //1. 如果我们希望把目标方法执行的结果，返回给切入方法
    //2. 可以再 @AfterReturning 增加属性 , 比如 returning = "res"
    //3. 同时在切入方法增加 Object res
    //4. 注意: returning = "res" 和 Object res 的 res名字一致
    //@AfterReturning(value = "execution(public float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float, float))", returning = "res")
    //使用切入点
    @AfterReturning(value = "myPointCut()", returning = "res")
    public void showSuccessEndLog(JoinPoint joinPoint, Object res) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showSuccessEndLog()-方法执行正常结束-日志-方法名-" + signature.getName() + " 返回的结果是=" + res);
    }


    //异常通知：即把showExceptionLog方法切入到目标对象方法执行发生异常的的catch{}
    //@AfterThrowing(value = "execution(public float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float, float))", throwing = "throwable")
    //直接使用切入点表达式
    @AfterThrowing(value = "myPointCut()", throwing = "throwable")
    public void showExceptionLog(JoinPoint joinPoint, Throwable throwable) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showExceptionLog()-方法执行异常-日志-方法名-" + signature.getName() + " 异常信息=" + throwable);
    }

    //最终通知：即把showFinallyEndLog方法切入到目标方法执行后(不管是否发生异常,都要执行 finally{})
    //@After(value = "execution(public float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float, float))")
    //直接使用切入点
    @After(value = "myPointCut()")
    public void showFinallyEndLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showFinallyEndLog()-方法最终执行完毕-日志-方法名-" + signature.getName());
    }

    //新的前置通知
    //@Before(value = "execution(public void com.hspedu.spring.aop.aspectj.Phone.work()) || execution(public void com.hspedu.spring.aop.aspectj.Camera.work())")
    //public void hi(JoinPoint joinPoint) {
    //    Signature signature = joinPoint.getSignature();
    //    System.out.println("切面类的hi()-执行的目标方法-" + signature.getName());
    //}

    //切入表达式也可以指向接口的方法, 这时切入表达式会对实现了接口的类/对象生效
    //比如下面我们是对UsbInterface 切入，那么对实现类Phone 和 Camera对象都作用了
    @Before(value = "execution(public void com.hspedu.spring.aop.aspectj.UsbInterface.work())")
    public void hi(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("切面类的hi()-执行的目标方法-" + signature.getName());
    }


    //给Car配置一个前置通知
    @Before(value = "execution(public void Car.run())")
    public void ok1(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
        System.out.println("切面类的ok1()-执行的目标方法-" + signature.getName());

    }

    //返回通知
    @AfterReturning(value = "execution(public void Car.run())")
    public void ok2(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
        System.out.println("切面类的ok2()-执行的目标方法-" + signature.getName());

    }

    //异常通知
    @AfterThrowing(value = "execution(public void Car.run())")
    public void ok3(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
        System.out.println("切面类的ok3()-执行的目标方法-" + signature.getName());

    }

    //后置通知
    @After(value = "execution(public void Car.run())")
    public void ok4(JoinPoint joinPoint) {


        Signature signature = joinPoint.getSignature();
        System.out.println("切面类的ok4()-执行的目标方法-" + signature.getName());
        //演示一下JoinPoint常用的方法.
        joinPoint.getSignature().getName(); // 获取目标方法名
        joinPoint.getSignature().getDeclaringType().getSimpleName(); // 获取目标方法所属类的简单类名
        joinPoint.getSignature().getDeclaringTypeName(); // 获取目标方法所属类的类名
        joinPoint.getSignature().getModifiers(); // 获取目标方法声明类型(public、private、protected)
        Object[] args = joinPoint.getArgs(); // 获取传入目标方法的参数，返回一个数组
        joinPoint.getTarget(); // 获取被代理的对象
        joinPoint.getThis(); // 获取代理对象自己
    }
}
