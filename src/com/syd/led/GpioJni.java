package com.syd.led;

//import java.io.ByteArrayOutputStream;
//import java.nio.Buffer;
//import java.nio.ByteBuffer;


public class GpioJni {
	/**
	 * �������ܣ� ���豸
	 * ʹ��˵����
	 *     ���أ�0 �򿪳ɹ�? -1 ��������
	 */
	public native int gpioDeviceOpen();
	/**
	 * �������壺
	 * �������ܣ� �ر��豸
	 * ʹ��˵����
	 *     ���أ�0 �رճɹ�
	 */
	public native int gpioDeviceClose();
	/**
	 * �������ܣ�ȡ��ǰ״̬��
	 * ʹ��˵����
	 *     ���أ�>0 ��ӡ�豸�ɹ��򿪣�<=0 �豸δ��
	 */
	public native int getDeviceStatus();
	/**
	 * �������ܣ���ȡ��ֵ��
	 * ʹ��˵����
	 *     ���أ�>0 ��ӡ�豸�ɹ��򿪣�<=0 �豸δ��
	 */
	public native int readKey();
	
	public native int setLED(int value);
	
	static {
		System.loadLibrary("gpio-jni");
	}
}
