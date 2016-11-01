package org.shadow.codestatistic.pojo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Config {
	private String rootPath;
	private Set<String> extensions;
	private Set<String> singleComment;
	private Set<MultiComment> multiComment;

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public Set<String> getExtensions() {
		return extensions;
	}

	public void setExtensions(String extensions) {
		this.extensions = new HashSet<String>();
		String[] extens = extensions.replaceAll("\\s*", "").split(",");
		for (String exten : extens) {
			this.extensions.add(exten.trim());
		}
	}

	public Set<String> getSingleComment() {
		return singleComment;
	}

	public void setSingleComment(String singleComment) {
		this.singleComment = new HashSet<String>(Arrays.asList(singleComment.replaceAll("\\s*", "").split(",")));
	}

	public Set<MultiComment> getMultiComment() {
		return multiComment;
	}

	public void setMultiComment(String multiComment) {
		this.multiComment = new HashSet<MultiComment>();
		String[] rawMultiComment = multiComment.trim().split(",");
		for (String pattern : rawMultiComment) {
			String[] p = pattern.trim().split(" ");
			this.multiComment.add(new MultiComment(p[0], p[1]));
		}
	}

}
