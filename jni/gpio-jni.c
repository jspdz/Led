#include <string.h>
#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <fcntl.h>
#include <sys/ioctl.h>

#define DEVICE_NAME "/dev/gpiod11"
#define GD11_LED_CMD	4
#define GD11_LED_STATE	0
#define GD11_LED_ON		0
#define GD11_LED_OFF	1


#define LOGI(fmt, args...) __android_log_print(ANDROID_LOG_INFO,  TAG, fmt, ##args)
#define LOGD(fmt, args...) __android_log_print(ANDROID_LOG_DEBUG, TAG, fmt, ##args)
#define LOGE(fmt, args...) __android_log_print(ANDROID_LOG_ERROR, TAG, fmt, ##args)

static int fd = -1;


/**
 * 函数功能： 打开设备
 * 使用说明：
 *     返回：0 打开成功? -1 其它错误
 */
jint Java_com_syd_led_GpioJni_gpioDeviceOpen( JNIEnv* env,jobject thiz)
{
    fd = open(DEVICE_NAME, O_RDWR);
    if(fd == -1)
    {
        printf("==Failed to open device %s.==\n", DEVICE_NAME);
        return -1;
    }
    //ioctl(fd,MEM_IOC_OPEN,NULL);
    return 0;
}
/**
 * 函数定义：
 * 函数功能： 关闭设备
 * 使用说明：
 *     返回：0 关闭成功
 */
jint Java_com_syd_led_GpioJni_gpioDeviceClose( JNIEnv* env,jobject thiz)
{
    int ret;

    if(fd == -1)
    	return -1;
    else
    {
        //ioctl(fd,MEM_IOC_CLOSE,NULL);
    	ret = close(fd);
    	if(ret < 0)
    	{
    		printf("Close printer failed!\n");
    	}
    	else
    		fd = -1;
    	return ret;
    }
}

/**
 * 函数功能：取当前状态。
 * 使用说明：
 *     返回：>0 设备成功打开，<=0 设备未打开
 */
jint Java_com_syd_led_GpioJni_getDeviceStatus( JNIEnv* env,jobject thiz)
{
    if(fd <= 0)
        return -1;
    return fd;
}
/**
 * 函数定义：
 * 函数功能： 读取键值
 * 使用说明：
 *     返回：0 关闭成功
 */
jint Java_com_syd_led_GpioJni_readKey( JNIEnv* env,jobject thiz)
{
    int ret;

    if(fd == -1)
    	return -1;
    else
    {
    	int  val = -2;
    	int  size = 0;

    	size=read(fd,&val,sizeof(val));
    	val = val & 1;
    	ret = size;
    	return val;
    }
}

jint Java_com_syd_led_GpioJni_setLED( JNIEnv* env,jobject thiz,jint value)
{
    int tmp;
    /*
     * struct gpio_data
	{
		unsigned char  cmd;
		unsigned char  status;
		unsigned short value;
	};
     *
     */
    int led;
    int led_cmd;
    int led_status;
    int led_value;


    // led cmd =4; state=0;value=0/1;
    if(fd == -1){
    	return -1;
    }
    else{
    	led_cmd = GD11_LED_CMD;
    	led_status = GD11_LED_STATE;
    	if(value){
    		led_value = GD11_LED_ON;
    	}
    	else{
    		led_value = GD11_LED_OFF;
    	}
    	led = ((led_cmd)&0xff)|
    			((led_status<<8)&0xff00)|
    			((led_value<<16)&0xffff0000);
    	write(fd, &led, sizeof(int));

    	return 0;
    }
}
