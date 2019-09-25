#include <jni.h>
#include <string>
#include <getopt.h>
#include <errno.h>
#include <fstream>
#include <unistd.h>
#include <iostream>

class working{
public:
    int r = 11;
    working(std::string st){
        int c = 0;
        if(st.find_first_not_of(' ') != std::string::npos)
        {
            c = 1;
        }
        if(st == "" || c == 0){
            r = 0;
        }else if(st == "," || st == "!" || st == "." || st == ";" || st == "\"" || st == "[" || st == "]" || st == "<"){
            r = 0;
        }else{
            r = 1;
        }
    }
};

extern "C" JNIEXPORT jstring JNICALL
Java_project_oop_engine_SearchAct_stringFromJNI(
        JNIEnv* env,
        jobject, jstring qu) {
     const char *pa = env->GetStringUTFChars(qu, NULL);
     std::string squ = pa;
    std::string res = "pass";
    std::string res2 = "fail";
     working w1(squ);

    if(w1.r == 1){
        return env->NewStringUTF(res.c_str());
    }else{
        return env->NewStringUTF(res2.c_str());
    }

}

