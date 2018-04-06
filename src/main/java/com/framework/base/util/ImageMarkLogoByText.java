package com.framework.base.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 图片加文字操作
 * @author 任文龙
 *
 */
public class ImageMarkLogoByText {
	
	private static Logger log = LoggerFactory.getLogger(ImageMarkLogoByText.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String srcImgPath = "E:/qrcodetest/createimage.jpg";
		String logoText = "[我的设备名称]";
		String targerPath = "e:/qrcodetest/abc_test1.jpg";


		// 给图片添加水印
		ImageMarkLogoByText.markByText(logoText, srcImgPath, targerPath,false);

		// 给图片添加水印,水印旋转-45
		// ImageMarkLogoByText.markByText(logoText, srcImgPath, targerPath2, -45);
	}

	/**
	 * 给图片添加水印
	 * 
	 * @param logoText	需要写入的文字。
	 * @param srcImgPath	写入图片路径
	 * @param targerPath	另存图片路径
	 */
	public static void markByText(String logoText, String srcImgPath, String targerPath,boolean isSplit) {
		createQrTextImg(logoText, srcImgPath, targerPath, null, isSplit);
	}

	/**
	 * 给图片添加水印、可设置水印的旋转角度
	 * 
	 * @param logoText
	 * @param srcImgPath
	 * @param targerPath
	 * @param degree
	 */
	public static void markByText(String logoText, String srcImgPath, String targerPath, Integer degree) {
		// 主图片的路径
		InputStream is = null;
		OutputStream os = null;
		try {
			log.info("开始写入文字到背景图片。");
			Image srcImg = ImageIO.read(new File(srcImgPath));

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);

			// 得到画笔对象
			// Graphics g= buffImg.getGraphics();
			Graphics2D g = buffImg.createGraphics();

			// 设置对线段的锯齿状边缘处理 VALUE_INTERPOLATION_BILINEAR
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,
					0, null);

			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
			}

			// 设置颜色
			g.setColor(Color.black);

			// 设置 Font
			Font font = new Font("宋体", Font.BOLD, 30);
			g.setFont(font);

			// 设置透明度
			float alpha = 1.0f;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

			// 第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y) .
			FontRenderContext context = g.getFontRenderContext();
			Rectangle2D bounds = font.getStringBounds(logoText, context);
			double x = (srcImg.getWidth(null) - bounds.getWidth()) / 2;
			Double ix = new Double(x);
			double y = srcImg.getWidth(null) - (srcImg.getWidth(null) - bounds.getHeight());
			Double iy = new Double(y);
			g.drawString(logoText, ix.intValue(), iy.intValue());

			g.dispose();

			os = new FileOutputStream(targerPath);

			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
			log.info("成功写入文字到图片。"+ targerPath);
		} catch (Exception e) {
			log.error("写入文字到图片出错！" + e.getMessage());
		} finally {
			try {
				if (null != is)
					is.close();
			} catch (Exception e) {
				log.error("关闭写入文件流出错！" + e.getMessage());
			}
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				log.error("关闭输出文件流出错！" + e.getMessage());
			}
		}
	}

	/**
	 * 给图片添加水印、可设置水印的旋转角度
	 * 
	 * @param logoText
	 * @param srcImgPath
	 * @param targerPath
	 * @param degree
	 */
	public static void createQrTextImg(String logoText, String srcImgPath, String targerPath, Integer degree,boolean isSplit) {
		// 主图片的路径
		InputStream is = null;
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);

			// 得到画笔对象
			// Graphics g= buffImg.getGraphics();
			Graphics2D g = buffImg.createGraphics();

			// 设置对线段的锯齿状边缘处理 VALUE_INTERPOLATION_BILINEAR
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			// 创建图片，设置压缩值Image.SCALE_SMOOTH使用图像质量最优
			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,
					0, null);

			// 是否需要旋转（45°）
			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
			}

			// 设置颜色
			g.setColor(Color.black);

			// 设置 Font
			Font font = new Font("宋体", Font.BOLD, 30);
			g.setFont(font);

			// 设置透明度
			float alpha = 1.0f;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			String[] strContent = {logoText};
			 //分解需要输出内容
			if(isSplit){
				strContent = logoText.split("/");
			}
			FontRenderContext context = g.getFontRenderContext();
			Rectangle2D bounds;
			double x ;
			Double ix;
			double y;
			Double iy;
			double totaly = 0;
			for(String strTemp : strContent){
				// 获取内容大小。
				bounds = font.getStringBounds(strTemp, context);
				x = (srcImg.getWidth(null) - bounds.getWidth()) / 2;
				ix = new Double(x);
				y = srcImg.getHeight(null) - (srcImg.getHeight(null) - bounds.getHeight()) + totaly;
				iy = new Double(y);
				totaly = totaly + y;
				// 第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y) .
				g.drawString(strTemp, ix.intValue(), iy.intValue());
			}

			g.dispose();
			
			//获取生成目录。并判断是否存在
			String strPath = targerPath.toString().substring(0,targerPath.toString().lastIndexOf("/"));
			log.info("目标目录为：" + strPath);
			File largeDirPath = new File(strPath);
			// 该目录不存在则创建
			if (!largeDirPath.exists()) {
				largeDirPath.mkdirs();
			}
			os = new FileOutputStream(targerPath);
			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
			log.info("文字写入图片成功" + targerPath);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
