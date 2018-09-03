package com.dn;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.*;
import java.io.FileWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.text.StyledEditorKit.ForegroundAction;
import java.security.*;
import javax.swing.*;

public class Console extends JFrame {
	ArrayList<String>[] str = new ArrayList[5];
	final static int up = 0;
	final static int down = 1;// 产生照片时的生产方向
	final static int left = 2;
	final static int right = 3;

	final static int notMove = 0;
	final static int moveRight = 1;
	final static int moveLeft = 2;
	final static int moveUp = 3;
	final static int moveDown = 4;
	final static int textMove = 5;
	static int RedEnvelopesCount = 0;
	int[] time = {0,10000,26000,44000,66000,81000,100000,117000};  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Console frame = new Console();
	}

	public List<Integer> jbs = new ArrayList<Integer>();
	int a[] = { 3, 4, 6, 7, 11, 12, 14, 16, 17, 19, 20, 22, 24, 26, 27, 28, 30, 32, 34, 36, 38, 40, 42, 44, 48, 50, 52,
			58, 60, 68 };

	public void initListButton() {
		for (int i = 0; i < a.length; i++) {
			jbs.add(a[i]);
		}
	}

	frameTra f;
	frameTra.CJPanel c;

	public Console() {
		// TODO Auto-generated constructor stub
		super("图片");
		URL url = Console.class.getResource("xg.png");
		Icon icon = new ImageIcon(url);
		Container container = getContentPane();// 获取容器
		setLayout(new GridLayout(8, 9, 5, 5));
		initListButton();

		JButton jb[] = new JButton[30];

		for (int i = 0; i < 72; i++) { // 8x9网格布局
			if (jbs.contains(i + 1)) {
				// System.out.println("下标为"+jbs.indexOf(i+1));
				if (jbs.indexOf(i + 1) == 29) {
					jb[jbs.indexOf(i + 1)] = new JButton("入口"); // 定义一个按钮
				} else {
					jb[jbs.indexOf(i + 1)] = new JButton("按钮" + (jbs.indexOf(i + 1) + 1)); // 定义一个按钮
				}
				jb[jbs.indexOf(i + 1)].setBorderPainted(false); // 去边框
				jb[jbs.indexOf(i + 1)].setBackground(Color.red);
				// jb[jbs.indexOf(i + 1)].setMaximumSize(new Dimension(54,50));
				// jb[jbs.indexOf(i + 1)].setIcon(icon);
				container.add(jb[jbs.indexOf(i + 1)]);
			} else {
				JLabel jl = new JLabel();
				jl.setIcon(icon);
				jl.setHorizontalAlignment(SwingConstants.CENTER);
				container.add(jl);
			}
		}

		// jb[1].setBounds(10, 10, 100, 21);

		jb[29].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (f == null) {
					f = new frameTra();
					c = new frameTra().new CJPanel();
					f.setTitle("19岁生日礼物");
					f.setSize(1280, 800);// 窗体大小
				}

				for (int i = 0; i < c.ParticleSrcs.length; i++) {
					c.ParticleSrcs[i] = null; // 置空
				}

				setPicBehaviors(0, "xin3", "smallHeart", 10, 0, true, textMove, 3, 430, 150, right);
				setPicBehaviors(1, "name2", "smallHeart", 10, 0, true, textMove, 2, 215, 300, right);
				setPicBehaviors(2, "index4", "smallHeart", 10, 0, true, textMove, 2, 50, 100, right);
				//// System.out.println(c.ParticleSrc.size());
				f.add(c);
				c.repaint();
				f.setVisible(true);
				c.launchJPanel();
				f.setDefaultCloseOperation(HIDE_ON_CLOSE);
				new Thread(new Runnable() { // jb0
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(time[1]);
						} catch (InterruptedException e) {
//							e.printStackTrace();
						}
						c.ParticleSrcs[0] = null;
						c.particleInfos[4] = null;
						new Thread(new Runnable() {	
							public void run() {

								setPicBehaviors(1, "dn1", "smallHeart", 200, 0, true, moveLeft, 2, 600, 200, right);
								setPicBehaviors(3, "Birthday", "smallHeart", 50, 0, true, textMove, 10, -30, -0, right);

							}
						}).start();
						
						setPicBehaviors(2, "gdjd", "smallHeart", 300, 0, true, moveRight, 4, -200, 0, right);
					}
				}).start();
				new Thread(new Runnable() { // jb17

					public void run() {
						try {
							Thread.sleep(time[2]);
						} catch (InterruptedException e) {
//							e.printStackTrace();
						}
						new Thread(new Runnable() {

							public void run() {

								// setPicBehaviors(1, "罗丹妮","smallHeart", 200, 0, true, notMove,2, -0, 0,
								// right);
								setPicBehaviors(1, "dn5", "smallHeart", 100, 0, true, notMove, 2, 830, 20, right);
							}
						}).start();
						c.ParticleSrcs[0] = null;
						c.ParticleSrcs[3] = null;
						c.ParticleSrcs[4] = null;
						setPicBehaviors(2, "JB16", "smallHeart", 20, 0, true, textMove, 2, 20, 20, right);
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO: handle exception
						}
						setPicBehaviors(3, "xin3", "smallHeart", 30, 0, true, textMove, 9, 800, 100, right);
					}
				}).start();
				
				new Thread(new Runnable() { // jb6

					public void run() {
						try {
							Thread.sleep(time[3]);
						} catch (InterruptedException e) {

						}
						
						c.ParticleSrcs[0] = null;
						c.ParticleSrcs[4] = null;
						c.ParticleSrcs[2] = null;
						new Thread(new Runnable() {
							public void run() {
								c.ParticleSrcs[1] = null;
								setPicBehaviors(1, "dn3", "smallHeart", 200, 0, true, moveRight, 4, 0, 100, right);
							}
						}).start();
						
						setPicBehaviors(3, "JB6", "smallHeart", 30, 0, true, textMove, 50, 0, 0, right);
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO: handle exception
						}
						setPicBehaviors(2, "xin3", "smallHeart", 30, 0, true, textMove, 9, 800, 100, right);
					}
				}).start();
				new Thread(new Runnable() { // jb3

					public void run() {
						try {
							Thread.sleep(time[4]);
						} catch (InterruptedException e) {

						}
						new Thread(new Runnable() {
							public void run() {
								setPicBehaviors(1, "dn4", "smallHeart", 300, 0, true, moveDown, 2, 0, -200, right);
								// setPicBehaviors(2, "gdjd2-2x2", "smallHeart", 300, 0, true, moveLeft,2, 300,
								// 0, right);
							}
						}).start();
						c.ParticleSrcs[0] = null;
						c.ParticleSrcs[3] = null;
						c.ParticleSrcs[4] = null;
//						// setPicBehaviors(3,"xin5", "smallHeart", 300, 0, true, textMove,2, 500, -40,right);
						setPicBehaviors(2, "JB3", "smallHeart", 100, 0, true, textMove, 20, 400, 90, right);

					}
				}).start();
				new Thread(new Runnable() { // jb9

					public void run() {
						try {
							Thread.sleep(time[5]);
						} catch (InterruptedException e) {

						}
						new Thread(new Runnable() {
							public void run() {
								setPicBehaviors(1, "dn7", "smallHeart", 300, 0, true, moveUp, 2, 800, 300, right);
								// setPicBehaviors(2, "gdjd2-2x2", "smallHeart", 300, 0, true, moveLeft,2, 300,
								// 0, right);
							}
						}).start();
						// setPicBehaviors(3,"xin5", "smallHeart", 300, 0, true, textMove,2, 500, -40,right);
						c.ParticleSrcs[0] = null;
						c.ParticleSrcs[3] = null;
						c.ParticleSrcs[4] = null;
						setPicBehaviors(2, "JB9", "smallHeart", 30, 0, true, textMove, 5, -10, 20, right);
					}
				}).start();
				new Thread(new Runnable() { // jb14

					public void run() {
						try {
							Thread.sleep(time[6]);
						} catch (InterruptedException e) {

						}
						new Thread(new Runnable() {
							public void run() {
								try {
									Thread.sleep(3000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								c.ParticleSrcs[1] = null;
								setPicBehaviors(1, "dn6", "smallHeart", 200, 0, true, moveDown, 4, 780, -300, left);
								setPicBehaviors(3, "JB14", "smallHeart", 300, 0, true, textMove, 50, 80, 80, right);
							}
						}).start();
						c.ParticleSrcs[0] = null;
						c.ParticleSrcs[4] = null;
						setPicBehaviors(2, "szz2", "smallHeart", 20, 0, true, moveLeft, 4, 800, 0, right);
					}
				}).start();
				new Thread(new Runnable() {
					
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(time[7]);
						} catch (InterruptedException e) {
							// TODO: handle exception
						}
						new TemporaryWindow1("<html><body>如需单张查看请点击相应按键，<br>内容分布在1，4，7，8，10，15<br>和17号键中<br>提示：不要关闭图像视窗<body><html>");
						TemporaryWindow TW = new TemporaryWindow();
					}
				}).start();
				new TemporaryWindow1("<html><body>&nbsp请在联网的情况下进行操作</body></html>");
			}
		});

		jb[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (f == null) {
					f = new frameTra();
					c = new frameTra().new CJPanel();
					c.setBackground(Color.BLACK);
					f.setTitle("19岁生日礼物");
					f.setSize(1280, 800);// 窗体大小
					f.add(c);
					c.repaint();
					c.launchJPanel();
					f.setVisible(true);
				}
				
				for (int i = 0; i < c.ParticleSrcs.length; i++) {
					c.ParticleSrcs[i] = null; // 置空
				}

				/*
				 * 初始化 显示粒子数置零c.picCount = 0;c.picCount1 = 0; 指定粒子加载速度 speed 是否原色绘制 isSrc
				 * 指定粒子群是否移动/移动方式 move 初始坐标m,n
				 */
				// try {
				// new Music().playMusic();
				// } catch (Exception e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				setPicBehaviors(0, "xin3", "smallHeart", 10, 0, true, textMove, 2, 430, 150, right);
				setPicBehaviors(1, "name2", "smallHeart", 10, 0, true, textMove, 2, 215, 300, right);
				setPicBehaviors(2, "index4", "smallHeart", 10, 0, true, textMove, 2, 50, 100, right);
			}
		});

		jb[0].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (f == null) {
					f = new frameTra();
					c = new frameTra().new CJPanel();
					c.setBackground(Color.BLACK);
					f.setTitle("19岁生日礼物");
					f.setSize(1280, 800);// 窗体大小
					f.add(c);
					c.repaint();
					c.launchJPanel();
					f.setVisible(true);
				}
				for (int i = 0; i < c.ParticleSrcs.length; i++) {
					c.ParticleSrcs[i] = null; // 置空
				}
				new Thread(new Runnable() {
					public void run() {
						setPicBehaviors(1, "dn1", "smallHeart", 200, 0, true, moveLeft, 2, 600, 200, right);
						setPicBehaviors(3, "Birthday", "smallHeart", 30, 0, true, textMove, 10, -30, -0, right);
					}
				}).start();
				setPicBehaviors(2, "gdjd", "smallHeart", 300, 0, true, moveRight, 4, -200, 0, right);
			}
		});

		jb[9].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (f == null) {
					f = new frameTra();
					c = new frameTra().new CJPanel();
					c.setBackground(Color.BLACK);
					f.setTitle("19岁生日礼物");
					f.setSize(1280, 800);// 窗体大小
					f.add(c);
					c.repaint();
					c.launchJPanel();
					f.setVisible(true);
				}
				for (int i = 0; i < c.ParticleSrcs.length; i++) {
					c.ParticleSrcs[i] = null; // 置空
				}

				new Thread(new Runnable() {
					public void run() {
						setPicBehaviors(1, "dn7", "smallHeart", 300, 0, true, moveUp, 2, 800, 300, right);
						// setPicBehaviors(2, "gdjd2-2x2", "smallHeart", 300, 0, true, moveLeft,2, 300,
						// 0, right);
					}
				}).start();
				// setPicBehaviors(3,"xin5", "smallHeart", 300, 0, true, textMove,2, 500, -40,
				// right);
				setPicBehaviors(3, "JB9", "smallHeart", 30, 0, true, notMove, 5, -10, 20, right);
			}
		});

		jb[3].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (f == null) {
					f = new frameTra();
					c = new frameTra().new CJPanel();
					c.setBackground(Color.BLACK);
					f.setTitle("19岁生日礼物");
					f.setSize(1280, 800);// 窗体大小
					f.add(c);
					c.repaint();
					c.launchJPanel();
					f.setVisible(true);
				}
				for (int i = 0; i < c.ParticleSrcs.length; i++) {
					c.ParticleSrcs[i] = null; // 置空
				}

				new Thread(new Runnable() {
					public void run() {
						setPicBehaviors(1, "dn4", "smallHeart", 300, 0, true, moveDown, 2, 0, -200, right);
						// setPicBehaviors(2, "gdjd2-2x2", "smallHeart", 300, 0, true, moveLeft,2, 300,
						// 0, right);
					}
				}).start();
				
				setPicBehaviors(3, "JB3", "smallHeart", 100, 0, true, textMove, 20, 400, 90, right);
			}
		});

		jb[6].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (f == null) {
					f = new frameTra();
					c = new frameTra().new CJPanel();
					c.setBackground(Color.BLACK);
					f.setTitle("19岁生日礼物");
					f.setSize(1280, 800);// 窗体大小
					f.add(c);
					c.repaint();
					c.launchJPanel();
					f.setVisible(true);
				}
				for (int i = 0; i < c.ParticleSrcs.length; i++) {
					c.ParticleSrcs[i] = null; // 置空
				}
				new Thread(new Runnable() {
					public void run() {

						setPicBehaviors(1, "dn3", "smallHeart", 200, 0, true, moveRight, 2, 0, 100, right);
					}
				}).start();
				setPicBehaviors(2, "xin3", "smallHeart", 300, 0, true, notMove, 2, 800, 100, right);
				setPicBehaviors(3, "JB6", "smallHeart", 300, 0, true, textMove, 50, 0, 0, right);
			}
		});

		jb[14].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (f == null) {
					f = new frameTra();
					c = new frameTra().new CJPanel();
					c.setBackground(Color.BLACK);
					f.setTitle("19岁生日礼物");
					f.setSize(1280, 800);// 窗体大小
					f.add(c);
					c.repaint();
					c.launchJPanel();
					f.setVisible(true);
				}
				for (int i = 0; i < c.ParticleSrcs.length; i++) {
					c.ParticleSrcs[i] = null; // 置空
				}
				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						setPicBehaviors(1, "dn6", "smallHeart", 200, 0, true, moveDown, 4, 780, -300, left);
						setPicBehaviors(3, "JB14", "smallHeart", 300, 0, true, textMove, 50, 80, 80, right);
					}
				}).start();
				setPicBehaviors(4, "szz2", "smallHeart", 20, 0, true, moveLeft, 4, 800, 0, right);

			}
		});

		jb[16].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (f == null) {
					f = new frameTra();
					c = new frameTra().new CJPanel();
					c.setBackground(Color.BLACK);
					f.setTitle("19岁生日礼物");
					f.setSize(1280, 800);// 窗体大小
					f.add(c);
					c.repaint();
					c.launchJPanel();
					f.setVisible(true);
				}
				for (int i = 0; i < c.ParticleSrcs.length; i++) {
					c.ParticleSrcs[i] = null; // 置空
				}
				new Thread(new Runnable() {
					public void run() {
						// setPicBehaviors(1, "罗丹妮","smallHeart", 200, 0, true, notMove,2, -0, 0,
						// right);
						setPicBehaviors(2, "dn5", "smallHeart", 100, 0, true, notMove, 2, 830, 20, right);
					}
				}).start();
				setPicBehaviors(3, "JB16", "smallHeart", 20, 0, true, textMove, 2, 20, 20, right);
			}
		});

		jb[25].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				TemporaryWindow TW = new TemporaryWindow();
			}
		});
		//红包按钮
		jb[11].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				RedEnvelopes RE = new RedEnvelopes("9");
				new TemporaryWindow1("<html><body>恭喜你获得一份小礼物,请联系客服:<br>QQ:1693547683<br>微信:gnip_oaix<>");
				
			}
		});
		
		jb[12].addActionListener(new ActionListener() {
					
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				RedEnvelopes RE = new RedEnvelopes("1314");
				new TemporaryWindow1("<html><body>恭喜你获得一份小礼物,请联系客服:<br>QQ:1693547683<br>微信:gnip_oaix<>");
			}
		});
		
		jb[17].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				RedEnvelopes RE1 = new RedEnvelopes("5",0,100,RedEnvelopesCount);
				RedEnvelopesCount ++;
				RedEnvelopes RE2 = new RedEnvelopes("20",240,100,RedEnvelopesCount);
				RedEnvelopesCount ++;
				RedEnvelopes RE3 = new RedEnvelopes("13",240*2,100,RedEnvelopesCount);
				RedEnvelopesCount ++;
				RedEnvelopes RE4 = new RedEnvelopes("14",240*3,100,RedEnvelopesCount);
				new TemporaryWindow1("<html><body>恭喜你获得一份小礼物,请联系客服:<br>QQ:1693547683<br>微信:gnip_oaix<>");
			}
		});
		
		jb[21].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				RedEnvelopes RE = new RedEnvelopes("81");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
				new TemporaryWindow1("<html><body>恭喜你获得一份小礼物,请联系客服:<br>QQ:1693547683<br>微信:gnip_oaix");
			}
		});
		
		jb[22].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				RedEnvelopes RE = new RedEnvelopes("001");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
				new TemporaryWindow1("<html><body>这是一个假的红包<br>请转至18号键</body></html>");
			}
		});
		
		container.setBackground(Color.black);
		this.setVisible(true);
		setBounds(520, 77, 777, 666);
		setResizable(false);
		// setSize(777, 520);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void setPicBehaviors(int id, String picName, String picHeart, int productSpeed, int destorySpeed,
			Boolean isSrcColor, int moveType, int moveSpeed, int m, int n, int direction) {
		c.ParticleSrcs[id] = c.initParticle(picName, picHeart);
		// System.out.println("old date:"+c.ParticleSrcs[id].size());
		if (c.particleInfos[id] == null) {
			c.particleInfos[id] = new ParticleInfo(productSpeed, destorySpeed, isSrcColor, moveType, moveSpeed, m, n,
					direction);
		} else {
			c.particleInfos[id].set(productSpeed, destorySpeed, isSrcColor, moveType, moveSpeed, m, n, direction);
		}
		// c.ParticleSrcs[id] = display.resetListOrder(c.ParticleSrcs[id]);
		c.initXY(id);
	}

}

class TemporaryWindow1 extends JFrame{
	Container container = getContentPane();// 获取容器
	public TemporaryWindow1 (String content) {
		// TODO Auto-generated constructor stub
		super("Windows");
		JLabel jl1 = new JLabel(content);
		Font font=new Font("宋体",Font.BOLD,24); 
		jl1.setFont(font);
		setLayout(null);
		jl1.setBounds(60, 20, 500, 180);
		container.add(jl1);
		container.setBackground(Color.white);
		this.setVisible(true);
		setResizable(false); //固定窗口大小
		setBounds(256, 128, 500, 360);
		setDefaultCloseOperation(HIDE_ON_CLOSE); //触发相应事件后关闭窗口
	}
}

class TemporaryWindow extends JFrame {
	public int count = 0;
	Date day = new Date();
	static int questionNo = 0;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String[] questions = {"喜欢这份生日礼物吗?","我想知道你上次拒绝我的原因","<html><body>&nbsp我们的缘分走到哪了</body></html>","<html><body>&nbsp&nbsp&nbsp&nbsp还有个小礼物，请注意查收,请从<br>&nbsp&nbsp按键12、13、18、22、23中选择一个打开</body></html>"};
	String[] questions2 = {"<html><body>&nbsp要不再考虑一下呗</body></html>","真的。。。想好了吗？"};
	gitTest git = new gitTest();
	Container container = getContentPane();// 获取容器
	JButton[] jb = new JButton[4];
	JTextField jt = new JTextField();
	final JLabel jl = new JLabel(questions[questionNo]);
	public TemporaryWindow() {
		// TODO Auto-generated constructor stub
		super("Windows");

		Font font=new Font("宋体",Font.BOLD,36); 
		jl.setFont(font);
		
		setLayout(null);
		jl.setBounds(100, 100, 500, 60);
		container.add(jl);
		this.select();

		container.setBackground(Color.white);
		this.setVisible(true);
		setResizable(false); //固定窗口大小
		setBounds(256, 256, 500, 360);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //触发相应事件后关闭窗口
	}
	
	public void select() {
		switch (questionNo) {
		case 0:
			this.creatJB(0, "很喜欢", "...","很喜欢你送的礼物");
			this.creatJB(1, "还行", "...","谢谢你送的礼物");
			break;
		case 1:
			jt.setBounds(20, 100, 450, 50);
			jl.setBounds(0,0,500,60);
			this.creatJB(1, "提交", "...", null);
			container.add(jt);
			break;
		case 2:
//			final JProgressBar[] progressBar = new JProgressBar[4];
			final JProgressBar progressBar = new JProgressBar();
			final JProgressBar progressBar1 = new JProgressBar();
			final JProgressBar progressBar2 = new JProgressBar();
			final JProgressBar progressBar3 = new JProgressBar();
			progressBar.setValue(1);
			progressBar.setBounds(200,120,240,30);
			progressBar.setBackground(Color.white);
			progressBar.setForeground(Color.green);
			progressBar.setStringPainted(true);
			container.add(progressBar,BorderLayout.NORTH);
			jl.setBounds(60, 20, 500, 100);
			this.creatJB(0, "我们还有很长的路", "...","我们还有很长的路");
			jb[0].setBounds(10,120,180,30);
			
			progressBar1.setValue(20);
			progressBar1.setBounds(200,170,240,30);
			progressBar1.setBackground(Color.white);
			progressBar1.setForeground(Color.green);
			progressBar1.setStringPainted(true);
			container.add(progressBar1,BorderLayout.NORTH);
			this.creatJB(1, "我们先做朋友吧", "...","我们先做朋友吧");
			jb[1].setBounds(10,170,180,30);
			
			progressBar2.setValue(50);
			progressBar2.setBounds(200,220,240,30);
			progressBar2.setBackground(Color.white);
			progressBar2.setForeground(Color.green);
			progressBar2.setStringPainted(true);
			container.add(progressBar2,BorderLayout.NORTH);
			this.creatJB(2, "我们做普通的朋友吧", "...","我们做普通的朋友吧");
			jb[2].setBounds(10,220,180,30);
			
			progressBar3.setValue(80);
			progressBar3.setBounds(200,270,240,30);
			progressBar3.setBackground(Color.white);
			progressBar3.setForeground(Color.green);
			progressBar3.setStringPainted(true);
			container.add(progressBar3,BorderLayout.NORTH);
			this.creatJB(3, "部长，相忘于江湖", "...","部长，相忘于江湖");
			jb[3].setBounds(10,270,180,30);
			
			break;
		case 3:
			Font font = new Font("宋体",Font.BOLD,18);
			jl.setFont(font);
			jl.setBounds(5,5,500,200);
			this.creatJB(1, "确定", "...", null);
			break;

		default:
			break;
		}
	}

	public void creatJB(int id,String JBName,final String content,final String returnContent) {
		jb[id] = new JButton(JBName);
		jb[id].setBorderPainted(false);
		switch (id) {
		case 0:
			jb[id].setBounds(50, 200, 80, 30);
			jb[id].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
//					Respond("Become my girl friend:Yes\n");
					
					new Thread(new Runnable() {
						public void run() {
							Respond(returnContent + df.format(day) + "\n");
							if (questionNo == questions.length-2) {
								git.pushFile(".git");	
							}
						}
					}).start();
					disposeWindow();
					questionNo ++;
					if(questionNo < questions.length)new TemporaryWindow().select();
				}
			});
			break;
		case 1:
			jb[id].setBounds(150, 200, 160, 30);
			jb[id].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					new Thread(new Runnable() {
						public void run() {
							if(returnContent == null) {
								Respond(jt.getText() + df.format(day) + "\n");
							}else {
								Respond(returnContent + df.format(day) + "\n");	
							}
							if (questionNo == questions.length-2) {
								git.pushFile(".git");	
							}
						}
					}).start();
					disposeWindow();
					questionNo ++;
					if(questionNo < questions.length)new TemporaryWindow().select();
				}
			});
			break;
		case 2:
			jb[id].setBounds(330, 200, 80, 30);
			jb[id].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					new Thread(new Runnable() {
						public void run() {
							if(returnContent == null) {
								Respond(jt.getText() + df.format(day) + "\n");
							}else {
								Respond(returnContent + df.format(day) + "\n");	
							}
							if (questionNo == questions.length-2) {
								git.pushFile(".git");	
							}
						}
					}).start();
					disposeWindow();
					questionNo ++;
					if(questionNo < questions.length)new TemporaryWindow().select();
				}
			});
			break;
		case 3:
			jb[id].setBounds(330, 200, 80, 30);
			jb[id].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Respond(returnContent + count + df.format(day) + "\n");
					
					if (count == questions2.length) {
						new Thread(new Runnable() {
							public void run() {
								git.pushFile(".git");
							}
						}).start();
						disposeWindow();
						questionNo ++;
						new TemporaryWindow().select();
					}else {
						count++;
						jl.setText(questions2[count-1]);
					}
				}
			});
			break;
			

		default:
			break;
		}
		container.add(jb[id]);
	}
	
	public void disposeWindow() {
		this.dispose();
	}

	public void Respond(String str) {
		FileWriter writer;
		try {
			writer = new FileWriter("Respond/Respond1", true);
			writer.write(str);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class RedEnvelopes extends JFrame{
	
	public void paint(Graphics g) {
	
		Image img = new ImageIcon("Pic\\" + moneyCount + ".png").getImage();
		g.setColor(Color.black);
		g.fillRect(0, 0, 1280, 768);
		g.drawImage(img, 0, 27, 360, 500,null);
		g.drawImage(img, 0, 27, 360, 500,null);
	}
	
	String moneyCount;
	Date day = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	gitTest git = new gitTest();
	boolean isTimeout;
	public RedEnvelopes(String moneyCount) {
		
		super("Windows");
		isTimeout = false;
		this.moneyCount = moneyCount;
		this.setVisible(true);
		setResizable(false); //固定窗口大小
//		setBounds(256, 256, 360, 530);
		Respond(moneyCount + "元" + df.format(day) + "\n");
		new Thread(new Runnable() {
			public void run() {
				git.pushFile(".git");
			}
		}).start();
		setBounds(256, 256, 360, 530);
		new Thread(new Runnable() {

			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		setDefaultCloseOperation(HIDE_ON_CLOSE); //触发相应事件后关闭窗口
		// TODO Auto-generated constructor stub
	}
	public RedEnvelopes(String moneyCount,int x,int y,int RedEnvelopesCount) {
		
			super("Windows");
			isTimeout = true;
			this.moneyCount = moneyCount;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			Respond(moneyCount + "元" + df.format(day) + "\n");
			System.out.println(RedEnvelopesCount);
			if(RedEnvelopesCount == 3) {
				new Thread(new Runnable() {
					public void run() {
						git.pushFile(".git");
					}
				}).start();
			}
			this.setVisible(true);
			setResizable(false); //固定窗口大小
			setBounds(x, y, 360, 530);
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						repaint();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			setDefaultCloseOperation(HIDE_ON_CLOSE); //触发相应事件后关闭窗口
			// TODO Auto-generated constructor stub
		}
	
	public void Respond(String str) {
		FileWriter writer;
		try {
			writer = new FileWriter("Respond/Respond1", true);
			writer.write(str);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
