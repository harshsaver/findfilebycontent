#include <jni.h>
#include <string>
#include <getopt.h>
#include <errno.h>
#include <fstream>
#include <unistd.h>
#include <iostream>
#include <dirent.h>

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

extern "C" JNIEXPORT jstring JNICALL
Java_project_oop_engine_SearchAct_fileFromJNI(
        JNIEnv* env,
        jobject, jstring ff) {
    const char *pa = env->GetStringUTFChars(ff, NULL);
    std::string sfname = pa;
    std::string fpath = "/storage/emulated/0/Download/",collection="",cname,fname="",stat = "null";
    DIR *dpdf;
    struct dirent *epdf;
    dpdf = opendir("/storage/emulated/0/Download/");

    if (auto dir = opendir("/storage/emulated/0/Download/")) {
        while (auto f = readdir(dir)) {
            if (!f->d_name || f->d_name[0] == '.')
                continue; // Skip everything that starts with a dot

            collection = collection + "," + f->d_name;
            cname = f->d_name;
            if (cname.find(sfname) != std::string::npos) {
                fname = cname;
                stat = "pass";
            }
        }
        closedir(dir);
    }
    if(stat == "pass"){
        closedir(dpdf);
        std::ifstream is;
        is.open(fpath+fname);
        std::string l_1,final = "";
        int ln = 1;
        while(std::getline(is, l_1)) {
            std::string s = std::to_string(ln);
            final = l_1 +"\n"+s+ l_1;
            ln++;
        }
        return env->NewStringUTF(final.c_str());
    }else{
        return env->NewStringUTF(stat.c_str());
    }

}