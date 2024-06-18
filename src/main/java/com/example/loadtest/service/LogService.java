package com.example.loadtest.service;

public interface LogService {

    public long sendAllUsingSync();

    public long sendAllUsingAsync();

}