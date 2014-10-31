package com.syd.led;

//import java.io.ByteArrayOutputStream;
//import java.nio.Buffer;
//import java.nio.ByteBuffer;


public class GpioJni {
	/**
	 * 函数功能： 打开设备
	 * 使用说明：
	 *     返回：0 打开成功? -1 其它错误
	 */
	public native int gpioDeviceOpen();
	/**
	 * 函数定义：
	 * 函数功能： 关闭设备
	 * 使用说明：
	 *     返回：0 关闭成功
	 */
	public native int gpioDeviceClose();
	/**
	 * 函数功能：取当前状态。
	 * 使用说明：
	 *     返回：>0 打印设备成功打开，<=0 设备未打开
	 */
	public native int getDeviceStatus();
	/**
	 * 函数功能：读取键值。
	 * 使用说明：
	 *     返回：>0 打印设备成功打开，<=0 设备未打开
	 */
	public native int readKey();
	
	public native int setLED(int value);
	
	static {
		System.loadLibrary("gpio-jni");
	}
}
