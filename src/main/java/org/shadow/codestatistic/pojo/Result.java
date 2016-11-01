package org.shadow.codestatistic.pojo;

public class Result {
	private long codeLines;

	private long commentLines;

	private long blankLines;

	public Result() {

	}

	public Result(long codeLines, long commentLines, long blankLines) {
		this.codeLines = codeLines;
		this.commentLines = commentLines;
		this.blankLines = blankLines;
	}

	public long getCodeLines() {
		return codeLines;
	}

	public void setCodeLines(long codeLines) {
		this.codeLines = codeLines;
	}

	public long getCommentLines() {
		return commentLines;
	}

	public void setCommentLines(long commentLines) {
		this.commentLines = commentLines;
	}

	public long getBlankLines() {
		return blankLines;
	}

	public void setBlankLines(long blankLines) {
		this.blankLines = blankLines;
	}

	public long getTotalLines() {
		return codeLines + commentLines + blankLines;
	}

}
