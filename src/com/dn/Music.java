package com.dn;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Music {
//	 public static void main(String[] args) throws Exception {
//		 new TestInternet().internet(args);
//	 }
	public void playMusic() throws Exception {
		File file = new File("source/周冬雨 - 如果我爱你.mp3");
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream stream = new BufferedInputStream(fis);

		final Player player = new Player(stream);
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						player.play();
					} catch (JavaLayerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		System.out.println("播放中");
	}
}

class TestInternet {

	static BufferedReader bufferedReader;

	public void internet(String[] args) throws IOException {

//		Scanner input = new Scanner(System.in);

//		System.out.print("请输入IP(180.97.33.107)或者域名(baidu.com):");

//		String address = input.next();
		String address = "baidu.com";

		try {

			Process process = Runtime.getRuntime()

					.exec("ping " + address + " -t");

			bufferedReader = new BufferedReader(new InputStreamReader(

					process.getInputStream()));

			String connectionStr = null;

			while ((connectionStr = bufferedReader.readLine()) != null) {

				System.out.println(connectionStr);

			}

		} catch (IOException e) {

			System.out.println("链接失败");

			e.printStackTrace();

		} finally {

			bufferedReader.close();

		}

	}

}