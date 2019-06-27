package com.Draymond.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DemoSearcheer {

    public static  void main(String []args){

        //设置火狐浏览器的位置
        System.setProperty("webdriver.firefox.bin", "E:\\Mozilla Firefox\\firefox.exe");

        //设置geckdriver
        System.setProperty("webdriver.gecko.driver", "E:\\Mozilla Firefox\\geckodriver.exe");
        WebDriver driver ;driver = new FirefoxDriver();
        driver.get("http://www.baidu.com");


    }

}
