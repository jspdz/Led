LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := gpio-jni
LOCAL_SRC_FILES := gpio-jni.c

include $(BUILD_SHARED_LIBRARY)
