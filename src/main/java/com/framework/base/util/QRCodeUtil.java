package com.framework.base.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码操作类
 * @author 任文龙
 *
 */
public class QRCodeUtil {

	private static Logger log = LoggerFactory.getLogger(ImageMarkLogoByText.class);
	/**
	 * 生成二维码
	 * 
	 * @param content
	 * @param width
	 * @param height
	 * @param filePath
	 * @return
	 * @throws WriterException
	 * @throws IOException
	 */
	public static String createEncode(String content, String filePath, String fileName) throws WriterException,
			IOException {
		PropertiesUtil propUtils = new PropertiesUtil("file.properties");

		int width = Integer.parseInt(propUtils.getProperty("qrcode_width"));
		int height = Integer.parseInt(propUtils.getProperty("qrcode_height"));
		String format = propUtils.getProperty("qrcode_pic_type");// 图像类型
		if (0 == width) {
			width = 420;
		}
		if (0 == height) {
			height = 420;
		}
		log.info("开始生成二维码！宽度："+ width +"  高度：" + height);
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵

		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}

		Path path = FileSystems.getDefault().getPath(filePath, fileName);
		MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像
		log.info("二维码输出成功！" + path);
		return path.toString();
	}

	/**
	 * 生成二维码，可自定义边距
	 * 
	 * @param content
	 * @param width
	 * @param height
	 * @param filePath
	 * @param fileName
	 * @param margin
	 * @return
	 * @throws WriterException
	 * @throws IOException
	 */
	public static String createEncode(String content, String filePath, String fileName, int margin)
			throws WriterException, IOException {
		String format = "png";// 图像类型
		//获取配置文件中 二维码大小。
		PropertiesUtil propUtils = new PropertiesUtil("file.properties");
		int width = Integer.parseInt(propUtils.getProperty("qrcode_width"));
		int height = Integer.parseInt(propUtils.getProperty("qrcode_height"));
		if (0 == width) {
			width = 420;
		}
		if (0 == height) {
			height = 420;
		}
		log.info("开始生成二维码！宽度："+ width +"  高度：" + height);
		
		Map<EncodeHintType,Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, margin);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵

		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}

		Path path = FileSystems.getDefault().getPath(filePath, fileName);
		MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像
		log.info("二维码输出成功！" + path);
		return path.toString();
	}

	private static MultiFormatWriter mutiWriter = new MultiFormatWriter();

	/**
	 * 生成彩色二维码
	 * 
	 * @param content
	 * @param width
	 * @param height
	 * @param srcImagePath
	 * @param destImagePath
	 */
	public static void encodeCorlor(String content, int width, int height, String srcImagePath, String destImagePath) {
		try {
			File file = new File(destImagePath.substring(0, destImagePath.lastIndexOf("/")));
			if (!file.exists()) {
				file.mkdir();
			}
			ImageIO.write(genBarcode(content, width, height, srcImagePath), "png", new File(destImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}

	private static BufferedImage genBarcode(String content, int width, int height, String srcImagePath)
			throws WriterException, IOException {
		Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
		hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		hint.put(EncodeHintType.MARGIN, 0);
		// 生成二维码
		BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hint);

		int[] pixels = new int[width * height];
		for (int y = 0; y < matrix.getHeight(); y++) {
			for (int x = 0; x < matrix.getWidth(); x++) {

				// 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
				pixels[y * width + x] = matrix.get(x, y) ? 803193 : 16765952;
			}
		}
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		image.getRaster().setDataElements(0, 0, width, height, pixels);
		return image;
	}
}
