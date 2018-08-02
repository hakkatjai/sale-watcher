package com.github.hakkatjai.saleswatcher.watcher;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

@Configuration
@EnableScheduling
public class WatcherScheduler {

    private static final String NON_NUMERIC_REGEX = "[^\\d.]";
    private WatcherRepository watcherRepository;

    public WatcherScheduler(WatcherRepository watcherRepository) {
        this.watcherRepository = watcherRepository;
    }

    @Scheduled(cron = "* 0/30 * * * *")
    public void watch() {
        watcherRepository.findAll().forEach(record -> {
            Watcher watcherFromDb = watcherRepository.findById(record.getId()).orElse(null);
            if (watcherFromDb != null) {
                try {
                    Document doc = Jsoup.connect(watcherFromDb.getUrl()).get();
                    Elements currentPriceElements = doc.getElementsByAttributeValue(watcherFromDb.getCurrentPriceAttributeName(), watcherFromDb.getCurrentPriceAttributeValue());
                    Elements formerPriceElements = doc.getElementsByAttributeValue(watcherFromDb.getFormerPriceAttributeName(), watcherFromDb.getFormerPriceAttributeValue());
                    Double currentPrice = watcherFromDb.getCurrentPrice();
                    if (currentPriceElements.iterator().hasNext()) {
                        String currentPriceText = currentPriceElements.iterator().next().text();
                        if (StringUtils.isNotBlank(currentPriceText)) {
                            currentPrice = new Double(currentPriceText.replace(",", ".").replaceAll(NON_NUMERIC_REGEX, ""));
                        }
                    }
                    Double formerPrice = watcherFromDb.getFormerPrice();
                    if (formerPriceElements.iterator().hasNext()) {
                        String formerPriceText = formerPriceElements.iterator().next().text();
                        if (StringUtils.isNotBlank(formerPriceText)) {
                            formerPrice = new Double(formerPriceText.replace(",", ".").replaceAll(NON_NUMERIC_REGEX, ""));
                        }

                    }
                    Watcher watcherFromInternet = new Watcher(watcherFromDb);
                    watcherFromInternet.setCurrentPrice(currentPrice);
                    watcherFromInternet.setFormerPrice(formerPrice);

                    if (watcherFromDb.getCurrentPrice() == null) {
                        watcherRepository.save(watcherFromInternet);
                        System.out.println("Initialized user entry with prices from internet");
                    } else if (watcherFromInternet.isCheaperThan(watcherFromDb)) {
                        watcherRepository.save(watcherFromInternet);
                        System.out.println("send notification");
                    } else {
                        System.out.println("no sale yet");

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
