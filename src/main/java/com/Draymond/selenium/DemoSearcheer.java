package com.Draymond.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class DemoSearcheer {

    public static void main(String[] args) {
        WebDriver driver = init();

        List<Job> jobs = new LinkedList<Job>();

        try {
            // 进入首页
            driver.get("https://www.lagou.com/zhaopin/Java/?labelWords=label");

            //设置筛选条件
            choseOptions(driver);

            //开始解析页面，分页获取工资
            findMoneyByPagination(driver, jobs);

            //打印工资
            printMoney(jobs);
            driver.quit();
            System.out.println("程序执行完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WebDriver init() {
        //设置火狐浏览器的位置
        System.setProperty("webdriver.firefox.bin", "E:\\Mozilla Firefox\\firefox.exe");

        //设置geckdriver
        System.setProperty("webdriver.gecko.driver", "E:\\Mozilla Firefox\\geckodriver.exe");

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }


    private static void choseOptions(WebDriver driver) {
        // 选择城市
        String cityName = "北京";
        WebElement cityAuthorElement = driver.findElement(By.xpath("//div[@class='other-hot-city']//a[contains(text(),'" + cityName + "')]"));
        cityAuthorElement.click();

        // 选择工作经验
        // 不限 应届毕业生 3年及以下 3-5年 5-10年 10年以上 不要求
        choseByTitle(driver, "应届毕业生", "工作经验");

        // 选择学历要求
        // 不限 大专 本科 硕士 博士 不要求
        choseByTitle(driver, "本科", "学历要求");


        // 选择行业领域
        choseByTitle(driver, "移动互联网", "行业领域");
    }


    private static void choseByTitle(WebDriver driver, String companyMember, String companyMemberChosenTitle) {
        WebElement companyElement = driver.findElement(By.xpath("//li[@class='multi-chosen']/span[contains(text(),'" + companyMemberChosenTitle + "')]"));
        WebElement companyAuthorElement = companyElement.findElement(By.xpath("../a[contains(text(),'" + companyMember + "')]"));
        companyAuthorElement.click();
    }


    private static void findMoneyByPagination(WebDriver driver, List<Job> jobs) {
        List<WebElement> jobElements = driver.findElements(By.className("list_item_top"));
        for (WebElement jobElement : jobElements) {
            String money = jobElement.findElement(By.className("money")).getText();
            String position = jobElement.findElement(By.tagName("h3")).getText();
            String companyName = jobElement.findElement(By.className("company_name")).getText();
            String industry = jobElement.findElement(By.className("industry")).getText();
            Job job = new Job();
            job.setCompany(companyName);
            job.setIndustry(industry);
            job.setMoney(money);
            job.setPosition(position);
            jobs.add(job);
        }

        if (isElementExist(driver, By.className("pager_next"))) {
            WebElement nextPageElement = driver.findElement(By.className("pager_next"));
            if (nextPageElement != null) {
                boolean canClickNextPageBtn = !nextPageElement.getAttribute("class").contains("pager_next_disabled");
                if (canClickNextPageBtn) {
                    nextPageElement.click();
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                    }
                    findMoneyByPagination(driver, jobs);
                }
            }
        }
    }

    private static void printMoney(List<Job> jobs) throws Exception {
        //当前项目路径
        String currentFile = new File("").getCanonicalPath().replace("\\", "\\\\");
        String localtion = currentFile + "\\src\\main\\resources\\information.txt";


        FileOutputStream out = new FileOutputStream(localtion, false);
        for (Job job : jobs) {
            out.write(job.toString().getBytes());
            out.write("\r\n".getBytes());
            out.flush();
        }
        out.close();
    }

    public static boolean isElementExist(WebDriver driver, By selector) {
        try {
            driver.findElement(selector);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
