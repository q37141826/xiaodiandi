package com.qixiu.qixiu.utils.bankutil;

public class BankCheckUtil {
    public static String check(String cardNumber) {
        String result;
        cardNumber = cardNumber.replaceAll(" ", "");
        //位数校验
        if (cardNumber.length() == 16 || cardNumber.length() == 19) {
        } else {
            return "卡号位数无效";
        }
        //校验
        if (CheckBankCard.isBankCard(cardNumber) == true) {
        } else {
            return "卡号校验失败";
        }
        //判断是不是银联，老的卡号都是服务电话开头，比如农行是95599
        // http://cn.unionpay.com/cardCommend/gyylbzk/gaishu/file_6330036.html
        if (cardNumber.startsWith("62")) {
            result = ("中国银联卡");
        } else {
            return "国外的卡，或者旧银行卡，暂时没有收录";
        }
        String name = BankCardBin.getNameOfBank(cardNumber.substring(0, 6), 0);// 获取银行卡的信息
        result = name;
        //归属地每个银行不一样，这里只收录农行少数地区代码
        if (name.startsWith("农业银行") == true) {
            if (cardNumber.length() == 19) {
                //4大银行的16位是信用卡
                //注意：信用卡没有开户地之说，归总行信用卡部。唯独中国银行的长城信用卡有我的地盘这个属性
                name = ABCBankAddr.getAddrOfBank(cardNumber.substring(6, 10));
                result = ("开户地:" + name);
            }
        }
        return result;
    }

    public static boolean isBankCard(String cardNum) {
        return CheckBankCard.isBankCard(cardNum);
    }
}