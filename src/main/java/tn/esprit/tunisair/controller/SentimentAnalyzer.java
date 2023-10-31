package tn.esprit.tunisair.controller;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;
import tn.esprit.tunisair.dto.AvisDto;

import java.util.List;

@Component



    public class SentimentAnalyzer {

        private static final String[] positiveWords = {"bien", "forte", "excellent", "merveilleux"};
        private static final String[] negativeWords = {"faible", "mauvaise", "décevant", "nul"};

        public SentimentAnalysisResult analyze(List<AvisDto> avisList) {
            int positiveCount = 0;
            int negativeCount = 0;
            int neutralCount = 0;

            for (AvisDto avis : avisList) {
                String sentiment = analyzeSentiment(avis.getText());
                if (sentiment.equals("positive")) {
                    positiveCount++;
                } else if (sentiment.equals("negative")) {
                    negativeCount++;
                } else {
                    neutralCount++;
                }
            }

            return new SentimentAnalysisResult(positiveCount, negativeCount, neutralCount);
        }

        private String analyzeSentiment(String text) {
            String[] words = text.toLowerCase().split("\\s+");
            int positiveWordCount = 0;
            int negativeWordCount = 0;

            for (String word : words) {
                if (ArrayUtils.contains(positiveWords, word)) {
                    positiveWordCount++;
                } else if (ArrayUtils.contains(negativeWords, word)) {
                    negativeWordCount++;
                }
            }

            if (positiveWordCount > negativeWordCount) {
                return "positive";
            } else if (negativeWordCount > positiveWordCount) {
                return "negative";
            } else {
                return "neutral";
            }
        }
    }


