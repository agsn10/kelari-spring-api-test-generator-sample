package com.example.demo.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class IsJohnMatcher extends BaseMatcher<String> {

    @Override
    public boolean matches(Object item) {
        return "John".equals(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a string equal to 'John'");
    }

    public static Matcher<String> isJohn() {
        return new IsJohnMatcher();
    }
}