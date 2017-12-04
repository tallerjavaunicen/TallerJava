/*
 * edu_isistan_jni_CLib.cpp
 *
 *  Created on: Nov 24, 2017
 *      Author: knife
 */
#include <jni.h>
#include <stdio.h>
#include "edu_isistan_jni_CLib.h"

JNIEXPORT jdouble JNICALL Java_edu_isistan_jni_CLib_dotMultiplication
  (JNIEnv *env, jobject thisObj, jdoubleArray a, jdoubleArray b) {
	jdouble res = 0;
	jsize len = env->GetArrayLength(a);
	if (len != env->GetArrayLength(b)) {
		return env->ThrowNew(env->FindClass("java/lang/IndexOutOfBoundsException"),
				"Falló feo");
	}
	jboolean isCopya = JNI_FALSE;
	jboolean isCopyb = JNI_FALSE;
	jdouble *adata = env->GetDoubleArrayElements(a, &isCopya);
	jdouble *bdata = env->GetDoubleArrayElements(b, &isCopyb);
	for(int i = 0; i < len; i++)
		res += adata[i] * bdata[i];
	if(isCopya)
		env->ReleaseDoubleArrayElements(a, adata, 0);
	if(isCopyb)
			env->ReleaseDoubleArrayElements(b, bdata, 0);
	return res;
}
