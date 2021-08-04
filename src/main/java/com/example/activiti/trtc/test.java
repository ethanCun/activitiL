package com.example.activiti.trtc;

public class test {

    public static void main(String[] args) {

        TLSSigAPIv2 api = new TLSSigAPIv2(1400405629, "7988ddee216dffbe8cf7afc2b1a3a0e5a109f1883b916687de4e6aef89c9a519");
        System.out.print(api.genUserSig("xiaojun", 180*86400));
    }
}
