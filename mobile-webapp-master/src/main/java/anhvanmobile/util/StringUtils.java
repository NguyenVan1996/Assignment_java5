package anhvanmobile.util;

public class StringUtils {
	
	public static String formatProductName(String name) {
		if(name==null) throw new NullPointerException("Parameter str is null.");
		name = VNCharacterUtils.removeAccent(name);
		name = replaceWhiteSpaceWithDash(name);
		return name;
	}
	
	public static String replaceWhiteSpaceWithDash(String str) {
		if(str==null) throw new NullPointerException("Parameter str is null.");
		return str.trim().replaceAll("\\s+", "-");
	}
	
	public static String getFileExtension(String fileName) {
		if(fileName==null) throw new NullPointerException("The param fileName is null!");
		String name = fileName.trim();
		if(name.contains(".")) {
			int lastIndexOf = name.lastIndexOf(".");
			String extension = name.substring(lastIndexOf, name.length());
			return extension;
		}
		return null;
	}
	
}
