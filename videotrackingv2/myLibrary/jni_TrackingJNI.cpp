#include "jni_TrackingJNI.h"
#include <string.h>
#include <iostream>
#include <fstream>

#include "tracking.h"
using namespace std;

JNIEXPORT jboolean JNICALL Java_jni_TrackingJNI_trackingMethod
	(JNIEnv *env, jobject obj, jstring rawFileName, jint x1, jint y1, jint x2, jint y2, jstring outputVideoName) {
		printf("\n**** C++ output ****");
		printf("\nCoordinates:");
		printf("\nx1: %d", x1);
		printf("\ny1: %d", y1);
		printf("\nx2: %d", x2);
		printf("\ny2: %d", y2);

		const char *str1 = env->GetStringUTFChars(rawFileName, 0);
		char cap1[256];
		strcpy(cap1, str1);
		env->ReleaseStringUTFChars(rawFileName, str1);
		printf("\n%s", str1);

		const char *str2 = env->GetStringUTFChars(outputVideoName, 0);
		char cap2[256];
		strcpy(cap2, str2);
		env->ReleaseStringUTFChars(outputVideoName, str2);
		printf("\n%s", str2);

		Tracking track(str1, x1, y1, x2, y2);
		if (str2 == NULL || strlen(str2) == 0)
		{
			track.setExport(false);
			printf("Do not output");
		}
		else
		{
			track.setExport(true);
			track.setExportVideoPath(str2);
			printf("Do output");
		}

		track.start(true);
		printf("\nfinished");
		//*******
		//write your program here!!!!!

		//*******

		return true;
}
