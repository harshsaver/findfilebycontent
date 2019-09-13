#include <jni.h>
#include <string>
#include <getopt.h>
#include <errno.h>
#include <fstream>
#include <unistd.h>

 extern "C" JNIEXPORT jstring JNICALL
Java_project_oop_engine_SearchAct_stringFromJNI(
        JNIEnv* env,
        jobject, jstring qu) {
     const char *pa = env->GetStringUTFChars(qu, NULL);
     std::string res = "pass";
     std::string res2 = "fail";
     std::string squ = pa;
     if(squ == "" || squ == " "){
         return env->NewStringUTF(res2.c_str());
     }else{
         return env->NewStringUTF(res.c_str());
     }

}

