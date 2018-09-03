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
		GetPicData src = new GetPicData();// ��ȡ���ļ�
		// src.getImagePixel("F:/Users/16935/DN/canvas/gdjd.png");
		// src.objectWrite("source/gdjd.dat");//������ļ���
		String imgName = "JB9";
		src.getImagePixel("F:/Users/16935/DN/canvas/" + imgName + ".png");
		
		src.objectWrite("source/" + imgName + ".dat");// ������ļ���

	}

	public void objectWrite(String file) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(ParticleSrc);
		oos.flush();
		oos.close();
	}

	// �����г�ͼƬ
	public void getImageFunction(int minx, int miny, int width, int height, int m, int n, int[] rgb, BufferedImage bi,
			int pixel[]) {

		for (int i = minx; i < width - 1; i += 2) { // ԭͼƬ���ض�ȡ
			for (int j = miny; j < height - 1; j += 2) {
				pixel[i * height + j] = bi.getRGB(i, j); // �������д��뽫һ������ת��ΪRGB����
				if (pixel[i * height + j] != 16777215) { // !=16777215 ȥ�� // ==-16777216 ����
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

	// �����г�ͼƬ
	public void getImageFunctionUp(int minx, int miny, int width, int height, int m, int n, int[] rgb, BufferedImage bi,
			int pixel[]) {

		for (int j = miny; j < height - 1; j += 2) {
			for (int i = minx; i < width - 1; i += 2) { // ԭͼƬ���ض�ȡ
				pixel[i * height + j] = bi.getRGB(i, j); // �������д��뽫һ������ת��ΪRGB����
				
				if (pixel[i * height + j] != 16777215) { // !=16777215 ȥ�� // ==-16777216 ����
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
			for (int i = 0; i < width; i += 4) { // ԭͼƬ���ض�ȡ
				for (int j = yStart; j < yStart + plus; j += 4) {
					pixel[i * height + j] = bi.getRGB(i, j); // �������д��뽫һ������ת��ΪRGB����
					if (pixel[i * height + j] != 16777215 && pixel[i * height + j] !=16053492) { // !=16777215 ȥ�� // ==-16777216 ����
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

	public int getImagePixel(String image) { // ��ȡԭͼ�������ֵ
		int[] rgb = new int[3];
		int m = 50, n = 80; // ԭ������
		// int m = 215, n = 300;//��������
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

//		int cenh = (miny + height) / 4;// ���λ���
//		for (int i = minx; i < width - 1; i += 1) { // ԭͼƬ���ض�ȡ
//			for (int j = miny; j < cenh; j += 1) {
//				pixel[i * height + j] = bi.getRGB(i, j); // �������д��뽫һ������ת��ΪRGB����
//				if (pixel[i * height + j] != 16777215) { // !=16777215 ȥ�� // ==-16777216 ���� //
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
//		for (int i = width - 1; i > minx; i -= 1) { // ԭͼƬ���ض�ȡ
//			for (int j = cenh; j < height - 1; j += 1) {
//				pixel[i * height + j] = bi.getRGB(i, j); // �������д��뽫һ������ת��ΪRGB����
//				if (pixel[i * height + j] != 16777215) { // !=16777215 ȥ�� ==-16777216 ���� //
////					System.out.println(pixel[i * height + j] + "-" + i + "-" + j);
//					rgb[0] = (pixel[i * height + j] & 0xff0000) >> 16;
//					rgb[1] = (pixel[i * height + j] & 0xff00) >> 8;
//					rgb[2] = (pixel[i * height + j] & 0xff);
//					Particle1 particle = new Particle1(i + m, j + n, rgb[0], rgb[1], rgb[2]);
//					ParticleSrc.add(particle);
//				}
//			}
//		}

		System.out.println("ͼƬ���Ϊ��" + width * height);
		System.out.println("pixel2����Ϊ��" + ParticleSrc.size());
		return 0;
	}
}

class displayType {
	public ArrayList<Particle1> resetListOrder(ArrayList<Particle1> listDate) {
		ArrayList<Particle1> listNewDate = new ArrayList();
		// System.out.println("===================" + listDate.size());
		int Count;
		ArrayList<Integer> test = new ArrayList();
		for (int i = 0; i < listDate.size(); i++) { // ��ʼ�����б�
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