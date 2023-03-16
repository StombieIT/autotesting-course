package org.stombie;

import org.openqa.selenium.By;

public class FeedPage {

    public By getUserNavigationLink() {
        return By.cssSelector(".nav-side.__navigation a");
    }
}
