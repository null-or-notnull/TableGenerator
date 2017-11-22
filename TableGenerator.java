
import java.util.ArrayList;
import java.util.Arrays;

public class TableGenerator {
    private String topTitle;
    private ArrayList<String> titles;
    private ArrayList<String[]> data;
    private ArrayList<Integer> length;
    final int SPACE = 5;

    private String node = "+";
    private String horizontalLine = "-";
    private String verticalLine = "|";

    public TableGenerator() {
        titles = new ArrayList<>();
        data = new ArrayList<>();
        this.topTitle = "";
    }

    public TableGenerator(String topTitle) {
        titles = new ArrayList<>();
        data = new ArrayList<>();
        this.topTitle = "<<" + topTitle + ">>";
    }

    public void setTableStyle(String all) {
        node = all;
        horizontalLine = all;
        verticalLine = all;
    }

    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(String... titles) {
        this.titles.addAll(Arrays.asList(titles));

    }

    public void addData(String... data) throws Exception {
        if (data.length != titles.size()) throw new Exception("incorrect header");
        this.data.add(data);
    }

    private void updateLength() {
        length = new ArrayList<>();
        for (String title : titles)
            length.add(title.length());

        for (String[] s : data)
            for (int i = 0; i < s.length; i++)
                if (length.get(i) < s[i].length())
                    length.set(i, s[i].length());

        for (int i = 0; i < length.size(); i++)
            length.set(i, length.get(i) + SPACE);
        int len = getLength();
        while (topTitle.length() > len) {
            for (int i = 0; i < length.size(); i++)
                length.set(i, length.get(i) + 1);
            len = getLength();
        }

    }

    private int getLength() {
        int len = 0;
        for (int l : length)
            len += l;
        return len;
    }

    public String generateTable() {
        String table = "";
        updateLength();
        table += generateTopTitle();
        table += br();
        table += generateTitles();
        table += br();
        table += generateBody();

        return table;
    }

    private String generateBody() {
        String table = "";
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                String[] temp = data.get(i);
                for (int j = 0; j < temp.length; j++) {
                    table += verticalLine;
                    table += String.format("%-" + length.get(j) + "s", temp[j]);
                }
                table += verticalLine;
                table += "\n";
            }
            table += br();
        }
        return table;
    }

    private String generateTitles() {
        String table = "";
        for (int i = 0; i < titles.size(); i++) {
            table += verticalLine;
            table += String.format("%-" + length.get(i) + "s", titles.get(i));
        }
        table += verticalLine;
        table += "\n";
        return table;
    }

    private String generateTopTitle() {
        String header = "";
        header += generateHLine();
        int temp = generateHLine().length();

        header += verticalLine;
        String content = "";
        content = String.format("%-" + ((generateHLine().length() - 3)) + "s", topTitle);
        header += content;
        header += verticalLine;
        header += "\n";
        return header;
    }

    private String generateHLine() {
        String header = "";
        header += node;
        for (int i = 0; i < br().length() - 3; i++) {
            header += horizontalLine;
        }
        header += node;
        header += "\n";
        return header;
    }

    private String br() {
        String table = "";
        for (int i = 0; i < titles.size(); i++) {
            table += node;
            for (int j = 0; j < length.get(i); j++)
                table += horizontalLine;
        }
        table += node;
        table += "\n";
        return table;
    }

}
