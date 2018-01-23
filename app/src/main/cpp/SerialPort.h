#ifndef MOBITRONICS_SERIALPORT_H
#define MOBITRONICS_SERIALPORT_H

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     android_serialport_api_SerialPort
 * Method:    open
 * Signature: (Ljava/lang/String;II)Ljava/io/FileDescriptor;
 */
JNIEXPORT jobject JNICALL Java_com_vcc_vicente_mobitronics_SerialPort_open
        (JNIEnv *, jclass, jstring, jint, jint);

/*
 * Class:     android_serialport_api_SerialPort
 * Method:    close
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_vcc_vicente_mobitronics_SerialPort_close
(JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif

#endif //MOBITRONICS_SERIALPORT_H
