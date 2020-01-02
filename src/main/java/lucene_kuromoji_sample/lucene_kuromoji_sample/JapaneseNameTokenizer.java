package lucene_kuromoji_sample.lucene_kuromoji_sample;

import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.codelibs.neologd.ipadic.lucene.analysis.ja.JapaneseTokenizer;

import java.io.IOException;
import java.io.StringReader;

public class JapaneseNameTokenizer {
	private static final char[] c = { '\u3000' };
	private static final String WSPACE = new String(c); // 全角スペース

	private static boolean isEmpty(String value) {
		return (value == null || value.trim().length() == 0 || value.replaceAll(WSPACE, "").length() == 0);
	}

	public static String getFullName(String name) throws IOException {
		if (isEmpty(name))
			return "";

		// 返却する漢字氏名・フリガナ氏名
		StringBuilder kanjiName = new StringBuilder();

		try (JapaneseTokenizer tokenizer = new JapaneseTokenizer(null, false, JapaneseTokenizer.Mode.NORMAL)) {

			tokenizer.setReader(new StringReader(name));

			CharTermAttribute charTermAttribute = tokenizer.addAttribute(CharTermAttribute.class);
			tokenizer.reset();
			while (tokenizer.incrementToken()) {
				String kanji = charTermAttribute.toString();

				// 半角・全角スペース・*はskip
				if (isEmpty(kanji) || kanji.equals("*")) {
					continue;
				}

				kanjiName.append(kanji + " ");
			}

			return kanjiName.toString().trim();
		}
	}
}
