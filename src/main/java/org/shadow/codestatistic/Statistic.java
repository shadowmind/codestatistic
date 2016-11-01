package org.shadow.codestatistic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import org.shadow.codestatistic.pojo.Config;
import org.shadow.codestatistic.pojo.MultiComment;
import org.shadow.codestatistic.pojo.Result;

public class Statistic {
	private long codeLines = 0;
	private long commentLines = 0;
	private long blankLines = 0;

	private Config config;

	public Result run(Config config) {
		this.config = config;

		File file = new File(config.getRootPath());
		if (file.exists()) {
			statistic(file);
		} else {
			throw new IllegalArgumentException(config.getRootPath() + " don't exists");
		}

		Result result = new Result(codeLines, commentLines, blankLines);

		return result;
	}

	private void statistic(File file) {
		if (file.isFile()) {
			if (isStatisticFile(file.getName())) {
				count(file);
			}
		} else {
			File[] files = file.listFiles();
			for (File f : files) {
				statistic(f);
			}
		}
	}

	private void count(File file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty()) {
					blankLines++;
				} else if (isSingleComment(line)) {
					commentLines++;
				} else if (isMultiComment(line)) {
					commentLines++;
				} else {
					codeLines++;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isStatisticFile(String fileName) {
		Set<String> extensions = config.getExtensions();
		for (String suffix : extensions) {
			if (fileName.endsWith(suffix)) {
				return true;
			}
		}

		return false;
	}

	private boolean isSingleComment(String line) {
		Set<String> singleComment = config.getSingleComment();
		Set<MultiComment> multiComment = config.getMultiComment();

		for (String prefix : singleComment) {
			if (line.startsWith(prefix)) {
				return true;
			}
		}

		for (MultiComment mc : multiComment) {
			if (line.startsWith(mc.getPrefix()) && line.endsWith(mc.getSuffix())) {
				return true;
			}
		}

		return false;
	}

	boolean inMultiComment = false;
	MultiComment currentMultiComment = null;

	private boolean isMultiComment(String line) {
		Set<MultiComment> multiComment = config.getMultiComment();

		for (MultiComment mc : multiComment) {
			if (line.startsWith(mc.getPrefix())) {
				inMultiComment = true;
				currentMultiComment = mc;

				return true;
			}

			if (inMultiComment && line.endsWith(currentMultiComment.getSuffix())) {
				inMultiComment = false;

				return true;
			}
		}

		if (inMultiComment) {
			return true;
		}

		return false;
	}

}
