package com.dn;

import java.io.File;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javazoom.jl.decoder.JavaLayerException;


public class gitTest {
	final static Logger LOG = LoggerFactory.getLogger(gitTest.class);
	public static String cloneRepository(String url, String localPath) {
		try {
			System.out.println("��ʼ����......");
			CloneCommand cc = Git.cloneRepository().setURI(url);
			
			cc.setDirectory(new File(localPath)).call();

			System.out.println("�������......");

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public static String pushFile(String localPath) {
		
		try {
			System.out.println("��ʼ�ϴ�......");
			
			Git git = Git.open(new File(localPath));
			 git.add().addFilepattern("Respond").call();  
             //�ύ
             git.commit().setMessage("����0").call();   
             git.push().setCredentialsProvider(new UsernamePasswordCredentialsProvider("dxiaoping", "dxp971216")).call();
//             LOG.info("push " + git.getRepository()+File.separator+git.getRepository().getBranch());
			System.out.println("�ϴ����......");
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("��������������½���");
//			LOG.error("Commit And Push error! \n"+e.getMessage());
			return "error";
		}
	}

//	public static void main(String[] args) {
////		String localPath = "C:/Users/16935/Desktop/Exetest/1437";
//		String localPath = ".git";
//		String url = "https://github.com/dxiaoping";
//
//		pushFile(localPath);
//	}
}