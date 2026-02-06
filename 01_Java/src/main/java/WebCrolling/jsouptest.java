package WebCrolling;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class jsouptest {
    
    // 우리 학과 홈페이지 교과과정 크롤링하기
    public void cehomepage() {

        List<ComputerEngineering> celist = new ArrayList<>();   // ComputerEngineering 객체 설계 필요 ⭐️
        String url = "https://ce.jnu.ac.kr/ce/16658/subview.do?enc=Zm5jdDF8QEB8JTJGc2JqJTJGY2UlMkYzNjAlMkZsaXN0LmRvJTNGc3JjaFlyJTNEMjAyMyUyNnNyY2hHcmQlM0QlMjZzcmNoVHJtJTNEJTI2c3JjaENvZGUlM0QlMjZzcmNoTmFtZSUzRCUyNg%3D%3D";

        // Document 객체 생성 -> 학년, 학기, 교과목명 추출해서 celist에 추가
        try {

            Document ceDoc = Jsoup.connect(url).get();
            Elements elements = ceDoc.select("tr");

            // 학년: #_sbj > form:nth-child(3) > div > table > tbody > tr > td:nth-child(2)
            // 학기: #_sbj > form:nth-child(3) > div > table > tbody > tr > td:nth-child(3)
            // 교과목명: #_sbj > form:nth-child(3) > div > table > tbody > tr > td.title.subJect


            // 기본 enhanced for문: 각 tr (Element)에서 값 추출
            for (Element ele : elements) {

                String grade = ele.select("td:nth-child(2)").text();
                String term = ele.select("td:nth-child(3)").text();
                String subject = ele.select("td.title.subJect").text();

                ComputerEngineering ce = ComputerEngineering.builder()
                                    .grade(grade)
                                    .term(term)
                                    .subject(subject)
                                    .build();
                celist.add(ce);

                System.out.printf("%s %s, 교과목명: %s%n", grade, term, subject);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

public static void main(String[] args) {
    
    jsouptest j = new jsouptest();
    j.cehomepage();
}

}

