#include <jni.h>
#include <string>
#include <getopt.h>
#include <errno.h>
#include <fstream>
#include <unistd.h>



extern "C" JNIEXPORT jstring JNICALL Java_project_oop_engine_SearchAct_ndkopenfile
        (JNIEnv *env, jobject,jstring j)
{
char myStr[20];
//std::string myStr;
    const char *path = env->GetStringUTFChars(j, NULL);
FILE* fp = fopen("/storage/emulated/0/Download/ex.txt","r");
//if(fp!=NULL)
//{
fgets(myStr,20,fp);
//fflush(fp);
//fclose(fp);
return env->NewStringUTF(myStr);
//}
//else
//{
//fclose(fp);
//return env->NewStringUTF("Error opening file!");
//}
}

/**
 extern "C" JNIEXPORT jstring JNICALL
Java_project_oop_engine_SearchAct_stringFromJNI(
        JNIEnv* env,
        jobject /* this ) {
std::string hello = "Hello from C++";
std::string pathFromJava = "";
const char *path = (pathFromJava + "/storage/emulated/0/Download/someFileName.txt").c_str();
std::ofstream stream;
try {

stream.open(path);

if(stream.good()) {
stream << "test 367";

stream.close();
}
}
catch (std::ios_base::failure& e) {
//std::cout << "Error no: " << strerror(errno);
}
return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_project_oop_engine_SearchAct_setconfiguration(JNIEnv * env, jclass obj, jstring path)
{
    //convert your string into std::string.
    const char *nativeString = env->GetStringUTFChars(path, 0);

    //make here your fopen.
    fopen(nativeString,"r");
    std::string j = "hello";
    //getline(nativeString,j);
    //j = fread(nativeString);
    return env->NewStringUTF(j.c_str());
}
 **/