package com.dn;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.print.attribute.standard.PrinterInfo;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javazoom.jl.decoder.JavaLayerException;

public class WaitForYou {

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// frameTra f = new frameTra();
	// f.setDefaultCloseOperation(EXIT_ON_CLOSE);
	// }

}

class frameTra extends JFrame {
	CJPanel c;

	frameTra() {

	}

	class CJPanel extends JPanel {
		final static int up = 0; //产生照片时的生产方向
		final static int down = 1;
		final static int left = 2;
		final static int right = 3;
		
		final static int notMove = 0;
		final static int moveRight = 1;
		final static int moveLeft = 2;
		final static int moveUp = 3;
		final static int moveDown = 4;
		final static int textMove = 5;
		ArrayList<Particle1>[] ParticleSrcs = new ArrayList[5];
		ParticleInfo[] particleInfos = new ParticleInfo[5];
		// public ArrayList<Particle1> ParticleSrc0 = new ArrayList<Particle1>();
		public ArrayList<Images> Heards = new ArrayList<Images>(); // 定义心形图片集合
		public ArrayList<Particle1> rainList = new ArrayList<Particle1>();

		public void launchJPanel() { // 画布刷新频率
			new Thread(new Runnable() {

				public void run() {
					while (true) {
						repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}

		int timeCount = 0;

		public void paint(Graphics g) {

			g.setColor(Color.black);
			g.fillRect(0, 0, 1280, 768);

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			timeCount += 10;
			draw(g);
		}

		Image img;
		int floatCount = 0;
		int rainCount = 100;

		public void draw(Graphics g) {
			for (int i = 0; i < rainList.size(); i++) { // 流星雨效果
				rainList.get(i).x += 1;
				rainList.get(i).y += 2;
				g.setColor(new Color(rainList.get(i).r, rainList.get(i).g, rainList.get(i).b));
				g.fillOval(rainList.get(i).x, rainList.get(i).y, 2, 2);
				if (rainList.get(i).x > 1280 || rainList.get(i).y > 768 || rainList.get(i).x * rainList.get(i).y < 0) {
					rainList.get(i).x = random(1480) - 200;
					rainList.get(i).y = random(200);
					rainList.get(i).r = random(255);
					rainList.get(i).g = random(255);
					rainList.get(i).b = random(255);
				}
			}

			if (img != null) { // 绘制心形图片
				if (timeCount > 3000 && ParticleSrcs[0] != null) {
					int flag;
					for (int i = 0; i < Heards.size(); i++) {
						flag = (i * 57 + floatCount) % ParticleSrcs[0].size();
						Heards.get(i).x = ParticleSrcs[0].get(flag).x;
						Heards.get(i).y = ParticleSrcs[0].get(flag).y;
					}
					floatCount += 3;
				}

				for (int i = 0; i < Heards.size(); i++) {
					g.drawImage(img, Heards.get(i).x + 5, Heards.get(i).y, 13, 13, null);
					g.drawImage(img, Heards.get(i).x - 350, Heards.get(i).y, 13, 13, null);
					g.drawImage(img, Heards.get(i).x + 355, Heards.get(i).y, 13, 13, null);
					// Heards.get(i).x += 2; // 自由粒子移动
					Heards.get(i).y -= 1;
					if (Heards.get(i).x > 1280 || Heards.get(i).y > 768 || Heards.get(i).x * Heards.get(i).y < 0) {
						Heards.get(i).x = random(1280);
						Heards.get(i).y = random(768);
					}
				}
			}

			for (int i = 0; i < ParticleSrcs.length; i++) { // 照片像素绘制

				if (ParticleSrcs[i] != null) {
					if (particleInfos[i].picCount < ParticleSrcs[i].size() - particleInfos[i].productSpeed - 1) {
						particleInfos[i].picCount += particleInfos[i].productSpeed;
					} else {
						particleInfos[i].picCount = ParticleSrcs[i].size();
					}

					if (particleInfos[i].direction == right) {
						for (int j = 0; j < particleInfos[i].picCount; j += 1) {
							Color my_Color;
							if (particleInfos[i].isSrcColor) {
								my_Color = new Color(ParticleSrcs[i].get(j).r, ParticleSrcs[i].get(j).g,
										ParticleSrcs[i].get(j).b);
							} else {
								my_Color = new Color(random(255), random(255), random(255));
							}
							g.setColor(my_Color);
							if (i != 0) {
								g.fillRect(ParticleSrcs[i].get(j).x, ParticleSrcs[i].get(j).y, 1, 1);
							}else {
								g.fillRect(ParticleSrcs[i].get(j).x + 5, ParticleSrcs[i].get(j).y, 1, 1);
								g.fillRect(ParticleSrcs[i].get(j).x - 350, ParticleSrcs[i].get(j).y, 1, 1); // 重绘
								g.fillRect(ParticleSrcs[i].get(j).x + 355, ParticleSrcs[i].get(j).y, 1, 1); // 重绘
							}
							

						}
					} else {
						for (int j = ParticleSrcs[i].size() - 1; j >= ParticleSrcs[i].size()
								- particleInfos[i].picCount; j -= 1) {
							Color my_Color;
							if (particleInfos[i].isSrcColor) {
								my_Color = new Color(ParticleSrcs[i].get(j).r, ParticleSrcs[i].get(j).g,
										ParticleSrcs[i].get(j).b);
							} else {
								my_Color = new Color(random(255), random(255), random(255));
							}
							g.setColor(my_Color);
							if (i != 0) {
								g.fillRect(ParticleSrcs[i].get(j).x, ParticleSrcs[i].get(j).y, 1, 1);
							}else {
								g.fillRect(ParticleSrcs[i].get(j).x + 5, ParticleSrcs[i].get(j).y, 1, 1);
								g.fillRect(ParticleSrcs[i].get(j).x - 350, ParticleSrcs[i].get(j).y, 1, 1); // 重绘
								g.fillRect(ParticleSrcs[i].get(j).x + 355, ParticleSrcs[i].get(j).y, 1, 1);
							}
						}
					}

					switch (particleInfos[i].moveType) {
					case notMove:
//						Collections.shuffle(ParticleSrcs[i]);
						break;
					case moveRight:
						moveRight(g, i);
						break;
					case textMove:
						textMove(g, i);
						break;
					case moveLeft:
						moveLeft(g, i);
						break;
					case moveUp:
						moveUp(g,i);
						break;
					case moveDown:
						moveDown(g,i);
						break;

					default:
						break;
					}
				}
			}
		}

		public ArrayList<Particle1> objectReadParticle(String file) throws Exception {
			ArrayList<Particle1> particles = new ArrayList<Particle1>();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			particles = (ArrayList<Particle1>) ois.readObject();
			ois.close();
			return particles;
		}

		public ArrayList<Particle1> initParticle(String imgSrc, String smallHeart) { // 粒子对象集合获取
			img = new ImageIcon("Pic\\" + smallHeart + ".png").getImage();
			if (Heards.size() < 33) {
				for (int i = 0; i < 33; i++) {
					Images heard = new Images(this.random(1280 - 52), this.random(768 - 52));
					Heards.add(heard);
				}
			}
			if (rainList.size() < rainCount) {
				for (int i = 0; i < rainCount; i++) {
					Particle1 rainDrop = new Particle1(random(1280), random(768), random(255), random(255),
							random(255));
					rainList.add(rainDrop);
				}
			}
			ArrayList<Particle1> particle1s = new ArrayList<Particle1>();
			ArrayList<Particle1> listNewDate = new ArrayList();
			try {
				particle1s = objectReadParticle("source/" + imgSrc + ".dat");
//				
				System.out.println(imgSrc + "粒子数为" + particle1s.size());
			} catch (Exception e) {
				System.out.println(imgSrc + "为空");
				particle1s = null;
			}
			return particle1s;
		}

		public void initXY(int j) {
			for (int i = 0; i < ParticleSrcs[j].size(); i++) {
				ParticleSrcs[j].get(i).x += particleInfos[j].m;
				ParticleSrcs[j].get(i).y += particleInfos[j].n;
			}
		}

		public void moveRight(Graphics g, int j) {
			for (int i = 0; i < ParticleSrcs[j].size(); i++) {
				if (1280 > ParticleSrcs[j].get(ParticleSrcs[j].size() - 1).x && particleInfos[j].isDestory == false) {
					ParticleSrcs[j].get(i).x += particleInfos[j].moveSpeed;
				} else {
					particleInfos[j].isDestory = true;
				}
			}

//			if (particleInfos[j].isDestory) {
//				for (int i = 0; i < ParticleSrcs[j].size(); i++) {
//					if (ParticleSrcs[j].get(i).x % 3 == 0 && ParticleSrcs[j].get(i).y % 3 == 0) {
//						ParticleSrcs[j].remove(i);
//					}
//				}
//				// System.out.println(ParticleSrc.size());
//				if (particleInfos[j].destoryCount < ParticleSrcs[j].size() - 201) {
//					particleInfos[j].destoryCount += 200;
//				} else {
//					particleInfos[j].destoryCount = ParticleSrcs[j].size();
//				}
//				for (int i = 0; i < particleInfos[j].destoryCount; i++) {
//					ParticleSrcs[j].get(i).y += 10;
//					ParticleSrcs[j].get(i).x -= 10;
//				}
//			}
		}

		public void moveLeft(Graphics g, int j) {
			for (int i = 0; i < ParticleSrcs[j].size(); i++) {
				if (0 < ParticleSrcs[j].get(0).x && particleInfos[j].isDestory == false) {
					ParticleSrcs[j].get(i).x -= particleInfos[j].moveSpeed;
				} else {
					particleInfos[j].isDestory = true;
				}
			}

//			if (particleInfos[j].isDestory) {
//				for (int i = 0; i < ParticleSrcs[j].size(); i++) {
//					if (ParticleSrcs[j].get(i).x % 3 == 0 && ParticleSrcs[j].get(i).y % 3 == 0) {
//						ParticleSrcs[j].remove(i);
//					}
//				}
//				// System.out.println(ParticleSrc.size());
//				if (particleInfos[j].destoryCount < ParticleSrcs[j].size() - 201) {
//					particleInfos[j].destoryCount += 200;
//				} else {
//					particleInfos[j].destoryCount = ParticleSrcs[j].size();
//				}
//				for (int i = 0; i < particleInfos[j].destoryCount; i++) {
//					ParticleSrcs[j].get(i).y -= 10;
//					ParticleSrcs[j].get(i).x += 10;
//				}
//			}
		}

		// int floatText = 0;
		public void textMove(Graphics g, int j) {
			int flag;
			
			int[] r = new int[particleInfos[j].moveSpeed];
			int[] g_ = new int[particleInfos[j].moveSpeed];
			int[] b = new int[particleInfos[j].moveSpeed];
			for (int i = 0; i < b.length; i++) {
				r[i] = ParticleSrcs[j].get(ParticleSrcs[j].size() - b.length + i).r;
				g_[i] = ParticleSrcs[j].get(ParticleSrcs[j].size() - b.length + i).g;
				b[i] = ParticleSrcs[j].get(ParticleSrcs[j].size() - b.length + i).b;
				// 20
				// 0 1 2 3 4
				// 25 26 27 28 29
			}
			for (int i = ParticleSrcs[j].size() - 1; i >= particleInfos[j].moveSpeed; i--) {
				// flag = (i-20+ParticleSrcs[j].size()+floatText) % ParticleSrcs[j].size();
				flag = (i + 1 + ParticleSrcs[j].size()) % ParticleSrcs[j].size();
				ParticleSrcs[j].get(i).r = ParticleSrcs[j].get(i - particleInfos[j].moveSpeed).r;
				ParticleSrcs[j].get(i).g = ParticleSrcs[j].get(i - particleInfos[j].moveSpeed).g;
				ParticleSrcs[j].get(i).b = ParticleSrcs[j].get(i - particleInfos[j].moveSpeed).b;

			}
			for (int i = 0; i < b.length; i++) {
				ParticleSrcs[j].get(i).r = r[i];
				ParticleSrcs[j].get(i).g = g_[i];
				ParticleSrcs[j].get(i).b = b[i];
			}

			// floatText += 5;
		}

		public void moveUp(Graphics g,int j) {
			for (int i = 0; i < ParticleSrcs[j].size(); i++) {
				if (100 < ParticleSrcs[j].get(0).y && particleInfos[j].isDestory == false) {
					ParticleSrcs[j].get(i).y -= particleInfos[j].moveSpeed;
				} else {
					particleInfos[j].isDestory = true;
				}
			}
		}
		
		public void moveDown(Graphics g,int j) {
			for (int i = 0; i < ParticleSrcs[j].size(); i++) {
				if (768 > ParticleSrcs[j].get(ParticleSrcs[j].size() - 1).y && particleInfos[j].isDestory == false) {
					ParticleSrcs[j].get(i).y += particleInfos[j].moveSpeed;
				} else {
					particleInfos[j].isDestory = true;
				}
			}
		}

		public int random(int countRange) {
			return (int) Math.round(Math.random() * countRange);
		}
	}
}

class Images implements Serializable {
	int x, y;

	public Images(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class ParticleInfo {
	int picCount = 0; //
	int destoryCount = 0;
	int productSpeed; // 生产速度
	int destorySpeed; // 消亡速度
	boolean isSrcColor = true; // 是否使用原图片颜色
	int moveType; // 移动方式
	int moveSpeed;
	int m, n; // 初始坐标位移
	boolean isDestory = false; // 是否进行销毁
	int direction;

	public ParticleInfo(int productSpeed, int destorySpeed, boolean isSrcColor, int moveType,int moveSpeed, int m, int n,
			int direction) {
		// TODO Auto-generated constructor stub
		this.productSpeed = productSpeed;
		this.destorySpeed = destorySpeed;
		this.isSrcColor = isSrcColor;
		this.moveType = moveType;
		this.moveSpeed = moveSpeed;
		this.m = m;
		this.n = n;
		this.direction = direction;
	}

	public void set(int productSpeed, int destorySpeed, boolean isSrcColor, int moveType,int moveSpeed, int m, int n, int direction) {
		this.productSpeed = productSpeed;
		this.destorySpeed = destorySpeed;
		this.isSrcColor = isSrcColor;
		this.moveType = moveType;
		this.moveSpeed = moveSpeed;
		this.m = m;
		this.n = n;
		this.direction = direction;
		this.picCount = 0; //
		this.destoryCount = 0;
		this.isDestory = false; // 是否进行销毁 boolean isDestory = false; //是否进行销毁
	}
}

class Particle1 implements Serializable {
	int x, y;
	int r, g, b;

	public Particle1(int x, int y, int r, int g, int b) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Particle1 other = (Particle1) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}