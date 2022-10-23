package test;


import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

import org.junit.jupiter.api.DisplayName;

public class AndroidSearchTests extends TestBase {
    @Tag("mobile")
    @Test
    @DisplayName("Mobile search test for Wikipedia")
    void searchTest() {
        step("Type search", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Video");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));

    }

    @Tag("mobile")
    @Test
    @DisplayName("First NFTY search result")
    void LetterSearch() {
        step("NFTY type search", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("NFTY");
        });
        step("Check first result", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_description")).shouldHave(text("Reform Jewish youth organization"));
        });
    }


    @Tag("mobile")
    @Test
    @DisplayName("Hide this card feature")
    void hideThisCard() {
        step("Click on article settings", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/view_list_card_header_menu")).click();
        });
        step("Check possibility to hide the selected article", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/title")).shouldHave(exactText("Hide this card"));
            $(AppiumBy.id("org.wikipedia.alpha:id/title")).click();
        });

    }

    @Tag("mobile")
    @Test
    @DisplayName("Language settings")
    void settingsTest() {
        step("Open wiki settings tub", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/nav_more_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/main_drawer_settings_container")).click();
        });
        step("Click on Wikipedia language settings", () -> {
            $(AppiumBy.id("android:id/title")).click();
            //валится когда пытается кликнуть на "Wikipedia languages"
                $(AppiumBy.linkText("Wikipedia languages")).click();
          //  $(AppiumBy.id("org.wikipedia.alpha:id/wiki_language_title")).click();
        });
        step("Search for Deutsch language", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/preference_languages_filter")).sendKeys("Deutsch");
        });
        step("Setup Deutsch language", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/language_subtitle")).click();
        });
        step("Check that we got selected language in settings", () -> {
            $(AppiumBy.id("android:id/summary")).shouldHave(exactText("Deutsch"));
        });


    }
}
