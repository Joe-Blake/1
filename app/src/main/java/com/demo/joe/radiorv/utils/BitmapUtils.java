package com.demo.joe.radiorv.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.MeasureSpec;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BitmapUtils {

	private static String TAG = "BitmapUtils";

	/*------------------------------------
	 * 绘制图片
	 *
	 * @param		x 屏幕上的x坐标	
	 * @param		y 屏幕上的y坐标
	 * @param		w 要绘制的图片的宽度	
	 * @param		h 要绘制的图片的高度
	 * @param		bx图片上的x坐标
	 * @param		by图片上的y坐标
	 *
	 * @return		null
	 ------------------------------------*/
	public static void drawImage(Canvas canvas, Bitmap blt, int x, int y, int w, int h, int bx, int by) {
		Rect src = new Rect();// 图片
		Rect dst = new Rect();// 屏幕

		src.left = bx;
		src.top = by;
		src.right = bx + w;
		src.bottom = by + h;

		dst.left = x;
		dst.top = y;
		dst.right = x + w;
		dst.bottom = y + h;
		canvas.drawBitmap(blt, src, dst, null);

		src = null;
		dst = null;
	}

	/***
	 * 　　* 加载本地图片 　　* @param context：主运行函数实例 　　* @param
	 * bitAdress：图片地址，一般指向R下的drawable目录 　　* @return 　　
	 */

	public static Bitmap CreatImage(Context context, int bitAdress) {
		Bitmap bitmaptemp = null;
		bitmaptemp = BitmapFactory.decodeResource(context.getResources(), bitAdress);
		return bitmaptemp;
	}

	/***
	 * 　　* 图片平均分割方法，将大图平均分割为N行N列，方便用户使用 　　* 　　* @param g 　　* ：画布 　　* @param
	 * paint 　　* ：画笔 　　* @param imgBit 　　* ：图片 　　* @param x 　　* ：X轴起点坐标 　　* @param
	 * y 　　* ：Y轴起点坐标 　　* @param w 　　* ：单一图片的宽度 　　* @param h 　　* ：单一图片的高度 　　* @param
	 * line 　　* ：第几列 　　* @param row 　　* ：第几行
	 */

	public static void cuteImage(Canvas g, Paint paint, Bitmap imgBit, int x, int y, int w, int h, int line, int row) {
		g.clipRect(x, y, x + w, h + y);
		g.drawBitmap(imgBit, x - line * w, y - row * h, paint);
		g.restore();
	}

	public static Bitmap zoomImage(Bitmap bgimage, float scale) {
		Bitmap bitmap = null;
		if (bgimage != null) {
			int width = bgimage.getWidth();
			int height = bgimage.getHeight();
			Matrix matrix = new Matrix();
			matrix.postScale(scale, scale);
			bitmap = Bitmap.createBitmap(bgimage, 0, 0, width, height, matrix, true);
		}
		return bitmap;
	}

	public static Drawable zoomDrawable(Drawable drawable, int newWidth, int newHeitht) {
		Bitmap oldbmp = drawableToBitmap(drawable);// drawable转换成bitmap
		Bitmap newbmp = zoomImage(oldbmp, newWidth, newHeitht);
		oldbmp.recycle();
		oldbmp = null;
		return new BitmapDrawable(newbmp); // 把bitmap转换成drawable并返回
	}

	public static Bitmap drawableToBitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
				drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		// canvas.setBitmap(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;

	}

	/**
	 * 不变形图片缩放，以长宽的变小比例缩放
	 * 
	 * @param bgimage
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static Bitmap zoomImage(Bitmap bgimage, int newWidth, int newHeight) {
		Bitmap bitmap = null;
		if (bgimage != null) {
			// 获取这个图片的宽和高

			int width = bgimage.getWidth();
			int height = bgimage.getHeight();

			// 创建操作图片用的matrix对象

			Matrix matrix = new Matrix();

			// 计算缩放率，新尺寸除原始尺寸
			float scaleWidth = 0;
			float scaleHeight = 0;
			if (newWidth == 0 && newHeight == 0) {
				return bgimage;
			} else if (newWidth == 0) {
				scaleHeight = ((float) newHeight) / height;
				scaleWidth = scaleHeight;
			} else if (newHeight == 0) {
				scaleWidth = ((float) newWidth) / width;
				scaleHeight = scaleWidth;
			} else {
				scaleWidth = ((float) newWidth) / width;
				scaleHeight = ((float) newHeight) / height;
				if(scaleWidth>scaleHeight){
					scaleWidth = scaleHeight;
				}else{
					scaleHeight = scaleWidth;
				}
			}
			// 缩放图片动作
			matrix.postScale(scaleWidth, scaleHeight);
			bitmap = Bitmap.createBitmap(bgimage, 0, 0, width, height, matrix, true);
		}

		return bitmap;

	}

	public static Bitmap toRoundBitmap(Bitmap bitmap, int left, int top, int radus) {
		float right, bottom;
		right = left + radus;
		bottom = top + radus;

		Bitmap output = Bitmap.createBitmap(radus, radus, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect(left, top, (int) right, (int) bottom);
		final Rect dst = new Rect(0, 0, radus, radus);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, radus, radus, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		return output;
	}

	public Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			top = 0;
			bottom = width;
			left = 0;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		return output;
	}

	/**
	 * 读取图片属性：旋转的角度
	 * 
	 * @param path
	 *            图片绝对路径
	 * @return degree旋转的角度
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
		}
		return degree;
	}

	public static Bitmap convertViewToBitmap(View view) {
		try {
			view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
			view.buildDrawingCache();
			return view.getDrawingCache();
		}catch (Exception e){
		}
		return null;
	}

	public static Bitmap loadGifFirstBitmap(String url) {
		Bitmap bitmap = null;
		HttpURLConnection urlConnection = null;
		InputStream is = null;
		try {
			urlConnection = (HttpURLConnection) new URL(url).openConnection();
			is = urlConnection.getInputStream();
			Movie movie = Movie.decodeStream(is);
			bitmap = Bitmap.createBitmap(movie.width(), movie.height(), Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			movie.draw(canvas, 0, 0);
			canvas.save();
			return bitmap;
		} catch (IOException e) {
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
				}
			}
			if (urlConnection != null){
				urlConnection.disconnect();
			}
		}
		return null;
	}

	public static Bundle getGifBitmapInfo(String url) {
		Bitmap bitmap = null;
		HttpURLConnection urlConnection = null;
		InputStream is = null;
		try {
			urlConnection = (HttpURLConnection) new URL(url).openConnection();
			is = urlConnection.getInputStream();
			Movie movie = Movie.decodeStream(is);
			bitmap = Bitmap.createBitmap(movie.width(), movie.height(), Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			movie.draw(canvas, 0, 0);
			canvas.save();
			Bundle bundle = new Bundle();
			bundle.putInt("w",bitmap.getWidth());
			bundle.putInt("h",bitmap.getHeight());
			return bundle;
		} catch (IOException e) {
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
				}
			}
			if (urlConnection != null){
				urlConnection.disconnect();
			}
		}
		return null;
	}

	public static Bitmap Bytes2Bimap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}
}
