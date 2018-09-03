package com.dn;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class GetPicData {
	public List<Particle1> ParticleSrc = new ArrayList<Particle1>();
	public String ImageFile;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		GetPicData src = new GetPicData();// 读取的文件
		// src.getImagePixel("F:/Users/16935/DN/canvas/gdjd.png");
		// src.objectWrite("source/gdjd.dat");//保存的文件名
		String imgName = "JB9";
		src.getImagePixel("F:/Users/16935/DN/canvas/" + imgName + ".png");
		
		src.objectWrite("source/" + imgName + ".dat");// 保存的文件名

	}

	public void objectWrite(String file) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(ParticleSrc);
		oos.flush();
		oos.close();
	}

	// 竖排列出图片
	public void getImageFunction(int minx, int miny, int width, int height, int m, int n, int[] rgb, BufferedImage bi,
			int pixel[]) {

		for (int i = minx; i < width - 1; i += 2) { // 原图片像素读取
			for (int j = miny; j < height - 1; j += 2) {
				pixel[i * height + j] = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
				if (pixel[i * height + j] != 16777215) { // !=16777215 去黑 // ==-16777216 留黑
					// System.out.println(pixel[i * height + j] + "-" + i + "-" + j);
					rgb[0] = (pixel[i * height + j] & 0xff0000) >> 16;
					rgb[1] = (pixel[i * height + j] & 0xff00) >> 8;
					rgb[2] = (pixel[i * height + j] & 0xff);
					Particle1 particle = new Particle1(i + m, j + n, rgb[0], rgb[1], rgb[2]);
					ParticleSrc.add(particle);
				}
			}
		}
	}

	// 横排列出图片
	public void getImageFunctionUp(int minx, int miny, int width, int height, int m, int n, int[] rgb, BufferedImage bi,
			int pixel[]) {

		for (int j = miny; j < height - 1; j += 2) {
			for (int i = minx; i < width - 1; i += 2) { // 原图片像素读取
				pixel[i * height + j] = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
				
				if (pixel[i * height + j] != 16777215) { // !=16777215 去黑 // ==-16777216 留黑
					// System.out.println(pixel[i * height + j] + "-" + i + "-" + j);
					rgb[0] = (pixel[i * height + j] & 0xff0000) >> 16;
					rgb[1] = (pixel[i * height + j] & 0xff00) >> 8;
					rgb[2] = (pixel[i * height + j] & 0xff);
					Particle1 particle = new Particle1(i + m, j + n, rgb[0], rgb[1], rgb[2]);
					ParticleSrc.add(particle);
				}
			}
		}
	}

	public void getImageFunction1(int minx, int miny, int width, int height, int m, int n, int[] rgb, BufferedImage bi,
			int pixel[]) {
		int plus = height / 5;
		int yStart = 0;
		for (int k = 0; k < height / plus; k++) {
			for (int i = 0; i < width; i += 4) { // 原图片像素读取
				for (int j = yStart; j < yStart + plus; j += 4) {
					pixel[i * height + j] = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
					if (pixel[i * height + j] != 16777215 && pixel[i * height + j] !=16053492) { // !=16777215 去黑 // ==-16777216 留黑
						 System.out.println(pixel[i * height + j] + "-" + i + "-" + j);
						rgb[0] = (pixel[i * height + j] & 0xff0000) >> 16;
						rgb[1] = (pixel[i * height + j] & 0xff00) >> 8;
						rgb[2] = (pixel[i * height + j] & 0xff);
						Particle1 particle = new Particle1(i + m, j + n, rgb[0], rgb[1], rgb[2]);
						ParticleSrc.add(particle);
					}
				}
			}
			yStart += plus;
		}
//		Collections.shuffle(ParticleSrc);
	}

	public int getImagePixel(String image) { // 获取原图像的像素值
		int[] rgb = new int[3];
		int m = 50, n = 80; // 原点坐标
		// int m = 215, n = 300;//名字坐标
		File file = new File(image);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		System.out.println("width=" + width + ",height=" + height + ".");
		System.out.println("minx=" + minx + ",miniy=" + miny + ".");
		int pixel[] = new int[width * height];
		Particle1 pixel2[] = new Particle1[width * height];

		getImageFunction1(minx, miny, width, height, minx, miny, rgb, bi, pixel);

//		int cenh = (miny + height) / 4;// 心形绘制
//		for (int i = minx; i < width - 1; i += 1) { // 原图片像素读取
//			for (int j = miny; j < cenh; j += 1) {
//				pixel[i * height + j] = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
//				if (pixel[i * height + j] != 16777215) { // !=16777215 去空 // ==-16777216 留黑 //
////					System.out.println(pixel[i * height + j] + "-" + i + "-" + j);
//					rgb[0] = (pixel[i * height + j] & 0xff0000) >> 16;
//					rgb[1] = (pixel[i * height + j] & 0xff00) >> 8;
//					rgb[2] = (pixel[i * height + j] & 0xff);
//					Particle1 particle = new Particle1(i + m, j + n, rgb[0], rgb[1], rgb[2]);
//					ParticleSrc.add(particle);
//				}
//			}
//		}
//
//		for (int i = width - 1; i > minx; i -= 1) { // 原图片像素读取
//			for (int j = cenh; j < height - 1; j += 1) {
//				pixel[i * height + j] = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
//				if (pixel[i * height + j] != 16777215) { // !=16777215 去黑 ==-16777216 留黑 //
////					System.out.println(pixel[i * height + j] + "-" + i + "-" + j);
//					rgb[0] = (pixel[i * height + j] & 0xff0000) >> 16;
//					rgb[1] = (pixel[i * height + j] & 0xff00) >> 8;
//					rgb[2] = (pixel[i * height + j] & 0xff);
//					Particle1 particle = new Particle1(i + m, j + n, rgb[0], rgb[1], rgb[2]);
//					ParticleSrc.add(particle);
//				}
//			}
//		}

		System.out.println("图片面积为：" + width * height);
		System.out.println("pixel2长度为：" + ParticleSrc.size());
		return 0;
	}
}

class displayType {
	public ArrayList<Particle1> resetListOrder(ArrayList<Particle1> listDate) {
		ArrayList<Particle1> listNewDate = new ArrayList();
		// System.out.println("===================" + listDate.size());
		int Count;
		ArrayList<Integer> test = new ArrayList();
		for (int i = 0; i < listDate.size(); i++) { // 初始整型列表
			test.add(i);
		}

		while (0 < listDate.size()) {
			Count = random(listDate.size());
			if (!listNewDate.contains(listDate.get(Count))) {
				listNewDate.add(listDate.get(Count));
				listDate.remove(Count);
			}
		}
		return listNewDate;
	}

	public int random(int countRange) {
		return (int) Math.round(Math.random() * countRange);
	}
}