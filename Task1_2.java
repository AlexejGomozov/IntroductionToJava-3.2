package Pattern.Matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Дана строка, содержащая следующий текст (xml-документ):
<notes>
 <note id = "1">
 <to>Вася</to>
 <from>Света</from>
 <heading>Напоминание</heading>
 <body>Позвони мне завтра!</body>
 </note>
 <note id = "2">
 <to>Петя</to>
 <from>Маша</from>
 <heading>Важное напоминание</heading>
 <body/>
 </note>
 </notes>
 Напишите анализатор, позволяющий последовательно возвращать содержимое узлов xml-документа и его тип (открывающий тег, закрывающий тег, содержимое тега, тег без тела). Пользоваться готовыми парсерами XML для решения данной задачи нельзя.
 */

public class Task1_2 {

	private static String xmlAnalyzer(String xml) {

        Pattern open = Pattern.compile("<\\w.+?>");
        Pattern close = Pattern.compile("</\\w+>");
        Pattern body = Pattern.compile(">.+?<");
        Pattern noBody = Pattern.compile("<\\w.+?/>");

        StringBuilder result = new StringBuilder();
        String[] lines = xml.split("\\n\\s");

        for (String line : lines) {
            Matcher mOpen = open.matcher(line);
            Matcher mClose = close.matcher(line);
            Matcher mBody = body.matcher(line);
            Matcher mEmpty = noBody.matcher(line);
            if (mEmpty.find()) {
                result.append(mEmpty.group());
                result.append(" - tag without body\n");
            } else if (mOpen.find()) {
                result.append(mOpen.group());
                result.append(" - open\n");
            }
            if (mBody.find()) {
                result.append(mBody.group().substring(1));
                result.append("\b - body\n");
            }
            if (mClose.find()) {
                result.append(mClose.group());
                result.append(" -close\n");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String xml = "<notes>\n" +
                "    <note id = \"1\">\n" +
                "        <to>Вася</to>\n" +
                "        <from>Света</from>\n" +
                "        <heading>Напоминание</heading>\n" +
                "        <body>Позвони мне завтра!</body>\n" +
                "    </note>\n" +
                "    <note id = \"2\">\n" +
                "        <to>Петя</to>\n" +
                "        <from>Маша</from>\n" +
                "        <heading>Важное напоминание</heading>\n" +
                "        <body/>\n" +
                "    </note>\n" +
                " </notes> \n";
        String xmlAnalysis = xmlAnalyzer(xml);
        System.out.println(xmlAnalysis);
    }	
}
