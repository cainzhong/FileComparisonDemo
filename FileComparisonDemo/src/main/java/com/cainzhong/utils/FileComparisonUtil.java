package com.cainzhong.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.ListUtils;

public class FileComparisonUtil {

	// ------- Constants (static final) ----------------------------------------

	// ------- Static Variables (static) ---------------------------------------

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(FindClass.class);

	/*
	 * \s 匹配任何空白字符，包括空格、制表符、换页符等 \S 匹配任何非空白字符
	 */
	private final static String PATTERN_BEGIN_WITH_CLASS = "(class)(\\s*)(=)(\\s*)(\")(\\s*\\S*)(\\s*\\S*)\"";

	private final static String PATTERN_BEGIN_WITH_DOT = "(.)(\\s*\\S*)(\\s*\\S*)(\\{)";
	
//	private final static String PATTERN_BEGIN_WITH_CLASS = "class=\"(.+?)\"";
//
//	private final static String PATTERN_BEGIN_WITH_DOT = "(.)(\\s*\\S*)(\\s*\\S*)(\\{)";
	
	

	// ------- Instance Variables (private) ------------------------------------

	// ------- Constructors ----------------------------------------------------

	// ------- Instance Methods (public) ---------------------------------------
	/**
	 * Get the CSS class from a file using regular expression.Begin with "class=".
	 *
	 * @param inFileName the file path which will be matched.
	 * @param pattern the pattern of regular expression
	 * @param matcherIndextern the index of pattern which used to return the pattern's value 
	 * @return a list of String which contains CSS class.
	 */
	public List<String> findClassInClariaSetting(String inFileName,String pattern,int matcherIndextern) {
		File inFile = new File(inFileName);
		BufferedReader reader = null;
		List<String> cssClassList = new ArrayList<String>();
		try {
			System.out.println("Read the file by line, one time one line：");
			reader = new BufferedReader(new FileReader(inFile));
			String tempString = null;
			// Case Insensitives
			Pattern cssPattern = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
			// Only read one line at one time until 'null'
			while ((tempString = reader.readLine()) != null) {
				
				Matcher cssMatcher = cssPattern.matcher(tempString);
				while (cssMatcher.find()) {
					String matcherString = cssMatcher.group(matcherIndextern);
			        cssClassList.add(matcherString);
					System.out.println(matcherString);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		return cssClassList;
	}

	/**
	 * Get the intersection of two List.
	 * 
	 * @param commonCssClassList a list of Common CSS.
	 * @param projectCssClassList a list of Project CSS.
	 * @return the intersection of two List.
	 */
	@SuppressWarnings("unused")
	private List<String> resultCSS(List<String> commonCssClassList,
			List<String> projectCssClassList,String outFileName) {
		@SuppressWarnings("unchecked")
		List<String> resultList = ListUtils.intersection(commonCssClassList,
				projectCssClassList);
		FileWriter writer = null;
		for(String result:resultList){
			try {
				System.out.println("********************result********************");
				System.out.println(result);
				writer = new FileWriter(outFileName, true);
				writer.write(result + "\r\n");
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
		return resultList;
	}

	public static void main(String args[]) {
		String projectCSSFileName = "E:/findCSS/projectCSS.txt";
		String commonCSSFileName = "E:/findCSS/commonCSS.txt";
		String outFileName = "E:/findCSS/result.txt";
		FileComparisonUtil findClass = new FileComparisonUtil();
		List<String> projectCssClassList = findClass.findClassInClariaSetting(projectCSSFileName,PATTERN_BEGIN_WITH_CLASS,6);
		List<String> commonCssClassList = findClass.findClassInClariaSetting(commonCSSFileName,PATTERN_BEGIN_WITH_DOT,2);
		findClass.resultCSS(commonCssClassList,projectCssClassList,outFileName);
	}
	// ------- Instance Methods (protected) ------------------------------------

	// ------- Instance Methods (private) --------------------------------------

	// ------- Static Methods --------------------------------------------------

	// ------- Optional Inner Class ------------------------------------------

}
