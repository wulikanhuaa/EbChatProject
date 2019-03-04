package com.qywl.ebchat.httpRequest;

import android.content.Context;


import com.qywl.ebchat.base.BaseApplication;
import com.qywl.ebchat.config.SharedConstants;
import com.qywl.ebchat.utils.DeviceUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: created by ${关云秀}
 * 时间：2018/12/25 10:52
 * 描述：
 */
public class Netparameter {
    public static Map initParameter(Context mContext){
        Map<String,String> maps = new HashMap<>();
        maps.put("token",BaseApplication.mSharedPrefUtil.getString(SharedConstants.TOKEN,""));
        maps.put("client_type","android");
        maps.put("unique_id",DeviceUtils.getUniqueId(mContext));
        maps.put("uid",BaseApplication.mSharedPrefUtil.getInt(SharedConstants.UID,0)+"");
        maps.put("ab",BaseApplication.mSharedPrefConfigUtil.getString(SharedConstants.AB,"")+"");
        return maps;
    }
}

