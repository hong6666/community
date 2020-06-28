package com.lhh.community.exception;

/**
 * @program: community
 * @Date: 2020/1/13 11:11
 * @Author: lhh
 * @Description: 自定义异常继承运行时异常
 * 1、使用自定义异常类就统一对外异常展示的方式。
 * 2、自定义异常可以在我们项目中某些特殊的业务逻辑时抛出异常，
 *    比如"中性".equals(sex)，性别等于中性时我们要抛出异常，而Java是不会有这种异常的。
 *    系统中有些错误是符合Java语法的，但不符合我们项目的业务逻辑。
 * 3、使用自定义异常继承相关的异常来抛出处理后的异常信息可以隐藏底层的异常，这样更安全，
 *    异常信息也更加的直观。自定义异常可以抛出我们自己想要抛出的信息，
 *    可以通过抛出的信息区分异常发生的位置，根据异常名我们就可以知道哪里有异常，
 *    根据异常提示信息进行程序修改。比如空指针异常NullPointException，
 *    我们可以抛出信息为“xxx为空”定位异常位置，而不用输出堆栈信息。
 */

public class CustomizeException extends RuntimeException{

    /**
     * 异常消息
     */
    private String message;

    /**
     * 异常码
     */
    private Integer code;

    /**
     * 通过自定义错误接口参数得到自定义异常的消息及异常码
     * @param errorCode ICustomizeErrorCode自定义错误
     */
    public CustomizeException(ICustomizeErrorCode errorCode)
    {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    public Integer getCode()
    {
        return code;
    }
}
