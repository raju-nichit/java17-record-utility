package com.shivay.util;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        UserRecord originalRecord = new UserRecord("Raju", "test@yopmail.com","9890");
        
        // Map of field overrides
        Map<String, Object> overrides = Map.of(
            "username", "rrrrr",
            "email", "kkkkkkk"
        );

        UserRecord updatedRecord = RecordUtil.clone(originalRecord, overrides);

        System.out.println(originalRecord);
        System.out.println(updatedRecord);
    }
}
