package com.bharosa.model;

import java.math.BigDecimal;

public class PaytmRequestModel {


    public final static String MID="WorldP64425807474247";
    public final static String MERCHANT_KEY="kbzk1DSbJiV_O3p5";
    public final static String INDUSTRY_TYPE_ID="Retail";
    public final static String CHANNEL_ID="WEB";
    public final static String WEBSITE="worldpressplg";
    public final static String PAYTM_URL="https://pguat.paytm.com/oltp-web/processTransaction";

	
    public long CAMPAIGN_ID;
    public String REQUEST_TYPE;
//	private String MID;
    public long ORDER_ID; 
    public BigDecimal TXN_AMOUNT; 
//	private String CHANNEL_ID; 
//	private String INDUSTRY_TYPE_ID; 
//	private String WEBSITE;
    public String CHECKSUMHASH; 
    public String MOBILE_NO; 
    public String CUST_ID;
	public String EMAIL;
	public String  CALLBACK_URL;
	
	public String getREQUEST_TYPE() {
		return REQUEST_TYPE;
	}
	public void setREQUEST_TYPE(String rEQUEST_TYPE) {
		REQUEST_TYPE = rEQUEST_TYPE;
	}
	public String getMID() {
		return MID;
	}
	public long getORDER_ID() {
		return ORDER_ID;
	}
	public void setORDER_ID(long oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}
	public BigDecimal getTXN_AMOUNT() {
		return TXN_AMOUNT;
	}
	public void setTXN_AMOUNT(BigDecimal tXN_AMOUNT) {
		TXN_AMOUNT = tXN_AMOUNT;
	}
	public String getCHANNEL_ID() {
		return CHANNEL_ID;
	}
	public String getINDUSTRY_TYPE_ID() {
		return INDUSTRY_TYPE_ID;
	}
	public String getWEBSITE() {
		return WEBSITE;
	}
	public String getCHECKSUMHASH() {
		return CHECKSUMHASH;
	}
	public void setCHECKSUMHASH(String cHECKSUMHASH) {
		CHECKSUMHASH = cHECKSUMHASH;
	}
	public String getMOBILE_NO() {
		return MOBILE_NO;
	}
	public void setMOBILE_NO(String mOBILE_NO) {
		MOBILE_NO = mOBILE_NO;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getCUST_ID() {
		return CUST_ID;
	}
	public void setCUST_ID(String cUST_ID) {
		CUST_ID = cUST_ID;
	}
	public String getCALLBACK_URL() {
		return CALLBACK_URL;
	}
	public void setCALLBACK_URL(String cALLBACK_URL) {
		CALLBACK_URL = cALLBACK_URL;
	}
	public long getCAMPAIGN_ID() {
		return CAMPAIGN_ID;
	}
	public void setCAMPAIGN_ID(long cAMPAIGN_ID) {
		CAMPAIGN_ID = cAMPAIGN_ID;
	}
	
	
}
