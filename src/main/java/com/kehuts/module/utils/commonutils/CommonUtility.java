/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kehuts.module.utils.commonutils;

import java.time.LocalDateTime;
import java.time.Period;

/**
 *
 * @author patrick
 */
public class CommonUtility {
    public static LocalDateTime getDaysAhead(int days) {
        Period period = Period.ofDays(days);
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime.plus(period);
        return dateTime;
    }
}
