package pl.coderslab.warsztat_01.zadanie_05;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {

        findPopularWords("http://www.onet.pl");

        findMostPopularWords();
    }

    static void findPopularWords(String url) {

        Connection connect = Jsoup.connect(url);

        try {

            Document document = connect.get();

            Elements links = document.select("span.title");

            Path path = Paths.get("popular_words.txt");

            ArrayList<String> outList = new ArrayList<>();


            for (Element elem : links) {

                StringTokenizer tokenizer = new StringTokenizer(elem.text());

                while (tokenizer.hasMoreTokens()) {

                    String word = tokenizer.nextToken().replaceAll("\"", "")

                            .replaceAll("\\.", "")

                            .replaceAll(",", "")

                            .replaceAll(":", "")

                            .replaceAll("\\?", "")

                            .replaceAll("!", "")

                            .replaceAll("-", "")

                            .trim()

                            .toLowerCase();

                    if (word.length() > 3) {

                        outList.add(word);

                    }

                }

            }

            Files.write(path, outList);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    static void findMostPopularWords() {

        Path path = Paths.get("popular_words.txt");

        try {

            List<String> lines = Files.readAllLines(path);

            String[][] words = new String[lines.size()][2];

            for (String line : lines) {

                for (int i = 0; i < words.length; i++) {

                    if (words[i][0] == null) {

                        words[i] = new String[]{line, "1"};

                        break;

                    }

                    if (words[i][0].equals(line)) {

                        int count = Integer.parseInt(words[i][1]);

                        words[i][1] = String.valueOf(count + 1);

                        break;

                    }

                }

            }


            String[][] mostPopular = new String[10][2];


            String word = null;

            for (int i = 0; i < mostPopular.length; i++) {

                int max = 0;

                for (int j = 0; j < words.length; j++) {

                    if (words[j][0] == null) {

                        break;

                    }

                    int count = Integer.parseInt(words[j][1]);

                    if (count > max) {

                        boolean alreadyUsed = false;

                        for (int k = 0; k < mostPopular.length && !alreadyUsed; k++) {

                            if (words[j][0].equals(mostPopular[k][0])) {

                                alreadyUsed = true;

                            }

                        }

                        if (!alreadyUsed) {

                            word = words[j][0];

                            max = count;

                        }

                    }

                }

                mostPopular[i] = new String[]{word, String.valueOf(max)};

            }

            FileWriter out = new FileWriter("most_popular_words.txt", false);

            for (String[] winners : mostPopular) {

                out.append(winners[0] + " " + winners[1] + "\n");

            }

            out.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
