package com.bharosa.paytm;
import com.paytm.pg.merchant.CheckSumServiceHelper;
import java.util.Random;
import java.util.TreeMap;
/**
 * Created by gshah on 8/4/17.
 */
import com.paytm.pg.merchant.CheckSumServiceHelper;


public  class PaytmUtil {

    public final static String MID="WorldP64425807474247";
    public final static String MERCHANT_KEY="kbzk1DSbJiV_O3p5";
    public final static String INDUSTRY_TYPE_ID="Retail";
    public final static String CHANNEL_ID="WEB";
    public final static String WEBSITE="worldpressplg";
    public final static String PAYTM_URL="https://pguat.paytm.com/oltp-web/processTransaction";


    public String generatePayLoad() {
        TreeMap<String, String> paramMap = new TreeMap<String, String>();
        paramMap.put("MID", MID);
        paramMap.put("ORDER_ID", "\""+givenUsingPlainJava_whenGeneratingRandomIntegerBounded_thenCorrect()+"\"");
        paramMap.put("CUST_ID", "CUST00011");
        paramMap.put("INDUSTRY_TYPE_ID", INDUSTRY_TYPE_ID);
        paramMap.put("CHANNEL_ID", CHANNEL_ID);
        paramMap.put("TXN_AMOUNT", "1.00");
        paramMap.put("WEBSITE", WEBSITE);
        paramMap.put("EMAIL", "test@gmail.com");
        paramMap.put("MOBILE_NO", "9999999999");
        paramMap.put("CALLBACK_URL", "http://localhost:8080/");

        try {
            String checkSum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(MERCHANT_KEY, paramMap);
            paramMap.put("CHECKSUMHASH", checkSum);

            System.out.println("Paytm Payload: " + paramMap);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return paramMap.toString();
    }
    public int givenUsingPlainJava_whenGeneratingRandomIntegerBounded_thenCorrect() {
        int leftLimit = 1;
        int rightLimit = 10;
        return  leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));
    }


}
