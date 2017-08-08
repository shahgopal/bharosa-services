package com.bharosa.paytm;
import com.bharosa.model.Campaign;
import com.bharosa.model.PaymentRequest;
import com.bharosa.model.PaytmRequestModel;
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


    public static TreeMap<String, String> generatePayLoad(PaytmRequestModel paytmRequestModel) {
    	
    	System.out.println(paytmRequestModel.getCAMPAIGN_ID());
    	System.out.println(paytmRequestModel.getCUST_ID());
    	System.out.println(paytmRequestModel.getTXN_AMOUNT());
    	System.out.println(paytmRequestModel.getCALLBACK_URL());
        TreeMap<String, String> paramMap = new TreeMap<String, String>();
        paramMap.put("MID", MID);
        paramMap.put("ORDER_ID", "\""+givenUsingPlainJava_whenGeneratingRandomIntegerBounded_thenCorrect()+"\"");
        paramMap.put("CUST_ID", paytmRequestModel.getCUST_ID() != null?paytmRequestModel.getCUST_ID():"ANYNONYMUS");
        paramMap.put("INDUSTRY_TYPE_ID", INDUSTRY_TYPE_ID);
        paramMap.put("CHANNEL_ID", CHANNEL_ID);
        paramMap.put("TXN_AMOUNT", paytmRequestModel.getTXN_AMOUNT().toPlainString());
        paramMap.put("WEBSITE", WEBSITE);
        if(paytmRequestModel.getEMAIL() != null)
        paramMap.put("EMAIL", paytmRequestModel.getEMAIL());
        if (paytmRequestModel.getMOBILE_NO()!= null)
        paramMap.put("MOBILE_NO",paytmRequestModel.getMOBILE_NO());
        paramMap.put("CALLBACK_URL", paytmRequestModel.getCALLBACK_URL());
        
        try {
            String checkSum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(MERCHANT_KEY, paramMap);
            paramMap.put("CHECKSUMHASH", checkSum);

            System.out.println("Paytm Payload: " + paramMap);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        return paramMap;
    }
    public static long givenUsingPlainJava_whenGeneratingRandomIntegerBounded_thenCorrect() {
        int leftLimit = 1;
        int rightLimit = 10;
        return  leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));
    }

    public static PaymentRequest paymentRequestBuilder(PaytmRequestModel paytmRequestModel)
    {
    	PaymentRequest pr = new PaymentRequest();
    	pr.setChecksumHash(paytmRequestModel.getCHECKSUMHASH());
    	pr.setCustomerId(paytmRequestModel.getCUST_ID());
    	pr.setEmailId(paytmRequestModel.getEMAIL());
    	pr.setMobileNo(paytmRequestModel.getMOBILE_NO());
    	pr.setOrderId(paytmRequestModel.getORDER_ID());
    	pr.setTxnAmount(paytmRequestModel.getTXN_AMOUNT());
    	pr.setEmailId(paytmRequestModel.getEMAIL());
    	
    	return pr;
    }

}
