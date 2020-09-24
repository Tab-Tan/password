package com.tan.pswdTest;

import com.tan.utils.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PswdTest {

    @Test
    public void test(){

//        int salt = (int) (Math.random() * 1_0000_0000);
//        System.out.println("盐："+salt);
//        PasswordEncoder pe = new PasswordEncoder();
//        pe.setSalt(salt);
//        String tan666 = pe.arrEncode("tan666", salt);
//        System.out.println("arrEncode密码："+tan666);
//        String tan6661 = pe.replaceEncode("tan666", salt);
//        System.out.println("replaceEncode密码："+tan6661);
//        String tan6662 = pe.shiftEncode("tan666", salt);
//        System.out.println("shiftEncode密码："+tan6662);


//        盐：57323202
//        2yWvvv
//                OnI4O4666nat2yWvvv2yWvvv

        int salt = 57323202;
        System.out.println("盐："+salt);
        PasswordEncoder pe = new PasswordEncoder();
//        pe.setSalt(salt);
        String tan666 = pe.passwordEncode("tan666");
        System.out.println("明文:"+"tan666"+" 密文:"+tan666);

    }
}
