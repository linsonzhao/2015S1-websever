package com;

import java.util.Calendar;

import dao.AppInfo;

public class TrackingFile {

	private String sourceFile;
	private String outputFile;
	private static final String sourceFolder = "raw_video";
	private static final String outputFolder = "tracking_video";

	public TrackingFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	
	public String getSourceFile() {
		try {
			sourceFile = sourceFile.replace("/", "\\");
		} catch (Exception e) {

		}
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public String getOutputFile() {
		System.out.println("sourceFile: " + sourceFile);
		try {
			String[] sub = sourceFile.split("\\.")[0].split("/");
			String name = "";
			for (String s : sub) {
				name = s;
			}
			System.out.println(name + "_tracking");
			//for previous version
//			outputFile = sourceFile.replace(name + ".", name + "_tracking.")
//					.replace(sourceFolder, outputFolder).replace("/", "\\")
//					.replace("mp4", "mpg");
			//for new version
			outputFile = sourceFile.replace(name + ".", name + Calendar.getInstance().getTimeInMillis() + "_tracking.")
					.replace(sourceFolder, outputFolder).replace("/", "\\");
			System.out.println("outputFile: " + outputFile);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

}
