public class HtmlTableBuilder {
	private StringBuilder sb;
	
	public HtmlTableBuilder(String title){
		this.sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>");
		sb.append(EscapeHTML.stringToHTMLString(title));
		sb.append("</title>");
		sb.append("</head>");
		sb.append("<body>");
	}
	
	public HtmlTableBuilder startTable(){
		sb.append("<table style=\" text-align: left;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">");
		sb.append("<tbody>");
		return this;
	}
	
	public HtmlTableBuilder endTable(){
		sb.append("</tbody>");
		sb.append("</table>");
		return this;
	}
	
	public HtmlTableBuilder startRow(){
		sb.append("<tr>");
		return this;
	}
	
	public HtmlTableBuilder endRow(){
		sb.append("</tr>");
		return this;
	}
	
	public HtmlTableBuilder addCol(String str){
		sb.append("<td>");
		sb.append(EscapeHTML.stringToHTMLString(str));
		sb.append("</td>");
		return this;
	}
	
	public String output(){
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
	
}
