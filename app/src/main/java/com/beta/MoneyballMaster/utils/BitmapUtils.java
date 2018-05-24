package com.beta.MoneyballMaster.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;


/**
 * Created by yas on 2018/3/21.
 */

public class BitmapUtils {

    public static Bitmap decodeSampledBitmapFromResouce(Resources res, int resId,
                                                 int reqWidth, int reqHeight){
        //First decode with inJustBounds=true  to check dimensions
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(res,resId,options);

        //Calculate inSampleSize
        options.inSampleSize=calculateInSampleSize(options,reqWidth,reqHeight);

        //Decode bitmap with inSampleSize set
        return BitmapFactory.decodeResource(res,resId,options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
                                            int reqHeight) {
        final int height=options.outHeight;
        final int width=options.outWidth;
        int inSampleSize=1;
        if (height>reqHeight||width>reqWidth){
            final int halfHeight=height/2;
            final int halfWidth=width/2;
            while((halfHeight/inSampleSize)>=reqHeight&&(halfWidth/inSampleSize)>=reqWidth){
                inSampleSize*=2;
            }
        }
        return inSampleSize;
    }

    public static String encodeBase64File(String path) throws Exception {
        File  file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.encodeToString(buffer, Base64.DEFAULT);
    }
}
