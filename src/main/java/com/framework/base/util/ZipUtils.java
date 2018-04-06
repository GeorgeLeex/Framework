package com.framework.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 压缩文件操作类
 * 
 * @author 任文龙
 *
 */
public class ZipUtils {

	private static final Logger log = LoggerFactory.getLogger(ZipUtils.class);
	/** 创建文件夹的同步锁. */
	private static final ReentrantLock folderLock = new ReentrantLock();

	private ZipUtils() {
	};

	/**
	 * 创建ZIP文件
	 * 
	 * @param sourcePath
	 *            文件或文件夹路径
	 * @param zipPath
	 *            生成的zip文件存在路径（包括文件名）
	 */
	public static void createZip(String sourcePath, String zipPath) {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			
			folderLock.lock();
			//判断是否存在。
			try {
				File largeDirPath = new File(zipPath.substring(0,zipPath.lastIndexOf(File.separator)));
				// 该目录不存在则创建
				if (!largeDirPath.exists()) {
					largeDirPath.mkdirs();
				}
			} finally {
				folderLock.unlock();
			}
			
			fos = new FileOutputStream(zipPath);
			zos = new ZipOutputStream(fos);
			writeZip(new File(sourcePath), "", zos);
		} catch (FileNotFoundException e) {
			log.error("创建ZIP文件失败", e);
			new FileNotFoundException(e.getMessage());
		} finally {
			try {
				if (zos != null) {
					zos.close();
				}
			} catch (IOException e) {
				log.error("创建ZIP文件失败", e);
				new IOException(e.getMessage());
			}

		}
	}

	/**
	 * 写入ZIP文件、
	 * 
	 * @param file
	 *            文件对象
	 * @param parentPath
	 *            上级目录
	 * @param zos
	 *            输出对象
	 */
	private static void writeZip(File file, String parentPath, ZipOutputStream zos) {
		if (file.exists()) {
			if (file.isDirectory()) {// 处理文件夹
				parentPath += file.getName() + File.separator;
				File[] files = file.listFiles();
				if (files.length != 0) {
					for (File f : files) {
						writeZip(f, parentPath, zos);
					}
				} else { // 空目录则创建当前目录
					try {
						zos.putNextEntry(new ZipEntry(parentPath));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						log.info("目录不存在，创建时出错");
						new IOException(e.getMessage());
					}
				}
			} else {
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
					ZipEntry ze = new ZipEntry(parentPath + file.getName());
					zos.putNextEntry(ze);
					byte[] content = new byte[1024];
					int len;
					while ((len = fis.read(content)) != -1) {
						zos.write(content, 0, len);
						zos.flush();
					}

				} catch (FileNotFoundException e) {
					log.error("创建ZIP文件失败", e);
					new FileNotFoundException(e.getMessage());
				} catch (IOException e) {
					log.error("创建ZIP文件失败", e);
					new IOException(e.getMessage());
				} finally {
					try {
						if (fis != null) {
							fis.close();
						}
					} catch (IOException e) {
						log.error("创建ZIP文件失败", e);
						new IOException(e.getMessage());
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		createZip("E:/media/qrcode/final", "E:/media/qrcode/a.zip");
	}
}
