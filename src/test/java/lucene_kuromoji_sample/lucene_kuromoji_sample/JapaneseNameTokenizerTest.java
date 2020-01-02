package lucene_kuromoji_sample.lucene_kuromoji_sample;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class JapaneseNameTokenizerTest {
	@Test
	public void nameWithSpace1() throws IOException {
		String e = "渋谷 武幸";
		String a = JapaneseNameTokenizer.getFullName("渋谷　武幸");
		Assert.assertEquals(e, a);
	}

	@Test
	public void nameWithSpace2() throws IOException {
		String e = "マーグレーヴ 篤子";
		String a = JapaneseNameTokenizer.getFullName("マーグレーヴ　篤子");
		Assert.assertEquals(e, a);
	}

	@Test
	public void nameWithoutSpace1() throws IOException {
		String e = "小野 ミドリ";
		String a = JapaneseNameTokenizer.getFullName("小野ミドリ");
		Assert.assertEquals(e, a);
	}

	@Test
	public void nameWithoutSpace2() throws IOException {
		String e = "福場 彩";
		String a = JapaneseNameTokenizer.getFullName("福場彩");
		Assert.assertEquals(e, a);
	}
}
