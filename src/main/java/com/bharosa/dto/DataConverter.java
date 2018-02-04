package com.bharosa.dto;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Destination;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.util.CollectionUtils;

import com.bharosa.model.CampaignData;
import com.bharosa.model.CampaignImageData;
import com.bharosa.model.CampaignSupportersData;
import com.bharosa.model.PaymentRequestData;
import com.bharosa.model.PaymentResponseData;
import com.bharosa.model.User;

public class DataConverter {
	
	public static Campaign convertCampaign(CampaignData source,
            Campaign target) {
        if (source == null) {
            return target;
        }
        ModelMapper mMapper = new ModelMapper();
        mMapper.map(source, target);
        if (!CollectionUtils.isEmpty(source.getCampaignSupportersData())) {
        	List<CampaignSupportersData> campaignSupporterDataList = source.getCampaignSupportersData();
        	List<CampaignSupporters> campaignSupporterList = new ArrayList<CampaignSupporters>(); 
        	campaignSupporterDataList.forEach(p -> campaignSupporterList.add(convertCampaignSupporters(p, new CampaignSupporters())));
        }
        if (!CollectionUtils.isEmpty(source.getCampaignImagesData())) {
        	List<CampaignImageData> campaignImagesDataList = source.getCampaignImagesData();
        	List<CampaignImage> campaignImageList = new ArrayList<CampaignImage>(); 
        	campaignImagesDataList.forEach(p -> campaignImageList.add(convertCampaignImage(p, new CampaignImage())));
        }

        if(source.getUser()!=null){
            target.setUser(convertUser(source.getUser(), new AppUser()));
        }
        return target;

    }
	public static CampaignImage convertCampaignImage(CampaignImageData source,
			CampaignImage target) {
        if (source == null) {
            return target;
        }
        ModelMapper mMapper = new ModelMapper();
        
        TypeMap<CampaignImageData, CampaignImage> typeMap = mMapper.createTypeMap(CampaignImageData.class, CampaignImage.class);
//        		typeMap.addMappings(mapper -> mapper.<Long>map(src -> src.getCampaignData().getId(), (dest, v) -> dest.setCampaignId(v)));
        		typeMap.addMapping(src -> src.getCampaignData().getId(), CampaignImage::setCampaignId);
//        		typeMap.addMapping(Source::getAge, Destination::setAgeString);
//        		typeMap.addMappings(mapper -> mapper.skip(CampaignImage::setImageData));

        mMapper.map(source, target);
        return target;

    }
	public static CampaignSupporters convertCampaignSupporters(CampaignSupportersData source,
			CampaignSupporters target) {
        if (source == null) {
            return target;
        }
        ModelMapper mMapper = new ModelMapper();
        mMapper.map(source, target);
        return target;

    }
	public static PaymentRequest convertPaymentRequestData(PaymentRequestData source,
			PaymentRequest target) {
        if (source == null) {
            return target;
        }
        ModelMapper mMapper = new ModelMapper();
        mMapper.map(source, target);
        return target;

    }
	public static PaymentResponse convertPaymentResponse(PaymentResponseData source,
			PaymentResponse target) {
        if (source == null) {
            return target;
        }
        ModelMapper mMapper = new ModelMapper();
        mMapper.map(source, target);
        return target;

    }
	public static AppUser convertUser(User source,
			AppUser target) {
        if (source == null) {
            return target;
        }
        ModelMapper mMapper = new ModelMapper();
        mMapper.map(source, target);
        return target;

    }


}
