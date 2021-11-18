package com.hovel.util;

import com.deepoove.poi.XWPFTemplate;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class WordDemo {

    public static void main(String[] args) throws IOException {
//        XWPFTemplate template = XWPFTemplate.compile("C:\\Users\\Administrator\\Desktop\\test.doc").render(
//                new HashMap<String, Object>(){{
//                    put("params", "Hi, poi-tl Word模板引擎");
//                }});
//        template.writeAndClose(new FileOutputStream("d:\\output.doc"));
        String filePath = "C:\\Users\\Administrator\\Desktop\\test.doc";
        try {
            HWPFDocument doc = new HWPFDocument(new FileInputStream(filePath));
            doc = replaceText(doc, "{{param}}", "MyValue1");
            saveWord("d:\\output.doc", doc);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private static HWPFDocument replaceText(HWPFDocument doc, String findText, String replaceText){
        Range r1 = doc.getRange();

        for (int i = 0; i < r1.numSections(); ++i ) {
            Section s = r1.getSection(i);
            for (int x = 0; x < s.numParagraphs(); x++) {
                Paragraph p = s.getParagraph(x);
                for (int z = 0; z < p.numCharacterRuns(); z++) {
                    CharacterRun run = p.getCharacterRun(z);
                    String text = run.text();
                    if(text.contains(findText)) {
                        run.replaceText(findText, replaceText);
                    }
                }
            }
        }
        return doc;
    }

    private static void saveWord(String filePath, HWPFDocument doc) throws FileNotFoundException, IOException{
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(filePath);
            doc.write(out);
        }
        finally{
            out.close();
        }
    }
}
