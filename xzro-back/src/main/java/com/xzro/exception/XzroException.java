package com.xzro.exception;

/**
 * ClassName: XzroException
 * Package: com.xzro.Exception
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/17 15:13
 * @Version 1.0
 */
public class XzroException extends Exception{
    static final long serialVersionUID = -3327516932224329948L;
    public XzroException() {
        super();
    }
    public XzroException(String message) {
        super(message);
    }
}
