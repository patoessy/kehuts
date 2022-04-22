package com.kehuts.module.utils;

public record PagedResults (int currentPage, int pages, int size, Object data){}
