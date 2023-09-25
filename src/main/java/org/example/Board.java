package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Board {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //리스트 함수로 만들어서 하기.

        while(true) {
            String cmd = scan.nextLine();
            Connection conn = null; // DB 접속하는 객체
            Statement stmt = null; // SQL 전송하는 객체1
            ResultSet rs = null; // 결과 받아오는 객체. 얘가포인터임. 행단위로 가리킴.


            String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
            String user = "root";
            String pass = "";

            if(cmd.equals("list")){
                Class.forName("com.mysql.cj.jdbc.Driver");
                // 2. Connection 획득
                conn = DriverManager.getConnection(url, user, pass);
                //3. Statement 생성
                stmt = conn.createStatement();

                String sql = "SELECT * FROM article";
                rs = stmt.executeQuery(sql);
                //board db의 article table에서 데이터를 꺼내와 출력
                while(rs.next()) {
                    System.out.println(rs.getString("title"));
                    System.out.println(rs.getString("content")); // 문자열로 리턴
                    System.out.println(rs.getInt("id")); // 문자열로 리턴
                    System.out.println("========================");
                }

            }else if(cmd.equals("add")){
                Connection conn = null; // DB 접속하는 객체
                Statement stmt = null; // SQL 전송하는 객체
                ResultSet rs = null; // 결과 받아오는 객체. 얘가포인터임. 행단위로 가리킴.

                String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
                String user = "root";
                String pass = "";


                try {

                    // 1. 드라이버 세팅
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    // 2. Connection 획득
                    conn = DriverManager.getConnection(url, user, pass);
                    //3. Statement 생성
                    stmt = conn.createStatement();


                    Class.forName("com.mysql.cj.jdbc.driver");

                    System.out.println("3번까지 문제 없이 실행");

                    String sql = "INSERT INTO article SET title = '제목2',content = '내용4'"; //이부분도 이해하기1!
                    stmt.executeUpdate(sql);
                    System.out.println("게시물 등록이 완료 되었습니다.");
                    //조회 결과가 있는거 -> executeQuery(sql) 함수실행
                    //조회 결과가 없는거 -> executeUpdate(sql)


                    while(rs.next()) {
                        System.out.println(rs.getString("title"));
                        System.out.println(rs.getString("content")); // 문자열로 리턴
                        System.out.println(rs.getInt("id")); // 문자열로 리턴
                        System.out.println("========================");
                    }

                } catch (Exception e) {
                    System.out.println("접속 시도중 문제 발생!!");
                }
            }
            else if(cmd.equals("exit")){
                System.out.println("프로그램 종료.");
                break;
            }
        }
    }
}
