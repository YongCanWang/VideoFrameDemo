package cn.mapscloud.videoframe.Utils;

import android.Manifest;
import android.content.Context;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by wangyongcan on 2018/6/22.
 */

public class PermissionUtils {


    public static void applyPermission(Context context) {

        //所要申请的权限
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

        if (EasyPermissions.hasPermissions(context, perms)) {//检查是否获取该权限

        } else {
            //第二个参数是被拒绝后再次申请该权限的解释
            //第三个参数是请求码
            //第四个参数是要申请的权限
            EasyPermissions.requestPermissions(context, "必要的权限", 0, perms);
        }

    }
}
