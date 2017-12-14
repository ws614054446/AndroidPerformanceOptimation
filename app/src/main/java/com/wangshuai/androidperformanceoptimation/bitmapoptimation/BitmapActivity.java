package com.wangshuai.androidperformanceoptimation.bitmapoptimation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wangshuai.androidperformanceoptimation.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class BitmapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

    }

    /**
     * 质量压缩：通过算法扣掉了图片中的一些某个些点附近相近的像素，达到降低质量文件大小的目的
     *
     * 他只能实现对file的影响，对加载这个图片出来的bitmap内存是无法节省的，还是那么大
     * 因为bitmap在内存中的大小是按照像素计算的，也就是width*height，对于质量压缩，并不会改变图片的真实的像素
     *
     * 使用场景：将图片压缩后保存到本地，或者将图片上传的服务器。
     */
    private void qualityCompress(){
        File imgFile = new File(Environment.getExternalStorageDirectory(),"img.png");
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(),options);

        compressImageToFile(bitmap,new File("","质量压缩.jpeg"));
    }

    /**
     * 尺寸压缩:
     * 通过减少单位尺寸的像素值，真正意义上降低像素
     * 使用场景：缓存缩略图，头像
     * @param bitmap
     * @param file
     */
    private void compressImageToFileBySize(Bitmap bitmap,File file){
        //压缩尺寸倍数：值越大，图片的尺寸就越小
        int ratio = 4;
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth()/ratio,bitmap.getHeight()/ratio,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(result);
        RectF rect = new RectF(0,0,bitmap.getWidth()/ratio,bitmap.getHeight()/ratio);
        canvas.drawBitmap(bitmap,null,rect,null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        result.compress(Bitmap.CompressFormat.JPEG,100,baos);

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(baos.toByteArray());
            outputStream.flush();
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 采样率压缩：
     * 改变了图片的像素，他是通过先读取图片的边，然后在自己设定图片的边，然后根据设定，读取图片的像素。在读取的时候，并不是所有的像素都读取，
     * 而是由选择的。所以这种方式减少了像素的个数，能改变图片在内存中的占用大小。
     *
     * 采样率压缩，的的确确的改变了图片占用内存问题，但是由于像素改变，压缩容易造成失真问题。使用采样率压缩，不需要一开始把图片完全读取到内存，
     * 而是先读取图片的边，然后设置图片的尺寸，然后再根据尺寸，选择的读取像素。这种方法避免了一开始就吧图片读入内存而造成的oom异常。
     * @param filePath
     * @param file
     */
    private void compreseImageToFileBySamplingRate(String filePath,File file){
        //数值越高，图片像素越低，必须是2的倍数
        int inSampleSize = 8;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;//true:不会真正加载图片，二十得到图片的宽高信息
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath,options);

    }

    private void compressImageToFile(Bitmap bitmap, File file) {
        int quality = 50;//0-100
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,quality,baos);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            try {
                outputStream.write(baos.toByteArray());
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
