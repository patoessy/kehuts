/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kehuts.module.utils;

/**
 *
 * @author patrick
 */
public record ServerReponse<T> (
        T data,
        boolean successful,
        String message,
        int resultCode
){}
