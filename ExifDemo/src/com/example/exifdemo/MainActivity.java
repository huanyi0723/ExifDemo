package com.example.exifdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.media.ExifInterface;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.widget.TextView;

public class MainActivity extends Activity {

  ArrayList<String> fileNames = new ArrayList<String>(); //本地图片路径
  String imgPath;
  StringBuilder text = new StringBuilder();
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    

    fileNames.clear();
    Cursor cursor = getContentResolver().query(Media.EXTERNAL_CONTENT_URI, null, null, null, null);
    while (cursor.moveToNext()) {
      byte[] data = cursor.getBlob(cursor.getColumnIndex(Media.DATA)); //图片的保存位置的数据
      fileNames.add(new String(data, 0, data.length - 1));
    }
    
    if (fileNames.size() > 0) {
      imgPath = fileNames.get(4);
    }
    
    TextView show = (TextView)findViewById(R.id.show);
    //show.setText(imgPath);
    
    /*
     * 目前Android SDK定义的Tag有:
    
    public static final String TAG_ORIENTATION = "Orientation"; 方向
    public static final String TAG_DATETIME = "DateTime"; 时间日期
    public static final String TAG_MAKE = "Make"; 设备制造商
    public static final String TAG_MODEL = "Model"; 设备型号
    public static final String TAG_FLASH = "Flash"; 闪光灯
    public static final String TAG_IMAGE_WIDTH = "ImageWidth"; 图片宽
    public static final String TAG_IMAGE_LENGTH = "ImageLength"; 图片长
    public static final String TAG_GPS_LATITUDE = "GPSLatitude"; 纬度
    public static final String TAG_GPS_LONGITUDE = "GPSLongitude"; 经度
    public static final String TAG_GPS_LATITUDE_REF = "GPSLatitudeRef"; 纬度参考 
    public static final String TAG_GPS_LONGITUDE_REF = "GPSLongitudeRef"; 经度参考 
    public static final String TAG_EXPOSURE_TIME = "ExposureTime"; 曝光时间
    public static final String TAG_APERTURE = "FNumber"; 光圈值
    public static final String TAG_ISO = "ISOSpeedRatings";
    */ 

    try {
      //android读取图片EXIF信息
      ExifInterface exifInterface=new ExifInterface(imgPath);
      String Orientation=exifInterface.getAttribute(ExifInterface.TAG_ORIENTATION);
      String DateTime=exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
      String Make=exifInterface.getAttribute(ExifInterface.TAG_MAKE);
      String Model=exifInterface.getAttribute(ExifInterface.TAG_MODEL);
      String Flash=exifInterface.getAttribute(ExifInterface.TAG_FLASH);
      String ImageWidth=exifInterface.getAttribute(ExifInterface.TAG_IMAGE_WIDTH);
      String ImageLength=exifInterface.getAttribute(ExifInterface.TAG_IMAGE_LENGTH);
      String ExposureTime=exifInterface.getAttribute(ExifInterface.TAG_EXPOSURE_TIME);
      String FNumber=exifInterface.getAttribute(ExifInterface.TAG_APERTURE);
      String ISOSpeedRatings=exifInterface.getAttribute(ExifInterface.TAG_ISO);
      
      text.append("路径---" + imgPath + "\n");
      text.append("方向---" + Orientation + "\n");
      text.append("时间日期---" + DateTime + "\n");
      text.append("设备制造商---" + Make + "\n");
      text.append("设备型号---" + Model + "\n");
      text.append("闪光灯---" + Flash + "\n");
      text.append("图片宽---" + ImageWidth + "\n");
      text.append("图片长---" + ImageLength + "\n");
      text.append("曝光时间---" + ExposureTime + "\n");
      text.append("光圈值---" + FNumber + "\n");
      text.append("ISOSpeedRatings---" + ISOSpeedRatings + "\n");
      
      exifInterface.setAttribute("mytag", "test");
      text.append("mytag---" + exifInterface.getAttribute("mytag") + "\n");
      
    } catch (Exception e) {
      e.printStackTrace();
    }

    show.setText(text);
    
  }

  
  
  
   

  
}
