package com.tan.utils;

/**
 * 本类介绍
 * @apiNote  <p>
 *     功能简介：1. 实现了三种古典密码学思想，并且将其综合只对外暴露综合加密方法。
 *             2. 其中静态代码快加载安全字符集，{a~z,A~Z,0~9}
 *             3. 提供了成员变量盐<code>salt<code/>的默认值，
 *             当然你可以通过setter/getter方法进行注入和获取盐的值@^~^@
 *     <p/>
 *      <p>本类像模像样的对密码加密了，自我感觉很棒。<p/>
 *        <p>安全数据&对应ASCII：'0~ 9'=48 ~ 57'a～z'=97~122'A~Z'=65 ~ 90<p/>
 * @date 2020年9月21日23:58:41
 * @author Tab-Tan
 */
public class PasswordEncoder {

    private static final int a = 97;
    private static final int z = 122;
    private static final int A = 65;
    private static final int Z = 90;
    private static final int s0 = 48;
    private static final int s9 = 57;
    private int salt = (int) (Math.random()*1_0000_0000);
    private static int[] ints;

    //初始化安全数组
    static {
        ints = new int[62];
        int j = a;
        int k = s0;
        int m = A;
        for (int i = 0; i < 26; i++) {
            ints[i] = j;
            ints[i+26] = m;
            j++;
            m++;
            if (i<10){
                ints[i+52] = k;
                k++;
            }
        }
    }

    public void setSalt(String salt){ this.salt = Integer.parseInt(salt); }

    public void setSalt(int salt){ this.salt = salt; }

    public int getSalt(){ return this.salt; }

    /**
     * <p>第一个参数填需要加密的密码，第二个参数填一个int型的整数当盐。<p/>
     * @param password 需要加密的密码
     * @param salt 盐
     * @return 返回密文
     */
    public String passwordEncode(String password,int salt){
        return arrEncode(shiftEncode(password,salt)+replaceEncode(password,salt),salt);
    }

    /**
     * <p>参数填密码，返回一个加密后的密文。<p/>
     * @param password 需要加密的密码
     * @return 返回密文
     */
    public String passwordEncode(String password){
        return passwordEncode(password,salt);
    }

    /**
     * 古典方法一之移位
     * @param oldPswd 需要加密的密码
     * @param salt 盐
     * @return 返回密文
     */
    private String shiftEncode(String oldPswd,int salt){

        char[] chars = oldPswd.toCharArray();//拆分
        StringBuilder sb = new StringBuilder(oldPswd.length());//合并
        //如果是奇数，第奇数位前移，偶数位后移salt个单位，反之则反
        if (salt%2==1){
            for (int i = 0; i < chars.length; i++) {
                if (i%2==0){
                    chars[i] = (char) ints[Math.abs((chars[i]-salt))%ints.length];
                }else {
                    chars[i] = (char) ints[(chars[i]+salt)%ints.length];
                }
                sb.append(chars[i]);
            }
        }else {
            for (int i = 0; i < chars.length; i++) {
                if (i%2==0){
                    chars[i] = (char) ints[(chars[i]+salt)%ints.length];
                }else {
                    chars[i] = (char) ints[Math.abs((chars[i]-salt))%ints.length];
                }
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    //方法一重载
    @Deprecated
    private String shiftEncode(String oldPswd){
        return shiftEncode(oldPswd,salt);
    }

    //古典方法二之移型换位（换汤不换药）
    private String replaceEncode(String oldPswd,int salt){

        StringBuilder sb = new StringBuilder(oldPswd.length());

        char[] chars = oldPswd.toCharArray();
        //根据盐获取哈希值进行替换
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ints[hashValue(chars[i])%ints.length];
        }
        return sb.append(chars).toString();
    }
    //方法二重载
    @Deprecated
    private String replaceEncode(String oldPswd){
        return replaceEncode(oldPswd,salt);
    }
    //古典方法三之排列置换
    private String arrEncode(String oldPswd,int salt){
        StringBuilder sb = new StringBuilder(oldPswd.length());
        if (hashValue(salt)%2==0){
           return sb.append(oldPswd).reverse().toString();
        }else {
            return sb.append(oldPswd).toString();
        }
    }
    //方法三重写
    @Deprecated
    private String arrEncode(String oldPswd){
        return arrEncode(oldPswd,salt);
    }

    //小小哈希值，方便获取散列值
    private int hashValue(int val){
        String s = String.valueOf(val);
        return s.hashCode();

    }

//    public int hashCode() {
//        int h = hash;
//        if (h == 0 && value.length > 0) {
//            char val[] = value;
//
//            for (int i = 0; i < value.length; i++) {
//                h = 31 * h + val[i];
//            }
//            hash = h;
//        }
//        return h;
//    }
}
