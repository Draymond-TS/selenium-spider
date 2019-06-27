package com.Draymond.selenium;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Job {
    private String money;
    private String company;
    private String position;
    private String industry;

    @Override
    public String toString() {
        return "公司:"+company+" 职位:"+position+" 公司性质:"+industry;
    }
}
