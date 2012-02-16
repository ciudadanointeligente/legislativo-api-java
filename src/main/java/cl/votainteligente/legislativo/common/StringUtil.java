package cl.votainteligente.legislativo.common;

import java.util.HashSet;
import java.util.Set;

public class StringUtil {
	/**
	 * Clean accents and stopwords from a string
	 * @param string string to clean
	 * @return a clean string
	 */
	public static String cleanString(String string) {
		Set<String> stopwords = new HashSet<String>();
		stopwords.add("el");
		stopwords.add("la");
		stopwords.add("los");
		stopwords.add("las");
		stopwords.add("en");
		stopwords.add("de");

		StringBuilder sb = new StringBuilder();

		for (String word : string.trim().split("[ ]")) {
			word = word.replaceAll("[ÁÀáà]","a").replaceAll("[ÉÈéè]","e").replaceAll("[ÍÌíì]","i").replaceAll("[ÓÒóò]","o").replaceAll("[ÚÙúù]","u").replaceAll("[Ññ]", "n").replaceAll("\\W", "");

			if (stopwords.contains(word)) {
				continue;
			}

			if (sb.length() > 0) {
				sb.append(" ");
			}

			sb.append(word);
		}

		return sb.toString();
	}

	/**
	 * Clean accents from a string
	 * @param string string to clean
	 * @return a clean string
	 */
	public static String cleanAccents(String string) {
		StringBuilder sb = new StringBuilder();

		for (String word : string.trim().split("[ ]")) {
			if (sb.length() > 0) {
				sb.append(" ");
			}

			sb.append(word.replaceAll("[ÁÀáà]","a").replaceAll("[ÉÈéè]","e").replaceAll("[ÍÌíì]","i").replaceAll("[ÓÒóò]","o").replaceAll("[ÚÙúù]","u").replaceAll("[Ññ]", "n").replaceAll("\\W", ""));
		}

		return sb.toString();
	}

	/**
	 * Generates code for cleaning accents in a SQL statement for a given field
	 *
	 * @param field the field to clean
	 * @return SQL code for cleaning the field
	 */
	public static String cleanSQLTableField(String field) {
		return "replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(" + field + ",'Á','A'),'À','A'),'á','a'),'à','a'),'É','E'),'È','E'),'é','e'),'è','e'),'Í','I'),'Ì','I'),'í','i'),'ì','i'),'Ó','O'),'Ò','O'),'ó','o'),'ò','o'),'Ú','U'),'Ù','U'),'ú','u'),'ù','u'),'Ñ','N'),'ñ','n')";
	}
}
