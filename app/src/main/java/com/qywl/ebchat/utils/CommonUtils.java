package com.qywl.ebchat.utils;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.flyco.dialog.widget.NormalDialog;
import com.qywl.ebchat.R;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author: created by ${关云秀}
 * 时间：2018/12/26 9:37
 * 描述：
 */
public class CommonUtils {
    public static ZLoadingDialog getDialog(Context mContext, String text){
        ZLoadingDialog dialog = new ZLoadingDialog(mContext);
        dialog.setLoadingBuilder(Z_TYPE.SINGLE_CIRCLE)//设置类型
                .setLoadingColor(R.color.black)//颜色
                .setHintText(text)
                // .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.GRAY)  // 设置字体颜色
                .show();
        return dialog;
    }
    public static NormalDialog getSetDialog(Context context, String content){
        final NormalDialog dialog = new NormalDialog(context);
        dialog
                .content(content)
                .style(NormalDialog.STYLE_TWO)//
                .titleTextSize(18)//
                .contentTextColor(Color.parseColor("#000000"))//
                .dividerColor(Color.parseColor("#222222"))//
                .btnTextSize(15.5f, 15.5f)//
                .btnTextColor(Color.parseColor("#fd5739"), Color.parseColor("#fd5739"))//

                .widthScale(0.7f)//
                .btnTextColor(R.color.app_color)
                .isTitleShow(false)
        ;
        return dialog;
    }
 /*   public static boolean getNumInteger(Context context,String money){
        int moneyInt = Integer.valueOf(money);
        if (moneyInt % 100 != 0) {
            moneyInt = moneyInt + (100 - moneyInt % 100);

            MyToast.s(context.getString(R.string.exchange_str3));
            return false;
        }else {
            return true;
        }
    }*/

    /**
     * 交易撤单弹窗
     * @param context
     * @param content
     * @return
     */
    public static NormalDialog getCancleOrderDialog(Context context, String content){
        final NormalDialog dialog = new NormalDialog(context);
        dialog
                .content(content)
                .style(NormalDialog.STYLE_TWO)//
                .titleTextSize(18)//
                .title("提示")
                .titleTextColor(Color.parseColor("#242C3E"))
                .contentTextColor(Color.parseColor("#000000"))//
                .dividerColor(Color.parseColor("#222222"))//
                .btnTextSize(15.5f, 15.5f)//
                .btnTextColor(Color.parseColor("#242C3E"), Color.parseColor("#242C3E"))//
                .widthScale(0.7f)//
                .btnTextColor(R.color.app_color);
        return dialog;
    }


    /**
     * 交易页面完善信息弹窗
     * @param context
     * @param content
     * @return
     */
    public static NormalDialog getAuthSetDialog(Context context, String content){

        final NormalDialog dialog = new NormalDialog(context);
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog
                .content(content)
                .style(NormalDialog.STYLE_TWO)//
                .titleTextSize(18)//
                .contentTextColor(Color.parseColor("#000000"))//
                .dividerColor(Color.parseColor("#222222"))//
                .btnTextSize(15.5f, 15.5f)//
                .btnTextColor(Color.parseColor("#242C3E"), Color.parseColor("#242C3E"))//

                .widthScale(0.7f)//
                .btnTextColor(R.color.app_color)
                .isTitleShow(false)
        ;
        return dialog;
    }
    private static final int MIN_DELAY_TIME= 1000;  // 两次点击间隔不能少于1000ms
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }

    public static String getIPAddress(Context context) {
    NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
     if (info != null && info.isConnected()) {
         if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
              try {
                         //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                     for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                             NetworkInterface intf = en.nextElement();
                                 for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                                        InetAddress inetAddress = enumIpAddr.nextElement();
                                         if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                                return inetAddress.getHostAddress();
                                         }
                                 }
                             }
                         } catch (SocketException e) {
                             e.printStackTrace();
                         }


                    } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                         WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                         WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                         String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                        return ipAddress;
              }
            } else {
                 //当前无网络连接,请在设置中打开网络
            }
             return null;
         }


         /**
      * 将得到的int类型的IP转换为String类型
      *
      * @param ip
      * @return
      */
         public static String intIP2StringIP(int ip) {
             return (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + (ip >> 24 & 0xFF);
         }

}
