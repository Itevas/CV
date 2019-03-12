package com.lelek.cv.service;

import java.io.IOException;

import static com.lelek.cv.service.ReadFrom.readFrom;

public class Main {

    public static void main(String[] args) throws IOException{

        readFrom("cv.tex");

    }
}
