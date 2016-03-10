package com.device42.client.util;

import java.util.Collection;

public final class StringUtil {
	private StringUtil() {}
	public static String stringJoin(String separator, Collection<String> collection) {
		StringBuilder result = new StringBuilder();
		boolean start = true;
		for (String data : collection) {
			if (start) {
				start = false;
			} else {
				result.append(separator);
			}
			result.append(data);
		}
		return result.toString();
	}
	
	public static boolean isNotBlank(String value) {
		return value != null && value.trim().length() > 0;
	}

}
