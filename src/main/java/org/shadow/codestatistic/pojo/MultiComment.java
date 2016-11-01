package org.shadow.codestatistic.pojo;

public class MultiComment {
	private String prefix;

	private String suffix;

	public MultiComment(String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSuffix() {
		return suffix;
	}
}
