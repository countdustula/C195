package com.example.c195;

@FunctionalInterface
/**This is the interface for the "alert" function. */
public interface MyInterface {

    /**This is the lambda function that is used in this program.  It alerts the user with the given arguments.
     *  It's used in the "add customer" screen if a field is empty when
     * hitting submit */
    public void alert(String x, String y, String z);
}
