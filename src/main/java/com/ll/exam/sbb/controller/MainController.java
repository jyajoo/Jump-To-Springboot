package com.ll.exam.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {

    int increaseNo = -1;

    @RequestMapping("/sbb")
    @ResponseBody                      // 아래 함수의 리턴값을 문자열화하여 브라우저 응답 바디에 담는다.
    public String index() {
        System.out.println("Hello");   // 서버에서 출력
        return "안녕하세요~~";           // 먼 미래에 브라우저에 출력
    }

    @GetMapping("/page1")
    @ResponseBody
    public String showGet() {
        return """
                <form method = "POST" action = "/page2">
                    <input type = "text" name = "age" placeholder = "나이" />
                    <input type = "submit" value = "page2로 POST 방식으로 이동" />
                </form>
                """;
    }

    @PostMapping("/page2")
    @ResponseBody
    public String showPage2Post(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1> 입력된 나이 : %d</h1>
                <h1> 안녕하세요, POST 방식으로 접속되었습니다. </h1>
                """.formatted(age);
    }

    @GetMapping("/page2")
    @ResponseBody
    public String showPage2Get(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요, POST 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @GetMapping("/plus")
    @ResponseBody
    public int showPlus(int a, int b) {
        return a + b;
    }

    @GetMapping("/minus")
    @ResponseBody
    public int showMinus(int a, int b) {
        return a - b;
    }

    @GetMapping("/increase")
    @ResponseBody
    public int showIncrease() {
        return ++increaseNo;
    }

    @GetMapping("/gugudan")
    @ResponseBody
    public String showGugudan(Integer dan, Integer limit) {
        if (limit == null) {
            limit = 9;
        }

        if (dan == null) {
            dan = 9;
        }

        Integer finalDan = dan;
        return IntStream.rangeClosed(1, limit)
                .mapToObj(i -> "%d * %d = %d".formatted(finalDan, i, finalDan * i))
                .collect(Collectors.joining("<br>\n"));
    }

    @GetMapping("/mbti/{name}")
    @ResponseBody
    public String mbti(@PathVariable String name) {
        String rs = switch (name) {
            case "홍길동" -> "INFP";
            case "임꺽정" -> "ENFP";
            case "정윤아" -> "ISFJ";
            default -> "MBTI";
        };
        return rs;
    }

    @GetMapping("/saveSessionAge/{name}/{value}")
    @ResponseBody
    public String saveSessionAge(@PathVariable String name, @PathVariable String value, HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute(name, value);
        return "세션변수 %s의 값이 %s(으)로 설정되었습니다.".formatted(name, value);
    }

    @GetMapping("/getSessionAge/{name}")
    @ResponseBody
    public String getSessionAge(HttpSession httpSession, @PathVariable String name) {
        String value = String.valueOf(httpSession.getAttribute(name));

        return "세션변수 %s의 값이 %s 입니다.".formatted(name, value);
    }
}
