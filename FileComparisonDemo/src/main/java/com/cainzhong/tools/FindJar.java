package com.cainzhong.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Cain
 * 
 */
public class FindJar {
	/*the List has all the file path*/
	private static List<String> fileList = new ArrayList<String>();
	/*the List has all the file path which is a jar file*/
	private static List<String> fileListHasJar = new ArrayList<String>();

	public void readfile(String filePath) throws FileNotFoundException,
			IOException {
		try {
			File file = new File(filePath);
			if (!file.isDirectory()) {
				if (findJarInDirectory(file) == false) {
					fileList.add(filePath);
				} else {
					fileListHasJar.add(filePath);
				}
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filePath + "\\" + filelist[i]);
					if (!readfile.isDirectory()) {
						if (findJarInDirectory(readfile) == false) {
							fileList.add(filePath);
						} else {
							fileListHasJar.add(filePath);
						}
					} else if (readfile.isDirectory()) {
						readfile(filePath + "\\" + filelist[i]);
					}
				}

			}
		} catch (FileNotFoundException e) {
			System.out.println("readfile() Exception:" + e.getMessage());
		}
	}

	private boolean findJarInDirectory(File file) {
		String jarSurfix = "jar";
		String fileName = file.getName();
		String surfix = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (surfix.equals(jarSurfix)) {
			return true;
		}
		return false;

	}

	private List<String> removeDuplicatedElement(List<String> list) {
		List<String> tempList = new ArrayList<String>();
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String a = it.next();
			if (tempList.contains(a)) {
				it.remove();
			} else {
				tempList.add(a);
			}
		}
		return tempList;
	}

	private void deleteDirectory(List<String> list) {
		List<String> unDeltedDirectoryList = new ArrayList<String>();
		for (String file : list) {
			String newFile = file.replace('\\', '/');
			// If this pathname denotes a directory, then the directory must be
			// empty in order to be deleted.
			File deleteDirectory = new File(newFile);
			String[] filelist = deleteDirectory.list();
			// delete the file first which is under the given directory, then
			// delete the directory.
			for (int i = 0; i < filelist.length; i++) {
				File deleteFile = new File(deleteDirectory.getAbsolutePath()
						+ "\\" + filelist[i]);
				deleteFile.delete();
			}
			if (deleteDirectory.delete()) {
				System.out.println(deleteDirectory.getAbsolutePath() + " deleted.");
			} else {
				unDeltedDirectoryList.add(deleteDirectory.getAbsolutePath());
			}
		}

		if (unDeltedDirectoryList.size() > 0) {
			int deletedDirectory = list.size() - unDeltedDirectoryList.size();
			System.out.println("There are " + deletedDirectory + "directory which has no jar been deleted.");
			for (String unDeltedDirectory : unDeltedDirectoryList) {
				System.out.println(unDeltedDirectory + " can not delete.");
			}
			System.out.println("There are " + unDeltedDirectoryList.size() + "directory which has no jar can not be deleted.");
		} else {
			System.out.println("There are all irectory which has no jar been deleted.");
		}
	}

	private List<String> pickUpNoJarDirectory(List<String> fileListHasJar,
			List<String> fileList) {
		Iterator<String> it = fileListHasJar.iterator();
		while (it.hasNext()) {
			String a = it.next();
			if (fileList.contains(a)) {
				fileList.remove(a);
			}
		}
		return fileList;
	}

	public static void main(String args[]) throws FileNotFoundException,
			IOException {
		String filePath = "C:/Users/Cain/.m2/repository";
		FindJar findJar = new FindJar();
		findJar.readfile(filePath);
		List<String> uniqueList = findJar.removeDuplicatedElement(fileList);
		List<String> hasNoJarList = findJar.pickUpNoJarDirectory(
				fileListHasJar, uniqueList);
		for (String unique : hasNoJarList) {
			System.out.println(unique);
		}
		System.out.println("There are " + hasNoJarList.size()
				+ " do not have jar file.");
		System.out.println("Input 'y or Y' to delete the above directory.");
		if (System.in.read() == 'y' || System.in.read() == 'Y') {
			findJar.deleteDirectory(hasNoJarList);
		}
	}
}
