## 用Selenium写的爬虫

## 场景
通过selenium实现人为操作自动化，根据工作经验，学历要求，公司规模，行业领域抓取相对应公司的信息和薪资,输出到information

## 环境
- JDK1.8
- IDEA
- Selenium
```xml
  <dependencies>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-server</artifactId>
            <version>3.141.59</version>
        </dependency>
    </dependencies>
```
- [火狐浏览器 67.0.4][http://www.firefox.com.cn/]
- [geckodriver 最新版本][https://github.com/mozilla/geckodriver/]


## X-path问题细节
```java
//针对 '//' 和 '/' 的区别
/*  通过下面案例
*  '//' 是任意路径下的
*  '/'  是当前路径下的一个子节点
*/

xpath("//div[@class='other-hot-city']//a[contains(text()),'TS']"));
findElement(By.xpath("//li[@class='multi-chosen']/span[contains(text(),'TS')]"));

```